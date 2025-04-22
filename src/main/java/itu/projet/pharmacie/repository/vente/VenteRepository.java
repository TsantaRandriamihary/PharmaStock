package itu.projet.pharmacie.repository.vente;

import itu.projet.pharmacie.model.vente.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface VenteRepository extends JpaRepository<Vente, Integer> {

    @Query("SELECT v FROM Vente v WHERE v.vendeur.idVendeur = :idVendeur AND v.dateVente BETWEEN :dateDebut AND :dateFin")
    List<Vente> findByVendeurIdAndDateVenteBetween(Integer idVendeur, Timestamp dateDebut, Timestamp dateFin);

    List<Vente> findByVendeurIdVendeur(Integer idVendeur);
}
