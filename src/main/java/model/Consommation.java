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

    private int id;
    private Sejour sejour;
    private int quantite;
    private Vector<Produit> list_produit = new Vector<Produit>();

    public int getId() {
        return this.id;
    }

    public int getQuantite() {
        return this.quantite;
    }

    public Sejour getSejour() {
        return this.sejour;
    }

    public Vector<Produit> getLProduits() {
        return this.list_produit;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public void setSejour(Sejour newSejour) {
        this.sejour = newSejour;
    }

    public void setQuantite(int newQuantite) {
        this.quantite = newQuantite;
    }

}