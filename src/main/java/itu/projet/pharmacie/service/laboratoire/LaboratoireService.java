package itu.projet.pharmacie.service.laboratoire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itu.projet.pharmacie.model.laboratoire.Laboratoire;
import itu.projet.pharmacie.model.produit.Produit;
import itu.projet.pharmacie.repository.laboratoire.LaboratoireRepository;
import itu.projet.pharmacie.repository.produit.ProduitRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LaboratoireService {

    @Autowired
    private LaboratoireRepository laboratoireRepository;

    @Autowired
    private ProduitRepository produitRepository;

    public Laboratoire createLaboratoire(Laboratoire laboratoire) {
        return laboratoireRepository.save(laboratoire);
    }

    public Optional<Laboratoire> getLaboratoireById(Integer id) {
        return laboratoireRepository.findById(id);
    }

    public List<Laboratoire> getAllLaboratoires() {
        return laboratoireRepository.findAll();
    }

    public Laboratoire updateLaboratoire(Integer id, Laboratoire updatedLaboratoire) {
        return laboratoireRepository.findById(id).map(existingLaboratoire -> {
            existingLaboratoire.setNomLaboratoire(updatedLaboratoire.getNomLaboratoire());
            existingLaboratoire.setContactLaboratoire(updatedLaboratoire.getContactLaboratoire());
            existingLaboratoire.setAdresseLaboratoire(updatedLaboratoire.getAdresseLaboratoire());
            return laboratoireRepository.save(existingLaboratoire);
        }).orElseThrow(() -> new RuntimeException("Laboratoire not found with id: " + id));
    }

    public void deleteLaboratoire(Integer id) {
        if (laboratoireRepository.existsById(id)) {
            laboratoireRepository.deleteById(id);
        } else {
            throw new RuntimeException("Laboratoire not found with id: " + id);
        }
    }

    public List<Produit> getProduits(Integer idLaboratoire) {
        return laboratoireRepository.findById(idLaboratoire)
                .map(produitRepository::findByLaboratoire)
                .orElseThrow(() -> new RuntimeException("Laboratoire not found with id: " + idLaboratoire));
    }
}
