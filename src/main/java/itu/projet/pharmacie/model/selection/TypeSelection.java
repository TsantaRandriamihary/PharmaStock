package itu.projet.pharmacie.model.selection;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "type_selection")
public class TypeSelection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_selection")
    private Integer idTypeSelection;

    @Column(name = "nom_selection", length = 50, nullable = false)
    private String nomSelection;

    @OneToMany(mappedBy = "typeSelection", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Selection> selections;

    // Getters and Setters
    public Integer getIdTypeSelection() {
        return idTypeSelection;
    }

    public void setIdTypeSelection(Integer idTypeSelection) {
        this.idTypeSelection = idTypeSelection;
    }

    public String getNomSelection() {
        return nomSelection;
    }

    public void setNomSelection(String nomSelection) {
        this.nomSelection = nomSelection;
    }

    public List<Selection> getSelections() {
        return selections;
    }

    public void setSelections(List<Selection> selections) {
        this.selections = selections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeSelection that = (TypeSelection) o;
        return Objects.equals(idTypeSelection, that.idTypeSelection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTypeSelection);
    }
}
