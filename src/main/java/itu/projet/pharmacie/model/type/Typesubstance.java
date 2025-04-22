package itu.projet.pharmacie.model.type;

import jakarta.persistence.*;

@Entity
public class Typesubstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTypesubstance;

    private String nomTypesubstance;

    
    // Getters and Setters
    public Integer getIdTypesubstance() {
        return idTypesubstance;
    }

    public void setIdTypesubstance(Integer idTypesubstance) {
        this.idTypesubstance = idTypesubstance;
    }

    public String getNomTypesubstance() {
        return nomTypesubstance;
    }

    public void setNomTypesubstance(String nomTypesubstance) {
        this.nomTypesubstance = nomTypesubstance;
    }

    
}
