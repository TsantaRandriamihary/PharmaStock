package itu.projet.pharmacie.service.vente;

import itu.projet.pharmacie.model.mvtstock.MouvementStockDetails;
import itu.projet.pharmacie.model.vente.Vente;
import itu.projet.pharmacie.model.vente.VenteDetails;
import itu.projet.pharmacie.repository.mvtstock.MouvementStockDetailsRepository;
import itu.projet.pharmacie.repository.mvtstock.MouvementStockRepository;
import itu.projet.pharmacie.repository.vente.VenteDetailsRepository;
import itu.projet.pharmacie.repository.vente.VenteRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class VenteService {

    @Autowired
    private VenteRepository venteRepository;

    @Autowired
    private VenteDetailsRepository venteDetailsRepository;

    @Autowired
    private MouvementStockDetailsRepository mouvementStockDetailsRepository;

    public Vente createVenteWithDetails(Vente vente, List<VenteDetails> detailsList) {
        for (VenteDetails details : detailsList) {
            if (!isStockSufficient(details.getLot().getIdLot(), details.getQuantiteVendue())) {
                throw new RuntimeException("Stock insuffisant pour le lot ID : " + details.getLot().getIdLot());
            }
        }

        Vente savedVente = venteRepository.save(vente);

        for (VenteDetails details : detailsList) {
            details.setVente(savedVente);
            venteDetailsRepository.save(details);
        }

        return savedVente;
    }

    @Transactional
    public Vente updateVenteWithDetails(Integer idVente, Vente updatedVente, List<VenteDetails> updatedDetailsList) {
        return venteRepository.findById(idVente).map(existingVente -> {
            existingVente.setDateVente(updatedVente.getDateVente());
            existingVente.setEtatVente(updatedVente.getEtatVente());
            existingVente.setMontantTotal(updatedVente.getMontantTotal());
            existingVente.setDescriptionVente(updatedVente.getDescriptionVente());
            existingVente.setClient(updatedVente.getClient());
            Vente savedVente = venteRepository.save(existingVente);

            venteDetailsRepository.deleteAll(venteDetailsRepository.findByVente(savedVente));

            for (VenteDetails details : updatedDetailsList) {
                if (!isStockSufficient(details.getLot().getIdLot(), details.getQuantiteVendue())) {
                    throw new RuntimeException("Stock insuffisant pour le lot ID : " + details.getLot().getIdLot());
                }
                details.setVente(savedVente);
                venteDetailsRepository.save(details);
            }

            return savedVente;
        }).orElseThrow(() -> new RuntimeException("Vente not found with id: " + idVente));
    }

    
    private boolean isStockSufficient(Integer lotId, Integer quantite) {
        List<MouvementStockDetails> mouvements = mouvementStockDetailsRepository.findAll();

        int stockDisponible = mouvements.stream()
                .filter(mouvement -> mouvement.getLot().getIdLot().equals(lotId))
                .mapToInt(mouvement -> mouvement.getEntree() - mouvement.getSortie())
                .sum();

        return stockDisponible >= quantite;
    }

    @Transactional
    public void deleteVenteWithDetails(Integer idVente) {
        venteRepository.findById(idVente).ifPresent(existingVente -> {
            List<VenteDetails> detailsList = venteDetailsRepository.findByVente(existingVente);
            venteDetailsRepository.deleteAll(detailsList);

            venteRepository.delete(existingVente);
        });
    }

    public Optional<Vente> getVenteById(Integer id) {
        return venteRepository.findById(id);
    }

    public List<Vente> getAllVentes() {
        return venteRepository.findAll();
    }

    public void deleteVente(Integer id) {
        if (venteRepository.existsById(id)) {
            venteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Vente not found with id: " + id);
        }
    }

    public List<VenteDetails> getVenteDetails(Integer idVente) {
        return venteRepository.findById(idVente)
                .map(venteDetailsRepository::findByVente)
                .orElseThrow(() -> new RuntimeException("Vente not found with id: " + idVente));
    }
}

