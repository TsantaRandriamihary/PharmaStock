package itu.projet.pharmacie.controller.substance;


import itu.projet.pharmacie.model.substance.Substance;
import itu.projet.pharmacie.repository.type.TypesubstanceRepository;
import itu.projet.pharmacie.service.substance.SubstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/substances")
public class SubstanceController {

    @Autowired
    private SubstanceService substanceService;

    @Autowired
    private TypesubstanceRepository typeSubstanceRepository;


    @GetMapping("")
    public String listSubstances(Model model) {
        List<Substance> substances = substanceService.getAllSubstances();
        model.addAttribute("substances", substances);
        return "elements/substance/list";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("substance", new Substance());
        model.addAttribute("types", typeSubstanceRepository.findAll());

        return "elements/substance/add"; 
    }


    @PostMapping("/save")
    public String saveSubstance(
            @ModelAttribute Substance substance, 
            Model model, 
            RedirectAttributes redirectAttributes) {
        try {
            substanceService.createSubstance(substance);
            redirectAttributes.addFlashAttribute("success", "Substance enregistrée avec succès !");
            return "redirect:/substances";
        } catch (Exception e) {
            model.addAttribute("substance", substance);
            model.addAttribute("types", typeSubstanceRepository.findAll());
            model.addAttribute("error", e.getMessage());
            return "elements/substance/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String editSubstance(@PathVariable("id") Integer id, Model model) {
        try {
            Substance substance = (substanceService.getSubstanceById(id)).get();
            model.addAttribute("substance", substance);
            model.addAttribute("types", typeSubstanceRepository.findAll());
            return "elements/substance/add"; 
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la récupération de la substance : " + e.getMessage());
            return "redirect:/substances/add"; 
        }
    }

    @PostMapping("/update")
    public String updateSubstance(
            @ModelAttribute Substance substance, 
            Model model, 
            RedirectAttributes redirectAttributes) {
        try {
            substanceService.updateSubstance(substance.getIdSubstance(), substance);
            redirectAttributes.addFlashAttribute("success", "Substance enregistrée avec succès !");
            return "redirect:/substances";
        } catch (Exception e) {
            model.addAttribute("substance", substance);
            model.addAttribute("types", typeSubstanceRepository.findAll());
            model.addAttribute("error", e.getMessage());
            return "elements/substance/add";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteSubstance(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            substanceService.deleteSubstance(id);
            redirectAttributes.addFlashAttribute("success", "Substance supprimée avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression : " + e.getMessage());
        }
        return "redirect:/substances"; 
    }
}
