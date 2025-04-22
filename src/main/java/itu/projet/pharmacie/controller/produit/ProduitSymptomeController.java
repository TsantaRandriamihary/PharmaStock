package itu.projet.pharmacie.controller.produit;


import itu.projet.pharmacie.model.produit.contreindication.ContreIndicationId;
import itu.projet.pharmacie.model.produit.symptomeproduit.ProduitSymptome;
import itu.projet.pharmacie.model.produit.symptomeproduit.ProduitSymptomeId;
import itu.projet.pharmacie.model.symptome.Symptome;
import itu.projet.pharmacie.service.produit.ProduitService;
import itu.projet.pharmacie.service.produit.ProduitSymptomeService;
import itu.projet.pharmacie.repository.symptome.SymptomeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produitsymptome")
public class ProduitSymptomeController {

    @Autowired
    private ProduitSymptomeService produitSymptomeService;

    @Autowired
    private ProduitService produitService;

    @Autowired
    private SymptomeRepository symptomeReSymptomeRepository;

    @GetMapping("/add/{idProduit}")
    public String addProduitSymptomeForm(@PathVariable Integer idProduit, Model model) {
        List<Symptome> symptomes = symptomeReSymptomeRepository.findAll();
        model.addAttribute("idProduit", idProduit);
        model.addAttribute("symptomes", symptomes);
        model.addAttribute("produitSymptome", new ProduitSymptome());
        return "/produit/addsymptome";
    }

    @PostMapping("/save")
    public String addProduitSymptome(@ModelAttribute ProduitSymptome produitSymptome) {
        produitSymptome.setId(new ProduitSymptomeId(produitSymptome.getIdProduit(), produitSymptome.getIdSymptome()));
        produitSymptomeService.createProduitSymptome(produitSymptome);
        return "redirect:/produits/details/" + produitSymptome.getIdProduit();
    }

    @GetMapping("/delete/{idProduit}/{idSymptome}")
    public String deleteProduitSymptome(
            @PathVariable Integer idProduit,
            @PathVariable Integer idSymptome) {
        produitSymptomeService.deleteProduitSymptome(new ProduitSymptomeId(idProduit, idSymptome));
        return "redirect:/produits/details/" + idProduit;
    }
}
