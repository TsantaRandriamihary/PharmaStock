package itu.projet.pharmacie.model.selection;

import jakarta.persistence.*;
import java.util.Objects;

import itu.projet.pharmacie.model.produit.Produit;

@Entity
@Table(name = "selection")
public class Selection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_selection")
    private Integer idSelection;

    @Column(name = "date_debut")
    private String dateDebut;

    @Column(name = "date_fin")
    private String dateFin;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "idTypeSelection")
    private TypeSelection typeSelection;

    @ManyToOne
    @JoinColumn(name = "idProduit")
    private Produit produit;

    // Getters and Setters
    public Integer getIdSelection() {
        return idSelection;
    }

    public void setIdSelection(Integer idSelection) {
        this.idSelection = idSelection;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeSelection getTypeSelection() {
        return typeSelection;
    }

    public void setTypeSelection(TypeSelection typeSelection) {
        this.typeSelection = typeSelection;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Selection selection = (Selection) o;
        return Objects.equals(idSelection, selection.idSelection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSelection);
    }
}
