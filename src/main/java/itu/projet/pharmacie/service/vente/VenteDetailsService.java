package itu.projet.pharmacie.service.vente;

import itu.projet.pharmacie.model.produit.Produit;
import itu.projet.pharmacie.model.vente.VenteDetails;
import itu.projet.pharmacie.repository.vente.VenteDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VenteDetailsService {

    @Autowired
    private VenteDetailsRepository venteDetailsRepository;

    public VenteDetails addVenteDetails(VenteDetails venteDetails) {
        return venteDetailsRepository.save(venteDetails);
    }

    public List<VenteDetails> getAllVenteDetails() {
        return venteDetailsRepository.findAll();
    }

    public Optional<VenteDetails> getVenteDetailsById(Integer id) {
        return venteDetailsRepository.findById(id);
    }

    public VenteDetails updateVenteDetails(Integer id, VenteDetails updatedVenteDetails) {
        return venteDetailsRepository.findById(id).map(existingVenteDetails -> {
            existingVenteDetails.setQuantiteVendue(updatedVenteDetails.getQuantiteVendue());
            existingVenteDetails.setPrixVenteUnitaire(updatedVenteDetails.getPrixVenteUnitaire());
            existingVenteDetails.setLot(updatedVenteDetails.getLot());
            existingVenteDetails.setVente(updatedVenteDetails.getVente());
            return venteDetailsRepository.save(existingVenteDetails);
        }).orElseThrow(() -> new RuntimeException("VenteDetails not found with id: " + id));
    }

    public void deleteVenteDetails(Integer id) {
        if (venteDetailsRepository.existsById(id)) {
            venteDetailsRepository.deleteById(id);
        } else {
            throw new RuntimeException("VenteDetails not found with id: " + id);
        }
    }

    
    public List<VenteDetails> getVenteDetailsByProduits(List<Produit> produits) {
        if (produits == null || produits.isEmpty()) {
            return new ArrayList<>();
        }
        return venteDetailsRepository.findByLot_ProduitIn(produits);
    }
}
