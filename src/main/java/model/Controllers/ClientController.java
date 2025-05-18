package model.Controllers;

import model.Client;
import model.DAO.ClientDAO;
import java.util.Vector;

public class ClientController {
    private static ClientDAO clientDAO = new ClientDAO();

    public static void ajouterClient(Client client) {
        clientDAO.addClient(client);
    }

    public static Client getClientById(int id) {
        return clientDAO.getClientById(id);
    }

    public static void supprimerClient(int id) {
        clientDAO.deleteClient(id);
    }

    public static void modifierClient(Client client) {
        clientDAO.updateClient(client);
    }

    public static Vector<Client> getTousLesClients() {
        return clientDAO.getAllClients();
    }
}
