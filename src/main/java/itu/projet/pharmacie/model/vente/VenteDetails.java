package itu.projet.pharmacie.model.vente;

import jakarta.persistence.*;
import itu.projet.pharmacie.model.lot.Lot;

@Entity
public class VenteDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVenteDetails;

    private Integer quantiteVendue;
    private Double prixVenteUnitaire;

    @ManyToOne
    @JoinColumn(name = "idLot")
    private Lot lot;

    @ManyToOne
    @JoinColumn(name = "idVente")
    private Vente vente;

    // Getters and Setters
    public Integer getIdVenteDetails() {
        return idVenteDetails;
    }

    public void setIdVenteDetails(Integer idVenteDetails) {
        this.idVenteDetails = idVenteDetails;
    }

    public Integer getQuantiteVendue() {
        return quantiteVendue;
    }

    public void setQuantiteVendue(Integer quantiteVendue) {
        this.quantiteVendue = quantiteVendue;
    }

    public Double getPrixVenteUnitaire() {
        return prixVenteUnitaire;
    }

    public void setPrixVenteUnitaire(Double prixVenteUnitaire) {
        this.prixVenteUnitaire = prixVenteUnitaire;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public Vente getVente() {
        return vente;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }
}
