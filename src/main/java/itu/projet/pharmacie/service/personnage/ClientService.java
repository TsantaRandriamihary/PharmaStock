package itu.projet.pharmacie.service.personnage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itu.projet.pharmacie.model.personnage.Client;
import itu.projet.pharmacie.model.vente.Vente;
import itu.projet.pharmacie.model.vente.VenteDetails;
import itu.projet.pharmacie.repository.personnage.ClientRepository;
import itu.projet.pharmacie.service.vente.VenteService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private VenteService venteService;

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> getClientById(Integer id) {
        return clientRepository.findById(id);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client updateClient(Integer id, Client updatedClient) {
        return clientRepository.findById(id).map(existingClient -> {
            existingClient.setNomClient(updatedClient.getNomClient());
            existingClient.setContactClient(updatedClient.getContactClient());
            existingClient.setAdresseClient(updatedClient.getAdresseClient());
            return clientRepository.save(existingClient);
        }).orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
    }

    // Delete a Client
    public void deleteClient(Integer id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
        } else {
            throw new RuntimeException("Client not found with id: " + id);
        }
    }

    public Map<Client, Map<String, Object>> getClientStats(String dateString) {
        List<Vente> ventes = (dateString == null || dateString.isEmpty())
                ? venteService.getAllVentes() 
                : venteService.getVentesByDate(dateString); 
        List<Client> clients = clientRepository.findAll();
        Map<Client, Map<String, Object>> clientStats = new HashMap<>();

        for (Client client : clients) {
            List<Vente> clientVentes = ventes.stream()
                    .filter(vente -> vente.getClient() != null && vente.getClient().equals(client))
                    .collect(Collectors.toList());

            int clientTotalQuantity = 0;
            double clientTotalMontant = 0;
            for (Vente vente : clientVentes) {
                List<VenteDetails> details = venteService.getVenteDetails(vente.getIdVente());
                for (VenteDetails detail : details) {
                    clientTotalQuantity += detail.getQuantiteVendue();
                    clientTotalMontant += detail.getQuantiteVendue() * detail.getLot().getProduit().getPrix();
                }
            }

            Map<String, Object> stats = new HashMap<>();
            stats.put("date", dateString != null && !dateString.isEmpty() ? dateString : "Tous");
            stats.put("montantTotal", clientTotalMontant);
            stats.put("quantiteTotal", clientTotalQuantity);

            clientStats.put(client, stats);
        }

        return clientStats;
    }


    
}
