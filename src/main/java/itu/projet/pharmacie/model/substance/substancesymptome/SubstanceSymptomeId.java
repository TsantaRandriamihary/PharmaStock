package itu.projet.pharmacie.model.substance.substancesymptome;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class SubstanceSymptomeId implements Serializable {

    private Integer idSubstance;
    private Integer idSymptome;

    // Constructeurs
    public SubstanceSymptomeId() {}

    public SubstanceSymptomeId(Integer idSubstance, Integer idSymptome) {
        this.idSubstance = idSubstance;
        this.idSymptome = idSymptome;
    }

    // Getters et Setters
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

    // hashCode et equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubstanceSymptomeId that = (SubstanceSymptomeId) o;
        return idSubstance.equals(that.idSubstance) &&
               idSymptome.equals(that.idSymptome);
    }

    @Override
    public int hashCode() {
        return 31 * idSubstance.hashCode() + idSymptome.hashCode();
    }
}
