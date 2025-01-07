package itu.projet.pharmacie.model.type;


import jakarta.persistence.*;

@Entity
public class Typeproduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTypeproduit;

    private String nomTypeproduit;

    // Getters and Setters
    public Integer getIdTypeproduit() {
        return idTypeproduit;
    }

    public void setIdTypeproduit(Integer idTypeproduit) {
        this.idTypeproduit = idTypeproduit;
    }

    public String getNomTypeproduit() {
        return nomTypeproduit;
    }

    public void setNomTypeproduit(String nomTypeproduit) {
        this.nomTypeproduit = nomTypeproduit;
    }
}

