package itu.projet.pharmacie.model.achat;

import jakarta.persistence.*;
import itu.projet.pharmacie.model.lot.Lot;

@Entity
public class AchatDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAchatDetails;

    private Double prixAchatUnitaire;
    private Double prixAchatTotal;

    @ManyToOne
    @JoinColumn(name = "idLot")
    private Lot lot;

    @ManyToOne
    @JoinColumn(name = "idAchat")
    private Achat achat;

    // Getters and Setters
    public Integer getIdAchatDetails() {
        return idAchatDetails;
    }

    public void setIdAchatDetails(Integer idAchatDetails) {
        this.idAchatDetails = idAchatDetails;
    }

    public Double getPrixAchatUnitaire() {
        return prixAchatUnitaire;
    }

    public void setPrixAchatUnitaire(Double prixAchatUnitaire) {
        this.prixAchatUnitaire = prixAchatUnitaire;
    }

    public Double getPrixAchatTotal() {
        return prixAchatTotal;
    }

    public void setPrixAchatTotal(Double prixAchatTotal) {
        this.prixAchatTotal = prixAchatTotal;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public Achat getAchat() {
        return achat;
    }

    public void setAchat(Achat achat) {
        this.achat = achat;
    }


}
