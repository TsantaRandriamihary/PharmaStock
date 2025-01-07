package itu.projet.pharmacie.service.achat;

import itu.projet.pharmacie.model.achat.Achat;
import itu.projet.pharmacie.model.achat.AchatDetails;
import itu.projet.pharmacie.repository.achat.AchatDetailsRepository;
import itu.projet.pharmacie.repository.achat.AchatRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AchatService {

    @Autowired
    private AchatRepository achatRepository;

    @Autowired
    private AchatDetailsRepository achatDetailsRepository;

    public Achat createAchatWithDetails(Achat achat, List<AchatDetails> detailsList) {
        Achat savedAchat = achatRepository.save(achat);

        for (AchatDetails details : detailsList) {
            details.setAchat(savedAchat);
            achatDetailsRepository.save(details);
        }

        return savedAchat;
    }

    @Transactional
    public Achat updateAchatWithDetails(Integer idAchat, Achat updatedAchat, List<AchatDetails> updatedDetailsList) {
        return achatRepository.findById(idAchat).map(existingAchat -> {
            existingAchat.setDescriptionAchat(updatedAchat.getDescriptionAchat());
            existingAchat.setDateAchat(updatedAchat.getDateAchat());
            existingAchat.setEtatAchat(updatedAchat.getEtatAchat());
            existingAchat.setMontantTotal(updatedAchat.getMontantTotal());
            existingAchat.setLaboratoire(updatedAchat.getLaboratoire());
            existingAchat.setFournisseur(updatedAchat.getFournisseur());
            Achat savedAchat = achatRepository.save(existingAchat);

            achatDetailsRepository.deleteAll(achatDetailsRepository.findByAchat(savedAchat));

            for (AchatDetails details : updatedDetailsList) {
                details.setAchat(savedAchat);
                achatDetailsRepository.save(details);
            }

            return savedAchat;
        }).orElseThrow(() -> new RuntimeException("Achat not found with id: " + idAchat));
    }

    @Transactional
    public void deleteAchatWithDetails(Integer idAchat) {
        achatRepository.findById(idAchat).ifPresent(existingAchat -> {
            List<AchatDetails> detailsList = achatDetailsRepository.findByAchat(existingAchat);
            achatDetailsRepository.deleteAll(detailsList);

            achatRepository.delete(existingAchat);
        });
    }

    public Optional<Achat> getAchatById(Integer id) {
        return achatRepository.findById(id);
    }

    public List<Achat> getAllAchats() {
        return achatRepository.findAll();
    }

    public void deleteAchat(Integer id) {
        if (achatRepository.existsById(id)) {
            achatRepository.deleteById(id);
        } else {
            throw new RuntimeException("Achat not found with id: " + id);
        }
    }

    public List<AchatDetails> getAchatDetails(Integer idAchat) {
        return achatRepository.findById(idAchat)
                .map(achatDetailsRepository::findByAchat)
                .orElseThrow(() -> new RuntimeException("Achat not found with id: " + idAchat));
    }
}
