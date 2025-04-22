package itu.projet.pharmacie.model.historiqueprix;


import jakarta.persistence.*;

import java.sql.Timestamp;

import itu.projet.pharmacie.model.produit.Produit;

@Entity
public class HistoriquePrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHistoriquePrix;

    private Timestamp dateChangement;
    private Double prix;

    @ManyToOne
    @JoinColumn(name = "idProduit")
    private Produit produit;

    // Getters and Setters
    public Integer getIdHistoriquePrix() {
        return idHistoriquePrix;
    }

    public void setIdHistoriquePrix(Integer idHistoriquePrix) {
        this.idHistoriquePrix = idHistoriquePrix;
    }

    public Timestamp getDateChangement() {
        return dateChangement;
    }

    public void setDateChangement(Timestamp dateChangement) {
        this.dateChangement = dateChangement;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
