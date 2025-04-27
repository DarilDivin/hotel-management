package model;

/**
 * 
 */
public class Personnel {

    /**
     * Default constructor
     */
    public Personnel(String nom, String prenom, String email, String password, Hotel hotel) {
        this.hotel = hotel;
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
    public Hotel hotel = new Hotel();

}