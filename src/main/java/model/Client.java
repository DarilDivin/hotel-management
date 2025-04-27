package model;

import java.util.*;

/**
 * 
 */
public class Client {

    /**
     * Default constructor
     */
    public Client(String nom, String prenom, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }

    /**
     * 
     */
    public String nom;

    /**
     * 
     */
    public String prenom;

    /**
     * 
     */
    public String email;

    /**
     * 
     */
    public String password;

    /**
     * 
     */
    public Vector<Hotel> list_hotel = new Vector<Hotel>();

    /**
     * 
     */
    public Vector<Reservation> list_reservation = new Vector<Reservation>();

}