package itu.projet.pharmacie.controller.personnage;

import itu.projet.pharmacie.model.personnage.Client;
import itu.projet.pharmacie.service.personnage.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("")
    public String listClients(Model model) {
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        return "client/list";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("client", new Client());
        return "client/add";
    }

    @PostMapping("/save")
    public String saveClient(
            @ModelAttribute Client client,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            clientService.createClient(client);
            redirectAttributes.addFlashAttribute("success", "Client enregistré avec succès !");
            return "redirect:/clients";
        } catch (Exception e) {
            model.addAttribute("client", client);
            model.addAttribute("error", e.getMessage());
            return "client/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String editClient(@PathVariable("id") Integer id, Model model) {
        try {
            Client client = clientService.getClientById(id)
                    .orElseThrow(() -> new RuntimeException("Client non trouvé."));
            model.addAttribute("client", client);
            return "client/add";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la récupération du client : " + e.getMessage());
            return "redirect:/clients/add";
        }
    }

    @PostMapping("/update")
    public String updateClient(
            @ModelAttribute Client client,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            clientService.updateClient(client.getIdClient(), client);
            redirectAttributes.addFlashAttribute("success", "Client mis à jour avec succès !");
            return "redirect:/clients";
        } catch (Exception e) {
            model.addAttribute("client", client);
            model.addAttribute("error", e.getMessage());
            return "client/add";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            clientService.deleteClient(id);
            redirectAttributes.addFlashAttribute("success", "Client supprimé avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression : " + e.getMessage());
        }
        return "redirect:/clients";
    }
}
