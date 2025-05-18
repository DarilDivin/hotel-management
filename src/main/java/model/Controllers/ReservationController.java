package model.Controllers;

import model.Reservation;
import model.DAO.ReservationDAO;
import java.util.Vector;

public class ReservationController {
    private ReservationDAO reservationDAO;

    public ReservationController(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    public void ajouterReservation(Reservation reservation) {
        reservationDAO.ajouterReservation(reservation);
    }

    public Reservation getReservationById(int id) {
        return reservationDAO.getReservationById(id);
    }

    public void supprimerReservation(int id) {
        reservationDAO.supprimerReservation(id);
    }

    public void modifierReservation(Reservation reservation) {
        reservationDAO.modifierReservation(reservation);
    }

    public Vector<Reservation> getTousLesReservations() {
        return reservationDAO.getTousLesReservations();
    }
}
