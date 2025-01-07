package itu.projet.pharmacie.repository.maladiesymptome;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.maladiesymptome.MaladieSymptome;
import itu.projet.pharmacie.model.maladiesymptome.MaladieSymptomeId;

@Repository
public interface MaladieSymptomeRepository extends JpaRepository<MaladieSymptome, MaladieSymptomeId> {
    List<MaladieSymptome> findByIdMaladie(Integer idMaladie);
    List<MaladieSymptome> findByIdSymptome(Integer idSymptome);
}

