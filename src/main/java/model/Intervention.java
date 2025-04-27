package model;

import java.util.*;

/**
 * 
 */
public class Intervention {

    /**
     * Default constructor
     */
    public Intervention(Chambre chambre, AgentNettoyage agent_nettoyage, Date date) {
        this.chambre = chambre;
        this.agent_nettoyage = agent_nettoyage;
        this.date = date;
    }

    /**
     * 
     */
    public Date date;

    /**
     * 
     */
    public Chambre chambre;

    /**
     * 
     */
    public AgentNettoyage agent_nettoyage;

}