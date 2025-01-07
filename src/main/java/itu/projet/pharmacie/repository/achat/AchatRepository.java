package itu.projet.pharmacie.repository.achat;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.achat.Achat;

@Repository
public interface AchatRepository extends JpaRepository<Achat, Integer> {
}

