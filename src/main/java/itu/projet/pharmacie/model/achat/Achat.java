package itu.projet.pharmacie.model.achat;


import jakarta.persistence.*;
import itu.projet.pharmacie.model.personnage.Fournisseur;
import itu.projet.pharmacie.model.laboratoire.Laboratoire;

import java.sql.Timestamp;

@Entity
public class Achat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAchat;

    private String descriptionAchat;
    private Timestamp dateAchat;
    private Integer etatAchat;
    private Double montantTotal;

    @ManyToOne(optional = true)
    @JoinColumn(name = "idLaboratoire")
    private Laboratoire laboratoire;

    @ManyToOne(optional = true)
    @JoinColumn(name = "idFournisseur")
    private Fournisseur fournisseur;

    // Getters and Setters
    public Integer getIdAchat() {
        return idAchat;
    }

    public void setIdAchat(Integer idAchat) {
        this.idAchat = idAchat;
    }

    public String getDescriptionAchat() {
        return descriptionAchat;
    }

    public void setDescriptionAchat(String descriptionAchat) {
        this.descriptionAchat = descriptionAchat;
    }

    public Timestamp getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Timestamp dateAchat) {
        this.dateAchat = dateAchat;
    }

    public Integer getEtatAchat() {
        return etatAchat;
    }

    public void setEtatAchat(Integer etatAchat) {
        this.etatAchat = etatAchat;
    }

    public Double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(Double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public Laboratoire getLaboratoire() {
        return laboratoire;
    }

    public void setLaboratoire(Laboratoire laboratoire) {
        this.laboratoire = laboratoire;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }
}
