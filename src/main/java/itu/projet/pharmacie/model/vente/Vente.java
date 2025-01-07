package itu.projet.pharmacie.model.vente;


import jakarta.persistence.*;
import itu.projet.pharmacie.model.personnage.Client;

import java.sql.Timestamp;

@Entity
public class Vente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVente;

    private Timestamp dateVente;
    private Integer etatVente;
    private Double montantTotal;
    private String descriptionVente;

    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;

    // Getters and Setters
    public Integer getIdVente() {
        return idVente;
    }

    public void setIdVente(Integer idVente) {
        this.idVente = idVente;
    }

    public Timestamp getDateVente() {
        return dateVente;
    }

    public void setDateVente(Timestamp dateVente) {
        this.dateVente = dateVente;
    }

    public Integer getEtatVente() {
        return etatVente;
    }

    public void setEtatVente(Integer etatVente) {
        this.etatVente = etatVente;
    }

    public Double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(Double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public String getDescriptionVente() {
        return descriptionVente;
    }

    public void setDescriptionVente(String descriptionVente) {
        this.descriptionVente = descriptionVente;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
