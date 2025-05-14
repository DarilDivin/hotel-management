package model;

/**
 * 
 */
public class Personnel {

    /**
     * 
     */
    private String nom;

    /**
     * 
     */
    private String prenom;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String password;

    /**
     * Hotel de fonction
     */
    public Hotel hotel;

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

    /*getters */
    protected String getNom () {
        return nom;
    }

    protected String getPrenom () {
        return prenom;
    }

    protected String getEmail () {
        return email;
    }

    protected String getPassword() {
        return password;
    }

    protected Hotel getHotel() {
        return hotel;
    }

    /*Setters */
    protected void setNom(String newNom) {
        this.nom = newNom;
    }

    protected void setPrenom(String newPrenom) {
        this.prenom = newPrenom;
    }

    protected void setEmail(String newEmail) {
        this.email = newEmail;
    }

    protected void setPassword(String newPassword) {
        this.password = newPassword;
    }

    protected void setHotel(Hotel newHotel) {
        this.hotel = newHotel;
    }

}