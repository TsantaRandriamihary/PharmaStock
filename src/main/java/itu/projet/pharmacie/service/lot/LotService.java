package itu.projet.pharmacie.service.lot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import itu.projet.pharmacie.model.lot.Lot;
import itu.projet.pharmacie.repository.lot.LotRepository;
import itu.projet.pharmacie.repository.mvtstock.MouvementStockDetailsRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LotService {

    @Autowired
    private LotRepository lotRepository;

    @Autowired
    private MouvementStockDetailsRepository mouvementStockDetailsRepository;

    public Lot createLot(Lot lot) {
        return lotRepository.save(lot);
    }

    public Optional<Lot> getLotById(Integer id) {
        return lotRepository.findById(id);
    }

    public List<Lot> getAllLots() {
        return lotRepository.findAll();
    }

    public Lot updateLot(Integer id, Lot updatedLot) {
        return lotRepository.findById(id).map(existingLot -> {
            existingLot.setNomLot(updatedLot.getNomLot());
            existingLot.setDateFabrication(updatedLot.getDateFabrication());
            existingLot.setDatePeremption(updatedLot.getDatePeremption());
            existingLot.setQuantiteLot(updatedLot.getQuantiteLot());
            existingLot.setProduit(updatedLot.getProduit());
            return lotRepository.save(existingLot);
        }).orElseThrow(() -> new RuntimeException("Lot not found with id: " + id));
    }

    public void deleteLot(Integer id) {
        if (lotRepository.existsById(id)) {
            lotRepository.deleteById(id);
        } else {
            throw new RuntimeException("Lot not found with id: " + id);
        }
    }

    
}
