package model;

import java.util.*;

/**
 *
 */
public class Etage {

    private int numero_etage;
    private Hotel hotel;
    
    private Vector<Chambre> list_chambre = new Vector<Chambre>();

    
    public Etage( int numero_etage) {
        this.numero_etage = numero_etage;
    }

    public int getNumero_etage() {
        return numero_etage;
    }
    public void setNumero_etage(int numero_etage) {
        this.numero_etage = numero_etage;
    }
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    public Hotel getHotel() {
        return hotel;
    }
    public Vector<Chambre> getList_chambre () {
        return this.list_chambre;
    }
}
