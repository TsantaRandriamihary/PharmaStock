package itu.projet.pharmacie.controller.vente;

import itu.projet.pharmacie.service.vente.VenteDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ventedetails")
public class VenteDetailsController {

    @Autowired
    private VenteDetailsService venteDetailsService;

    
    @GetMapping("/delete/{idVenteDetails}/{idVente}")
    public String deleteVenteDetails(@PathVariable Integer idVenteDetails, @PathVariable Integer idVente) {
        try {
            venteDetailsService.deleteVenteDetails(idVenteDetails);
        } catch (RuntimeException ex) {
            System.err.println("Erreur lors de la suppression du détail de vente : " + ex.getMessage());
        }
        return "redirect:/ventes/details/" + idVente;
    }
}
