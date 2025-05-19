package model;

/**
 * 
 */
public class Personnel {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String role;
    public Hotel hotel;

    /**
     * Default constructor
     */
    public Personnel(String nom, String prenom, String email, String password, String role, Hotel hotel) {
        this.hotel = hotel;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNom () {
        return nom;
    }

    public String getPrenom () {
        return prenom;
    }

    public String getEmail () {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setNom(String newNom) {
        this.nom = newNom;
    }

    public void setPrenom(String newPrenom) {
        this.prenom = newPrenom;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setHotel(Hotel newHotel) {
        this.hotel = newHotel;
    }

    public void setRole(String newRole) {
        this.role = newRole;
    }

}