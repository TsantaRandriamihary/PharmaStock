package itu.projet.pharmacie.service.personnage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import itu.projet.pharmacie.model.personnage.Fournisseur;
import itu.projet.pharmacie.repository.personnage.FournisseurRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FournisseurService {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    public Fournisseur createFournisseur(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }

    public Optional<Fournisseur> getFournisseurById(Integer id) {
        return fournisseurRepository.findById(id);
    }

    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurRepository.findAll();
    }

    public Fournisseur updateFournisseur(Integer id, Fournisseur updatedFournisseur) {
        return fournisseurRepository.findById(id).map(existingFournisseur -> {
            existingFournisseur.setNomFournisseur(updatedFournisseur.getNomFournisseur());
            existingFournisseur.setContactFournisseur(updatedFournisseur.getContactFournisseur());
            existingFournisseur.setAdresseFournisseur(updatedFournisseur.getAdresseFournisseur());
            return fournisseurRepository.save(existingFournisseur);
        }).orElseThrow(() -> new RuntimeException("Fournisseur not found with id: " + id));
    }

    public void deleteFournisseur(Integer id) {
        if (fournisseurRepository.existsById(id)) {
            fournisseurRepository.deleteById(id);
        } else {
            throw new RuntimeException("Fournisseur not found with id: " + id);
        }
    }
}
