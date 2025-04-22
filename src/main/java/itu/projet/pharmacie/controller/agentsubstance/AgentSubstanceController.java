package itu.projet.pharmacie.controller.agentsubstance;

import itu.projet.pharmacie.model.agent.agentsubstance.AgentSubstance;
import itu.projet.pharmacie.model.agent.agentsubstance.AgentSubstanceId;
import itu.projet.pharmacie.model.substance.Substance;
import itu.projet.pharmacie.model.agent.Agentpathogene;
import itu.projet.pharmacie.repository.agent.AgentpathogeneRepository;
import itu.projet.pharmacie.service.substance.AgentSubstanceService;
import itu.projet.pharmacie.service.substance.SubstanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/agentsubstance")
public class AgentSubstanceController {

    @Autowired
    private AgentSubstanceService agentSubstanceService;

    @Autowired
    private SubstanceService substanceService;

    @Autowired
    private AgentpathogeneRepository agentpathogeneRepository;

    @GetMapping
    public String listAgentSubstances(Model model) {
        List<AgentSubstance> agentSubstances = agentSubstanceService.getAllAgentSubstances();
        List<Substance> substances = substanceService.getAllSubstances();
        List<Agentpathogene> agentpathogenes = agentpathogeneRepository.findAll();

        Map<Integer, String> substanceMap = substances.stream()
                .collect(Collectors.toMap(Substance::getIdSubstance, Substance::getNomSubstance));
        
        Map<Integer, String> agentpathogeneMap = agentpathogenes.stream()
                .collect(Collectors.toMap(Agentpathogene::getIdAgentpathogene, Agentpathogene::getNomAgentpathogene));

        model.addAttribute("agentSubstances", agentSubstances);
        model.addAttribute("substanceMap", substanceMap);
        model.addAttribute("agentMap", agentpathogeneMap);

        return "elements/agentsubstance/list";
    }


    @GetMapping("/add")
    public String addAgentSubstanceForm(Model model) {
        List<Substance> substances = substanceService.getAllSubstances();
        List<Agentpathogene> agentpathogenes = agentpathogeneRepository.findAll();

        model.addAttribute("substances", substances);
        model.addAttribute("agentpathogenes", agentpathogenes);
        model.addAttribute("agentSubstance", new AgentSubstance());
        return "elements/agentsubstance/add";
    }

    // Ajouter une nouvelle relation
    @PostMapping("/save")
    public String addAgentSubstance(@ModelAttribute AgentSubstance agentSubstance) {
        agentSubstanceService.createAgentSubstance(agentSubstance);
        return "redirect:/agentsubstance";
    }

    @GetMapping("/edit/{idSubstance}/{idAgentpathogene}")
    public String editAgentSubstanceForm(
            @PathVariable Integer idSubstance,
            @PathVariable Integer idAgentpathogene,
            Model model) {
        AgentSubstanceId id = new AgentSubstanceId(idSubstance, idAgentpathogene);
        AgentSubstance agentSubstance = (agentSubstanceService.getAgentSubstanceById(id)).get();

        List<Substance> substances = substanceService.getAllSubstances();
        List<Agentpathogene> agentpathogenes = agentpathogeneRepository.findAll();

        model.addAttribute("substances", substances);
        model.addAttribute("agentpathogenes", agentpathogenes);
        model.addAttribute("agentSubstance", agentSubstance);
        return "elements/agentsubstance/add";
    }

    @PostMapping("/edit")
    public String editAgentSubstance(@ModelAttribute AgentSubstance agentSubstance) {
        agentSubstanceService.createAgentSubstance(agentSubstance);
        return "redirect:/agentsubstance";
    }

    // Supprimer une relation
    @GetMapping("/delete/{idSubstance}/{idAgentpathogene}")
    public String deleteAgentSubstance(
            @PathVariable Integer idSubstance,
            @PathVariable Integer idAgentpathogene) {
        AgentSubstanceId id = new AgentSubstanceId(idSubstance, idAgentpathogene);
        agentSubstanceService.deleteAgentSubstance(id);
        return "redirect:/agentsubstance";
    }
}
