package itu.projet.pharmacie.repository.mvtstock;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.mvtstock.MouvementStock;

@Repository
public interface MouvementStockRepository extends JpaRepository<MouvementStock, Integer> {
}

