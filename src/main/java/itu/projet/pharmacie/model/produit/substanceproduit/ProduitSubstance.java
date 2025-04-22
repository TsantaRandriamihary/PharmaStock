package itu.projet.pharmacie.model.produit.substanceproduit;

import jakarta.persistence.*;

@Entity
public class ProduitSubstance {

    @EmbeddedId
    private ProduitSubstanceId id;

    private Double degre;

    @Column(name = "idProduit", insertable = false, updatable = false)
    private Integer idProduit;

    @Column(name = "idSubstance", insertable = false, updatable = false)
    private Integer idSubstance;

    // Getters et Setters
    public ProduitSubstanceId getId() {
        return id;
    }

    public void setId(ProduitSubstanceId id) {
        this.id = id;
        this.idProduit = id.getIdProduit();
        this.idSubstance = id.getIdSubstance();
    }

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

    public Double getDegre() {
        return degre;
    }

    public void setDegre(Double degre) {
        this.degre = degre;
    }
}
