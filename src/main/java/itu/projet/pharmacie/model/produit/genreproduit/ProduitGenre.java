package itu.projet.pharmacie.model.produit.genreproduit;

import jakarta.persistence.*;

@Entity
public class ProduitGenre {

    @EmbeddedId
    private ProduitGenreId id;

    // Attributs idProduit et idGenre
    @Column(name = "idProduit", insertable = false, updatable = false)
    private Integer idProduit;

    @Column(name = "idGenre", insertable = false, updatable = false)
    private Integer idGenre;

    // Getters et Setters
    public ProduitGenreId getId() {
        return id;
    }

    public void setId(ProduitGenreId id) {
        this.id = id;
        this.idProduit = id.getIdProduit();
        this.idGenre = id.getIdGenre();
    }

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
}
