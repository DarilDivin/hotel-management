package model.Controllers;

import model.AgentNettoyage;
import model.DAO.AgentNettoyageDAO;
import java.util.Vector;

public class AgentNettoyageController {
    private AgentNettoyageDAO agentNettoyageDAO;

    public AgentNettoyageController(AgentNettoyageDAO agentNettoyageDAO) {
        this.agentNettoyageDAO = agentNettoyageDAO;
    }

    public void ajouterAgentNettoyage(AgentNettoyage agentNettoyage) {
        agentNettoyageDAO.ajouterAgentNettoyage(agentNettoyage);
    }

    public AgentNettoyage getAgentNettoyageById(int id) {
        return agentNettoyageDAO.getAgentNettoyageById(id);
    }

    public void supprimerAgentNettoyage(int id) {
        agentNettoyageDAO.supprimerAgentNettoyage(id);
    }

    public void modifierAgentNettoyage(AgentNettoyage agentNettoyage) {
        agentNettoyageDAO.modifierAgentNettoyage(agentNettoyage);
    }

    public Vector<AgentNettoyage> getTousLesAgentsNettoyage() {
        return agentNettoyageDAO.getTousLesAgentsNettoyage();
    }
}
