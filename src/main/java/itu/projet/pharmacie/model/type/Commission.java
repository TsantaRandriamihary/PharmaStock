package itu.projet.pharmacie.model.type;


import jakarta.persistence.*;

@Entity
@Table(name = "commission")
public class Commission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_commision")
    private Integer idCommission; 

    @Column(name = "pourcent_commission", nullable = false)
    private Double pourcentCommission;

    @Column(name = "min_chiffre_affaire", nullable = false)
    private Double minChiffreAffaire; 

    public Commission() {
    }

    public Commission(Integer idCommission, Double pourcentCommission, Double minChiffreAffaire) {
        this.idCommission = idCommission;
        this.pourcentCommission = pourcentCommission;
        this.minChiffreAffaire = minChiffreAffaire;
    }

    public Integer getIdCommission() {
        return idCommission;
    }

    public void setIdCommission(Integer idCommission) {
        this.idCommission = idCommission;
    }

    public Double getPourcentCommission() {
        return pourcentCommission;
    }

    public void setPourcentCommission(Double pourcentCommission) {
        this.pourcentCommission = pourcentCommission;
    }

    public Double getMinChiffreAffaire() {
        return minChiffreAffaire;
    }

    public void setMinChiffreAffaire(Double minChiffreAffaire) {
        this.minChiffreAffaire = minChiffreAffaire;
    }

    @Override
    public String toString() {
        return "Commission{" +
                "idCommission=" + idCommission +
                ", pourcentCommission=" + pourcentCommission +
                ", minChiffreAffaire=" + minChiffreAffaire +
                '}';
    }
}

