package itu.projet.pharmacie.service.produit;

import itu.projet.pharmacie.model.produit.genreproduit.ProduitGenre;
import itu.projet.pharmacie.model.produit.genreproduit.ProduitGenreId;
import itu.projet.pharmacie.repository.produit.genreproduit.ProduitGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitGenreService {

    @Autowired
    private ProduitGenreRepository produitGenreRepository;

    // Create
    public ProduitGenre createProduitGenre(ProduitGenre produitGenre) {
        return produitGenreRepository.save(produitGenre);
    }

    // Read
    public Optional<ProduitGenre> getProduitGenreById(ProduitGenreId id) {
        return produitGenreRepository.findById(id);
    }

    public List<ProduitGenre> getAllProduitGenres() {
        return produitGenreRepository.findAll();
    }

    // Update
    public ProduitGenre updateProduitGenre(ProduitGenre produitGenre) {
        if (produitGenreRepository.existsById(produitGenre.getId())) {
            return produitGenreRepository.save(produitGenre);
        }
        return null;
    }

    // Delete
    public void deleteProduitGenre(ProduitGenreId id) {
        if (produitGenreRepository.existsById(id)) {
            produitGenreRepository.deleteById(id);
        }
    }
}
