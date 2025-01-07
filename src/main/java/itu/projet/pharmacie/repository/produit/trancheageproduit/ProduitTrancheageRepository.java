package itu.projet.pharmacie.repository.produit.trancheageproduit;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.produit.trancheageproduit.ProduitTrancheage;
import itu.projet.pharmacie.model.produit.trancheageproduit.ProduitTrancheageId;

@Repository
public interface ProduitTrancheageRepository extends JpaRepository<ProduitTrancheage, ProduitTrancheageId> {
    List<ProduitTrancheage> findByIdProduit(Integer produitId);
    List<ProduitTrancheage> findByIdTrancheage(Integer idTrancheAge);
}

