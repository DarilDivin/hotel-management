package model;

/**
 * 
 */
public class Produit {

    /**
     * Default constructor
     */
    public Produit(String nom, int prix) {
        this.nom = nom;
        this.prix = prix;
    }

    private int id;
    private String nom;
    private int prix;
    private Consommation consommation;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return this.nom;
    }

    public int getPrix() {
        return this.prix;
    }

    public Consommation getConsommation() {
        return this.consommation;
    }

    public void setNom(String n) {
        this.nom = n;
    }
    
    public void setPrix(int p) {
        this.prix = p;
    }

}