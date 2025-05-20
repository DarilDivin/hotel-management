package model;

import java.util.*;



/**
 * 
 */
public class Sejour {

    /**
     * Default constructor
     */
    private int id;

    private Date date_debut;
    private Date date_fin;

    private Receptioniste receptioniste;
    private Chambre chambre;
    private Client client;

    public Sejour(Reservation reservation) {
        this.reservation = reservation;
        this.client = reservation.getClient();
        this.date_debut = reservation.getDateDebut();
        this.date_fin = reservation.getDateFin();
        this.receptioniste = reservation.getReceptionniste();
        this.chambre = reservation.getChambre();
    }

    public Sejour(Client client, Date date_debut, Date date_fin, Receptioniste receptioniste, Chambre chambre) {
        this.client = client;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.receptioniste = receptioniste;
        this.chambre = chambre;
    }

    private Reservation reservation;
    private Vector<Consommation> list_consommation = new Vector<Consommation>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Vector<Consommation> getListConsommation() {
        return this.list_consommation;
    }

    public void setReservation(Reservation newReservation) {
        this.reservation = newReservation;
    }

    public Receptioniste getReceptioniste() {
        return receptioniste;
    }

    public void setReceptioniste(Receptioniste newReceptioniste) {
        this.receptioniste = newReceptioniste;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre newChambre) {
        this.chambre = newChambre;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client newClient) {
        this.client = newClient;
    }

    public Date getDateDebut() {
        return this.date_debut;
    }

    public Date getDateFin() {
        return this.date_fin;
    }

    public Object[] toTableRowCustom(int row) {
        return new Object[]{
                false,
                row,
                id,
                client.getPrenom() + " " + client.getNom(),
                chambre.getNumero(),
                new java.text.SimpleDateFormat("dd/MM/yyyy").format(date_debut),
                new java.text.SimpleDateFormat("dd/MM/yyyy").format(date_fin)
        };
    }
}