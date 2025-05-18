package model.Controllers;

import model.AgentNettoyage;
import model.DAO.AgentNettoyageDAO;
import java.util.Vector;

public class AgentNettoyageController {
    private static AgentNettoyageDAO agentNettoyageDAO = new AgentNettoyageDAO();

    public static void ajouterAgentNettoyage(AgentNettoyage agentNettoyage) {
        agentNettoyageDAO.ajouterAgentNettoyage(agentNettoyage);
    }

    public static AgentNettoyage getAgentNettoyageById(int id) {
        return agentNettoyageDAO.getAgentNettoyageById(id);
    }

    public static void supprimerAgentNettoyage(int id) {
        agentNettoyageDAO.supprimerAgentNettoyage(id);
    }

    public static void modifierAgentNettoyage(AgentNettoyage agentNettoyage) {
        agentNettoyageDAO.modifierAgentNettoyage(agentNettoyage);
    }

    public static Vector<AgentNettoyage> getTousLesAgentsNettoyage() {
        return agentNettoyageDAO.getTousLesAgentsNettoyage();
    }
}