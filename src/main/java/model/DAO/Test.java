package model.DAO;

import model.*;

import java.util.Date;

import model.Controllers.*;
import java.util.Vector;

public class Test {
    public static void main(String[] args) {

        Hotel hotel = new Hotel("Royal Hotel London", "1 Road of London");
        HotelController.ajouterHotel(hotel);
        TypeChambreController.ajouterTypeChambre(new TypeChambre("Simple"));
        TypeChambreController.ajouterTypeChambre(new TypeChambre("Double"));
        TypeChambreController.ajouterTypeChambre(new TypeChambre("Suite"));
        TypeChambreController.ajouterTypeChambre(new TypeChambre("Penthouse"));

        Personnel p1 = new Personnel("D.", "Daril", "daril@gmail.com", "Baba2004", "administrateur", hotel);
        Personnel p2 = new Personnel("L.", "Angelin", "jose@gmail.com", "Baba2004", "receptioniste", hotel);
        Personnel p3 = new Personnel("G.", "Metus", "metus@gmail.com", "Baba2004", "agent de nettoyage", hotel);
        PersonnelController.ajouterPersonnel(p1);
        PersonnelController.ajouterPersonnel(p2);
        PersonnelController.ajouterPersonnel(p3);

    }
}
