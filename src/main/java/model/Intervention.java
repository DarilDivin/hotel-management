package model;

import java.util.*;

/**
 * 
 */
public class Intervention {

    private Date date;
    private Chambre chambre;
    private AgentNettoyage agent_nettoyage;

    /**
     * Default constructor
     */
    public Intervention(Chambre chambre, AgentNettoyage agent_nettoyage, Date date) {
        this.chambre = chambre;
        this.agent_nettoyage = agent_nettoyage;
        this.date = date;
    }

    public Date getDate() {
        return this.date;
    }
    public Chambre getChambre() {
        return this.chambre;
    }
    public AgentNettoyage getAgentNettoyage() {
        return this.agent_nettoyage;
    }

    public void setDate(Date d) {
        this.date = d;
    }

    public void setChambre(Chambre c) {
        this.chambre = c;
    }

    public void setAgentNettoyage(AgentNettoyage ag) {
        this.agent_nettoyage = ag;
    }
}