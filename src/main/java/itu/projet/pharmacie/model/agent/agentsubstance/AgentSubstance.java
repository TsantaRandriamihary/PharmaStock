package itu.projet.pharmacie.model.agent.agentsubstance;

import jakarta.persistence.*;

@Entity
public class AgentSubstance {

    @EmbeddedId
    private AgentSubstanceId id;

    @Column(name = "idSubstance", insertable = false, updatable = false)
    private Integer idSubstance;

    @Column(name = "idAgentpathogene", insertable = false, updatable = false)
    private Integer idAgentpathogene;

    // Getters et Setters
    public AgentSubstanceId getId() {
        return id;
    }

    public void setId(AgentSubstanceId id) {
        this.id = id;
        this.idSubstance = id.getIdSubstance();
        this.idAgentpathogene = id.getIdAgentpathogene();
    }

    public Integer getIdSubstance() {
        return idSubstance;
    }

    public void setIdSubstance(Integer idSubstance) {
        this.idSubstance = idSubstance;
    }

    public Integer getIdAgentpathogene() {
        return idAgentpathogene;
    }

    public void setIdAgentpathogene(Integer idAgentpathogene) {
        this.idAgentpathogene = idAgentpathogene;
    }
}
