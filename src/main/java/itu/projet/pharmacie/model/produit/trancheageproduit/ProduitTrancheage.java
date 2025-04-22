package itu.projet.pharmacie.model.produit.trancheageproduit;

import jakarta.persistence.*;

@Entity
public class ProduitTrancheage {

    @EmbeddedId
    private ProduitTrancheageId id;

    // Attributs idProduit et idTrancheage
    @Column(name = "idProduit", insertable = false, updatable = false)
    private Integer idProduit;

    @Column(name = "idTrancheage", insertable = false, updatable = false)
    private Integer idTrancheage;

    // Getters et Setters
    public ProduitTrancheageId getId() {
        return id;
    }

    public void setId(ProduitTrancheageId id) {
        this.id = id;
        this.idProduit = id.getIdProduit();
        this.idTrancheage = id.getIdTrancheage();
    }

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
}
