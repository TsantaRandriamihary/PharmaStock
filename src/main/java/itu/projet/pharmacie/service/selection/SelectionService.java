package itu.projet.pharmacie.service.selection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itu.projet.pharmacie.model.produit.Produit;
import itu.projet.pharmacie.model.selection.Selection;
import itu.projet.pharmacie.model.selection.TypeSelection;
import itu.projet.pharmacie.repository.produit.ProduitRepository;
import itu.projet.pharmacie.repository.selection.SelectionRepository;
import itu.projet.pharmacie.repository.selection.TypeSelectionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SelectionService {
    @Autowired
    private SelectionRepository selectionRepository;
    @Autowired
    private TypeSelectionRepository typeSelectionRepository;
    @Autowired
    private ProduitRepository produitRepository;

   
    /**
     * Insère une nouvelle sélection dans la base de données.
     * 
     * @param idProduit       ID du produit
     * @param idTypeSelection ID du type de sélection
     * @param description     Description de la sélection
     * @param dateDebut       Date de début
     * @param dateFin         Date de fin
     * @return La sélection créée
     * @throws IllegalArgumentException Si l'idProduit ou idTypeSelection est invalide
     */
    public Selection insererSelection(Integer idProduit, Integer idTypeSelection, String description, String dateDebut, String dateFin) {
        Optional<Produit> produitOpt = produitRepository.findById(idProduit);
        if (produitOpt.isEmpty()) {
            throw new IllegalArgumentException("Produit avec l'ID " + idProduit + " non trouvé.");
        }
        Optional<TypeSelection> typeSelectionOpt = typeSelectionRepository.findById(idTypeSelection);
        if (typeSelectionOpt.isEmpty()) {
            throw new IllegalArgumentException("Type de sélection avec l'ID " + idTypeSelection + " non trouvé.");
        }

        Selection selection = new Selection();
        selection.setProduit(produitOpt.get());
        selection.setTypeSelection(typeSelectionOpt.get());
        selection.setDescription(description);
        selection.setDateDebut(dateDebut);
        selection.setDateFin(dateFin);

        return selectionRepository.save(selection);
    }

    public List<Selection> filtrerSelections(Integer idTypeSelection, Integer mois, Integer annee) {
        List<Selection> toutesLesSelections = selectionRepository.findAll();    
        if (idTypeSelection != null) {
            toutesLesSelections = toutesLesSelections.stream()
                    .filter(selection -> selection.getTypeSelection() != null &&
                                         selection.getTypeSelection().getIdTypeSelection().equals(idTypeSelection))
                    .collect(Collectors.toList());
        }    
        if (mois != null) {
            toutesLesSelections = toutesLesSelections.stream()
                    .filter(selection -> {
                        String dateDebut = selection.getDateDebut();
                        if (dateDebut != null && !dateDebut.isEmpty()) {
                            int moisSelection = Integer.parseInt(dateDebut.substring(5, 7)); // Format "YYYY-MM-DD"
                            return moisSelection == mois;
                        }
                        return false;
                    })
                    .collect(Collectors.toList());
        }    
        if (annee != null) {
            toutesLesSelections = toutesLesSelections.stream()
                    .filter(selection -> {
                        String dateDebut = selection.getDateDebut();
                        if (dateDebut != null && !dateDebut.isEmpty()) {
                            int anneeSelection = Integer.parseInt(dateDebut.substring(0, 4)); // Format "YYYY-MM-DD"
                            return anneeSelection == annee;
                        }
                        return false;
                    })
                    .collect(Collectors.toList());
        }
        return toutesLesSelections;
    }
    
    
    
}
