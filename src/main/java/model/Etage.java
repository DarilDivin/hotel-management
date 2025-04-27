package model;

import java.util.*;

/**
 *
 */
public class Etage {

    public int numero_etage;

    
    public Vector<Chambre> list_chambre = new Vector<Chambre>();

  
    public Hotel hotel;

    
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
    public void addChambre(Chambre c) {
        list_chambre.add(c);
    }
    public void removeChambre(Chambre c) {
        list_chambre.remove(c);
    }
    public void removeChambre(int i) {
        list_chambre.remove(i);
    }
}
