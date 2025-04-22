package itu.projet.pharmacie.model.type;


import jakarta.persistence.*;

@Entity
public class Unite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUnite;

    private String nomUnite;

    private String abreviation;

    // Getters and Setters
    public Integer getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(Integer idUnite) {
        this.idUnite = idUnite;
    }

    public String getNomUnite() {
        return nomUnite;
    }

    public void setNomUnite(String nomUnite) {
        this.nomUnite = nomUnite;
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }
}
