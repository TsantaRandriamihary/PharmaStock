package itu.projet.pharmacie.service.maladie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itu.projet.pharmacie.model.maladiesymptome.MaladieSymptome;
import itu.projet.pharmacie.model.maladiesymptome.MaladieSymptomeId;
import itu.projet.pharmacie.model.produit.substanceproduit.ProduitSubstance;
import itu.projet.pharmacie.repository.maladiesymptome.MaladieSymptomeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MaladieSymptomeService {

    @Autowired
    private MaladieSymptomeRepository maladieSymptomeRepository;

    
 
    public MaladieSymptome createMaladieSymptome(MaladieSymptome maladieSymptome) throws Exception {
        List<MaladieSymptome> ps = maladieSymptomeRepository.findByIdMaladie(maladieSymptome.getId().getIdMaladie());
        
        double somme = 0;
        for (MaladieSymptome p : ps) {
            if (p.getDegre() != null) {
                somme += p.getDegre();
            }
            
        }
        if (somme + maladieSymptome.getDegre() > 100) {
            throw new Exception("Le pourcentage d'effet des symptomes pour cette maladie "+maladieSymptome.getIdMaladie()+" est deja rempli (degre = 100)");
        }
        return maladieSymptomeRepository.save(maladieSymptome);
    }

    public Optional<MaladieSymptome> getMaladieSymptomeById(MaladieSymptomeId id) {
        return maladieSymptomeRepository.findById(id);
    }

    public List<MaladieSymptome> getAllMaladieSymptomes() {
        return maladieSymptomeRepository.findAll();
    }

    public MaladieSymptome updateMaladieSymptome(MaladieSymptomeId id, MaladieSymptome updatedMaladieSymptome) {
        return maladieSymptomeRepository.findById(id).map(existingMaladieSymptome -> {
            existingMaladieSymptome.setId(updatedMaladieSymptome.getId());
            return maladieSymptomeRepository.save(existingMaladieSymptome);
        }).orElseThrow(() -> new RuntimeException("MaladieSymptome not found with id: " + id));
    }

    public void deleteMaladieSymptome(MaladieSymptomeId id) {
        if (maladieSymptomeRepository.existsById(id)) {
            maladieSymptomeRepository.deleteById(id);
        } else {
            throw new RuntimeException("MaladieSymptome not found with id: " + id);
        }
    }

    
}
