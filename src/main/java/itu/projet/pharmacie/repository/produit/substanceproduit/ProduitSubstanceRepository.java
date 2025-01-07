package itu.projet.pharmacie.repository.produit.substanceproduit;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.produit.substanceproduit.ProduitSubstance;
import itu.projet.pharmacie.model.produit.substanceproduit.ProduitSubstanceId;
import java.util.List;
@Repository
public interface ProduitSubstanceRepository extends JpaRepository<ProduitSubstance, ProduitSubstanceId> {
    List<ProduitSubstance> findByIdProduit(Integer produitId);
}

