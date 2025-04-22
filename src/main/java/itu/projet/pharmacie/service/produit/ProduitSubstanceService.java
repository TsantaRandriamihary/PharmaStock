package itu.projet.pharmacie.service.produit;

import itu.projet.pharmacie.model.produit.substanceproduit.ProduitSubstance;
import itu.projet.pharmacie.model.produit.substanceproduit.ProduitSubstanceId;
import itu.projet.pharmacie.repository.produit.substanceproduit.ProduitSubstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitSubstanceService {

    @Autowired
    private ProduitSubstanceRepository produitSubstanceRepository;

    // Create
    public ProduitSubstance createProduitSubstance(ProduitSubstance produitSubstance) throws Exception {
        List<ProduitSubstance> ps = produitSubstanceRepository.findByIdProduit(produitSubstance.getIdProduit());
        double somme = 0;
        for (ProduitSubstance p : ps) {
            somme += p.getDegre();
        }
        if (somme + produitSubstance.getDegre() > 100) {
            throw new Exception("Le pourcentage d'effet des substances pour ce produit "+produitSubstance.getIdProduit()+" est deja rempli (degre = 100)");
        }
        return produitSubstanceRepository.save(produitSubstance);
    }

    // Read
    public Optional<ProduitSubstance> getProduitSubstanceById(ProduitSubstanceId id) {
        return produitSubstanceRepository.findById(id);
    }

    public List<ProduitSubstance> getAllProduitSubstances() {
        return produitSubstanceRepository.findAll();
    }

    // Update
    public ProduitSubstance updateProduitSubstance(ProduitSubstance produitSubstance) {
        if (produitSubstanceRepository.existsById(produitSubstance.getId())) {
            return produitSubstanceRepository.save(produitSubstance);
        }
        return null;
    }

    // Delete
    public void deleteProduitSubstance(ProduitSubstanceId id) {
        if (produitSubstanceRepository.existsById(id)) {
            produitSubstanceRepository.deleteById(id);
        }
    }
}
