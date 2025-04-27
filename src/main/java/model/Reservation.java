package model;

import java.util.*;

public class Reservation {

    public Date date_debut;
    public Date date_fin;

    public Receptioniste receptioniste;

    public Client client;

    public Vector<Chambre> list_chambre = new Vector<Chambre>();

    public Sejour sejour;

    public Reservation(Client client, Date date_debut, Date date_fin, Vector<Chambre> list_chambre) {
        this.client = client;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.list_chambre = list_chambre;
    }

    public void setChambre(Chambre chambre) {
        this.list_chambre.add(chambre);
    }

    public void setSejour(Sejour sejour) {
        this.sejour = sejour;
    }

    public void setReceptioniste(Receptioniste receptioniste) {
        this.receptioniste = receptioniste;
    }

}
