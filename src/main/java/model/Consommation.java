package model;

import java.util.*;



/**
 * 
 */
public class Consommation {

    /**
     * Default constructor
     */
    public Consommation(Sejour sejour, Produit produit, int quantite) {
        this.quantite = quantite;
        this.sejour = sejour;
        this.list_produit.add(produit);
    }

    /**
     * 
     */
    public Sejour sejour;

    /**
     * 
     */
    public int quantite;

    /**
     * 
     */
    public Vector<Produit> list_produit = new Vector<Produit>();

    public void setProduit(Produit produit) {
        this.list_produit.add(produit);
    }
    public void setSejour(Sejour sejour) {
        this.sejour = sejour;
    }

}