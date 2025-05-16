package model;

import java.util.*;



/**
 * 
 */
public class Sejour {

    /**
     * Default constructor
     */
    public Sejour(Reservation reservation) {
        this.reservation = reservation;
    }

    private Reservation reservation;
    private Vector<Consommation> list_consommation = new Vector<Consommation>();

    public Reservation getReservation() {
        return reservation;
    }

    public Vector<Consommation> getListConsommation() {
        return this.list_consommation;
    }

    public void setReservation(Reservation newReservation) {
        this.reservation = newReservation;
    }
}