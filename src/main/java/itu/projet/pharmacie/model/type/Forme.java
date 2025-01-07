package itu.projet.pharmacie.model.type;


import jakarta.persistence.*;

@Entity
public class Forme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idForme;

    private String nomForme;

    // Getters and Setters
    public Integer getIdForme() {
        return idForme;
    }

    public void setIdForme(Integer idForme) {
        this.idForme = idForme;
    }

    public String getNomForme() {
        return nomForme;
    }

    public void setNomForme(String nomForme) {
        this.nomForme = nomForme;
    }
}
