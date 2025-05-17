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
        this.produit = produit;
    }

    private int id;
    private Sejour sejour;
    private int quantite;
    private Produit produit;

    public int getId() {
        return this.id;
    }

    public int getQuantite() {
        return this.quantite;
    }

    public Sejour getSejour() {
        return this.sejour;
    }

    public Produit getProduit() {
        return this.produit;
    }

    public void setProduit(  Produit produit) {
        this.produit = produit;
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