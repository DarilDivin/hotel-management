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
    }

    private String nom;
    private String prenom;
    private String email;
    private Vector<Hotel> list_hotel = new Vector<Hotel>();
    private Vector<Reservation> list_reservation = new Vector<Reservation>();

    public String getNom() {
        return this.nom;
    }
    public String getPrenom() {
        return this.prenom;
    }
    public String getEmail() {
        return this.email;
    }
    public Vector<Hotel> getList_hotel() {
      return this.list_hotel;
    }
    public Vector<Reservation> getList_reservation() {
      return this.list_reservation;
    }

    public void setNom(String n) {
        this.nom = n;
    }
    public void setPrenom(String p) {
        this.prenom = p;
    }
    public void setEmail(String e) {
        this.email = e;
    }

}