package itu.projet.pharmacie.controller.maladie;

import itu.projet.pharmacie.model.maladiesymptome.MaladieSymptome;
import itu.projet.pharmacie.model.maladiesymptome.MaladieSymptomeId;
import itu.projet.pharmacie.model.maladie.Maladie;
import itu.projet.pharmacie.model.symptome.Symptome;
import itu.projet.pharmacie.service.maladie.MaladieService;
import itu.projet.pharmacie.service.symptome.SymptomeService;
import itu.projet.pharmacie.service.maladie.MaladieSymptomeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/maladiesymptome")
public class MaladieSymptomeController {

    @Autowired
    private MaladieSymptomeService maladieSymptomeService;

    @Autowired
    private MaladieService maladieService;

    @Autowired
    private SymptomeService symptomeService;

    

    @GetMapping("/add")
    public String addMaladieSymptomeForm(Model model) {
        List<Maladie> maladies = maladieService.getAllMaladies();
        List<Symptome> symptomes = symptomeService.getAllSymptomes();

        model.addAttribute("maladies", maladies);
        model.addAttribute("symptomes", symptomes);
        model.addAttribute("maladieSymptome", new MaladieSymptome());
        return "/maladie/addmaladiesymptome";
    }

    @PostMapping("/save")
    public String addMaladieSymptome(@ModelAttribute MaladieSymptome maladieSymptome, Model model) {
        try {

            maladieSymptomeService.createMaladieSymptome(maladieSymptome);
        } catch (Exception e) {
            List<Maladie> maladies = maladieService.getAllMaladies();
            List<Symptome> symptomes = symptomeService.getAllSymptomes();
            model.addAttribute("maladies", maladies);
            model.addAttribute("symptomes", symptomes);
            model.addAttribute("maladieSymptome", new MaladieSymptome());
            model.addAttribute("error", e.getMessage());
            return "/maladie/addmaladiesymptome";
        }
        return "redirect:/maladies";
    }

    @GetMapping("/delete/{idMaladie}/{idSymptome}")
    public String deleteMaladieSymptome(
            @PathVariable Integer idMaladie,
            @PathVariable Integer idSymptome) {
        MaladieSymptomeId id = new MaladieSymptomeId(idMaladie, idSymptome);
        maladieSymptomeService.deleteMaladieSymptome(id);
        return "redirect:/maladies";
    }
}
