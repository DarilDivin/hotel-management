package model.Controllers;

import model.Receptioniste;
import model.DAO.ReceptionisteDAO;
import model.Reservation;
import model.Sejour;

import java.util.Vector;

public class ReceptionisteController {
    private static ReceptionisteDAO receptionisteDAO = new ReceptionisteDAO();

    public static void ajouterReceptioniste(Receptioniste receptioniste) {
        receptionisteDAO.ajouterReceptioniste(receptioniste);
    }

    public static Receptioniste getReceptionisteById(int id) {
        return receptionisteDAO.getReceptionisteById(id);
    }

    public static void supprimerReceptioniste(int id) {
        receptionisteDAO.supprimerReceptioniste(id);
    }

    public static void modifierReceptioniste(Receptioniste receptioniste) {
        receptionisteDAO.modifierReceptioniste(receptioniste);
    }

    public static Vector<Receptioniste> getTousLesReceptionistes() {
        return receptionisteDAO.getTousLesReceptionistes();
    }

    public static Vector<Sejour> getSejours(Receptioniste receptioniste) {
        return receptionisteDAO.getSejours(receptioniste);
    }

    public static Vector<Reservation> getReservations(Receptioniste receptioniste) {
        return receptionisteDAO.getReservations(receptioniste);
    }
}