package itu.projet.pharmacie.model.mvtstock;


import jakarta.persistence.*;
import itu.projet.pharmacie.model.produit.Produit;
import itu.projet.pharmacie.model.lot.Lot;

@Entity
public class MouvementStockDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMouvementDetails;

    private Integer entree;
    private Integer sortie;

    @ManyToOne
    @JoinColumn(name = "idProduit")
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "idLot")
    private Lot lot;

    @ManyToOne
    @JoinColumn(name = "idMouvement")
    private MouvementStock mouvementStock;

    // Getters and Setters
    public Integer getIdMouvementDetails() {
        return idMouvementDetails;
    }

    public void setIdMouvementDetails(Integer idMouvementDetails) {
        this.idMouvementDetails = idMouvementDetails;
    }

    public Integer getEntree() {
        return entree;
    }

    public void setEntree(Integer entree) {
        this.entree = entree;
    }

    public Integer getSortie() {
        return sortie;
    }

    public void setSortie(Integer sortie) {
        this.sortie = sortie;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public MouvementStock getMouvementStock() {
        return mouvementStock;
    }

    public void setMouvementStock(MouvementStock mouvementStock) {
        this.mouvementStock = mouvementStock;
    }
}
