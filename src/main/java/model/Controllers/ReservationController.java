package model.Controllers;

import model.Reservation;
import model.DAO.ReservationDAO;
import java.util.Vector;

public class ReservationController {
    private static ReservationDAO reservationDAO = new ReservationDAO();

    public static void ajouterReservation(Reservation reservation) {
        reservationDAO.ajouterReservation(reservation);
    }

    public static Reservation getReservationById(int id) {
        return reservationDAO.getReservationById(id);
    }

    public static void supprimerReservation(int id) {
        reservationDAO.supprimerReservation(id);
    }

    public static void modifierReservation(Reservation reservation) {
        reservationDAO.modifierReservation(reservation);
    }

    public static Vector<Reservation> getTousLesReservations() {
        return reservationDAO.getTousLesReservations();
    }
}
