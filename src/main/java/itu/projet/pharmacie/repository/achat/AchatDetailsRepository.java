package itu.projet.pharmacie.repository.achat;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.achat.Achat;
import itu.projet.pharmacie.model.achat.AchatDetails;

@Repository
public interface AchatDetailsRepository extends JpaRepository<AchatDetails, Integer> {
    List<AchatDetails> findByAchat(Achat achat);
}

