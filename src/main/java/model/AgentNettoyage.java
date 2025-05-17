package model;

import java.util.*;



/**
 * 
 */
public class AgentNettoyage extends Personnel {

    /**
     * Default constructor
     */

    public AgentNettoyage(String nom, String prenom, String email, String password, Hotel hotel) {
        super(nom, prenom, email, password, hotel);
    }

    private Vector<Intervention> list_intervention = new Vector<Intervention>();

    public Vector<Intervention> getList_intervention() {
        return this.list_intervention;
    }

}   