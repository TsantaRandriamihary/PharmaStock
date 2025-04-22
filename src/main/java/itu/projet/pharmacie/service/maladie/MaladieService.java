package itu.projet.pharmacie.service.maladie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import itu.projet.pharmacie.model.maladie.Maladie;
import itu.projet.pharmacie.model.maladiesymptome.MaladieSymptome;
import itu.projet.pharmacie.repository.maladie.MaladieRepository;
import itu.projet.pharmacie.repository.maladiesymptome.MaladieSymptomeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaladieService {

    @Autowired
    private MaladieRepository maladieRepository;

    @Autowired
    private MaladieSymptomeRepository maladieSymptomeRepository;


    public Maladie createMaladie(Maladie maladie) {
        return maladieRepository.save(maladie);
    }

    public Optional<Maladie> getMaladieById(Integer id) {
        return maladieRepository.findById(id);
    }

    public List<Maladie> getAllMaladies() {
        return maladieRepository.findAll();
    }

    public Maladie updateMaladie(Integer id, Maladie updatedMaladie) {
        return maladieRepository.findById(id).map(existingMaladie -> {
            existingMaladie.setNomMaladie(updatedMaladie.getNomMaladie());
            existingMaladie.setCodeCim(updatedMaladie.getCodeCim());
            existingMaladie.setAgentpathogene(updatedMaladie.getAgentpathogene());
            existingMaladie.setDomaine(updatedMaladie.getDomaine());
            return maladieRepository.save(existingMaladie);
        }).orElseThrow(() -> new RuntimeException("Maladie not found with id: " + id));
    }

    public void deleteMaladie(Integer id) {
        if (maladieRepository.existsById(id)) {
            maladieRepository.deleteById(id);
        } else {
            throw new RuntimeException("Maladie not found with id: " + id);
        }
    }

    public List<MaladieSymptome> getMaladieSymptomes(Integer idMaladie) {
        try {
            return maladieSymptomeRepository.findByIdMaladie(idMaladie);
        } catch (Exception e) {
            throw new RuntimeException("Maladie not found with id: " + idMaladie);
        }        
    }

    public List<Maladie> filterMaladies(String nom, Integer idDomaine, Integer idSymptome) {
        List<Maladie> maladies = maladieRepository.findAll();
        if (nom != null && !nom.isEmpty()) {
            maladies = maladies.stream()
                    .filter(m -> m.getNomMaladie().toLowerCase().contains(nom.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (idDomaine != null) {
            maladies = maladies.stream()
                    .filter(m -> m.getDomaine() != null && m.getDomaine().getIdDomaine().equals(idDomaine))
                    .collect(Collectors.toList());
        }
        if (idSymptome != null) {
            List<Integer> maladiesAvecSymptome = maladieSymptomeRepository.findByIdSymptome(idSymptome)
                    .stream()
                    .map(maladieSymptome -> maladieSymptome.getIdMaladie())
                    .collect(Collectors.toList());

            maladies = maladies.stream()
                    .filter(m -> maladiesAvecSymptome.contains(m.getIdMaladie()))
                    .collect(Collectors.toList());
        }
        return maladies;
    }
}
