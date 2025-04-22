package itu.projet.pharmacie.service.mvtstock;

import itu.projet.pharmacie.model.mvtstock.MouvementStock;
import itu.projet.pharmacie.model.mvtstock.MouvementStockDetails;
import itu.projet.pharmacie.repository.mvtstock.MouvementStockRepository;
import jakarta.transaction.Transactional;
import itu.projet.pharmacie.repository.mvtstock.MouvementStockDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MouvementStockService {

    @Autowired
    private MouvementStockRepository mouvementStockRepository;

    @Autowired
    private MouvementStockDetailsRepository mouvementStockDetailsRepository;

    // Méthode pour vérifier si le stock est disponible pour un lot spécifique.
    private boolean isStockAvailable(Integer lotId, Integer quantityToCheck) {
        // Calculer le stock disponible (entrée - sortie) pour le lot
        List<MouvementStockDetails> mouvements = mouvementStockDetailsRepository.findAll();
        int stockDisponible = mouvements.stream()
                .filter(mouvement -> mouvement.getLot().getIdLot().equals(lotId))  // Filtrer par lot
                .mapToInt(mouvement -> mouvement.getEntree() - mouvement.getSortie()) // Calculer la différence entre entrée et sortie
                .sum();

        // Retourner true si le stock est suffisant
        return stockDisponible >= quantityToCheck;
    }

    public MouvementStock addMouvementStock(MouvementStock mouvementStock) {
        return mouvementStockRepository.save(mouvementStock);
    }

    public List<MouvementStock> getAllMouvementStock() {
        return mouvementStockRepository.findAll();
    }

    public Optional<MouvementStock> getMouvementStockById(Integer id) {
        return mouvementStockRepository.findById(id);
    }

    public MouvementStock updateMouvementStock(Integer id, MouvementStock updatedMouvementStock) {
        return mouvementStockRepository.findById(id).map(existingMouvementStock -> {
            existingMouvementStock.setDateMouvement(updatedMouvementStock.getDateMouvement());
            existingMouvementStock.setEstAchat(updatedMouvementStock.getEstAchat());
            existingMouvementStock.setReference(updatedMouvementStock.getReference());
            return mouvementStockRepository.save(existingMouvementStock);
        }).orElseThrow(() -> new RuntimeException("MouvementStock not found with id: " + id));
    }

    public void deleteMouvementStock(Integer id) {
        if (mouvementStockRepository.existsById(id)) {
            mouvementStockRepository.deleteById(id);
        } else {
            throw new RuntimeException("MouvementStock not found with id: " + id);
        }
    }

    public MouvementStock addMouvementStockWithDetails(MouvementStock mouvementStock, List<MouvementStockDetails> details) {
        // Vérification de stock pour chaque détail
        for (MouvementStockDetails detail : details) {
            if (!isStockAvailable(detail.getLot().getIdLot(), detail.getEntree())) {
                throw new RuntimeException("Stock insuffisant pour le lot ID : " + detail.getLot().getIdLot());
            }
        }

        // Sauvegarde du mouvement de stock
        MouvementStock savedMouvementStock = mouvementStockRepository.save(mouvementStock);

        // Sauvegarde des détails associés
        for (MouvementStockDetails detail : details) {
            detail.setMouvementStock(savedMouvementStock);
            mouvementStockDetailsRepository.save(detail);
        }

        return savedMouvementStock;
    }

    @Transactional
    public MouvementStock updateMouvementStockWithDetails(Integer id, MouvementStock updatedMouvementStock, List<MouvementStockDetails> updatedDetails) {
        return mouvementStockRepository.findById(id).map(existingMouvementStock -> {
            existingMouvementStock.setDateMouvement(updatedMouvementStock.getDateMouvement());
            existingMouvementStock.setEstAchat(updatedMouvementStock.getEstAchat());
            existingMouvementStock.setReference(updatedMouvementStock.getReference());

            MouvementStock savedMouvementStock = mouvementStockRepository.save(existingMouvementStock);

            // Supprimer les anciens détails
            List<MouvementStockDetails> oldDetails = mouvementStockDetailsRepository.findAll();
            oldDetails.stream()
                      .filter(detail -> detail.getMouvementStock().getIdMouvement().equals(id))
                      .forEach(mouvementStockDetailsRepository::delete);

            // Vérification de stock pour les nouveaux détails
            for (MouvementStockDetails detail : updatedDetails) {
                if (!isStockAvailable(detail.getLot().getIdLot(), detail.getEntree())) {
                    throw new RuntimeException("Stock insuffisant pour le lot ID : " + detail.getLot().getIdLot());
                }
                detail.setMouvementStock(savedMouvementStock);
                mouvementStockDetailsRepository.save(detail);
            }

            return savedMouvementStock;
        }).orElseThrow(() -> new RuntimeException("MouvementStock not found with id: " + id));
    }

    @Transactional
    public void deleteMouvementStockWithDetails(Integer id) {
        if (mouvementStockRepository.existsById(id)) {
            List<MouvementStockDetails> details = mouvementStockDetailsRepository.findAll();
            details.stream()
                   .filter(detail -> detail.getMouvementStock().getIdMouvement().equals(id))
                   .forEach(mouvementStockDetailsRepository::delete);
            mouvementStockRepository.deleteById(id);
        } else {
            throw new RuntimeException("MouvementStock not found with id: " + id);
        }
    }

    public List<MouvementStockDetails> getMouvementStockDetails(Integer idMouvement) {
        return mouvementStockRepository.findById(idMouvement)
                .map(mouvementStockDetailsRepository::findByMouvementStock)
                .orElseThrow(() -> new RuntimeException("MouvementStock not found with id: " + idMouvement));
    }
}
