package itu.projet.pharmacie.repository.vente;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.vente.Vente;

@Repository
public interface VenteRepository extends JpaRepository<Vente, Integer> {
}

