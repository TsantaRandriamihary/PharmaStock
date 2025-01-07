package itu.projet.pharmacie.service.substance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import itu.projet.pharmacie.model.agent.agentsubstance.AgentSubstance;
import itu.projet.pharmacie.model.agent.agentsubstance.AgentSubstanceId;
import itu.projet.pharmacie.repository.agent.agentsubstance.AgentSubstanceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AgentSubstanceService {

    @Autowired
    private AgentSubstanceRepository agentSubstanceRepository;

    public AgentSubstance createAgentSubstance(AgentSubstance agentSubstance) {
        return agentSubstanceRepository.save(agentSubstance);
    }

    public Optional<AgentSubstance> getAgentSubstanceById(AgentSubstanceId id) {
        return agentSubstanceRepository.findById(id);
    }

    public List<AgentSubstance> getAllAgentSubstances() {
        return agentSubstanceRepository.findAll();
    }

    public AgentSubstance updateAgentSubstance(AgentSubstanceId id, AgentSubstance updatedAgentSubstance) {
        return agentSubstanceRepository.findById(id).map(existingAgentSubstance -> {
            existingAgentSubstance.setId(updatedAgentSubstance.getId());
            return agentSubstanceRepository.save(existingAgentSubstance);
        }).orElseThrow(() -> new RuntimeException("AgentSubstance not found with id: " + id));
    }

    public void deleteAgentSubstance(AgentSubstanceId id) {
        if (agentSubstanceRepository.existsById(id)) {
            agentSubstanceRepository.deleteById(id);
        } else {
            throw new RuntimeException("AgentSubstance not found with id: " + id);
        }
    }
}
