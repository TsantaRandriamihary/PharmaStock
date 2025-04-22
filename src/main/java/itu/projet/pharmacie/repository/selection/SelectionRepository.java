package itu.projet.pharmacie.repository.selection;

import itu.projet.pharmacie.model.selection.Selection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SelectionRepository extends JpaRepository<Selection, Integer> {

    Optional<Selection> findById(Integer selection);

    List<Selection> findByTypeSelectionIdTypeSelectionAndDateDebutLessThanEqualAndDateFinGreaterThanEqual(
            Integer idTypeSelection, String dateDebut, String dateFin);

    List<Selection> findByTypeSelectionIdTypeSelection(Integer idTypeSelection);

    @Query(value = "SELECT * FROM selection s " +
               "WHERE s.id_type_selection = :idTypeSelection " +
               "AND EXTRACT(MONTH FROM TO_DATE(s.date_debut, 'YYYY-MM-DD')) = :mois " +
               "AND EXTRACT(YEAR FROM TO_DATE(s.date_debut, 'YYYY-MM-DD')) = :annee", nativeQuery = true)
    List<Selection> findByTypeSelectionAndMonthAndYearNative(
            @Param("idTypeSelection") Integer idTypeSelection,
            @Param("mois") Integer mois,
            @Param("annee") Integer annee);

    @Query(value = "SELECT * FROM selection s " +
                   "WHERE s.id_type_selection = :idTypeSelection " +
                   "AND EXTRACT(MONTH FROM TO_DATE(s.date_debut, 'YYYY-MM-DD')) = :mois", nativeQuery = true)
    List<Selection> findByTypeSelectionAndMonth(
            @Param("idTypeSelection") Integer idTypeSelection,
            @Param("mois") Integer mois);

    @Query(value = "SELECT * FROM selection s " +
                   "WHERE s.id_type_selection = :idTypeSelection " +
                   "AND EXTRACT(YEAR FROM TO_DATE(s.date_debut, 'YYYY-MM-DD')) = :annee", nativeQuery = true)
    List<Selection> findByTypeSelectionAndYear(
            @Param("idTypeSelection") Integer idTypeSelection,
            @Param("annee") Integer annee);
}
