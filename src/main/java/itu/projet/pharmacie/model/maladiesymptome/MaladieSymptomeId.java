package itu.projet.pharmacie.model.maladiesymptome;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MaladieSymptomeId implements Serializable {

    private Integer idMaladie;
    private Integer idSymptome;

    public MaladieSymptomeId() {}

    public MaladieSymptomeId(Integer idMaladie, Integer idSymptome) {
        this.idMaladie = idMaladie;
        this.idSymptome = idSymptome;
    }

    // Getters and Setters
    public Integer getIdMaladie() {
        return idMaladie;
    }

    public void setIdMaladie(Integer idMaladie) {
        this.idMaladie = idMaladie;
    }

    public Integer getIdSymptome() {
        return idSymptome;
    }

    public void setIdSymptome(Integer idSymptome) {
        this.idSymptome = idSymptome;
    }

    // hashCode and equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaladieSymptomeId that = (MaladieSymptomeId) o;
        return Objects.equals(idMaladie, that.idMaladie) && Objects.equals(idSymptome, that.idSymptome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMaladie, idSymptome);
    }
}
