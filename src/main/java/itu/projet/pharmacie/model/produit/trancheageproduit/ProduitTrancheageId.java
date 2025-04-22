package itu.projet.pharmacie.model.produit.trancheageproduit;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProduitTrancheageId implements Serializable {

    private Integer idProduit;
    private Integer idTrancheage;

    // Constructeurs
    public ProduitTrancheageId() {}

    public ProduitTrancheageId(Integer idProduit, Integer idTrancheage) {
        this.idProduit = idProduit;
        this.idTrancheage = idTrancheage;
    }

    // Getters et Setters
    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public Integer getIdTrancheage() {
        return idTrancheage;
    }

    public void setIdTrancheage(Integer idTrancheage) {
        this.idTrancheage = idTrancheage;
    }

    // hashCode et equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduitTrancheageId that = (ProduitTrancheageId) o;
        return idProduit.equals(that.idProduit) &&
               idTrancheage.equals(that.idTrancheage);
    }

    @Override
    public int hashCode() {
        return 31 * idProduit.hashCode() + idTrancheage.hashCode();
    }
}
