package itu.projet.pharmacie.repository.personnage;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.personnage.Fournisseur;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {
}

