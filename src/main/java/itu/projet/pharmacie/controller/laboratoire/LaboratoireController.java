package itu.projet.pharmacie.controller.laboratoire;

import itu.projet.pharmacie.model.laboratoire.Laboratoire;
import itu.projet.pharmacie.service.laboratoire.LaboratoireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/laboratoires")
public class LaboratoireController {

    @Autowired
    private LaboratoireService laboratoireService;

    @GetMapping("")
    public String listLaboratoires(Model model) {
        List<Laboratoire> laboratoires = laboratoireService.getAllLaboratoires();
        model.addAttribute("laboratoires", laboratoires);
        return "laboratoire/list";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("laboratoire", new Laboratoire());
        return "laboratoire/add";
    }

    @PostMapping("/save")
    public String saveLaboratoire(
            @ModelAttribute Laboratoire laboratoire,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            laboratoireService.createLaboratoire(laboratoire);
            redirectAttributes.addFlashAttribute("success", "Laboratoire enregistré avec succès !");
            return "redirect:/laboratoires";
        } catch (Exception e) {
            model.addAttribute("laboratoire", laboratoire);
            model.addAttribute("error", e.getMessage());
            return "laboratoire/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String editLaboratoire(@PathVariable("id") Integer id, Model model) {
        try {
            Laboratoire laboratoire = laboratoireService.getLaboratoireById(id)
                    .orElseThrow(() -> new RuntimeException("Laboratoire non trouvé."));
            model.addAttribute("laboratoire", laboratoire);
            return "laboratoire/add";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la récupération du laboratoire : " + e.getMessage());
            return "redirect:/laboratoires/add";
        }
    }

    @PostMapping("/update")
    public String updateLaboratoire(
            @ModelAttribute Laboratoire laboratoire,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            laboratoireService.updateLaboratoire(laboratoire.getIdLaboratoire(), laboratoire);
            redirectAttributes.addFlashAttribute("success", "Laboratoire mis à jour avec succès !");
            return "redirect:/laboratoires";
        } catch (Exception e) {
            model.addAttribute("laboratoire", laboratoire);
            model.addAttribute("error", e.getMessage());
            return "laboratoire/add";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteLaboratoire(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            laboratoireService.deleteLaboratoire(id);
            redirectAttributes.addFlashAttribute("success", "Laboratoire supprimé avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression : " + e.getMessage());
        }
        return "redirect:/laboratoires";
    }
}
