package itu.projet.pharmacie.model.produit.genreproduit;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProduitGenreId implements Serializable {

    private Integer idProduit;
    private Integer idGenre;

    // Constructeurs
    public ProduitGenreId() {}

    public ProduitGenreId(Integer idProduit, Integer idGenre) {
        this.idProduit = idProduit;
        this.idGenre = idGenre;
    }

    // Getters et Setters
    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public Integer getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(Integer idGenre) {
        this.idGenre = idGenre;
    }

    // hashCode et equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduitGenreId that = (ProduitGenreId) o;
        return idProduit.equals(that.idProduit) &&
               idGenre.equals(that.idGenre);
    }

    @Override
    public int hashCode() {
        return 31 * idProduit.hashCode() + idGenre.hashCode();
    }
}
