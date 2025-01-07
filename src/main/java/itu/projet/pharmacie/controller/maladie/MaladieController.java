package itu.projet.pharmacie.controller.maladie;

import itu.projet.pharmacie.model.maladie.Maladie;
import itu.projet.pharmacie.model.maladiesymptome.MaladieSymptome;
import itu.projet.pharmacie.model.symptome.Symptome;
import itu.projet.pharmacie.service.maladie.MaladieService;
import itu.projet.pharmacie.service.symptome.SymptomeService;
import itu.projet.pharmacie.repository.agent.AgentpathogeneRepository;
import itu.projet.pharmacie.repository.domaine.DomaineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/maladies")
public class MaladieController {

    @Autowired
    private MaladieService maladieService;

    @Autowired
    private AgentpathogeneRepository agentpathogeneRepository;

    @Autowired
    private DomaineRepository domaineRepository;

    @Autowired
    private SymptomeService symptomeService;


    @GetMapping("")
    public String listMaladies(Model model) {
        List<Maladie> maladies = maladieService.getAllMaladies();
        model.addAttribute("maladies", maladies);
        return "maladie/list";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("maladie", new Maladie());
        model.addAttribute("agentsPathogenes", agentpathogeneRepository.findAll());
        model.addAttribute("domaines", domaineRepository.findAll());
        return "maladie/add";
    }

    @PostMapping("/save")
    public String saveMaladie(
            @ModelAttribute Maladie maladie,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            maladieService.createMaladie(maladie);
            redirectAttributes.addFlashAttribute("success", "Maladie enregistrée avec succès !");
            return "redirect:/maladies";
        } catch (Exception e) {
            model.addAttribute("maladie", maladie);
            model.addAttribute("agentsPathogenes", agentpathogeneRepository.findAll());
            model.addAttribute("domaines", domaineRepository.findAll());
            model.addAttribute("error", e.getMessage());
            return "maladie/add";
        }
    }

    // Modifier une maladie existante
    @GetMapping("/edit/{id}")
    public String editMaladie(@PathVariable("id") Integer id, Model model) {
        try {
            Maladie maladie = maladieService.getMaladieById(id).orElseThrow(() -> new RuntimeException("Maladie non trouvée."));
            model.addAttribute("maladie", maladie);
            model.addAttribute("agentsPathogenes", agentpathogeneRepository.findAll());
            model.addAttribute("domaines", domaineRepository.findAll());
            return "maladie/add";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la récupération de la maladie : " + e.getMessage());
            return "redirect:/maladies/add";
        }
    }

    // Mettre à jour une maladie existante
    @PostMapping("/update")
    public String updateMaladie(
            @ModelAttribute Maladie maladie,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            maladieService.updateMaladie(maladie.getIdMaladie(), maladie);
            redirectAttributes.addFlashAttribute("success", "Maladie mise à jour avec succès !");
            return "redirect:/maladies";
        } catch (Exception e) {
            model.addAttribute("maladie", maladie);
            model.addAttribute("agentsPathogenes", agentpathogeneRepository.findAll());
            model.addAttribute("domaines", domaineRepository.findAll());
            model.addAttribute("error", e.getMessage());
            return "maladie/add";
        }
    }

    // Supprimer une maladie
    @GetMapping("/delete/{id}")
    public String deleteMaladie(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            maladieService.deleteMaladie(id);
            redirectAttributes.addFlashAttribute("success", "Maladie supprimée avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression : " + e.getMessage());
        }
        return "redirect:/maladies";
    }

    @GetMapping("/details/{id}")
    public String maladieDetails(@PathVariable("id") Integer id, Model model) {
        try {
            Maladie maladie = maladieService.getMaladieById(id)
                    .orElseThrow(() -> new RuntimeException("Maladie non trouvée."));
            
            List<MaladieSymptome> maladieSymptomes = maladieService.getMaladieSymptomes(id);
            List<Symptome> allSymptomes = symptomeService.getAllSymptomes();

            Map<Integer, String> symptomesMap = allSymptomes.stream()
                    .filter(symptome -> maladieSymptomes.stream()
                            .anyMatch(ms -> ms.getIdSymptome().equals(symptome.getIdSymptome())))
                    .collect(Collectors.toMap(Symptome::getIdSymptome, Symptome::getNomSymptome));

            model.addAttribute("maladie", maladie);
            model.addAttribute("maladieSymptomes", maladieSymptomes);
            model.addAttribute("symptomesMap", symptomesMap);

            return "maladie/details"; 
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la récupération des détails : " + e.getMessage());
            return "redirect:/maladies"; 
        }
    }

}
