package itu.projet.pharmacie.model.maladie;

import jakarta.persistence.*;

import itu.projet.pharmacie.model.agent.Agentpathogene;
import itu.projet.pharmacie.model.domaine.Domaine;

@Entity
public class Maladie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMaladie;

    private String nomMaladie;

    private String codeCim;

    @ManyToOne
    @JoinColumn(name = "idAgentpathogene")
    private Agentpathogene agentpathogene;

    @ManyToOne
    @JoinColumn(name = "idDomaine")
    private Domaine domaine;

    
    // Getters and Setters
    public Integer getIdMaladie() {
        return idMaladie;
    }

    public void setIdMaladie(Integer idMaladie) {
        this.idMaladie = idMaladie;
    }

    public String getNomMaladie() {
        return nomMaladie;
    }

    public void setNomMaladie(String nomMaladie) {
        this.nomMaladie = nomMaladie;
    }

    public String getCodeCim() {
        return codeCim;
    }

    public void setCodeCim(String codeCim) {
        this.codeCim = codeCim;
    }

    public Agentpathogene getAgentpathogene() {
        return agentpathogene;
    }

    public void setAgentpathogene(Agentpathogene agentpathogene) {
        this.agentpathogene = agentpathogene;
    }

    public Domaine getDomaine() {
        return domaine;
    }

    public void setDomaine(Domaine domaine) {
        this.domaine = domaine;
    }

    
}
