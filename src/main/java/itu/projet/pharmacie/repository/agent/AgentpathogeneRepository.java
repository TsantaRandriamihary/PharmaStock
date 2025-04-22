package itu.projet.pharmacie.repository.agent;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.agent.Agentpathogene;

@Repository
public interface AgentpathogeneRepository extends JpaRepository<Agentpathogene, Integer> {
}

