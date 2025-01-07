package itu.projet.pharmacie.model.lot;

import jakarta.persistence.*;
import itu.projet.pharmacie.model.produit.Produit;

import java.sql.Timestamp;

@Entity
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLot;

    private Timestamp datePeremption;
    private Timestamp dateFabrication;
    private Integer quantiteLot;
    private String nomLot;

    @ManyToOne
    @JoinColumn(name = "idProduit")
    private Produit produit;

    // Getters and Setters
    public Integer getIdLot() {
        return idLot;
    }

    public void setIdLot(Integer idLot) {
        this.idLot = idLot;
    }

    public Timestamp getDatePeremption() {
        return datePeremption;
    }

    public void setDatePeremption(Timestamp datePeremption) {
        this.datePeremption = datePeremption;
    }

    public void setDatePeremption(String datePeremption) {
        Timestamp peremption = Timestamp.valueOf(datePeremption + " 00:00:00");
        this.datePeremption = peremption;
    }

    public Timestamp getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(Timestamp dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    public void setDateFabrication(String dateFabrication) {
        Timestamp fabrication = Timestamp.valueOf(dateFabrication + " 00:00:00");
        this.dateFabrication = fabrication;
    }

    public Integer getQuantiteLot() {
        return quantiteLot;
    }

    public void setQuantiteLot(Integer quantiteLot) {
        this.quantiteLot = quantiteLot;
    }

    public String getNomLot() {
        return nomLot;
    }

    public void setNomLot(String nomLot) {
        this.nomLot = nomLot;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
