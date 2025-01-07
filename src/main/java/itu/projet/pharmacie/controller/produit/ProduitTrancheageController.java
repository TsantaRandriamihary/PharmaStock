package itu.projet.pharmacie.controller.produit;

import itu.projet.pharmacie.model.produit.contreindication.ContreIndicationId;
import itu.projet.pharmacie.model.produit.trancheageproduit.ProduitTrancheage;
import itu.projet.pharmacie.model.produit.trancheageproduit.ProduitTrancheageId;
import itu.projet.pharmacie.model.type.Trancheage;
import itu.projet.pharmacie.service.produit.ProduitService;
import itu.projet.pharmacie.service.produit.ProduitTrancheageService;
import itu.projet.pharmacie.repository.type.TrancheageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produittrancheage")
public class ProduitTrancheageController {

    @Autowired
    private ProduitTrancheageService produitTrancheageService;

    @Autowired
    private ProduitService produitService;

    @Autowired
    private TrancheageRepository trancheageRepository;

    @GetMapping("/add/{idProduit}")
    public String addProduitTrancheageForm(@PathVariable Integer idProduit, Model model) {
        List<Trancheage> trancheages = trancheageRepository.findAll();
        model.addAttribute("idProduit", idProduit); 
        model.addAttribute("trancheages", trancheages);
        model.addAttribute("produitTrancheage", new ProduitTrancheage());
        return "/produit/addtrancheage";
    }

    @PostMapping("/save")
    public String addProduitTrancheage(@ModelAttribute ProduitTrancheage produitTrancheage) {
        produitTrancheage.setId(new ProduitTrancheageId(produitTrancheage.getIdProduit(), produitTrancheage.getIdTrancheage()));
        produitTrancheageService.createProduitTrancheage(produitTrancheage);
        return "redirect:/produits/details/" + produitTrancheage.getIdProduit();
    }

    @GetMapping("/delete/{idProduit}/{idTrancheage}")
    public String deleteProduitTrancheage(
            @PathVariable Integer idProduit,
            @PathVariable Integer idTrancheage) {
        produitTrancheageService.deleteProduitTrancheage(new ProduitTrancheageId(idProduit, idTrancheage));
        return "redirect:/produits/details/" + idProduit;
    }
}
