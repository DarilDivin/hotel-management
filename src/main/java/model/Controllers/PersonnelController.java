package model.Controllers;

import model.Personnel;
import model.DAO.PersonnelDAO;
import java.util.Vector;

public class PersonnelController {
    private static PersonnelDAO personnelDAO = new PersonnelDAO();

    public static void ajouterPersonnel(Personnel personnel) {
        personnelDAO.ajouterPersonnel(personnel);
    }

    public static Personnel getPersonnelById(int id) {
        return personnelDAO.getPersonnelById(id);
    }

    public static void supprimerPersonnel(int id) {
        personnelDAO.supprimerPersonnel(id);
    }

    public static void modifierPersonnel(Personnel personnel) {
        personnelDAO.modifierPersonnel(personnel);
    }

    public static Vector<Personnel> getTousLesPersonnels() {
        return personnelDAO.getTousLesPersonnels();
    }
}