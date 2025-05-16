package model;

import java.util.*;

/**
 *
 */
public class Hotel {

    private String nom;

    private Vector<Personnel> list_personnel = new Vector<Personnel>();

    private Vector<Etage> list_etage = new Vector<Etage>();

    private Vector<Client> list_client = new Vector<Client>();

    public Hotel(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Vector<Etage> getList_etages() {
        return list_etage;
    }

    public Vector<Personnel> getList_personnel() {
        return this.list_personnel;
    }

    public Vector<Client> getList_client() {
        return this.list_client;
    }
    

}
