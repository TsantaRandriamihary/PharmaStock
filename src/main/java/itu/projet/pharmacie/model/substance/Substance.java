package itu.projet.pharmacie.model.substance;

import jakarta.persistence.*;
import java.util.List;

import itu.projet.pharmacie.model.symptome.Symptome;
import itu.projet.pharmacie.model.type.Typesubstance;

@Entity
public class Substance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSubstance;

    private String nomSubstance;
    private String descriptionSubstance;

    @ManyToOne
    @JoinColumn(name = "id_typesubstance", nullable = false)
    private Typesubstance typeSubstance;

    
    
    public Integer getIdSubstance() {
        return idSubstance;
    }
 
    public void setIdSubstance(Integer idSubstance) {
        this.idSubstance = idSubstance;
    }

    public String getNomSubstance() {
        return nomSubstance;
    }

    public void setNomSubstance(String nomSubstance) {
        this.nomSubstance = nomSubstance;
    }

    public String getDescriptionSubstance() {
        return descriptionSubstance;
    }

    public void setDescriptionSubstance(String descriptionSubstance) {
        this.descriptionSubstance = descriptionSubstance;
    }

    public Typesubstance getTypeSubstance() {
        return typeSubstance;
    }

    public void setTypeSubstance(Typesubstance typeSubstance) {
        this.typeSubstance = typeSubstance;
    }

    
    
}
