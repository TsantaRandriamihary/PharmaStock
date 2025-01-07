package itu.projet.pharmacie.model.domaine;


import jakarta.persistence.*;

@Entity
public class Domaine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDomaine;

    private String nomDomaine;

    // Getters and Setters
    public Integer getIdDomaine() {
        return idDomaine;
    }

    public void setIdDomaine(Integer idDomaine) {
        this.idDomaine = idDomaine;
    }

    public String getNomDomaine() {
        return nomDomaine;
    }

    public void setNomDomaine(String nomDomaine) {
        this.nomDomaine = nomDomaine;
    }
}
