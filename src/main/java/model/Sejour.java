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

    /**
     * 
     */
    public Reservation reservation;

    /**
     * 
     */
    public Vector<Consommation> list_consommation = new Vector<Consommation>();


   
    public Reservation getReservation() {
        return reservation;
    }

    public void setConsommation(Consommation consommation) {
        this.list_consommation.add(consommation);
    }
}