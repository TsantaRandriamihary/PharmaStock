package itu.projet.pharmacie.model.maladiesymptome;

import jakarta.persistence.*;

@Entity
public class MaladieSymptome {

    @EmbeddedId
    private MaladieSymptomeId id;

    private Double degre;

    // Attributs idMaladie et idSymptome
    @Column(name = "idMaladie", insertable = false, updatable = false)
    private Integer idMaladie;

    @Column(name = "idSymptome", insertable = false, updatable = false)
    private Integer idSymptome;

    // Getters and Setters
    public MaladieSymptomeId getId() {
        return id;
    }

    public void setId(MaladieSymptomeId id) {
        this.id = id;
        this.idMaladie = id.getIdMaladie();
        this.idSymptome = id.getIdSymptome();
    }

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

    public Double getDegre() {
        return degre;
    }

    public void setDegre(Double degre) {
        this.degre = degre;
    }
}
