package model.DAO;

import model.Hotel;
import model.Chambre;
import model.TypeChambre;
import model.Client;
import model.Controllers.*;

import java.util.Vector;

public class Test {
    public static void main(String[] args) {

        ClientDAO clientDAO = new ClientDAO();

        ClientController clientController = new ClientController(clientDAO);

        Client client = new Client("Mathis", "ALIDJINOU", "Mathias.alidjinou@gmail.com", new Hotel("Hôtel Hêviosso", "123 Rue A"));
        clientController.ajouterClient(client);

    }
}
