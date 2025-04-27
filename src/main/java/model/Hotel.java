package model;

import java.util.*;

/**
 *
 */
public class Hotel {

    /**
     *
     */
    public String nom;

    /*
     * 
     */
    public Vector<Personnel> list_personnel = new Vector<Personnel>();

    /*
     * 
     */
    public Vector<Etage> list_etage = new Vector<Etage>();

    /*
     * 
     */
    public Vector<Client> list_client = new Vector<Client>();

    public Hotel(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void addPersonnel(Personnel p) {
        list_personnel.add(p);

    }

    public void addEtage(Etage e) {
        list_etage.add(e);

    }

    public void addClient(Client c) {
        list_client.add(c);

    }

    public void removePersonnel(Personnel p) {
        list_personnel.remove(p);

    }

    public void removeEtage(Etage e) {
        list_etage.remove(e);

    }

    public void removeClient(Client c) {
        list_client.remove(c);

    }

    public void afficherPersonnel() {
        for (Personnel p : list_personnel) {
            System.out.println(p);
        }

    }

    public void afficherEtage() {
        for (Etage e : list_etage) {
            System.out.println(e);
        }

    }

    public void afficherClient() {
        for (Client c : list_client) {
            System.out.println(c);
        }

    }

    public void afficherHotel() {
        System.out.println("Hotel: " + nom);
        System.out.println("Etages:");
        afficherEtage();

    }

    
}
