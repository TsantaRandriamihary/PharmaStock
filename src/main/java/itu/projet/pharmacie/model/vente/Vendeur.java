package itu.projet.pharmacie.model.vente;

import itu.projet.pharmacie.model.type.Genre;
import jakarta.persistence.*;

@Entity
@Table(name = "vendeur") 
public class Vendeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vendeur")
    private Integer idVendeur; 

    @Column(name = "nom_vendeur", nullable = false)
    private String nomVendeur; 

    @ManyToOne
    @JoinColumn(name = "id_genre", referencedColumnName = "idGenre", nullable = false)
    private Genre genre;

    @Transient
    private Double totalVendue;

    @Transient
    private Double totalCommission;

    public Vendeur() {
    }

    public Vendeur(Integer idVendeur, String nomVendeur, Genre genre) {
        this.idVendeur = idVendeur;
        this.nomVendeur = nomVendeur;
        this.genre = genre; 
    }

    public Integer getIdVendeur() {
        return idVendeur;
    }

    public void setIdVendeur(Integer idVendeur) {
        this.idVendeur = idVendeur;
    }

    public String getNomVendeur() {
        return nomVendeur;
    }

    public void setNomVendeur(String nomVendeur) {
        this.nomVendeur = nomVendeur;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Double getTotalVendue() {
        return totalVendue;
    }

    // Setter pour totalVendue
    public void setTotalVendue(Double totalVendue) {
        this.totalVendue = totalVendue;
    }

    public Double getTotalCommission() {
        return totalCommission;
    }

    public void setTotalCommission(Double totalCommission) {
        this.totalCommission = totalCommission;
    }

    @Override
    public String toString() {
        return "Vendeur{" +
                "idVendeur=" + idVendeur +
                ", nomVendeur='" + nomVendeur + '\'' +
                ", genre=" + genre.getNomGenre() + 
                '}';
    }
}
