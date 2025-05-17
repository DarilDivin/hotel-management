package model;

import java.util.*;

public class Reservation {

    private int id;

    private Date date_debut;
    private Date date_fin;

    private Receptioniste receptioniste;

    private Client client;

    private Vector<Chambre> list_chambre = new Vector<Chambre>();

    private Sejour sejour;

    public Reservation(Client client, Date date_debut, Date date_fin, Vector<Chambre> list_chambre) {
        this.client = client;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.list_chambre = list_chambre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return this.client;
    }

    public Date getDateDebut() {
        return date_debut;
    }

    public Date getDateFin() {
        return date_fin;
    }

    public Vector<Chambre> getListChambre() {
        return this.list_chambre;
    }

    public Sejour getSejour() {
        return this.sejour;
    }

    public Receptioniste getReceptionniste() {
        return this.receptioniste;
    }

    public void setSejour(Sejour newSejour) {
        this.sejour = newSejour;
    }

    public void setClient(Client newClient) {
        this.client = newClient;
    }

    public void setDateDebut(Date newDateDebut) {
        this.date_debut = newDateDebut;
    }

    public void setDateFin(Date newDateFin) {
        this.date_fin = newDateFin;
    }

    public void setReceptionniste(Receptioniste newReceptionniste) {
        this.receptioniste = newReceptionniste;
    }

}
