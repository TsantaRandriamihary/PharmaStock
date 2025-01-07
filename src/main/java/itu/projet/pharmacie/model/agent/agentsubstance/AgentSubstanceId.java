package itu.projet.pharmacie.model.agent.agentsubstance;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class AgentSubstanceId implements Serializable {

    private Integer idSubstance;
    private Integer idAgentpathogene;

    // Constructeur
    public AgentSubstanceId() {}

    public AgentSubstanceId(Integer idSubstance, Integer idAgentpathogene) {
        this.idSubstance = idSubstance;
        this.idAgentpathogene = idAgentpathogene;
    }

    // Getters et Setters
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

    // hashCode et equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgentSubstanceId that = (AgentSubstanceId) o;
        return idSubstance.equals(that.idSubstance) &&
               idAgentpathogene.equals(that.idAgentpathogene);
    }

    @Override
    public int hashCode() {
        return 31 * idSubstance.hashCode() + idAgentpathogene.hashCode();
    }
}
