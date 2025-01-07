package itu.projet.pharmacie.controller.produit;

import itu.projet.pharmacie.model.produit.Produit;
import itu.projet.pharmacie.model.symptome.Symptome;
import itu.projet.pharmacie.model.type.Trancheage;
import itu.projet.pharmacie.dto.ProduitDetailsDTO;
import itu.projet.pharmacie.service.produit.ProduitService;
import itu.projet.pharmacie.service.symptome.SymptomeService;
import itu.projet.pharmacie.repository.laboratoire.LaboratoireRepository;
import itu.projet.pharmacie.repository.type.TypeproduitRepository;
import itu.projet.pharmacie.repository.type.ClasseRepository;
import itu.projet.pharmacie.repository.type.FormeRepository;
import itu.projet.pharmacie.repository.type.TrancheageRepository;
import itu.projet.pharmacie.repository.type.UniteRepository;
import itu.projet.pharmacie.repository.domaine.DomaineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/produits")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @Autowired
    private SymptomeService symptomeService;

    @Autowired
    private TrancheageRepository trancheageRepository;

    @Autowired
    private LaboratoireRepository laboratoireRepository;

    @Autowired
    private TypeproduitRepository typeproduitRepository;

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private FormeRepository formeRepository;

    @Autowired
    private UniteRepository uniteRepository;

    @Autowired
    private DomaineRepository domaineRepository;

    @GetMapping("")
    public String listProduits(
            @RequestParam(required = false) Integer idSymptome,
            @RequestParam(required = false) Integer idTrancheage,
            Model model) {
        
        List<Produit> produits = produitService.filterProduits(idSymptome, idTrancheage);
        
        List<Symptome> symptomes = symptomeService.getAllSymptomes();
        List<Trancheage> tranches = trancheageRepository.findAll();

        model.addAttribute("produits", produits);
        model.addAttribute("symptomes", symptomes);
        model.addAttribute("tranches", tranches);

        model.addAttribute("idSymptome", idSymptome);
        model.addAttribute("idTrancheage", idTrancheage);
        
        return "produit/list";
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("produit", new Produit());
        model.addAttribute("laboratoires", laboratoireRepository.findAll());
        model.addAttribute("types", typeproduitRepository.findAll());
        model.addAttribute("classes", classeRepository.findAll());
        model.addAttribute("formes", formeRepository.findAll());
        model.addAttribute("unites", uniteRepository.findAll());
        model.addAttribute("domaines", domaineRepository.findAll());
        return "produit/add";
    }

    @PostMapping("/save")
    public String saveProduit(
            @ModelAttribute Produit produit,
            Model model,
            @RequestParam("date") String date,
            RedirectAttributes redirectAttributes) {
        try {
            Timestamp dateTemps = Timestamp.valueOf(date + " 00:00:00");
            produitService.insertProduitWithDate(produit, dateTemps);
            redirectAttributes.addFlashAttribute("success", "Produit enregistré avec succès !");
            return "redirect:/produits";
        } catch (Exception e) {
            model.addAttribute("produit", produit);
            model.addAttribute("date", date);
            model.addAttribute("laboratoires", laboratoireRepository.findAll());
            model.addAttribute("types", typeproduitRepository.findAll());
            model.addAttribute("classes", classeRepository.findAll());
            model.addAttribute("formes", formeRepository.findAll());
            model.addAttribute("unites", uniteRepository.findAll());
            model.addAttribute("domaines", domaineRepository.findAll());
            model.addAttribute("error", e.getMessage());
            return "produit/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String editProduit(@PathVariable("id") Integer id, Model model) {
        try {
            Produit produit = produitService.getProduitById(id)
                    .orElseThrow(() -> new RuntimeException("Produit non trouvé."));
            model.addAttribute("produit", produit);
            model.addAttribute("laboratoires", laboratoireRepository.findAll());
            model.addAttribute("types", typeproduitRepository.findAll());
            model.addAttribute("classes", classeRepository.findAll());
            model.addAttribute("formes", formeRepository.findAll());
            model.addAttribute("unites", uniteRepository.findAll());
            model.addAttribute("domaines", domaineRepository.findAll());
            return "produit/add";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la récupération du produit : " + e.getMessage());
            return "redirect:/produits/add";
        }
    }

    @PostMapping("/update")
    public String updateProduit(
            @ModelAttribute Produit produit,
            Model model,
            @RequestParam("date") String date,
            RedirectAttributes redirectAttributes) {
        try {
            Timestamp dateTemps = Timestamp.valueOf(date + " 00:00:00");
            produitService.updateProduitWithDate(produit, dateTemps);
            redirectAttributes.addFlashAttribute("success", "Produit mis à jour avec succès !");
            return "redirect:/produits";
        } catch (Exception e) {
            model.addAttribute("produit", produit);
            model.addAttribute("date", date);
            model.addAttribute("laboratoires", laboratoireRepository.findAll());
            model.addAttribute("types", typeproduitRepository.findAll());
            model.addAttribute("classes", classeRepository.findAll());
            model.addAttribute("formes", formeRepository.findAll());
            model.addAttribute("unites", uniteRepository.findAll());
            model.addAttribute("domaines", domaineRepository.findAll());
            model.addAttribute("error", e.getMessage());
            return "produit/add";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteProduit(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            produitService.deleteProduit(id);
            redirectAttributes.addFlashAttribute("success", "Produit supprimé avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression : " + e.getMessage());
        }
        return "redirect:/produits";
    }

    @GetMapping("/details/{id}")
    public String produitDetails(@PathVariable("id") Integer id, Model model) {
        try {
            ProduitDetailsDTO produitDetails = produitService.getDetails(id);
            if (produitDetails == null || produitDetails.getProduit() == null) {
                throw new RuntimeException("Détails du produit non trouvés pour l'ID : " + id);
            }
            model.addAttribute("produit", produitDetails.getProduit());
            model.addAttribute("produitDetails", produitDetails);
            return "produit/details";

        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la récupération des détails : " + e.getMessage());
            return "redirect:/produits";
        }
    }

}
