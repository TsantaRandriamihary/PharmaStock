package itu.projet.pharmacie.repository.domaine;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.domaine.Domaine;

@Repository
public interface DomaineRepository extends JpaRepository<Domaine, Integer> {
}

