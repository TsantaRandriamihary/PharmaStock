package itu.projet.pharmacie.repository.selection;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.selection.Selection;

@Repository
public interface SelectionRepository extends JpaRepository<Selection, Integer> {
    Optional<Selection> findById(Integer selection);
    List<Selection> findByTypeSelectionIdTypeSelectionAndDateDebutLessThanEqualAndDateFinGreaterThanEqual(
            Integer idTypeSelection, String dateDebut, String dateFin);
            List<Selection> findByTypeSelectionIdTypeSelection(
                Integer idTypeSelection);

}
