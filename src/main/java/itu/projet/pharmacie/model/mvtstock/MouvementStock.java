package itu.projet.pharmacie.model.mvtstock;


import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class MouvementStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMouvement;

    private Timestamp dateMouvement;
    private Boolean estAchat;
    private Integer reference;

    // Getters and Setters
    public Integer getIdMouvement() {
        return idMouvement;
    }

    public void setIdMouvement(Integer idMouvement) {
        this.idMouvement = idMouvement;
    }

    public Timestamp getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(Timestamp dateMouvement) {
        this.dateMouvement = dateMouvement;
    }

    public Boolean getEstAchat() {
        return estAchat;
    }

    public void setEstAchat(Boolean estAchat) {
        this.estAchat = estAchat;
    }

    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }
}
