package itu.projet.pharmacie.repository.type;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.type.Trancheage;

@Repository
public interface TrancheageRepository extends JpaRepository<Trancheage, Integer> {
}

