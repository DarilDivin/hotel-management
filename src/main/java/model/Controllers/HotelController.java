package model.Controllers;

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
}