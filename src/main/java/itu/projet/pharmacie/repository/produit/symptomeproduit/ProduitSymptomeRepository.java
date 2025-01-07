package itu.projet.pharmacie.repository.produit.symptomeproduit;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.produit.symptomeproduit.ProduitSymptome;
import itu.projet.pharmacie.model.produit.symptomeproduit.ProduitSymptomeId;

@Repository
public interface ProduitSymptomeRepository extends JpaRepository<ProduitSymptome, ProduitSymptomeId> {
    List<ProduitSymptome> findByIdProduit(Integer produitId);
    List<ProduitSymptome> findByIdIdSymptome(Integer idSymptome);

}

