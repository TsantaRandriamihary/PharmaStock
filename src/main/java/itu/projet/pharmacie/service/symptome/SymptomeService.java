package itu.projet.pharmacie.service.symptome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itu.projet.pharmacie.model.substance.substancesymptome.SubstanceSymptome;
import itu.projet.pharmacie.model.symptome.Symptome;
import itu.projet.pharmacie.repository.substance.substancesymptome.SubstanceSymptomeRepository;
import itu.projet.pharmacie.repository.symptome.SymptomeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SymptomeService {

    @Autowired
    private SymptomeRepository symptomeRepository;

    @Autowired
    private SubstanceSymptomeRepository substanceSymptomeRepository;


    public Symptome createSymptome(Symptome symptome) {
        return symptomeRepository.save(symptome);
    }

    public Optional<Symptome> getSymptomeById(Integer id) {
        return symptomeRepository.findById(id);
    }

    public List<Symptome> getAllSymptomes() {
        return symptomeRepository.findAll();
    }

    public Symptome updateSymptome(Integer id, Symptome updatedSymptome) {
        return symptomeRepository.findById(id).map(existingSymptome -> {
            existingSymptome.setNomSymptome(updatedSymptome.getNomSymptome());
            existingSymptome.setDescriptionSymptome(updatedSymptome.getDescriptionSymptome());
            return symptomeRepository.save(existingSymptome);
        }).orElseThrow(() -> new RuntimeException("Symptome not found with id: " + id));
    }

    public void deleteSymptome(Integer id) {
        if (symptomeRepository.existsById(id)) {
            symptomeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Symptome not found with id: " + id);
        }
    }

    public List<SubstanceSymptome> getSubstanceSymptomes(Integer idSymptome) {
        try {
            return substanceSymptomeRepository.findByIdSymptome(idSymptome);
        } catch (Exception e) {
            throw new RuntimeException("Symptome not found with id: " + idSymptome);
        }        
    }
}
