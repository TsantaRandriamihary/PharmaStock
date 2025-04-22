package itu.projet.pharmacie.repository.produit;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.laboratoire.Laboratoire;
import itu.projet.pharmacie.model.produit.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Integer> {
    List<Produit> findByLaboratoire(Laboratoire laboratoire);
}

