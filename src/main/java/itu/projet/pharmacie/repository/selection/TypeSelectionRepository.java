package itu.projet.pharmacie.repository.selection;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itu.projet.pharmacie.model.selection.TypeSelection;

@Repository
public interface TypeSelectionRepository extends JpaRepository<TypeSelection, Integer> {
    Optional<TypeSelection> findById( Integer idTypeSelection);

}
