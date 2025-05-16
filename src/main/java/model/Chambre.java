package model;

import java.util.*;

/*
 * Créer les accesseurs pour les listes
 */
public class Chambre {

    private int numero;
    private float prix;
    private float superficie;
    private Vector<Reservation> list_reservation = new Vector<Reservation>();
    private Etage etage;
    private Vector<Intervention> list_intervention = new Vector<Intervention>();
    private TypeChambre type_chambre;

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
    public Vector<Reservation> getList_reservation() {
      return list_reservation;
    }
    public Vector<Intervention> getList_intervention() {
      return list_intervention;
    }
}
