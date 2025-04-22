package itu.projet.pharmacie.model.produit.symptomeproduit;


import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProduitSymptomeId implements Serializable {

    private Integer idProduit;
    private Integer idSymptome;

    // Constructeurs
    public ProduitSymptomeId() {}

    public ProduitSymptomeId(Integer idProduit, Integer idSymptome) {
        this.idProduit = idProduit;
        this.idSymptome = idSymptome;
    }

    // Getters et Setters
    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
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
        ProduitSymptomeId that = (ProduitSymptomeId) o;
        return idProduit.equals(that.idProduit) &&
               idSymptome.equals(that.idSymptome);
    }

    @Override
    public int hashCode() {
        return 31 * idProduit.hashCode() + idSymptome.hashCode();
    }
}
