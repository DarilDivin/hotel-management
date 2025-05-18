package model.Controllers;

import model.Hotel;
import model.DAO.HotelDAO;

import java.util.Vector;

public class HotelController {
    private HotelDAO hotelDAO = new HotelDAO();

    public void AjouterHotel(String nom, String adresse) {
        Hotel hotel = new Hotel(nom, adresse);
        hotelDAO.ajouterHotel(hotel);
    }

    public Vector<Hotel> afficherHotels() {
        return hotelDAO.getAllHotels();
    }

    public Hotel getHotel(int id) {
        return hotelDAO.getHotelById(id);
    }

    public void modifierHotel(Hotel hotel) {
        hotelDAO.modifierHotel(hotel);
    }

    public void supprimerHotel(Hotel hotel) {

    }
}
