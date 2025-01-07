package itu.projet.pharmacie.controller.personnage;

import itu.projet.pharmacie.model.personnage.Fournisseur;
import itu.projet.pharmacie.service.personnage.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/fournisseurs")
public class FournisseurController {

    @Autowired
    private FournisseurService fournisseurService;

    @GetMapping("")
    public String listFournisseurs(Model model) {
        List<Fournisseur> fournisseurs = fournisseurService.getAllFournisseurs();
        model.addAttribute("fournisseurs", fournisseurs);
        return "fournisseur/list";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("fournisseur", new Fournisseur());
        return "fournisseur/add";
    }

    @PostMapping("/save")
    public String saveFournisseur(
            @ModelAttribute Fournisseur fournisseur,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            fournisseurService.createFournisseur(fournisseur);
            redirectAttributes.addFlashAttribute("success", "Fournisseur enregistré avec succès !");
            return "redirect:/fournisseurs";
        } catch (Exception e) {
            model.addAttribute("fournisseur", fournisseur);
            model.addAttribute("error", e.getMessage());
            return "fournisseur/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String editFournisseur(@PathVariable("id") Integer id, Model model) {
        try {
            Fournisseur fournisseur = fournisseurService.getFournisseurById(id)
                    .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé."));
            model.addAttribute("fournisseur", fournisseur);
            return "fournisseur/add";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la récupération du fournisseur : " + e.getMessage());
            return "redirect:/fournisseurs/add";
        }
    }

    @PostMapping("/update")
    public String updateFournisseur(
            @ModelAttribute Fournisseur fournisseur,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            fournisseurService.updateFournisseur(fournisseur.getIdFournisseur(), fournisseur);
            redirectAttributes.addFlashAttribute("success", "Fournisseur mis à jour avec succès !");
            return "redirect:/fournisseurs";
        } catch (Exception e) {
            model.addAttribute("fournisseur", fournisseur);
            model.addAttribute("error", e.getMessage());
            return "fournisseur/add";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteFournisseur(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            fournisseurService.deleteFournisseur(id);
            redirectAttributes.addFlashAttribute("success", "Fournisseur supprimé avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression : " + e.getMessage());
        }
        return "redirect:/fournisseurs";
    }
}
