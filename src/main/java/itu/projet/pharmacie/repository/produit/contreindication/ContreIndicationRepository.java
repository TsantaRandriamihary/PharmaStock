package itu.projet.pharmacie.repository.produit.contreindication;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.produit.contreindication.ContreIndication;
import itu.projet.pharmacie.model.produit.contreindication.ContreIndicationId;
import java.util.List;

@Repository
public interface ContreIndicationRepository extends JpaRepository<ContreIndication, ContreIndicationId> {
    List<ContreIndication> findByIdProduit(Integer produitId);
}

