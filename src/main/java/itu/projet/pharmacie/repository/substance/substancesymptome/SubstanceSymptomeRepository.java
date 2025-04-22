package itu.projet.pharmacie.repository.substance.substancesymptome;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.substance.substancesymptome.SubstanceSymptome;
import itu.projet.pharmacie.model.substance.substancesymptome.SubstanceSymptomeId;

@Repository
public interface SubstanceSymptomeRepository extends JpaRepository<SubstanceSymptome, SubstanceSymptomeId> {
    List<SubstanceSymptome> findByIdSymptome(Integer idSymptome);
}
