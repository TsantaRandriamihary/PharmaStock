package itu.projet.pharmacie.model.substance.substancesymptome;

import jakarta.persistence.*;

@Entity
public class SubstanceSymptome {

    @EmbeddedId
    private SubstanceSymptomeId id;

    // Attributs idSubstance et idSymptome
    @Column(name = "idSubstance", insertable = false, updatable = false)
    private Integer idSubstance;

    @Column(name = "idSymptome", insertable = false, updatable = false)
    private Integer idSymptome;

    // Getters et Setters
    public SubstanceSymptomeId getId() {
        return id;
    }

    public void setId(SubstanceSymptomeId id) {
        this.id = id;
        this.idSubstance = id.getIdSubstance();
        this.idSymptome = id.getIdSymptome();
    }

    public Integer getIdSubstance() {
        return idSubstance;
    }

    public void setIdSubstance(Integer idSubstance) {
        this.idSubstance = idSubstance;
    }

    public Integer getIdSymptome() {
        return idSymptome;
    }

    public void setIdSymptome(Integer idSymptome) {
        this.idSymptome = idSymptome;
    }
}
