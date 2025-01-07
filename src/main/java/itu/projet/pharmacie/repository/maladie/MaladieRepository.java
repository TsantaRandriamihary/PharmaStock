package itu.projet.pharmacie.repository.maladie;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.maladie.Maladie;

@Repository
public interface MaladieRepository extends JpaRepository<Maladie, Integer> {
}

