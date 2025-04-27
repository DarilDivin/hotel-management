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

    /**
     * 
     */
    public String nom;

    /**
     * 
     */
    public int prix;

    /**
     * 
     */
    public Consommation consommation;

    public void setConsommation(Consommation consommation) {
        this.consommation = consommation;
    }

}