package itu.projet.pharmacie.model.laboratoire;

import jakarta.persistence.*;

@Entity
public class Laboratoire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLaboratoire;

    private String nomLaboratoire;

    private String contactLaboratoire;

    private String adresseLaboratoire;

   
    // Getters and Setters
    public Integer getIdLaboratoire() {
        return idLaboratoire;
    }

    public void setIdLaboratoire(Integer idLaboratoire) {
        this.idLaboratoire = idLaboratoire;
    }

    public String getNomLaboratoire() {
        return nomLaboratoire;
    }

    public void setNomLaboratoire(String nomLaboratoire) {
        this.nomLaboratoire = nomLaboratoire;
    }

    public String getContactLaboratoire() {
        return contactLaboratoire;
    }

    public void setContactLaboratoire(String contactLaboratoire) {
        this.contactLaboratoire = contactLaboratoire;
    }

    public String getAdresseLaboratoire() {
        return adresseLaboratoire;
    }

    public void setAdresseLaboratoire(String adresseLaboratoire) {
        this.adresseLaboratoire = adresseLaboratoire;
    }

    
}
