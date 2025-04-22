package itu.projet.pharmacie.service.symptome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import itu.projet.pharmacie.model.substance.substancesymptome.SubstanceSymptome;
import itu.projet.pharmacie.model.substance.substancesymptome.SubstanceSymptomeId;
import itu.projet.pharmacie.repository.substance.substancesymptome.SubstanceSymptomeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubstanceSymptomeService {

    @Autowired
    private SubstanceSymptomeRepository substanceSymptomeRepository;

    public SubstanceSymptome createSubstanceSymptome(SubstanceSymptome substanceSymptome) {
        return substanceSymptomeRepository.save(substanceSymptome);
    }

    public Optional<SubstanceSymptome> getSubstanceSymptomeById(SubstanceSymptomeId id) {
        return substanceSymptomeRepository.findById(id);
    }

    public List<SubstanceSymptome> getAllSubstanceSymptomes() {
        return substanceSymptomeRepository.findAll();
    }

    public SubstanceSymptome updateSubstanceSymptome(SubstanceSymptomeId id, SubstanceSymptome updatedSubstanceSymptome) {
        return substanceSymptomeRepository.findById(id).map(existingSubstanceSymptome -> {
            existingSubstanceSymptome.setId(updatedSubstanceSymptome.getId());
            return substanceSymptomeRepository.save(existingSubstanceSymptome);
        }).orElseThrow(() -> new RuntimeException("SubstanceSymptome not found with id: " + id));
    }

    public void deleteSubstanceSymptome(SubstanceSymptomeId id) {
        if (substanceSymptomeRepository.existsById(id)) {
            substanceSymptomeRepository.deleteById(id);
        } else {
            throw new RuntimeException("SubstanceSymptome not found with id: " + id);
        }
    }

    
}
