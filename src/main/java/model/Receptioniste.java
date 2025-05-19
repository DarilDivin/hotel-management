package model;

import java.util.*;



/**
 * Créer une réservation
 * Valider une réservation
 * Annuler une réservation
 */
public class Receptioniste extends Personnel {

    /**
     * Default constructor
     */
    public Receptioniste(String nom, String prenom, String email, String password, String role, Hotel hotel) {
        // Faire une vérification de la présence de l'email dans la base de données avant de l'ajouter (Avec un try - catch)
        super(nom, prenom, email, password, role, hotel);
    }

    public Receptioniste(Personnel personnel) {
        super(personnel.getNom(), personnel.getPrenom(), personnel.getEmail(), personnel.getPassword(), personnel.getRole(), personnel.getHotel());
    }
    
    /**
     * 
     */
    public Vector<Reservation> list_reservation = new Vector<Reservation>();

}