package itu.projet.pharmacie.controller.historiqueprix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import itu.projet.pharmacie.repository.historiqueprix.HistoriquePrixRepository;
import itu.projet.pharmacie.model.historiqueprix.HistoriquePrix;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/histosprix")
public class HistoriquePrixController {
    
    @Autowired
    private HistoriquePrixRepository historiquePrixRepository;

    @GetMapping("")
    public String listHistoriquePrix(Model model) {
        List<HistoriquePrix> allHistoriques = historiquePrixRepository.findAll();

        // First, group by product name
        Map<String, List<HistoriquePrix>> groupedByProduct = allHistoriques.stream()
            .filter(h -> h.getProduit() != null)
            .collect(Collectors.groupingBy(
                h -> h.getProduit().getNomProduit(),
                HashMap::new,
                Collectors.toList()
            ));

        // Then sort each list by date
        groupedByProduct.forEach((produitNom, historiques) -> {
            historiques.sort((h1, h2) -> h2.getDateChangement().compareTo(h1.getDateChangement()));
        });

        model.addAttribute("priceHistoryByProduct", groupedByProduct);
        return "historiqueprix/list";
    }
}