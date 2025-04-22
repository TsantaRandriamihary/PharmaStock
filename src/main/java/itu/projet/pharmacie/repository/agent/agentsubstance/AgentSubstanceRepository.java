package itu.projet.pharmacie.repository.agent.agentsubstance;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.agent.agentsubstance.AgentSubstance;
import itu.projet.pharmacie.model.agent.agentsubstance.AgentSubstanceId;


@Repository
public interface AgentSubstanceRepository extends JpaRepository<AgentSubstance, AgentSubstanceId> {
    List<AgentSubstance> findByIdSubstance(Integer idSubstance);
}

