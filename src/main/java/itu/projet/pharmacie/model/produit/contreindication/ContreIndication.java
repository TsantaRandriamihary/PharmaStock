package itu.projet.pharmacie.model.produit.contreindication;

import jakarta.persistence.*;

@Entity
public class ContreIndication {

    @EmbeddedId
    private ContreIndicationId id;

    // Attributs idProduit et idSymptome
    @Column(name = "idProduit", insertable = false, updatable = false)
    private Integer idProduit;

    @Column(name = "idSymptome", insertable = false, updatable = false)
    private Integer idSymptome;

    private String descriptionContreIndication;

    // Getters et Setters
    public ContreIndicationId getId() {
        return id;
    }

    public void setId(ContreIndicationId id) {
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

    public String getDescriptionContreIndication() {
        return descriptionContreIndication;
    }

    public void setDescriptionContreIndication(String descriptionContreIndication) {
        this.descriptionContreIndication = descriptionContreIndication;
    }
}
