package itu.projet.pharmacie.repository.produit.genreproduit;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.produit.genreproduit.ProduitGenre;
import itu.projet.pharmacie.model.produit.genreproduit.ProduitGenreId;
import java.util.List;

@Repository
public interface ProduitGenreRepository extends JpaRepository<ProduitGenre, ProduitGenreId> {
    List<ProduitGenre> findByIdProduit(Integer produitId);
    List<ProduitGenre> findByIdGenre(Integer idGenre);
}

