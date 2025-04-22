package itu.projet.pharmacie.service.vente;

import itu.projet.pharmacie.model.type.Commission;
import itu.projet.pharmacie.model.type.Genre;
import itu.projet.pharmacie.model.vente.Vendeur;
import itu.projet.pharmacie.model.vente.Vente;
import itu.projet.pharmacie.repository.commission.CommissionRepository;
import itu.projet.pharmacie.repository.vente.VendeurRepository;
import itu.projet.pharmacie.repository.vente.VenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendeurService {

    @Autowired
    private VendeurRepository vendeurRepository;

    @Autowired
    private VenteRepository venteRepository;

    @Autowired
    private CommissionRepository commissionRepository;

    /**
     * Crée un nouveau vendeur et le sauvegarde dans la base de données.
     *
     * @param vendeur Le vendeur à créer.
     * @return Le vendeur créé.
     */
    public Vendeur createVendeur(Vendeur vendeur) {
        return vendeurRepository.save(vendeur);
    }

    /**
     * Met à jour un vendeur existant.
     *
     * @param idVendeur L'identifiant du vendeur à mettre à jour.
     * @param updatedVendeur Les nouvelles informations du vendeur.
     * @return Le vendeur mis à jour.
     */
    @Transactional
    public Vendeur updateVendeur(Integer idVendeur, Vendeur updatedVendeur) {
        return vendeurRepository.findById(idVendeur).map(existingVendeur -> {
            existingVendeur.setNomVendeur(updatedVendeur.getNomVendeur());
            return vendeurRepository.save(existingVendeur);
        }).orElseThrow(() -> new RuntimeException("Vendeur not found with id: " + idVendeur));
    }

    /**
     * Supprime un vendeur ainsi que toutes ses ventes associées.
     *
     * @param idVendeur L'identifiant du vendeur à supprimer.
     */
  

    /**
     * Récupère un vendeur par son ID.
     *
     * @param id L'identifiant du vendeur.
     * @return Une instance optionnelle du vendeur.
     */
    public Optional<Vendeur> getVendeurById(Integer id) {
        return vendeurRepository.findById(id);
    }

    /**
     * Récupère tous les vendeurs.
     *
     * @return La liste des vendeurs.
     */
    public List<Vendeur> getAllVendeurs() {
        return vendeurRepository.findAll();
    }

    
    public List<Vendeur> getVendeurAndDateRange(Timestamp dateDebut, Timestamp dateFin) {
        List<Vendeur> listeVendeur=this.getAllVendeurs();
        Commission commissionObj = commissionRepository.findAll().get(0);
        
        
        for (Vendeur vendeur : listeVendeur) {
            vendeur.setTotalVendue(0.0);
            vendeur.setTotalCommission(0.0);
            List<Vente> listeVente =  venteRepository.findByVendeurIdVendeur(vendeur.getIdVendeur());
            if (dateDebut != null) {
                System.out.println(dateDebut.toString());
                listeVente = listeVente.stream()
                        .filter(vente -> vente.getDateVente().compareTo(dateDebut) >= 0)
                        .collect(Collectors.toList());
            }
            if (dateFin != null) {
                System.out.println(dateFin.toString());
                listeVente = listeVente.stream()
                        .filter(vente -> vente.getDateVente().compareTo(dateFin) <= 0)
                        .collect(Collectors.toList());
            }
            for (Vente v : listeVente) {
                vendeur.setTotalVendue(vendeur.getTotalVendue()+v.getMontantTotal());
            }
            if (vendeur.getTotalVendue() >= commissionObj.getMinChiffreAffaire()) {
                double commission = vendeur.getTotalVendue() * commissionObj.getPourcentCommission();
                vendeur.setTotalCommission(commission);
            }
        }
        return listeVendeur;
    }


    public Map<Genre, Map<String, Double>> getVendeurStatsByGenre(List<Vendeur> vendeurs) {
        Commission commissionObj = commissionRepository.findAll().get(0);
        Map<Genre, Map<String, Double>> genreStats = new HashMap<>();
        for (Vendeur vendeur : vendeurs) {
            if (vendeur.getGenre() != null) {
                Genre genre = vendeur.getGenre();
                if (!genreStats.containsKey(genre)) {
                    genreStats.put(genre, new HashMap<>());
                    genreStats.get(genre).put("montantTotal", 0.0);
                    genreStats.get(genre).put("montantCommission", 0.0);
                }
                double montantTotal = genreStats.get(genre).get("montantTotal") + vendeur.getTotalVendue();
                genreStats.get(genre).put("montantTotal", montantTotal);
                if (vendeur.getTotalVendue() >= commissionObj.getMinChiffreAffaire()) {
                    double commission = vendeur.getTotalVendue() * commissionObj.getPourcentCommission();
                    double montantCommission = genreStats.get(genre).get("montantCommission") + commission;
                    genreStats.get(genre).put("montantCommission", montantCommission);
                }
            }
        }
        return genreStats;
    }
}

