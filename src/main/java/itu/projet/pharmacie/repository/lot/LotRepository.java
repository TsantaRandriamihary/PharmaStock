package itu.projet.pharmacie.repository.lot;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.lot.Lot;
import itu.projet.pharmacie.model.produit.Produit;

@Repository
public interface LotRepository extends JpaRepository<Lot, Integer> {
    List<Lot> findByProduitIdProduitAndDatePeremptionGreaterThanOrderByDatePeremptionAsc(Integer idProduit, Timestamp date);
}

