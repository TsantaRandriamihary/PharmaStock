package itu.projet.pharmacie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model) {
        return "accueil"; 
    }

    @GetMapping("/test")
    public String test(Model model) {
        return "tables-data"; 
    }

    

    
}
