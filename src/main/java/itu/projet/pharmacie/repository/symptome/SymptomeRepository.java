package itu.projet.pharmacie.repository.symptome;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.symptome.Symptome;

@Repository
public interface SymptomeRepository extends JpaRepository<Symptome, Integer> {
}
