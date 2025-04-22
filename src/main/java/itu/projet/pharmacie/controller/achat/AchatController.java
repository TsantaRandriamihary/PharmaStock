package itu.projet.pharmacie.controller.achat;

import itu.projet.pharmacie.model.achat.Achat;
import itu.projet.pharmacie.model.achat.AchatDetails;
import itu.projet.pharmacie.service.achat.AchatService;
import itu.projet.pharmacie.service.laboratoire.LaboratoireService;
import itu.projet.pharmacie.service.personnage.FournisseurService;
import itu.projet.pharmacie.service.lot.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/achats")
public class AchatController {

    @Autowired
    private AchatService achatService;

    @Autowired
    private LaboratoireService laboratoireService;

    @Autowired
    private FournisseurService fournisseurService;

    @Autowired
    private LotService lotService;

    @GetMapping
    public String listAchats(Model model) {
        List<Achat> achats = achatService.getAllAchats();
        model.addAttribute("achats", achats);
        return "achat/list";
    }

    @GetMapping("/add")
    public String addAchatForm(Model model) {
        model.addAttribute("achat", new Achat());
        model.addAttribute("laboratoires", laboratoireService.getAllLaboratoires());
        model.addAttribute("fournisseurs", fournisseurService.getAllFournisseurs());
        model.addAttribute("lots", lotService.getAllLots());  
        model.addAttribute("achatDetails", new AchatDetails());  
        return "achat/form";
    }

    @PostMapping("/save")
    public String saveAchat(
            @RequestParam("descriptionAchat") String descriptionAchat,
            @RequestParam("dateAchat") String dateAchatStr,
            @RequestParam(value = "laboratoireId", required = false) Integer laboratoireId,
            @RequestParam(value = "fournisseurId", required = false) Integer fournisseurId,
            @RequestParam("prixAchatUnitaire") List<Double> prixAchatUnitaireList,
            @RequestParam("lotId") List<Integer> lotIdList,
            Model model) {

        try {
            // Conversion de la date au format Timestamp
            Timestamp dateAchat = Timestamp.valueOf(dateAchatStr + " 00:00:00");

            // Création d'un nouvel achat
            Achat achat = new Achat();
            achat.setDescriptionAchat(descriptionAchat);
            achat.setDateAchat(dateAchat);

            // Laboratoire ou fournisseur
            if (laboratoireId != null && laboratoireId != 0) {
                achat.setLaboratoire(laboratoireService.getLaboratoireById(laboratoireId).get());
            }
            if (fournisseurId != null && fournisseurId != 0) {
                achat.setFournisseur(fournisseurService.getFournisseurById(fournisseurId).get());
            }

            // Création des détails d'achat
            List<AchatDetails> achatDetailsList = new ArrayList<>();
            for (int i = 0; i < lotIdList.size(); i++) {
                AchatDetails achatDetails = new AchatDetails();
                achatDetails.setLot(lotService.getLotById(lotIdList.get(i)).get());
                achatDetails.setPrixAchatUnitaire(prixAchatUnitaireList.get(i));
                achatDetailsList.add(achatDetails);
            }

            // Enregistrement de l'achat et des détails
            achatService.createAchatWithDetails(achat, achatDetailsList);

            // Message de succès
            model.addAttribute("success", "Achat et ses détails enregistrés avec succès !");
            return "redirect:/achats";
        } catch (Exception e) {
            // En cas d'erreur, on renvoie toutes les valeurs au formulaire
            model.addAttribute("descriptionAchat", descriptionAchat);
            model.addAttribute("dateAchat", dateAchatStr);
            model.addAttribute("laboratoireId", laboratoireId);
            model.addAttribute("fournisseurId", fournisseurId);
            model.addAttribute("prixAchatUnitaire", prixAchatUnitaireList);
            model.addAttribute("lotId", lotIdList);
            model.addAttribute("error", "Erreur lors de l'enregistrement de l'achat : " + e.getMessage());
            model.addAttribute("laboratoires", laboratoireService.getAllLaboratoires());
            model.addAttribute("fournisseurs", fournisseurService.getAllFournisseurs());
            model.addAttribute("lots", lotService.getAllLots());
            return "achat/form";
        }
    }

    @GetMapping("/edit/{idAchat}")
    public String editAchatForm(@PathVariable("idAchat") Integer idAchat, Model model) {
        try {
            // Chargement de l'achat et de ses détails
            Achat achat = achatService.getAchatById(idAchat)
                    .orElseThrow(() -> new RuntimeException("Achat not found with id: " + idAchat));
            List<AchatDetails> achatDetails = achatService.getAchatDetails(idAchat);
            for (AchatDetails a : achatDetails) {
                System.out.println("lot : " +a.getLot().getNomLot() +" Prix Un "+a.getPrixAchatUnitaire() );
            }

            // Transformation des dates
            String dateAchatStr = achat.getDateAchat().toLocalDateTime().toLocalDate().toString();

            // Ajout des données au modèle
            model.addAttribute("idAchat", achat.getIdAchat());
            model.addAttribute("descriptionAchat", achat.getDescriptionAchat());
            model.addAttribute("dateAchat", dateAchatStr);
            model.addAttribute("laboratoireId", achat.getLaboratoire() != null ? achat.getLaboratoire().getIdLaboratoire() : null);
            model.addAttribute("fournisseurId", achat.getFournisseur() != null ? achat.getFournisseur().getIdFournisseur() : null);
            model.addAttribute("achatDetails", achatDetails);
            model.addAttribute("laboratoires", laboratoireService.getAllLaboratoires());
            model.addAttribute("fournisseurs", fournisseurService.getAllFournisseurs());
            model.addAttribute("lots", lotService.getAllLots());
            return "achat/form";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors du chargement de l'achat : " + e.getMessage());
            return "redirect:/achats";
        }
    }


    @PostMapping("/update/{idAchat}")
    public String updateAchat(@PathVariable("idAchat") Integer idAchat,
                            @RequestParam("descriptionAchat") String descriptionAchat,
                            @RequestParam("dateAchat") String dateAchatStr,
                            @RequestParam(value = "laboratoireId", required = false) Integer laboratoireId,
                            @RequestParam(value = "fournisseurId", required = false) Integer fournisseurId,
                            @RequestParam("prixAchatUnitaire") List<Double> prixAchatUnitaireList,
                            @RequestParam("lotId") List<Integer> lotIdList,
                            Model model) {

        try { 
            Timestamp dateAchat = Timestamp.valueOf(dateAchatStr + " 00:00:00");
            Achat achat = new Achat();
            achat.setIdAchat(idAchat);
            achat.setDescriptionAchat(descriptionAchat);
            achat.setDateAchat(dateAchat);
            if (laboratoireId != null && laboratoireId != 0) {
                achat.setLaboratoire((laboratoireService.getLaboratoireById(laboratoireId)).get());
                achat.setFournisseur(null);
            }
            if (fournisseurId != null && fournisseurId != 0) {
                achat.setFournisseur((fournisseurService.getFournisseurById(fournisseurId)).get());
                achat.setLaboratoire(null);
            }
            List<AchatDetails> updatedDetails = new ArrayList<>();
            for (int i = 0; i < lotIdList.size(); i++) {
                AchatDetails achatDetails = new AchatDetails();
                achatDetails.setLot(lotService.getLotById(lotIdList.get(i)).get());
                achatDetails.setPrixAchatUnitaire(prixAchatUnitaireList.get(i));
                updatedDetails.add(achatDetails);
            }
            achatService.updateAchatWithDetails(idAchat, achat, updatedDetails);
            model.addAttribute("success", "Achat et ses détails mis à jour avec succès !");
            return "redirect:/achats";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la mise à jour de l'achat : " + e.getMessage());
            return "achat/form"; 
        }
    }

    @GetMapping("/delete/{idAchat}")
    public String deleteAchat(@PathVariable("idAchat") Integer idAchat, Model model) {
        try {
            achatService.deleteAchatWithDetails(idAchat);

            model.addAttribute("success", "Achat supprimé avec succès !");
            return "redirect:/achats";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la suppression de l'achat : " + e.getMessage());
            return "redirect:/achats";  
        }
    }

    @GetMapping("/details/{idAchat}")
    public String detailsAchat(@PathVariable("idAchat") Integer idAchat, Model model) {
        try {
            Achat achat = achatService.getAchatById(idAchat)
                    .orElseThrow(() -> new RuntimeException("Achat not found with id: " + idAchat));
            List<AchatDetails> achatDetailsList = achatService.getAchatDetails(idAchat);
            model.addAttribute("achat", achat);
            model.addAttribute("achatDetails", achatDetailsList);
            return "achat/details";  
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la récupération des détails de l'achat : " + e.getMessage());
            return "redirect:/achats";  
        }
    }


}
