package itu.projet.pharmacie.service.produit;

import itu.projet.pharmacie.model.produit.symptomeproduit.ProduitSymptome;
import itu.projet.pharmacie.model.produit.symptomeproduit.ProduitSymptomeId;
import itu.projet.pharmacie.repository.produit.symptomeproduit.ProduitSymptomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitSymptomeService {

    @Autowired
    private ProduitSymptomeRepository produitSymptomeRepository;

    // Create
    public ProduitSymptome createProduitSymptome(ProduitSymptome produitSymptome) {
        return produitSymptomeRepository.save(produitSymptome);
    }

    // Read
    public Optional<ProduitSymptome> getProduitSymptomeById(ProduitSymptomeId id) {
        return produitSymptomeRepository.findById(id);
    }

    public List<ProduitSymptome> getAllProduitSymptomes() {
        return produitSymptomeRepository.findAll();
    }

    // Update
    public ProduitSymptome updateProduitSymptome(ProduitSymptome produitSymptome) {
        if (produitSymptomeRepository.existsById(produitSymptome.getId())) {
            return produitSymptomeRepository.save(produitSymptome);
        }
        return null;
    }

    // Delete
    public void deleteProduitSymptome(ProduitSymptomeId id) {
        if (produitSymptomeRepository.existsById(id)) {
            produitSymptomeRepository.deleteById(id);
        }
    }
}
