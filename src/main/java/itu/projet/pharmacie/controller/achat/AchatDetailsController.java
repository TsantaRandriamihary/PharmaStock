package itu.projet.pharmacie.controller.achat;

import itu.projet.pharmacie.service.achat.AchatDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/achatdetails")
public class AchatDetailsController {

    @Autowired
    private AchatDetailsService achatDetailsService;

    
    @GetMapping("/delete/{idAchatDetails}/{idAchat}")
    public String deleteAchatDetails(@PathVariable Integer idAchatDetails, @PathVariable Integer idAchat) {
        try {
            achatDetailsService.deleteAchatDetails(idAchatDetails);
        } catch (RuntimeException ex) {
            System.err.println("Erreur lors de la suppression du détail d'achat : " + ex.getMessage());
        }
        return "redirect:/achats/details/" + idAchat;
    }
}
