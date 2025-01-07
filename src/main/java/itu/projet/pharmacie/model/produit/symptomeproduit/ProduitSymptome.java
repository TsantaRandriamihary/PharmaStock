package itu.projet.pharmacie.model.produit.symptomeproduit;

import jakarta.persistence.*;

@Entity
public class ProduitSymptome {

    @EmbeddedId
    private ProduitSymptomeId id;

    @Column(name = "idProduit", insertable = false, updatable = false)
    private Integer idProduit;

    @Column(name = "idSymptome", insertable = false, updatable = false)
    private Integer idSymptome;

    public ProduitSymptomeId getId() {
        return id;
    }

    public void setId(ProduitSymptomeId id) {
        this.id = id;
        this.idProduit = id.getIdProduit();
        this.idSymptome = id.getIdSymptome();
    }

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
}
