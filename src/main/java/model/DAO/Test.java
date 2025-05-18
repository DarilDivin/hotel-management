package model.DAO;

import model.Hotel;
import model.Reservation;
import java.util.Date;
import model.Receptioniste;
import model.Chambre;
import model.TypeChambre;
import model.Client;
import model.Controllers.*;

import java.util.Vector;

public class Test {
    public static void main(String[] args) {

        Hotel hotel = new Hotel("La bastille", "12 rue du vernillon");
        HotelController.ajouterHotel(hotel);

        Client client = new Client("Castemberry","Jean-Pierre","jp@gmail.com", hotel);
        ClientController.ajouterClient(client);

    }
}
