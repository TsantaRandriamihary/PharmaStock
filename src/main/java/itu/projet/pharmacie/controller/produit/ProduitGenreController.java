package itu.projet.pharmacie.controller.produit;


import itu.projet.pharmacie.model.produit.contreindication.ContreIndicationId;
import itu.projet.pharmacie.model.produit.genreproduit.ProduitGenre;
import itu.projet.pharmacie.model.produit.genreproduit.ProduitGenreId;
import itu.projet.pharmacie.model.type.Genre;
import itu.projet.pharmacie.service.produit.ProduitGenreService;
import itu.projet.pharmacie.service.produit.ProduitService;
import itu.projet.pharmacie.repository.type.GenreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/produitgenre")
public class ProduitGenreController {

    @Autowired
    private ProduitGenreService produitGenreService;

    @Autowired
    private ProduitService produitService;

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/add/{idProduit}")
    public String addProduitGenreForm(@PathVariable Integer idProduit, Model model) {
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("idProduit", idProduit);
        model.addAttribute("genres", genres);
        model.addAttribute("produitGenre", new ProduitGenre());
        return "/produit/addgenre";
    }

    @PostMapping("/save")
    public String addProduitGenre(@ModelAttribute ProduitGenre produitGenre) {
        produitGenre.setId(new ProduitGenreId(produitGenre.getIdProduit(), produitGenre.getIdGenre()));
        produitGenreService.createProduitGenre(produitGenre);
        return "redirect:/produits/details/" + produitGenre.getIdProduit();
    }

    @GetMapping("/delete/{idProduit}/{idGenre}")
    public String deleteProduitGenre(
            @PathVariable Integer idProduit,
            @PathVariable Integer idGenre) {
        produitGenreService.deleteProduitGenre(new ProduitGenreId(idProduit, idGenre));
        return "redirect:/produits/details/" + idProduit;
    }
}
