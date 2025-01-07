package itu.projet.pharmacie.service.substance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itu.projet.pharmacie.model.agent.agentsubstance.AgentSubstance;
import itu.projet.pharmacie.model.substance.Substance;
import itu.projet.pharmacie.repository.agent.agentsubstance.AgentSubstanceRepository;
import itu.projet.pharmacie.repository.substance.SubstanceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubstanceService {

    @Autowired
    private SubstanceRepository substanceRepository;

    @Autowired
    private AgentSubstanceRepository agentSubstanceRepository;


    public Substance createSubstance(Substance substance) {
        return substanceRepository.save(substance);
    }

    public Optional<Substance> getSubstanceById(Integer id) {
        return substanceRepository.findById(id);
    }

    public List<Substance> getAllSubstances() {
        return substanceRepository.findAll();
    }

    public Substance updateSubstance(Integer id, Substance updatedSubstance) {
        return substanceRepository.findById(id).map(existingSubstance -> {
            existingSubstance.setNomSubstance(updatedSubstance.getNomSubstance());
            existingSubstance.setDescriptionSubstance(updatedSubstance.getDescriptionSubstance());
            existingSubstance.setTypeSubstance(updatedSubstance.getTypeSubstance());
            return substanceRepository.save(existingSubstance);
        }).orElseThrow(() -> new RuntimeException("Substance not found with id: " + id));
    }

    public void deleteSubstance(Integer id) {
        if (substanceRepository.existsById(id)) {
            substanceRepository.deleteById(id);
        } else {
            throw new RuntimeException("Substance not found with id: " + id);
        }
    }

    public List<AgentSubstance> getAgentSubstances(Integer idSubstance) {
        try {
            return agentSubstanceRepository.findByIdSubstance(idSubstance);
        } catch (Exception e) {
            throw new RuntimeException("Substance not found with id: " + idSubstance);
        }        
    }
}
