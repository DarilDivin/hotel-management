package model.Controllers;

import model.Client;
import model.DAO.ClientDAO;
import java.util.Vector;

public class ClientController {
    private ClientDAO clientDAO;

    public ClientController(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public void ajouterClient(Client client) {
        clientDAO.addClient(client);
    }

    public Client getClientById(int id) {
        return clientDAO.getClientById(id);
    }

    public void supprimerClient(int id) {
        clientDAO.deleteClient(id);
    }

    public void modifierClient(Client client) {
        clientDAO.updateClient(client);
    }

    public Vector<Client> getTousLesClients() {
        return clientDAO.getAllClients();
    }
}
