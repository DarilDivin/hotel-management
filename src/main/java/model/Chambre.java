package model;

import java.util.*;

/*
 * Créer les accesseurs pour les listes
 */
public class Chambre {

    private int id;
    private String numero;
    private double prix;
    private double superficie;
    private String image;
    private Vector<Reservation> list_reservation = new Vector<Reservation>();
    private Vector<Intervention> list_intervention = new Vector<Intervention>();
    private TypeChambre type_chambre;

    public Chambre(TypeChambre type_chambre, String numero, String image, double prix, double superficie) {
        this.numero = numero;
        this.prix = prix;
        this.image = image;
        this.superficie = superficie;
        this.type_chambre = type_chambre;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int newid) {
        this.id = newid;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String newnumero) {
        this.numero = newnumero;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String newimage) {
        this.image = newimage;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(float prix) {
        this.prix = prix;
    }
    public double getSuperficie() {
        return superficie;
    }
    public void setSuperficie(float superficie) {
        this.superficie = superficie;
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
