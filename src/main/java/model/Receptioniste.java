package model;

import java.util.*;



/**
 * 
 */
public class Receptioniste extends Personnel {

    /**
     * Default constructor
     */
    public Receptioniste(String firstName, String lastName, String id, String position, Hotel hotel) {
        super(firstName, lastName, id, position, hotel);
    }
    
    /**
     * 
     */
    public Vector<Reservation> list_reservation = new Vector<Reservation>();

}