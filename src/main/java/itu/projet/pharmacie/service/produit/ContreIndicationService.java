package itu.projet.pharmacie.service.produit;

import itu.projet.pharmacie.model.produit.contreindication.ContreIndication;
import itu.projet.pharmacie.model.produit.contreindication.ContreIndicationId;
import itu.projet.pharmacie.repository.produit.contreindication.ContreIndicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContreIndicationService {

    @Autowired
    private ContreIndicationRepository contreIndicationRepository;

    // Create
    public ContreIndication createContreIndication(ContreIndication contreIndication) {
        return contreIndicationRepository.save(contreIndication);
    }

    // Read
    public Optional<ContreIndication> getContreIndicationById(ContreIndicationId id) {
        return contreIndicationRepository.findById(id);
    }

    public List<ContreIndication> getAllContreIndications() {
        return contreIndicationRepository.findAll();
    }

    // Update
    public ContreIndication updateContreIndication(ContreIndication contreIndication) {
        if (contreIndicationRepository.existsById(contreIndication.getId())) {
            return contreIndicationRepository.save(contreIndication);
        }
        return null;
    }

    // Delete
    public void deleteContreIndication(ContreIndicationId id) {
        if (contreIndicationRepository.existsById(id)) {
            contreIndicationRepository.deleteById(id);
        }
    }
}
