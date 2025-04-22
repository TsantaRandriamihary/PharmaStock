package itu.projet.pharmacie.repository.commission;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.type.Commission;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Integer> {
    
}
