package itu.projet.pharmacie.controller.vente;

import itu.projet.pharmacie.model.historiqueprix.HistoriquePrix;
import itu.projet.pharmacie.model.lot.Lot;
import itu.projet.pharmacie.model.symptome.Symptome;
import itu.projet.pharmacie.model.type.Forme;
import itu.projet.pharmacie.model.type.Trancheage;
import itu.projet.pharmacie.model.vente.Vente;
import itu.projet.pharmacie.model.vente.VenteDetails;
import itu.projet.pharmacie.repository.historiqueprix.HistoriquePrixRepository;
import itu.projet.pharmacie.repository.type.FormeRepository;
import itu.projet.pharmacie.repository.type.TrancheageRepository;
import itu.projet.pharmacie.service.vente.VenteService;
import itu.projet.pharmacie.service.vente.VendeurService;
import itu.projet.pharmacie.service.personnage.ClientService;
import itu.projet.pharmacie.service.produit.ProduitService;
import itu.projet.pharmacie.service.lot.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ventes")
public class VenteController {

    @Autowired
    private VenteService venteService;

    @Autowired
    private ClientService clientService;

    
    @Autowired
    private VendeurService vendeurService;

    @Autowired
    private LotService lotService;

    @Autowired
    private HistoriquePrixRepository historiquePrixRepository;

    @Autowired
    private FormeRepository formeRepository;

    @Autowired
    private ProduitService produitService;

    @Autowired
    private TrancheageRepository trancheageRepository;


    @GetMapping
    public String listVentes(
        @RequestParam(required = false) Integer idForme,
        @RequestParam(required = false) Integer idTrancheage,
        Model model) {
        List<Vente> ventes = venteService.filtrerVentes(idForme, idTrancheage);
        List<Forme> formes = formeRepository.findAll();
        List<Trancheage> tranches = trancheageRepository.findAll();

        model.addAttribute("formes", formes);
        model.addAttribute("tranches", tranches);

        model.addAttribute("idForme", idForme);
        model.addAttribute("idTrancheage", idTrancheage);
        
        model.addAttribute("ventes", ventes);
        return "vente/list";
    }

    @GetMapping("/add")
    public String addVenteForm(Model model) {
        model.addAttribute("vente", new Vente());
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("vendeur", vendeurService.getAllVendeurs());
        model.addAttribute("produits", produitService.getAllProduits());
        model.addAttribute("venteDetails", new VenteDetails());
        return "vente/form";
    }

    
    @PostMapping("/save")
    public String saveVente(
            @RequestParam("descriptionVente") String descriptionVente,
            @RequestParam("dateVente") String dateVenteStr,
            @RequestParam(value = "clientId", required = false) Integer clientId,
            @RequestParam(value = "vendeurId", required = false) Integer vendeurId,
            @RequestParam("produitId") List<Integer> produitIdList,
            @RequestParam("quantiteVendue") List<Integer> quantiteVendueList,
            Model model) {
        try {
            Timestamp dateVente = Timestamp.valueOf(dateVenteStr + " 00:00:00");
            Vente vente = new Vente();
            vente.setDescriptionVente(descriptionVente);
            vente.setDateVente(dateVente);
            if (clientId != null && clientId != 0) {
                vente.setClient(clientService.getClientById(clientId).get());
            }
            if (vendeurId != null && vendeurId != 0) {
                vente.setVendeur(vendeurService.getVendeurById(vendeurId).get());
            }
            List<VenteDetails> venteDetailsList = new ArrayList<>();
            for (int i = 0; i < produitIdList.size(); i++) {
                int produitId = produitIdList.get(i);
                int quantiteDemandee = quantiteVendueList.get(i);
                List<Lot> lots = produitService.getLotsForProduitWithQuantity(produitId, quantiteDemandee, dateVente);
                System.out.println("Quantite demandee finale : "+quantiteDemandee);
                for (Lot lot : lots) {
                    VenteDetails venteDetails = new VenteDetails();
                    venteDetails.setLot(lot);
                    int quantiteAPrelever = Math.min(quantiteDemandee, lot.getQuantiteLot());
                    System.out.println("Quantite a prelever : "+quantiteAPrelever);
                    quantiteDemandee -= quantiteAPrelever;
                    Optional<HistoriquePrix> historiquePrix = historiquePrixRepository.findLatestPriceBeforeDate(
                            lot.getProduit().getIdProduit(), dateVente
                    );
                    if (historiquePrix.isPresent()) {
                        venteDetails.setPrixVenteUnitaire(historiquePrix.get().getPrix());
                    } else {
                        
                        throw new Exception("Aucun prix défini pour le produit '" + lot.getProduit().getNomProduit() + "' à la date donnée.");
                    }
                    venteDetails.setQuantiteVendue(quantiteAPrelever);
                    venteDetailsList.add(venteDetails);
                    if (quantiteDemandee <= 0) break;
                }
                if (quantiteDemandee > 0) {
                    System.out.println("Quantite demandee finale : "+quantiteDemandee);
                    throw new Exception("Quantité insuffisante ou perimee pour le produit avec l'ID : " + produitId);
                }
            } 
            venteService.createVenteWithDetails(vente, venteDetailsList);

            model.addAttribute("success", "Vente enregistrée avec succès !");
            return "redirect:/ventes";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de l'enregistrement de la vente : " + e.getMessage());
            model.addAttribute("descriptionVente", descriptionVente);
            model.addAttribute("dateVente", dateVenteStr);
            model.addAttribute("clientId", clientId);
            model.addAttribute("produits", produitService.getAllProduits());
            model.addAttribute("clients", clientService.getAllClients());
            model.addAttribute("quantiteVendue", quantiteVendueList);
            e.printStackTrace();
            return "vente/form";
        }
    }


    @GetMapping("/edit/{idVente}")
    public String editVenteForm(@PathVariable("idVente") Integer idVente, Model model) {
        try {
            Vente vente = venteService.getVenteById(idVente)
                    .orElseThrow(() -> new RuntimeException("Vente not found with id: " + idVente));
            List<VenteDetails> venteDetails = venteService.getVenteDetails(idVente);
            String dateVenteStr = vente.getDateVente().toLocalDateTime().toLocalDate().toString();
            model.addAttribute("idVente", vente.getIdVente());
            model.addAttribute("descriptionVente", vente.getDescriptionVente());
            model.addAttribute("dateVente", dateVenteStr);
            model.addAttribute("clientId", vente.getClient() != null ? vente.getClient().getIdClient() : null);
            model.addAttribute("venteDetails", venteDetails);
            model.addAttribute("clients", clientService.getAllClients());
            model.addAttribute("produits", produitService.getAllProduits());
            return "vente/form";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors du chargement de la vente : " + e.getMessage());
            return "redirect:/ventes";
        }
    }

    @PostMapping("/update/{idVente}")
    public String updateVente(@PathVariable("idVente") Integer idVente,
                            @RequestParam("descriptionVente") String descriptionVente,
                            @RequestParam("dateVente") String dateVenteStr,
                            @RequestParam(value = "clientId", required = false) Integer clientId,
                            @RequestParam("quantiteVendue") List<Integer> quantiteVendue,
                            @RequestParam("produitId") List<Integer> produitIdList,
                            Model model) {
        List<VenteDetails> updatedDetails = new ArrayList<>();
        try {
            Timestamp dateVente = Timestamp.valueOf(dateVenteStr + " 00:00:00");
            Vente vente = new Vente();
            vente.setIdVente(idVente);
            vente.setDescriptionVente(descriptionVente);
            vente.setDateVente(dateVente);

            if (clientId != null && clientId != 0) {
                vente.setClient(clientService.getClientById(clientId).get());
            }

            for (int i = 0; i < produitIdList.size(); i++) {
                Integer produitId = produitIdList.get(i);
                int quantiteDemandee = quantiteVendue.get(i);
                List<Lot> lots = produitService.getLotsForProduitWithQuantity(produitId, quantiteDemandee, dateVente);

                for (Lot lot : lots) {
                    if (quantiteDemandee <= 0) break;

                    int quantiteUtilisee = Math.min(quantiteDemandee, lot.getQuantiteLot());
                    VenteDetails venteDetails = new VenteDetails();
                    venteDetails.setLot(lot);
                    Optional<HistoriquePrix> historiquePrix = historiquePrixRepository.findLatestPriceBeforeDate(
                            produitId, dateVente
                    );
                    if (historiquePrix.isPresent()) {
                        venteDetails.setPrixVenteUnitaire(historiquePrix.get().getPrix());
                    } else {
                        throw new Exception("Aucun prix défini pour le produit '" + lot.getProduit().getNomProduit() + "' à la date donnée.");
                    }
                    venteDetails.setQuantiteVendue(quantiteUtilisee);
                    updatedDetails.add(venteDetails);
                    quantiteDemandee -= quantiteUtilisee;
                }

                if (quantiteDemandee > 0) {
                    throw new Exception("Quantité insuffisante pour le produit ID: " + produitId);
                }
            }

            venteService.updateVenteWithDetails(idVente, vente, updatedDetails);
            model.addAttribute("success", "Vente et ses détails mis à jour avec succès !");
            return "redirect:/ventes";
        } catch (Exception e) {
            model.addAttribute("idVente", idVente);
            model.addAttribute("descriptionVente", descriptionVente);
            model.addAttribute("dateVente", dateVenteStr);
            model.addAttribute("clientId", clientId);
            model.addAttribute("produitId", produitIdList);
            model.addAttribute("error", "Erreur lors de l'enregistrement de la vente : " + e.getMessage());
            model.addAttribute("clients", clientService.getAllClients());
            model.addAttribute("produits", produitService.getAllProduits());
            model.addAttribute("quantiteVendue", quantiteVendue);
            model.addAttribute("venteDetails", updatedDetails);
            return "vente/form";
        }
    }


    @GetMapping("/delete/{idVente}")
    public String deleteVente(@PathVariable("idVente") Integer idVente, Model model) {
        try {
            venteService.deleteVenteWithDetails(idVente);
            model.addAttribute("success", "Vente supprimée avec succès !");
            return "redirect:/ventes";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la suppression de la vente : " + e.getMessage());
            return "redirect:/ventes";
        }
    }

    @GetMapping("/details/{idVente}")
    public String detailsVente(@PathVariable("idVente") Integer idVente, Model model) {
        try {
            Vente vente = venteService.getVenteById(idVente)
                    .orElseThrow(() -> new RuntimeException("Vente not found with id: " + idVente));
            List<VenteDetails> venteDetailsList = venteService.getVenteDetails(idVente);
            model.addAttribute("vente", vente);
            model.addAttribute("venteDetails", venteDetailsList);
            return "vente/details";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la récupération des détails de la vente : " + e.getMessage());
            return "redirect:/ventes";
        }
    }
}
