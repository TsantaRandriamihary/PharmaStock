package itu.projet.pharmacie.service.produit;

import itu.projet.pharmacie.model.historiqueprix.HistoriquePrix;
import itu.projet.pharmacie.model.lot.Lot;
import itu.projet.pharmacie.model.produit.Produit;
import itu.projet.pharmacie.dto.ProduitDetailsDTO;
import itu.projet.pharmacie.model.produit.trancheageproduit.ProduitTrancheage;
import itu.projet.pharmacie.model.substance.Substance;
import itu.projet.pharmacie.model.symptome.Symptome;
import itu.projet.pharmacie.model.type.*;
import itu.projet.pharmacie.model.produit.contreindication.ContreIndication;
import itu.projet.pharmacie.model.produit.genreproduit.ProduitGenre;
import itu.projet.pharmacie.model.produit.substanceproduit.ProduitSubstance;
import itu.projet.pharmacie.model.produit.symptomeproduit.ProduitSymptome;
import itu.projet.pharmacie.repository.historiqueprix.HistoriquePrixRepository;
import itu.projet.pharmacie.repository.lot.LotRepository;
import itu.projet.pharmacie.repository.mvtstock.MouvementStockDetailsRepository;
import itu.projet.pharmacie.repository.produit.ProduitRepository;
import itu.projet.pharmacie.repository.produit.contreindication.ContreIndicationRepository;
import itu.projet.pharmacie.repository.produit.genreproduit.ProduitGenreRepository;
import itu.projet.pharmacie.repository.produit.substanceproduit.ProduitSubstanceRepository;
import itu.projet.pharmacie.repository.produit.symptomeproduit.ProduitSymptomeRepository;
import itu.projet.pharmacie.repository.produit.trancheageproduit.ProduitTrancheageRepository;
import itu.projet.pharmacie.repository.substance.SubstanceRepository;
import itu.projet.pharmacie.repository.symptome.SymptomeRepository;
import itu.projet.pharmacie.repository.type.GenreRepository;
import itu.projet.pharmacie.repository.type.TrancheageRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;
    
    @Autowired
    private ProduitTrancheageRepository produitTrancheageRepository;
    
    @Autowired
    private ContreIndicationRepository contreIndicationRepository;
    
    @Autowired
    private ProduitGenreRepository produitGenreRepository;
    
    @Autowired
    private ProduitSubstanceRepository produitSubstanceRepository;
    
    @Autowired
    private ProduitSymptomeRepository produitSymptomeRepository;

    @Autowired
    private TrancheageRepository trancheageRepository;
    
    
    @Autowired
    private GenreRepository genreRepository;
    
    @Autowired
    private SubstanceRepository substanceRepository;
    
    @Autowired
    private SymptomeRepository symptomeRepository;

    @Autowired
    private LotRepository lotRepository;

    @Autowired
    private MouvementStockDetailsRepository mouvementStockDetailsRepository;


    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Optional<Produit> getProduitById(Integer id) {
        return produitRepository.findById(id);
    }

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Produit updateProduit(Produit produit) {
        if (produitRepository.existsById(produit.getIdProduit())) {
            return produitRepository.save(produit);
        }
        return null;
    }

    public void deleteProduit(Integer id) {
        if (produitRepository.existsById(id)) {
            historiquePrixRepository.deleteAllByProduitIdProduit(id);
            produitRepository.deleteById(id);
        } else {
            throw new RuntimeException("Produit not found with id: " + id);
        }
    }

    public ProduitDetailsDTO getDetails(Integer produitId) {
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new EntityNotFoundException("Produit introuvable avec l'ID : " + produitId));

        List<Trancheage> allTrancheages = trancheageRepository.findAll();
        List<Symptome> allSymptomes = symptomeRepository.findAll();
        List<Genre> allGenres = genreRepository.findAll();
        List<Substance> allSubstances = substanceRepository.findAll();

        // Tranche d'âge
        List<ProduitTrancheage> produitTrancheages = produitTrancheageRepository.findByIdProduit(produitId);
        Map<Integer, String> trancheageMap = allTrancheages.stream()
                .filter(trancheage -> produitTrancheages.stream()
                        .anyMatch(pta -> pta.getIdTrancheage().equals(trancheage.getIdTrancheage())))
                .collect(Collectors.toMap(
                        Trancheage::getIdTrancheage,
                        Trancheage::getNomTranche
                ));

        // Contre-indications
        List<ContreIndication> contreIndications = contreIndicationRepository.findByIdProduit(produitId);
        Map<Integer, String> contreIndicationMap = allSymptomes.stream()
                .filter(symptome -> contreIndications.stream()
                        .anyMatch(ci -> ci.getIdSymptome().equals(symptome.getIdSymptome())))
                .collect(Collectors.toMap(
                        Symptome::getIdSymptome,
                        Symptome::getNomSymptome
                ));

        // Genres
        List<ProduitGenre> produitGenres = produitGenreRepository.findByIdProduit(produitId);
        Map<Integer, String> genreMap = allGenres.stream()
                .filter(genre -> produitGenres.stream()
                        .anyMatch(pg -> pg.getIdGenre().equals(genre.getIdGenre())))
                .collect(Collectors.toMap(
                        Genre::getIdGenre,
                        Genre::getNomGenre
                ));

        // Substances
        List<ProduitSubstance> produitSubstances = produitSubstanceRepository.findByIdProduit(produitId);
        Map<Integer, String> substanceMap = allSubstances.stream()
                .filter(substance -> produitSubstances.stream()
                        .anyMatch(ps -> ps.getIdSubstance().equals(substance.getIdSubstance())))
                .collect(Collectors.toMap(
                        Substance::getIdSubstance,
                        Substance::getNomSubstance
                ));

        // Symptômes
        List<ProduitSymptome> produitSymptomes = produitSymptomeRepository.findByIdProduit(produitId);
        Map<Integer, String> symptomeMap = allSymptomes.stream()
                .filter(symptome -> produitSymptomes.stream()
                        .anyMatch(ps -> ps.getIdSymptome().equals(symptome.getIdSymptome())))
                .collect(Collectors.toMap(
                        Symptome::getIdSymptome,
                        Symptome::getNomSymptome
                ));

        ProduitDetailsDTO produitDetailsDTO = new ProduitDetailsDTO();
        produitDetailsDTO.setProduit(produit);
        produitDetailsDTO.setTrancheages(produitTrancheages);
        produitDetailsDTO.setContreIndications(contreIndications);
        produitDetailsDTO.setGenres(produitGenres);
        produitDetailsDTO.setSubstances(produitSubstances);
        produitDetailsDTO.setSymptomes(produitSymptomes);

        produitDetailsDTO.setTrancheageMap(trancheageMap);
        produitDetailsDTO.setContreIndicationMap(contreIndicationMap);
        produitDetailsDTO.setGenreMap(genreMap);
        produitDetailsDTO.setSubstanceMap(substanceMap);
        produitDetailsDTO.setSymptomeMap(symptomeMap);
        return produitDetailsDTO;
    }

    


    @Autowired
    private HistoriquePrixRepository historiquePrixRepository;

    public Produit insertProduitWithDate(Produit produit, Timestamp date) {
        Produit savedProduit = produitRepository.save(produit);

        HistoriquePrix historiquePrix = new HistoriquePrix();
        historiquePrix.setProduit(savedProduit);
        historiquePrix.setPrix(produit.getPrix());
        historiquePrix.setDateChangement(date);

        historiquePrixRepository.save(historiquePrix);

        return savedProduit;
    }

    public Produit updateProduitWithDate(Produit updatedProduit, Timestamp date) {
        Optional<Produit> existingProduitOpt = produitRepository.findById(updatedProduit.getIdProduit());

        if (existingProduitOpt.isPresent()) {
            Produit existingProduit = existingProduitOpt.get();

            if (!existingProduit.getPrix().equals(updatedProduit.getPrix())) {
                HistoriquePrix historiquePrix = new HistoriquePrix();
                historiquePrix.setProduit(existingProduit);
                historiquePrix.setPrix(updatedProduit.getPrix());
                historiquePrix.setDateChangement(date);

                historiquePrixRepository.save(historiquePrix);
            }

            return produitRepository.save(updatedProduit);
        } else {
            throw new RuntimeException("Produit not found with id: " + updatedProduit.getIdProduit());
        }
    }




    public List<Produit> filterProduits(String nom, Integer idType, Integer idClasse, Integer idForme,
                                        Integer idLaboratoire, Integer idDomaine, Integer idTrancheage,
                                        Integer idGenre, Double prixMin, Double prixMax) {
        List<Produit> produits = produitRepository.findAll();
        if (nom != null && !nom.isEmpty()) {
            produits = produits.stream()
                    .filter(p -> p.getNomProduit().toLowerCase().contains(nom.toLowerCase()))
                    .toList();
        }
        if (idType != null) {
            produits = produits.stream()
                    .filter(p -> p.getTypeproduit() != null && p.getTypeproduit().getIdTypeproduit().equals(idType))
                    .toList();
        }
        if (idClasse != null) {
            produits = produits.stream()
                    .filter(p -> p.getClasse() != null && p.getClasse().getIdClasse().equals(idClasse))
                    .toList();
        }
        if (idForme != null) {
            produits = produits.stream()
                    .filter(p -> p.getForme() != null && p.getForme().getIdForme().equals(idForme))
                    .toList();
        }
        if (idLaboratoire != null) {
            produits = produits.stream()
                    .filter(p -> p.getLaboratoire() != null && p.getLaboratoire().getIdLaboratoire().equals(idLaboratoire))
                    .toList();
        }
        if (idDomaine != null) {
            produits = produits.stream()
                    .filter(p -> p.getDomaine() != null && p.getDomaine().getIdDomaine().equals(idDomaine))
                    .toList();
        }
        if (idTrancheage != null) {
            List<Integer> produitsAvecTranche = produitTrancheageRepository.findByIdTrancheage(idTrancheage)
                    .stream()
                    .map(ProduitTrancheage::getIdProduit)
                    .toList();

            produits = produits.stream()
                    .filter(p -> produitsAvecTranche.contains(p.getIdProduit()))
                    .toList();
        }
        if (idGenre != null) {
            List<Integer> produitsAvecGenre = produitGenreRepository.findByIdGenre(idGenre)
                    .stream()
                    .map(ProduitGenre::getIdProduit)
                    .toList();

            produits = produits.stream()
                    .filter(p -> produitsAvecGenre.contains(p.getIdProduit()))
                    .toList();
        }
        if (prixMin != null) {
            produits = produits.stream()
                    .filter(p -> p.getPrix() != null && p.getPrix() >= prixMin)
                    .toList();
        }
        if (prixMax != null) {
            produits = produits.stream()
                    .filter(p -> p.getPrix() != null && p.getPrix() <= prixMax)
                    .toList();
        }
        return produits;
    }


    public Lot getProchainLotDisponible(Integer idProduit, Timestamp date) {
        List<Lot> lots = lotRepository.findByProduitIdProduitAndDatePeremptionGreaterThanOrderByDatePeremptionAsc(idProduit, date);

        for (Lot lot : lots) {
            Integer totalEntree = mouvementStockDetailsRepository.sumEntreeByLot(lot.getIdLot());
            Integer totalSortie = mouvementStockDetailsRepository.sumSortieByLot(lot.getIdLot());

            int quantiteDisponible = (totalEntree != null ? totalEntree : 0) - (totalSortie != null ? totalSortie : 0);

            if (quantiteDisponible > 0) {
                return lot;
            }
        }
        return null;
    }

    public List<Lot> getLotsForProduitWithQuantity(Integer produitId, int quantiteDemandee, Timestamp dateVente) {
        List<Lot> lots = lotRepository.findByProduitIdProduitAndDatePeremptionGreaterThanOrderByDatePeremptionAsc(produitId, dateVente);
        List<Lot> selectedLots = new ArrayList<>();

        for (Lot lot : lots) {
            int quantiteDisponible = mouvementStockDetailsRepository.sumEntreeByLot(lot.getIdLot()) - mouvementStockDetailsRepository.sumSortieByLot(lot.getIdLot());
            if (quantiteDisponible > 0) {
                selectedLots.add(lot);
                quantiteDemandee -= quantiteDisponible;
                lot.setQuantiteLot(quantiteDisponible);
                if (quantiteDemandee <= 0) break;
            }
        }

        if (quantiteDemandee > 0) {
            throw new RuntimeException("Quantité insuffisante pour le produit avec l'ID : " + produitId);
        }

        return selectedLots;
    }
    
}
