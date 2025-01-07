package itu.projet.pharmacie.model.agent;

import jakarta.persistence.*;
import itu.projet.pharmacie.model.type.Typeagent;

@Entity
public class Agentpathogene {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAgentpathogene;

    private String nomAgentpathogene;

    @ManyToOne
    @JoinColumn(name = "id_typeagent", nullable = false)
    private Typeagent typeAgent;

    
    
    public Integer getIdAgentpathogene() {
        return idAgentpathogene;
    }

    public void setIdAgentpathogene(Integer idAgentpathogene) {
        this.idAgentpathogene = idAgentpathogene;
    }

    public String getNomAgentpathogene() {
        return nomAgentpathogene;
    }

    public void setNomAgentpathogene(String nomAgentpathogene) {
        this.nomAgentpathogene = nomAgentpathogene;
    }

    public Typeagent getTypeAgent() {
        return typeAgent;
    }

    public void setTypeAgent(Typeagent typeAgent) {
        this.typeAgent = typeAgent;
    }

    
}
