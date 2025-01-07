package itu.projet.pharmacie.controller.mvtstock;

import itu.projet.pharmacie.service.mvtstock.MouvementStockDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mouvementstockdetails")
public class MouvementStockDetailsController {

    @Autowired
    private MouvementStockDetailsService mouvementStockDetailsService;

    @GetMapping("/delete/{idMouvementStockDetails}/{idMouvement}")
    public String deleteMouvementStockDetails(
            @PathVariable Integer idMouvementStockDetails,
            @PathVariable Integer idMouvement) {
        try {
            mouvementStockDetailsService.deleteMouvementStockDetails(idMouvementStockDetails);
        } catch (RuntimeException ex) {
            System.err.println("Erreur lors de la suppression du détail de mouvement de stock : " + ex.getMessage());
        }
        return "redirect:/mouvement-stock/details/" + idMouvement;
    }
}
