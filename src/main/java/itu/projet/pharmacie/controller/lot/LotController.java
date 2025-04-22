package itu.projet.pharmacie.controller.lot;

import itu.projet.pharmacie.model.lot.Lot;
import itu.projet.pharmacie.model.produit.Produit;
import itu.projet.pharmacie.service.lot.LotService;
import itu.projet.pharmacie.service.produit.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/lots")
public class LotController {

    @Autowired
    private LotService lotService;

    @Autowired
    private ProduitService produitService;

    @GetMapping("")
    public String listLots(Model model) {
        List<Lot> lots = lotService.getAllLots();        
        Map<Lot, String> lotDetailsMap = new HashMap<>();
        for (Lot lot : lots) {
            String produitNom = lot.getProduit() != null ? lot.getProduit().getNomProduit() : "Produit inconnu";
            lotDetailsMap.put(lot, produitNom);
        }
        model.addAttribute("lotDetailsMap", lotDetailsMap);
        return "lot/list";
    }
    
    @GetMapping("/add")
    public String addLot(Model model) {
        model.addAttribute("lot", new Lot());
        model.addAttribute("produits", produitService.getAllProduits()); 
        return "lot/add"; 
    }


    @PostMapping("/save")
    public String saveLot(
            @RequestParam("nomLot") String nomLot,
            @RequestParam("quantiteLot") Integer quantiteLot,
            @RequestParam("idProduit") Integer idProduit,
            @RequestParam("dateFabrication") String dateFabrication,
            @RequestParam("datePeremption") String datePeremption,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            Timestamp fabrication = Timestamp.valueOf(dateFabrication + " 00:00:00");
            Timestamp peremption = Timestamp.valueOf(datePeremption + " 00:00:00");

            Lot lot = new Lot();
            lot.setNomLot(nomLot);
            lot.setQuantiteLot(quantiteLot);
            lot.setDateFabrication(fabrication);
            lot.setDatePeremption(peremption);
            
            Produit produit = produitService.getProduitById(idProduit)
                    .orElseThrow(() -> new RuntimeException("Produit non trouvé."));
            lot.setProduit(produit);

            lotService.createLot(lot);
            redirectAttributes.addFlashAttribute("success", "Lot enregistré avec succès !");
            return "redirect:/lots";
        } catch (IllegalArgumentException e) {
            model.addAttribute("produits", produitService.getAllProduits());
            model.addAttribute("error", "Erreur lors de l'enregistrement : " + e.getMessage());
            return "lot/add";
        }
    }


    @GetMapping("/edit/{id}")
    public String editLot(@PathVariable("id") Integer id, Model model) {
        try {
            Lot lot = lotService.getLotById(id)
                    .orElseThrow(() -> new RuntimeException("Lot non trouvé."));
            String dateFString = formatDate(lot.getDateFabrication());
            String datePString = formatDate(lot.getDatePeremption());
            model.addAttribute("lot", lot);
            model.addAttribute("dateFabrication", dateFString);
            model.addAttribute("datePeremption", datePString);
            model.addAttribute("produits", produitService.getAllProduits());
            return "lot/add";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la récupération du lot : " + e.getMessage());
            return "redirect:/lots";
        }
    }

    @PostMapping("/update")
    public String updateLot(
            @RequestParam("idLot") Integer idLot,  
            @RequestParam("nomLot") String nomLot,
            @RequestParam("quantiteLot") Integer quantiteLot,
            @RequestParam("idProduit") Integer idProduit,
            @RequestParam("dateFabrication") String dateFabrication,
            @RequestParam("datePeremption") String datePeremption,
            Model model,
            RedirectAttributes redirectAttributes) {
            Timestamp fabrication = Timestamp.valueOf(dateFabrication + " 00:00:00");
            Timestamp peremption = Timestamp.valueOf(datePeremption + " 00:00:00");

            Lot lot = new Lot();
            lot.setIdLot(idLot);
            lot.setNomLot(nomLot);
            lot.setQuantiteLot(quantiteLot);
            lot.setDateFabrication(fabrication);
            lot.setDatePeremption(peremption);
            Produit p = (produitService.getProduitById(idProduit)).get();
            lot.setProduit(p);
        try {
            
            lotService.updateLot(lot.getIdLot(), lot);
            redirectAttributes.addFlashAttribute("success", "Lot mis à jour avec succès !");
            return "redirect:/lots";
        } catch (Exception e) {
            String dateFString = formatDate(lot.getDateFabrication());
            String datePString = formatDate(lot.getDatePeremption());
            model.addAttribute("lot", lot);
            model.addAttribute("dateFabrication", dateFString);
            model.addAttribute("datePeremption", datePString);            
            model.addAttribute("produits", produitService.getAllProduits());
            model.addAttribute("error", "Erreur lors de la mise à jour : " + e.getMessage());
            return "lot/add";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteLot(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            lotService.deleteLot(id);
            redirectAttributes.addFlashAttribute("success", "Lot supprimé avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression : " + e.getMessage());
        }
        return "redirect:/lots";
    }

    @GetMapping("/details/{id}")
    public String lotDetails(@PathVariable("id") Integer id, Model model) {
        try {
            Lot lot = lotService.getLotById(id)
                    .orElseThrow(() -> new RuntimeException("Détails du lot non trouvés."));
            model.addAttribute("lot", lot);
            return "lot/details";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la récupération des détails : " + e.getMessage());
            return "redirect:/lots";
        }
    }

    public String formatDate(Timestamp timestamp) {
        if (timestamp != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(timestamp);
        }
        return "";
    }
}
