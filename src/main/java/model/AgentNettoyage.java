package model;

import java.util.*;



/**
 * 
 */
public class AgentNettoyage extends Personnel {

    /**
     * Default constructor
     */

    public AgentNettoyage(String nom, String prenom, String email, String password, String role, Hotel hotel) {
        super(nom, prenom, email, password, role, hotel);
    }

    public AgentNettoyage(Personnel personnel) {
        super(personnel.getNom(), personnel.getPrenom(), personnel.getEmail(), personnel.getPassword(), personnel.getRole(), personnel.getHotel());
        setId(personnel.getId());
    }

    private Vector<Intervention> list_intervention = new Vector<Intervention>();

    public Vector<Intervention> getList_intervention() {
        return this.list_intervention;
    }

}   