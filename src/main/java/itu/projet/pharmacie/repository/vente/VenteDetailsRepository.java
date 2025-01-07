package itu.projet.pharmacie.repository.vente;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.vente.Vente;
import itu.projet.pharmacie.model.vente.VenteDetails;

@Repository
public interface VenteDetailsRepository extends JpaRepository<VenteDetails, Integer> {
    List<VenteDetails> findByVente(Vente vente);
}

