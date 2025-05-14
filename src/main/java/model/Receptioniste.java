package model;

import java.util.*;



/**
 * 
 */
public class Receptioniste extends Personnel {

    /**
     * Default constructor
     */
    public Receptioniste(String nom, String prenom, String email, String password, Hotel hotel) {
        super(nom, prenom, email, password, hotel);
    }
    
    /**
     * 
     */
    public Vector<Reservation> list_reservation = new Vector<Reservation>();

}