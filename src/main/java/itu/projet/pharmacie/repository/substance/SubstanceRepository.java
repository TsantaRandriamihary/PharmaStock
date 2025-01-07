package itu.projet.pharmacie.repository.substance;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.substance.Substance;

@Repository
public interface SubstanceRepository extends JpaRepository<Substance, Integer> {
}

