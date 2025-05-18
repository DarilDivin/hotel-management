package model.Controllers;

import model.Hotel;
import model.DAO.HotelDAO;
import java.util.Vector;

public class HotelController {
    private HotelDAO hotelDAO;

    public HotelController(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }

    public void ajouterHotel(Hotel hotel) {
        hotelDAO.ajouterHotel(hotel);
    }

    public Hotel getHotelById(int id) {
        return hotelDAO.getHotelById(id);
    }

    public void supprimerHotel(int id) {
        hotelDAO.supprimerHotel(id);
    }

    public void modifierHotel(Hotel hotel) {
        hotelDAO.modifierHotel(hotel);
    }

    public Vector<Hotel> getTousLesHotels() {
        return hotelDAO.getAllHotels();
    }
}
