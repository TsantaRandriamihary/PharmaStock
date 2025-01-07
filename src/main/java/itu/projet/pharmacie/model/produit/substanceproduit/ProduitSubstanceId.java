package itu.projet.pharmacie.model.produit.substanceproduit;


import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProduitSubstanceId implements Serializable {

    private Integer idProduit;
    private Integer idSubstance;

    public ProduitSubstanceId() {}

    public ProduitSubstanceId(Integer idProduit, Integer idSubstance) {
        this.idProduit = idProduit;
        this.idSubstance = idSubstance;
    }

    // Getters et Setters
    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public Integer getIdSubstance() {
        return idSubstance;
    }

    public void setIdSubstance(Integer idSubstance) {
        this.idSubstance = idSubstance;
    }

    // hashCode et equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduitSubstanceId that = (ProduitSubstanceId) o;
        return idProduit.equals(that.idProduit) &&
               idSubstance.equals(that.idSubstance);
    }

    @Override
    public int hashCode() {
        return 31 * idProduit.hashCode() + idSubstance.hashCode();
    }
}
