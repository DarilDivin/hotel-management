package model.Controllers;

import model.Personnel;
import model.DAO.PersonnelDAO;
import java.util.Vector;

public class PersonnelController {
    private PersonnelDAO personnelDAO;

    public PersonnelController(PersonnelDAO personnelDAO) {
        this.personnelDAO = personnelDAO;
    }

    public void ajouterPersonnel(Personnel personnel) {
        personnelDAO.ajouterPersonnel(personnel);
    }

    public Personnel getPersonnelById(int id) {
        return personnelDAO.getPersonnelById(id);
    }

    public void supprimerPersonnel(int id) {
        personnelDAO.supprimerPersonnel(id);
    }

    public void modifierPersonnel(Personnel personnel) {
        personnelDAO.modifierPersonnel(personnel);
    }

    public Vector<Personnel> getTousLesPersonnels() {
        return personnelDAO.getTousLesPersonnels();
    }
}
