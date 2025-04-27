package model;

import java.util.*;



/**
 * 
 */
public class AgentNettoyage extends Personnel {

    /**
     * Default constructor
     */
    public AgentNettoyage(String firstName, String lastName, String id, String position, Hotel hotel) {
        super(firstName, lastName, id, position, hotel);
    }

    /**
     * 
     */
    public Vector<Intervention> list_intervention = new Vector<Intervention>();

}   