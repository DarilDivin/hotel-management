package model.DAO;
import java.sql.*;
import java.util.*;
import model.Receptioniste;
import model.Personnel;

public class ReceptionisteDAO {
    private PersonnelDAO personnelDAO = new PersonnelDAO();

    public void ajouterReceptioniste(Receptioniste receptioniste) {
        personnelDAO.ajouterPersonnel(receptioniste);
    }

    public Receptioniste getReceptionisteById(int id) {
        Receptioniste receptioniste = (Receptioniste) personnelDAO.getPersonnelById(id);

        return receptioniste;
    }

    public void supprimerReceptioniste(int id) {
        personnelDAO.supprimerPersonnel(id);
    }

    public void modifierReceptioniste(Receptioniste receptioniste) {
        personnelDAO.modifierPersonnel(receptioniste);

    }

    public Vector<Receptioniste> getTousLesReceptionistes() {
        Vector<Receptioniste> receptionistes = new Vector<>();
        Vector<Personnel> personnels = personnelDAO.getTousLesPersonnels();

        for (Personnel personnel : personnels) {
            if (personnel instanceof Receptioniste) {
                receptionistes.add((Receptioniste) personnel);
            }
        }

        return receptionistes;
    }
}
