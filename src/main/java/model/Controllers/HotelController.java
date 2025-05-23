package model.Controllers;

import model.Chambre;
import model.Client;
import model.Hotel;
import model.DAO.HotelDAO;
import java.util.Vector;

public class HotelController {
    private static HotelDAO hotelDAO = new HotelDAO();

    public static void ajouterHotel(Hotel hotel) {
        hotelDAO.ajouterHotel(hotel);
    }

    public static Hotel getHotelById(int id) {
        return hotelDAO.getHotelById(id);
    }

    public static void supprimerHotel(int id) {
        hotelDAO.supprimerHotel(id);
    }

    public static void modifierHotel(Hotel hotel) {
        hotelDAO.modifierHotel(hotel);
    }

    public static Vector<Hotel> getTousLesHotels() {
        return hotelDAO.getAllHotels();
    }

    public static Hotel getHotelByNom(String nom) {
        return hotelDAO.getHotelByNom(nom);
    }

    public static Vector<Client> getClients(Hotel hotel) {
        return hotelDAO.getClients(hotel);
    }

    public static Vector<Chambre> getChambres(Hotel hotel) {
        return hotelDAO.getChambres(hotel);
    }
}