package itu.projet.pharmacie.controller.mvtstock;

import itu.projet.pharmacie.model.lot.Lot;
import itu.projet.pharmacie.model.mvtstock.MouvementStock;
import itu.projet.pharmacie.model.mvtstock.MouvementStockDetails;
import itu.projet.pharmacie.repository.mvtstock.MouvementStockDetailsRepository;
import itu.projet.pharmacie.service.lot.LotService;
import itu.projet.pharmacie.service.mvtstock.MouvementStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mouvement-stock")
public class MouvementStockController {

    @Autowired
    private MouvementStockService mouvementStockService;

    @Autowired
    private LotService lotService;

    @Autowired
    private MouvementStockDetailsRepository mouvementStockDetailsRepository;


    @GetMapping
    public String listMouvementsStock(Model model) {
        List<MouvementStock> mouvementsStock = mouvementStockService.getAllMouvementStock();
        model.addAttribute("mouvementsStock", mouvementsStock);
        return "mouvementstock/list";
    }

    @GetMapping("/add")
    public String addMouvementStockForm(Model model) {
        model.addAttribute("mouvementStock", new MouvementStock());
        model.addAttribute("lots", lotService.getAllLots());
        model.addAttribute("mouvementStockDetails", new MouvementStockDetails());
        return "mouvementstock/form";
    }

    @PostMapping("/save")
    public String saveMouvementStock(
            @RequestParam("dateMouvement") String dateMouvementStr,
            @RequestParam("lotId") List<Integer> lotIdList,
            @RequestParam("entree") List<Integer> entreeList,
            @RequestParam("sortie") List<Integer> sortieList,
            Model model) {

        try {
            Timestamp dateMouvement = Timestamp.valueOf(dateMouvementStr + " 00:00:00");
            for (int i = 0; i < lotIdList.size(); i++) {
                Integer lotId = lotIdList.get(i);
                Integer sortie = sortieList.get(i);

                Lot lot = lotService.getLotById(lotId).orElseThrow(() -> 
                    new RuntimeException("Lot non trouvé avec l'id : " + lotId));
                int quantiteDisponible = mouvementStockDetailsRepository.sumEntreeByLot(lot.getIdLot()) - mouvementStockDetailsRepository.sumSortieByLot(lot.getIdLot());
                if (sortie > quantiteDisponible) {
                    model.addAttribute("error", 
                        "La quantité demandée pour le lot " + lotId + 
                        " dépasse la quantité disponible (" + quantiteDisponible+ ").");
                    model.addAttribute("lots", lotService.getAllLots());
                    model.addAttribute("dateMouvement", dateMouvementStr);
                    model.addAttribute("lotId", lotIdList);
                    model.addAttribute("entree", entreeList);
                    model.addAttribute("sortie", sortieList);
                    return "mouvementstock/form";
                }
            }
            MouvementStock mouvementStock = new MouvementStock();
            mouvementStock.setDateMouvement(dateMouvement);
            List<MouvementStockDetails> mouvementStockDetailsList = new ArrayList<>();
            for (int i = 0; i < lotIdList.size(); i++) {
                MouvementStockDetails mouvementStockDetails = new MouvementStockDetails();
                Lot lot = lotService.getLotById(lotIdList.get(i)).get();
                mouvementStockDetails.setLot(lot);
                mouvementStockDetails.setProduit(lot.getProduit());
                mouvementStockDetails.setEntree(entreeList.get(i));
                mouvementStockDetails.setSortie(sortieList.get(i));
                mouvementStockDetailsList.add(mouvementStockDetails);
            }
            mouvementStockService.addMouvementStockWithDetails(mouvementStock, mouvementStockDetailsList);

            model.addAttribute("success", "Mouvement de stock enregistré avec succès !");
            return "redirect:/mouvement-stock";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de l'enregistrement du mouvement de stock : " + e.getMessage());
            model.addAttribute("lots", lotService.getAllLots());
            model.addAttribute("dateMouvement", dateMouvementStr);
            model.addAttribute("lotId", lotIdList);
            model.addAttribute("entree", entreeList);
            model.addAttribute("sortie", sortieList);
            return "mouvementstock/form";
        }
    }

    @GetMapping("/edit/{idMouvement}")
    public String editMouvementStockForm(@PathVariable("idMouvement") Integer idMouvement, Model model) {
        try {
            MouvementStock mouvementStock = mouvementStockService.getMouvementStockById(idMouvement)
                    .orElseThrow(() -> new RuntimeException("MouvementStock non trouvé avec l'id : " + idMouvement));
            
            List<MouvementStockDetails> mouvementStockDetails = mouvementStockService.getMouvementStockDetails(idMouvement);

            String dateMouvementStr = mouvementStock.getDateMouvement().toLocalDateTime().toLocalDate().toString();
            List<Integer> lotIdList = new ArrayList<>();
            List<Integer> entreeList = new ArrayList<>();
            List<Integer> sortieList = new ArrayList<>();

            for (MouvementStockDetails detail : mouvementStockDetails) {
                lotIdList.add(detail.getLot().getIdLot());
                entreeList.add(detail.getEntree());
                sortieList.add(detail.getSortie());
            }
            model.addAttribute("idMouvement", mouvementStock.getIdMouvement());
            model.addAttribute("dateMouvement", dateMouvementStr);
            model.addAttribute("lotId", lotIdList);
            model.addAttribute("entree", entreeList);
            model.addAttribute("sortie", sortieList);
            model.addAttribute("lots", lotService.getAllLots());
            
            return "mouvementstock/form";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors du chargement du mouvement de stock : " + e.getMessage());
            return "redirect:/mouvement-stock";
        }
    }


    @PostMapping("/update/{idMouvement}")
    public String updateMouvementStock(
            @PathVariable("idMouvement") Integer idMouvement,
            @RequestParam("dateMouvement") String dateMouvementStr,
            @RequestParam("lotId") List<Integer> lotIdList,
            @RequestParam("entree") List<Integer> entreeList,
            @RequestParam("sortie") List<Integer> sortieList,
            Model model) {

        try {
            Timestamp dateMouvement = Timestamp.valueOf(dateMouvementStr + " 00:00:00");

            // Validation des quantités disponibles
            for (int i = 0; i < lotIdList.size(); i++) {
                Integer lotId = lotIdList.get(i);
                Integer sortie = sortieList.get(i);

                Lot lot = lotService.getLotById(lotId).orElseThrow(() -> 
                    new RuntimeException("Lot non trouvé avec l'id : " + lotId));
                int quantiteDisponible = mouvementStockDetailsRepository.sumEntreeByLot(lot.getIdLot()) - mouvementStockDetailsRepository.sumSortieByLot(lot.getIdLot());
                if (sortie > quantiteDisponible) {
                    model.addAttribute("error", 
                        "La quantité demandée pour le lot " + lotId + 
                        " dépasse la quantité disponible (" + quantiteDisponible + ").");
                    model.addAttribute("lots", lotService.getAllLots());
                    model.addAttribute("dateMouvement", dateMouvementStr);
                    model.addAttribute("lotId", lotIdList);
                    model.addAttribute("entree", entreeList);
                    model.addAttribute("sortie", sortieList);
                    return "mouvementstock/form";
                }
            }

            MouvementStock mouvementStock = new MouvementStock();
            mouvementStock.setIdMouvement(idMouvement);
            mouvementStock.setDateMouvement(dateMouvement);

            List<MouvementStockDetails> updatedDetails = new ArrayList<>();
            for (int i = 0; i < lotIdList.size(); i++) {
                MouvementStockDetails mouvementStockDetails = new MouvementStockDetails();
                Lot lot = lotService.getLotById(lotIdList.get(i)).get();
                mouvementStockDetails.setLot(lot);
                mouvementStockDetails.setProduit(lot.getProduit());
                mouvementStockDetails.setEntree(entreeList.get(i));
                mouvementStockDetails.setSortie(sortieList.get(i));
                updatedDetails.add(mouvementStockDetails);
            }

            mouvementStockService.updateMouvementStockWithDetails(idMouvement, mouvementStock, updatedDetails);

            model.addAttribute("success", "Mouvement de stock mis à jour avec succès !");
            return "redirect:/mouvement-stock";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la mise à jour du mouvement de stock : " + e.getMessage());
            model.addAttribute("idMouvement", idMouvement);
            model.addAttribute("lots", lotService.getAllLots());
            model.addAttribute("dateMouvement", dateMouvementStr);
            model.addAttribute("lotId", lotIdList);
            model.addAttribute("entree", entreeList);
            model.addAttribute("sortie", sortieList);
            return "mouvementstock/form";
        }
    }

    @GetMapping("/delete/{idMouvement}")
    public String deleteMouvementStock(@PathVariable("idMouvement") Integer idMouvement, Model model) {
        try {
            mouvementStockService.deleteMouvementStockWithDetails(idMouvement);
            model.addAttribute("success", "Mouvement de stock supprimé avec succès !");
            return "redirect:/mouvement-stock";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la suppression du mouvement de stock : " + e.getMessage());
            return "redirect:/mouvement-stock";
        }
    }

    @GetMapping("/details/{idMouvement}")
    public String detailsMouvementStock(@PathVariable("idMouvement") Integer idMouvement, Model model) {
        try {
            MouvementStock mouvementStock = mouvementStockService.getMouvementStockById(idMouvement)
                    .orElseThrow(() -> new RuntimeException("MouvementStock non trouvé avec l'id : " + idMouvement));
            List<MouvementStockDetails> mouvementStockDetailsList = mouvementStockService.getMouvementStockDetails(idMouvement);
            model.addAttribute("mouvementStock", mouvementStock);
            model.addAttribute("mouvementStockDetails", mouvementStockDetailsList);
            return "mouvementstock/details";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la récupération des détails du mouvement de stock : " + e.getMessage());
            return "redirect:/mouvement-stock";
        }
    }
}
