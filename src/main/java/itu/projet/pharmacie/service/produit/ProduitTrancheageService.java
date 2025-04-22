package itu.projet.pharmacie.service.produit;

import itu.projet.pharmacie.model.produit.trancheageproduit.ProduitTrancheage;
import itu.projet.pharmacie.model.produit.trancheageproduit.ProduitTrancheageId;
import itu.projet.pharmacie.repository.produit.trancheageproduit.ProduitTrancheageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitTrancheageService {

    @Autowired
    private ProduitTrancheageRepository produitTrancheageRepository;

    // Create
    public ProduitTrancheage createProduitTrancheage(ProduitTrancheage produitTrancheage) {
        return produitTrancheageRepository.save(produitTrancheage);
    }

    // Read
    public Optional<ProduitTrancheage> getProduitTrancheageById(ProduitTrancheageId id) {
        return produitTrancheageRepository.findById(id);
    }

    public List<ProduitTrancheage> getAllProduitTrancheages() {
        return produitTrancheageRepository.findAll();
    }

    public ProduitTrancheage updateProduitTrancheage(ProduitTrancheage produitTrancheage) {
        if (produitTrancheageRepository.existsById(produitTrancheage.getId())) {
            return produitTrancheageRepository.save(produitTrancheage);
        }
        return null;
    }

    public void deleteProduitTrancheage(ProduitTrancheageId id) {
        if (produitTrancheageRepository.existsById(id)) {
            produitTrancheageRepository.deleteById(id);
        }
    }
}
