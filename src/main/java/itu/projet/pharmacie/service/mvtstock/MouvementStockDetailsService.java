package itu.projet.pharmacie.service.mvtstock;

import itu.projet.pharmacie.model.mvtstock.MouvementStockDetails;
import itu.projet.pharmacie.repository.mvtstock.MouvementStockDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MouvementStockDetailsService {

    @Autowired
    private MouvementStockDetailsRepository mouvementStockDetailsRepository;

    // CRUD de base

    public MouvementStockDetails addMouvementStockDetails(MouvementStockDetails mouvementStockDetails) {
        return mouvementStockDetailsRepository.save(mouvementStockDetails);
    }

    public List<MouvementStockDetails> getAllMouvementStockDetails() {
        return mouvementStockDetailsRepository.findAll();
    }

    public Optional<MouvementStockDetails> getMouvementStockDetailsById(Integer id) {
        return mouvementStockDetailsRepository.findById(id);
    }

    public MouvementStockDetails updateMouvementStockDetails(Integer id, MouvementStockDetails updatedMouvementStockDetails) {
        return mouvementStockDetailsRepository.findById(id).map(existingDetails -> {
            existingDetails.setEntree(updatedMouvementStockDetails.getEntree());
            existingDetails.setSortie(updatedMouvementStockDetails.getSortie());
            existingDetails.setProduit(updatedMouvementStockDetails.getProduit());
            existingDetails.setLot(updatedMouvementStockDetails.getLot());
            return mouvementStockDetailsRepository.save(existingDetails);
        }).orElseThrow(() -> new RuntimeException("MouvementStockDetails not found with id: " + id));
    }

    public void deleteMouvementStockDetails(Integer id) {
        if (mouvementStockDetailsRepository.existsById(id)) {
            mouvementStockDetailsRepository.deleteById(id);
        } else {
            throw new RuntimeException("MouvementStockDetails not found with id: " + id);
        }
    }
}
