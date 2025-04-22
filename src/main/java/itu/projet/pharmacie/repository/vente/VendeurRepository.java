package itu.projet.pharmacie.repository.vente;

import itu.projet.pharmacie.model.vente.Vente;
import itu.projet.pharmacie.model.vente.Vendeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendeurRepository extends JpaRepository<Vendeur, Integer> {

}
