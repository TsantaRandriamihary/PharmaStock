package itu.projet.pharmacie.model.symptome;

import jakarta.persistence.*;
import java.util.List;

import itu.projet.pharmacie.model.substance.Substance;

@Entity
public class Symptome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSymptome;

    private String nomSymptome;
    private String descriptionSymptome;

    

    
    public Integer getIdSymptome() {
        return idSymptome;
    }

    public void setIdSymptome(Integer idSymptome) {
        this.idSymptome = idSymptome;
    }

    public String getNomSymptome() {
        return nomSymptome;
    }

    public void setNomSymptome(String nomSymptome) {
        this.nomSymptome = nomSymptome;
    }

    public String getDescriptionSymptome() {
        return descriptionSymptome;
    }

    public void setDescriptionSymptome(String descriptionSymptome) {
        this.descriptionSymptome = descriptionSymptome;
    }

    

    
}
