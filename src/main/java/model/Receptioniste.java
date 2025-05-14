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
    public Receptioniste(String nom, String prenom, String email, String password, Hotel hotel) {
        // Faire une vérification de la présence de l'email dans la base de données avant de l'ajouter (Avec un try - catch)
        super(nom, prenom, email, password, hotel);
    }
    
    /**
     * 
     */
    public Vector<Reservation> list_reservation = new Vector<Reservation>();

}