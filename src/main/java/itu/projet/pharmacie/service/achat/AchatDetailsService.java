package itu.projet.pharmacie.service.achat;

import itu.projet.pharmacie.model.achat.AchatDetails;
import itu.projet.pharmacie.repository.achat.AchatDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AchatDetailsService {

    @Autowired
    private AchatDetailsRepository achatDetailsRepository;

    public AchatDetails createAchatDetails(AchatDetails achatDetails) {
        return achatDetailsRepository.save(achatDetails);
    }

    public Optional<AchatDetails> getAchatDetailsById(Integer id) {
        return achatDetailsRepository.findById(id);
    }

    public List<AchatDetails> getAllAchatDetails() {
        return achatDetailsRepository.findAll();
    }

    public AchatDetails updateAchatDetails(Integer id, AchatDetails updatedDetails) {
        return achatDetailsRepository.findById(id).map(existingDetails -> {
            existingDetails.setPrixAchatUnitaire(updatedDetails.getPrixAchatUnitaire());
            existingDetails.setPrixAchatTotal(updatedDetails.getPrixAchatTotal());
            existingDetails.setLot(updatedDetails.getLot());
            existingDetails.setAchat(updatedDetails.getAchat());
            return achatDetailsRepository.save(existingDetails);
        }).orElseThrow(() -> new RuntimeException("AchatDetails not found with id: " + id));
    }

    public void deleteAchatDetails(Integer id) {
        if (achatDetailsRepository.existsById(id)) {
            achatDetailsRepository.deleteById(id);
        } else {
            throw new RuntimeException("AchatDetails not found with id: " + id);
        }
    }
}
