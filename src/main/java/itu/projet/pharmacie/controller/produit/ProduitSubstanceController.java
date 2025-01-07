package itu.projet.pharmacie.controller.produit;


import itu.projet.pharmacie.model.produit.substanceproduit.ProduitSubstance;
import itu.projet.pharmacie.model.produit.substanceproduit.ProduitSubstanceId;
import itu.projet.pharmacie.model.substance.Substance;
import itu.projet.pharmacie.service.produit.ProduitService;
import itu.projet.pharmacie.service.produit.ProduitSubstanceService;
import itu.projet.pharmacie.repository.substance.SubstanceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produitsubstance")
public class ProduitSubstanceController {

    @Autowired
    private ProduitSubstanceService produitSubstanceService;

    @Autowired
    private ProduitService produitService;

    @Autowired
    private SubstanceRepository substanceReSubstanceRepository;

    @GetMapping("/add/{idProduit}")
    public String addProduitSubstanceForm(@PathVariable Integer idProduit, Model model) {
        List<Substance> substances = substanceReSubstanceRepository.findAll();
        model.addAttribute("idProduit", idProduit);
        model.addAttribute("substances", substances);
        model.addAttribute("produitSubstance", new ProduitSubstance());
        return "/produit/addsubstance";
    }

    @PostMapping("/save")
    public String addProduitSubstance(@ModelAttribute ProduitSubstance produitSubstance, Model model) {
        produitSubstance.setId(new ProduitSubstanceId(+produitSubstance.getIdProduit(), produitSubstance.getIdSubstance()));
        try {
            produitSubstanceService.createProduitSubstance(produitSubstance);
        } catch (Exception e) {
            List<Substance> substances = substanceReSubstanceRepository.findAll();
            model.addAttribute("idProduit", produitSubstance.getIdProduit());
            model.addAttribute("substances", substances);
            model.addAttribute("produitSubstance", new ProduitSubstance());
            model.addAttribute("error", e.getMessage());
            return "/produit/addsubstance";
        }
        return "redirect:/produits/details/" + produitSubstance.getIdProduit();
    }

    @GetMapping("/delete/{idProduit}/{idSubstance}")
    public String deleteProduitSubstance(
            @PathVariable Integer idProduit,
            @PathVariable Integer idSubstance) {
        produitSubstanceService.deleteProduitSubstance(new ProduitSubstanceId(idProduit, idSubstance));
        return "redirect:/produits/details/" + idProduit;
    }
}
