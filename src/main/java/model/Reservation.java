package model;

import java.util.*;

public class Reservation {

    private int id;
    private boolean statut = false;
    private Date date_debut;
    private Date date_fin;
    private Receptioniste receptioniste;
    private Chambre chambre;
    private Client client;

    public Reservation(Client client, Date date_debut, Date date_fin, Receptioniste receptioniste, Chambre chambre) {
        this.client = client;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.receptioniste = receptioniste;
        this.chambre = chambre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
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

    public Chambre getChambre() {
        return chambre;
    }

    public Receptioniste getReceptionniste() {
        return this.receptioniste;
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

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public Object[] toTableRowCustom(int row) {
        return new Object[]{
                false,
                row,
                id,
                client.getPrenom() + " " + client.getNom(),
                chambre.getNumero(),
                new java.text.SimpleDateFormat("dd/MM/yyyy").format(date_debut),
                new java.text.SimpleDateFormat("dd/MM/yyyy").format(date_fin),
                statut
        };
    }
}
