package itu.projet.pharmacie.controller.selection;

import itu.projet.pharmacie.model.selection.Selection;
import itu.projet.pharmacie.service.produit.ProduitService;
import itu.projet.pharmacie.service.selection.SelectionService;
import itu.projet.pharmacie.repository.selection.TypeSelectionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/selections")
public class SelectionController {

    @Autowired
    private SelectionService selectionService;
    @Autowired
    private ProduitService produitService;
    @Autowired
    private TypeSelectionRepository typeSelectionRepository;

    /**
     * Liste toutes les sélections disponibles ou filtrées.
     *
     * @param model Modèle pour passer les données à la vue
     * @return Vue pour afficher la liste des sélections
     */
    @GetMapping("")
    public String listerSelection(
            @RequestParam(value = "idTypeSelection", required = false) Integer idTypeSelection,
            @RequestParam(value = "dateDebut", required = false) String dateDebut,
            @RequestParam(value = "dateFin", required = false) String dateFin,
            Model model) {

        // Appel de la méthode de filtrage dans le service
        model.addAttribute("typeselection", typeSelectionRepository.findAll()); 
        List<Selection> selections = selectionService.filtrerSelections(idTypeSelection, dateDebut, dateFin);
        model.addAttribute("selections", selections);

        return "elements/selection/list"; // Vue pour afficher la liste des sélections
    }

    /**
     * Affiche le formulaire pour ajouter une sélection.
     *
     * @param model Modèle pour passer les données à la vue
     * @return Vue du formulaire d'ajout
     */
    @GetMapping("/add")
    public String afficherFormulaireAjout(Model model) {
        model.addAttribute("typeselection", typeSelectionRepository.findAll()); 
        model.addAttribute("produits", produitService.getAllProduits()); 
        model.addAttribute("selection", new Selection());
        return "elements/selection/add"; // Vue pour afficher le formulaire d'ajout
    }

    /**
     * Enregistre une nouvelle sélection.
     *
     * @param idProduit       ID du produit
     * @param idTypeSelection ID du type de sélection
     * @param description     Description de la sélection
     * @param dateDebut       Date de début
     * @param dateFin         Date de fin
     * @param redirectAttributes Redirection avec messages
     * @return Redirection vers la liste des sélections
     */
    @PostMapping("/save")
    public String insererSelection(
            @RequestParam("idProduit") Integer idProduit,
            @RequestParam("idTypeSelection") Integer idTypeSelection,
            @RequestParam("description") String description,
            @RequestParam("dateDebut") String dateDebut,
            @RequestParam("dateFin") String dateFin,
            RedirectAttributes redirectAttributes) {

        try {
            selectionService.insererSelection(idProduit, idTypeSelection, description, dateDebut, dateFin);
            redirectAttributes.addFlashAttribute("success", "Sélection enregistrée avec succès !");
            return "redirect:/selections";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de l'enregistrement de la sélection : " + e.getMessage());
            return "redirect:/selections/add";
        }
    }
}
