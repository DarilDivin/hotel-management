package model;

import java.util.*;

public class Chambre {

    public int numero;

    public float prix;

    public float superficie;

    public Vector<Reservation> list_reservation = new Vector<Reservation>();

    public Etage etage;

    public Vector<Intervention> list_intervention = new Vector<Intervention>();

    public TypeChambre type_chambre;

    public Chambre(Etage etage, TypeChambre type_chambre, int numero, float prix, float superficie) {
        this.numero = numero;
        this.prix = prix;
        this.superficie = superficie;
        this.etage = etage;
        this.type_chambre = type_chambre;
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public float getPrix() {
        return prix;
    }
    public void setPrix(float prix) {
        this.prix = prix;
    }
    public float getSuperficie() {
        return superficie;
    }
    public void setSuperficie(float superficie) {
        this.superficie = superficie;
    }
    public void setEtage(Etage etage) {
        this.etage = etage;
    }
    public Etage getEtage() {
        return etage;
    }
    public TypeChambre getTypeChambre() {
        return type_chambre;
    }
    public void setTypeChambre(TypeChambre type_chambre) {
        this.type_chambre = type_chambre;
    }

    public void addReservation(Reservation r) {
        list_reservation.add(r);
    }
    public void removeReservation(Reservation r) {
        list_reservation.remove(r);
    }
}
