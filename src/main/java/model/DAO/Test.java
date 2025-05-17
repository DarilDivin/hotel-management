package model.DAO;

import model.Hotel;

public class Test {
    public static void main(String[] args) {
        Hotel hotel = new Hotel("Babalola","24 Rue du père André JALAND");

        HotelDAO dao = new HotelDAO();
        dao.ajouterHotel(hotel);

        System.out.println(hotel.getId());
        System.out.println("Insertion de l'Hotel avec succes!");
    }
}
