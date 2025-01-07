package itu.projet.pharmacie.repository.laboratoire;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.laboratoire.Laboratoire;

@Repository
public interface LaboratoireRepository extends JpaRepository<Laboratoire, Integer> {
}

