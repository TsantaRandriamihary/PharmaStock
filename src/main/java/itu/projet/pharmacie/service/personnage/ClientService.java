package itu.projet.pharmacie.service.personnage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import itu.projet.pharmacie.model.personnage.Client;
import itu.projet.pharmacie.repository.personnage.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

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
}
