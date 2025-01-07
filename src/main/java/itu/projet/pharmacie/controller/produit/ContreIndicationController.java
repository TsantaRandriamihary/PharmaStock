package itu.projet.pharmacie.controller.produit;


import itu.projet.pharmacie.model.produit.contreindication.ContreIndication;
import itu.projet.pharmacie.model.produit.contreindication.ContreIndicationId;
import itu.projet.pharmacie.model.symptome.Symptome;
import itu.projet.pharmacie.service.produit.ContreIndicationService;
import itu.projet.pharmacie.service.produit.ProduitService;
import itu.projet.pharmacie.service.symptome.SymptomeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contreindication")
public class ContreIndicationController {

    @Autowired
    private ContreIndicationService contreIndicationService;

    @Autowired
    private ProduitService produitService;

    @Autowired
    private SymptomeService symptomeService;

    @GetMapping("/add/{idProduit}")
    public String addContreIndicationForm(@PathVariable Integer idProduit, Model model) {
        List<Symptome> symptomes = symptomeService.getAllSymptomes();
        model.addAttribute("idProduit", idProduit);
        model.addAttribute("symptomes", symptomes);
        model.addAttribute("contreIndication", new ContreIndication());
        return "/produit/addcontreindication";
    }

    @PostMapping("/save")
    public String addContreIndication(@ModelAttribute ContreIndication contreIndication) {
        contreIndication.setId(new ContreIndicationId(contreIndication.getIdProduit(), contreIndication.getIdSymptome()));
        contreIndicationService.createContreIndication(contreIndication);
        return "redirect:/produits/details/" + contreIndication.getIdProduit();
    }

    @GetMapping("/delete/{idProduit}/{idSymptome}")
    public String deleteContreIndication(
            @PathVariable Integer idProduit,
            @PathVariable Integer idSymptome) {
        contreIndicationService.deleteContreIndication(new ContreIndicationId(idProduit, idSymptome));
        return "redirect:/produits/details/" + idProduit;
    }
}
