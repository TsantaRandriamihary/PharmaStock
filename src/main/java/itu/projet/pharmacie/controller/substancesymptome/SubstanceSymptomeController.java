package itu.projet.pharmacie.controller.substancesymptome;

import itu.projet.pharmacie.model.substance.substancesymptome.SubstanceSymptome;
import itu.projet.pharmacie.model.substance.substancesymptome.SubstanceSymptomeId;
import itu.projet.pharmacie.model.substance.Substance;
import itu.projet.pharmacie.model.symptome.Symptome;
import itu.projet.pharmacie.service.substance.SubstanceService;
import itu.projet.pharmacie.service.symptome.SymptomeService;
import itu.projet.pharmacie.service.symptome.SubstanceSymptomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/substancesymptome")
public class SubstanceSymptomeController {

    @Autowired
    private SubstanceSymptomeService substanceSymptomeService;

    @Autowired
    private SubstanceService substanceService;

    @Autowired
    private SymptomeService symptomeService;

    @GetMapping
    public String listSubstanceSymptomes(Model model) {
        List<SubstanceSymptome> substanceSymptomes = substanceSymptomeService.getAllSubstanceSymptomes();
        List<Substance> substances = substanceService.getAllSubstances();
        List<Symptome> symptomes = symptomeService.getAllSymptomes();
        
        Map<Integer, String> substanceMap = substances.stream()
            .collect(Collectors.toMap(Substance::getIdSubstance, Substance::getNomSubstance));
        Map<Integer, String> symptomeMap = symptomes.stream()
            .collect(Collectors.toMap(Symptome::getIdSymptome, Symptome::getNomSymptome));

        model.addAttribute("substanceSymptomes", substanceSymptomes);
        model.addAttribute("substanceMap", substanceMap);
        model.addAttribute("symptomeMap", symptomeMap);
        
        return "elements/substancesymptome/list";
    }

    @GetMapping("/add")
    public String addSubstanceSymptomeForm(Model model) {
        List<Substance> substances = substanceService.getAllSubstances();
        List<Symptome> symptomes = symptomeService.getAllSymptomes();

        model.addAttribute("substanceSymptome", new SubstanceSymptome());
        model.addAttribute("substances", substances);
        model.addAttribute("symptomes", symptomes);

        return "elements/substancesymptome/add";
    }

    @PostMapping("/save")
    public String saveSubstanceSymptome(@ModelAttribute SubstanceSymptome substanceSymptome, Model model) {
        try {
            System.out.println("idSubstance : " + substanceSymptome.getId().getIdSubstance() + 
                            " idSymptome : " + substanceSymptome.getId().getIdSymptome());
            
            substanceSymptomeService.createSubstanceSymptome(substanceSymptome);
            return "redirect:/substancesymptome";
        } catch (Exception e) {
            model.addAttribute("error", "Une erreur s'est produite lors de l'enregistrement : " + e.getMessage());
            List<Substance> substances = substanceService.getAllSubstances();
            List<Symptome> symptomes = symptomeService.getAllSymptomes();

            model.addAttribute("substances", substances);
            model.addAttribute("symptomes", symptomes);
            return "elements/substancesymptome/add";
        }
    }


    @GetMapping("/edit/{idSubstance}/{idSymptome}")
    public String editSubstanceSymptomeForm(@PathVariable Integer idSubstance, @PathVariable Integer idSymptome, Model model) {
        try {
            SubstanceSymptomeId id = new SubstanceSymptomeId(idSubstance, idSymptome);
            SubstanceSymptome substanceSymptome = substanceSymptomeService.getSubstanceSymptomeById(id)
                    .orElseThrow(() -> new RuntimeException("SubstanceSymptome non trouvé"));

            List<Substance> substances = substanceService.getAllSubstances();
            List<Symptome> symptomes = symptomeService.getAllSymptomes();

            model.addAttribute("substanceSymptome", substanceSymptome);
            model.addAttribute("substances", substances);
            model.addAttribute("symptomes", symptomes);

            return "elements/substancesymptome/add";

        } catch (Exception e) {
            model.addAttribute("error", "Une erreur s'est produite : " + e.getMessage());
            return "redirect:/substancesymptome";
        }
    }

    @PostMapping("/update/{idSubstance}/{idSymptome}")
    public String updateSubstanceSymptome(@PathVariable Integer idSubstance, @PathVariable Integer idSymptome,
                                          @ModelAttribute SubstanceSymptome updatedSubstanceSymptome, Model model) {
        try {
            SubstanceSymptomeId id = new SubstanceSymptomeId(idSubstance, idSymptome);
            substanceSymptomeService.updateSubstanceSymptome(id, updatedSubstanceSymptome);
            return "redirect:/substancesymptome";

        } catch (Exception e) {
            model.addAttribute("error", "Une erreur s'est produite lors de la mise à jour : " + e.getMessage());
            List<Substance> substances = substanceService.getAllSubstances();
            List<Symptome> symptomes = symptomeService.getAllSymptomes();

            model.addAttribute("substanceSymptome", updatedSubstanceSymptome);
            model.addAttribute("substances", substances);
            model.addAttribute("symptomes", symptomes);

            return "elements/substancesymptome/add";
        }
    }

    @GetMapping("/delete/{idSubstance}/{idSymptome}")
    public String deleteSubstanceSymptome(@PathVariable Integer idSubstance, @PathVariable Integer idSymptome, Model model) {
        try {
            SubstanceSymptomeId id = new SubstanceSymptomeId(idSubstance, idSymptome);
            substanceSymptomeService.deleteSubstanceSymptome(id);
            return "redirect:/substancesymptome";

        } catch (Exception e) {
            model.addAttribute("error", "Une erreur s'est produite lors de la suppression : " + e.getMessage());
            return "redirect:/substancesymptome";
        }
    }
}
