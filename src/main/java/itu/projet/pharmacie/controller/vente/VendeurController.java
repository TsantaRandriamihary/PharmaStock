package itu.projet.pharmacie.controller.vente;

import itu.projet.pharmacie.model.type.Genre;
import itu.projet.pharmacie.model.vente.Vendeur;
import itu.projet.pharmacie.service.vente.VendeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/vendeurs")
public class VendeurController {

    @Autowired
    private VendeurService vendeurService;

    @GetMapping("")
    public String listVendeurs(@RequestParam(required = false) String dateDebutStr, @RequestParam(required = false) String dateFinStr , Model model) {
        List<Vendeur> vendeurs = vendeurService.getVendeurAndDateRange(null, null);
        
        if (dateDebutStr != null && dateFinStr!= null){
            Timestamp dateDebut = Timestamp.valueOf(dateDebutStr + " 00:00:00");
            Timestamp dateFin = Timestamp.valueOf(dateFinStr + " 00:00:00");
            vendeurs = vendeurService.getVendeurAndDateRange(dateDebut, dateFin);

        }
        Map<Genre, Map<String, Double>> statGenre = vendeurService.getVendeurStatsByGenre(vendeurs);
        model.addAttribute("vendeurs", vendeurs);
        model.addAttribute("statGenre", statGenre);
        return "vendeur/list"; 
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("vendeur", new Vendeur());
        return "vendeur/add"; 
    }

    @PostMapping("/save")
    public String saveVendeur(
            @ModelAttribute Vendeur vendeur,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            vendeurService.createVendeur(vendeur);
            redirectAttributes.addFlashAttribute("success", "Vendeur enregistré avec succès !");
            return "redirect:/vendeurs";
        } catch (Exception e) {
            model.addAttribute("vendeur", vendeur);
            model.addAttribute("error", e.getMessage());
            return "vendeur/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String editVendeur(@PathVariable("id") Integer id, Model model) {
        try {
            Vendeur vendeur = vendeurService.getVendeurById(id)
                    .orElseThrow(() -> new RuntimeException("Vendeur non trouvé."));
            model.addAttribute("vendeur", vendeur);
            return "vendeur/add"; 
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la récupération du vendeur : " + e.getMessage());
            return "redirect:/vendeurs/add";
        }
    }

    @PostMapping("/update")
    public String updateVendeur(
            @ModelAttribute Vendeur vendeur,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            vendeurService.updateVendeur(vendeur.getIdVendeur(), vendeur);
            redirectAttributes.addFlashAttribute("success", "Vendeur mis à jour avec succès !");
            return "redirect:/vendeurs";
        } catch (Exception e) {
            model.addAttribute("vendeur", vendeur);
            model.addAttribute("error", e.getMessage());
            return "vendeur/add";
        }
    }

}
