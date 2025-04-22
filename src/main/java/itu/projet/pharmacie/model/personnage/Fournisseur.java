package itu.projet.pharmacie.model.personnage;


import jakarta.persistence.*;

@Entity
public class Fournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFournisseur;

    private String nomFournisseur;
    private String contactFournisseur;
    private String adresseFournisseur;

    // Getters and Setters
    public Integer getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(Integer idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    public String getContactFournisseur() {
        return contactFournisseur;
    }

    public void setContactFournisseur(String contactFournisseur) {
        this.contactFournisseur = contactFournisseur;
    }

    public String getAdresseFournisseur() {
        return adresseFournisseur;
    }

    public void setAdresseFournisseur(String adresseFournisseur) {
        this.adresseFournisseur = adresseFournisseur;
    }
}
