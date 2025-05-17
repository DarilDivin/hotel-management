package model;

import java.util.*;

/**
 *
 */
public class Hotel {

    private int id;
    private String nom;
    private String adresse;
    private Vector<Personnel> list_personnel = new Vector<Personnel>();
    private Vector<Client> list_client = new Vector<Client>();

    public Hotel(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public void setAdresse(String newAdresse) {
        this.adresse = newAdresse;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Vector<Personnel> getList_personnel() {
        return this.list_personnel;
    }

    public Vector<Client> getList_client() {
        return this.list_client;
    }
    

}
