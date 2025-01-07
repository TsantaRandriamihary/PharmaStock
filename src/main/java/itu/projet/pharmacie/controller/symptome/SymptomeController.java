package itu.projet.pharmacie.controller.symptome;


import itu.projet.pharmacie.model.symptome.Symptome;
import itu.projet.pharmacie.service.symptome.SymptomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/symptomes")
public class SymptomeController {

    @Autowired
    private SymptomeService symptomeService;

    /**
     * Liste des symptômes
     */
    @GetMapping("")
    public String listSymptomes(Model model) {
        List<Symptome> symptomes = symptomeService.getAllSymptomes();
        model.addAttribute("symptomes", symptomes);
        return "elements/symptome/list"; 
    }

    /**
     * Affichage du formulaire d'ajout de symptôme
     */
    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("symptome", new Symptome());
        return "elements/symptome/add"; 
    }

    /**
     * Sauvegarde d'un nouveau symptôme
     */
    @PostMapping("/save")
    public String saveSymptome(
            @ModelAttribute Symptome symptome, 
            Model model, 
            RedirectAttributes redirectAttributes) {
        try {
            symptomeService.createSymptome(symptome);
            redirectAttributes.addFlashAttribute("success", "Symptôme enregistré avec succès !");
            return "redirect:/symptomes";
        } catch (Exception e) {
            model.addAttribute("symptome", symptome);
            model.addAttribute("error", e.getMessage());
            return "elements/symptome/add";
        }
    }

    /**
     * Affichage du formulaire de modification
     */
    @GetMapping("/edit/{id}")
    public String editSymptome(@PathVariable("id") Integer id, Model model) {
        try {
            Symptome symptome = symptomeService.getSymptomeById(id).orElseThrow(() -> 
                new RuntimeException("Symptôme introuvable avec l'id : " + id)
            );
            model.addAttribute("symptome", symptome);
            return "elements/symptome/add"; 
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la récupération du symptôme : " + e.getMessage());
            return "redirect:/symptomes/add"; 
        }
    }

    /**
     * Mise à jour d'un symptôme existant
     */
    @PostMapping("/update")
    public String updateSymptome(
            @ModelAttribute Symptome symptome, 
            Model model, 
            RedirectAttributes redirectAttributes) {
        try {
            symptomeService.updateSymptome(symptome.getIdSymptome(), symptome);
            redirectAttributes.addFlashAttribute("success", "Symptôme modifié avec succès !");
            return "redirect:/symptomes";
        } catch (Exception e) {
            model.addAttribute("symptome", symptome);
            model.addAttribute("error", e.getMessage());
            return "elements/symptome/add";
        }
    }

    /**
     * Suppression d'un symptôme
     */
    @GetMapping("/delete/{id}")
    public String deleteSymptome(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            symptomeService.deleteSymptome(id);
            redirectAttributes.addFlashAttribute("success", "Symptôme supprimé avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression : " + e.getMessage());
        }
        return "redirect:/symptomes";
    }
}
