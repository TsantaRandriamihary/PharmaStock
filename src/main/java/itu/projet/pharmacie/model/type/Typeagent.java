package itu.projet.pharmacie.model.type;

import jakarta.persistence.*;

@Entity
public class Typeagent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTypeagent;

    private String nomTypeagent;

    
    public Integer getIdTypeagent() {
        return idTypeagent;
    }

    public void setIdTypeagent(Integer idTypeagent) {
        this.idTypeagent = idTypeagent;
    }

    public String getNomTypeagent() {
        return nomTypeagent;
    }

    public void setNomTypeagent(String nomTypeagent) {
        this.nomTypeagent = nomTypeagent;
    }

    
}
