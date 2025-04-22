package itu.projet.pharmacie.repository.mvtstock;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.mvtstock.MouvementStock;
import itu.projet.pharmacie.model.mvtstock.MouvementStockDetails;


@Repository
public interface MouvementStockDetailsRepository extends JpaRepository<MouvementStockDetails, Integer> {
    List<MouvementStockDetails> findByMouvementStock(MouvementStock mouvementStock);

    @Query("SELECT SUM(m.entree) FROM MouvementStockDetails m WHERE m.lot.idLot = :idLot")
    Integer sumEntreeByLot(@Param("idLot") Integer idLot);

    @Query("SELECT SUM(m.sortie) FROM MouvementStockDetails m WHERE m.lot.idLot = :idLot")
    Integer sumSortieByLot(@Param("idLot") Integer idLot);
}

