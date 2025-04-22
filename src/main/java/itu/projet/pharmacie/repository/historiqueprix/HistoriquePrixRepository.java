package itu.projet.pharmacie.repository.historiqueprix;


import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.historiqueprix.HistoriquePrix;

@Repository
public interface HistoriquePrixRepository extends JpaRepository<HistoriquePrix, Integer> {
    void deleteAllByProduitIdProduit(Integer produitId);
    @Query("SELECT h FROM HistoriquePrix h " +
       "WHERE h.produit.idProduit = :idProduit AND h.dateChangement <= :dateVente " +
       "ORDER BY h.dateChangement DESC, h.idHistoriquePrix DESC " +
       "LIMIT 1")
Optional<HistoriquePrix> findLatestPriceBeforeDate(@Param("idProduit") Integer idProduit, 
                                                @Param("dateVente") Timestamp dateVente);
}

