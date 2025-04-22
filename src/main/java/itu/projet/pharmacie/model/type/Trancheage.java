package itu.projet.pharmacie.model.type;


import jakarta.persistence.*;

@Entity
public class Trancheage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTrancheage;

    private Integer ageMin;

    private Integer ageMax;

    private String nomTranche;

    // Getters and Setters
    public Integer getIdTrancheage() {
        return idTrancheage;
    }

    public void setIdTrancheage(Integer idTrancheage) {
        this.idTrancheage = idTrancheage;
    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public Integer getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }

    public String getNomTranche() {
        return nomTranche;
    }

    public void setNomTranche(String nomTranche) {
        this.nomTranche = nomTranche;
    }
}
