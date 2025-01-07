package itu.projet.pharmacie.model.produit;


import jakarta.persistence.*;
import itu.projet.pharmacie.model.type.*;
import itu.projet.pharmacie.model.laboratoire.Laboratoire;
import itu.projet.pharmacie.model.domaine.Domaine;

@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduit;

    private String nomProduit;
    private String descriptionProduit;
    private Integer quantiteUnitaire;
    private Double dose;
    private Integer etatProduit;
    private Double prix;

    @ManyToOne
    @JoinColumn(name = "idForme")
    private Forme forme;

    @ManyToOne
    @JoinColumn(name = "idClasse")
    private Classe classe;

    @ManyToOne
    @JoinColumn(name = "idLaboratoire")
    private Laboratoire laboratoire;

    @ManyToOne
    @JoinColumn(name = "idTypeproduit", nullable = false)
    private Typeproduit typeproduit;

    @ManyToOne
    @JoinColumn(name = "idUnite")
    private Unite unite;

    @ManyToOne
    @JoinColumn(name = "idDomaine")
    private Domaine domaine;
    

    // Getters and Setters
    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getDescriptionProduit() {
        return descriptionProduit;
    }

    public void setDescriptionProduit(String descriptionProduit) {
        this.descriptionProduit = descriptionProduit;
    }

    public Integer getQuantiteUnitaire() {
        return quantiteUnitaire;
    }

    public void setQuantiteUnitaire(Integer quantiteUnitaire) {
        this.quantiteUnitaire = quantiteUnitaire;
    }

    public Double getDose() {
        return dose;
    }

    public void setDose(Double dose) {
        this.dose = dose;
    }

    public Integer getEtatProduit() {
        return etatProduit;
    }

    public void setEtatProduit(Integer etatProduit) {
        this.etatProduit = etatProduit;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Forme getForme() {
        return forme;
    }

    public void setForme(Forme forme) {
        this.forme = forme;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Laboratoire getLaboratoire() {
        return laboratoire;
    }

    public void setLaboratoire(Laboratoire laboratoire) {
        this.laboratoire = laboratoire;
    }

    public Typeproduit getTypeproduit() {
        return typeproduit;
    }

    public void setTypeproduit(Typeproduit typeproduit) {
        this.typeproduit = typeproduit;
    }
    

    public Unite getUnite() {
        return unite;
    }

    public void setUnite(Unite unite) {
        this.unite = unite;
    }

    public Domaine getDomaine() {
        return domaine;
    }

    public void setDomaine(Domaine domaine) {
        this.domaine = domaine;
    }
}
