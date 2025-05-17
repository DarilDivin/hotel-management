package model.DAO;
import java.sql.*;
import java.util.*;
import model.AgentNettoyage;
import model.Personnel;


public class AgentNettoyageDAO {

    private PersonnelDAO personnelDAO = new PersonnelDAO();

    public void ajouterAgentNettoyage(AgentNettoyage agentNettoyage) {
        personnelDAO.ajouterPersonnel(agentNettoyage);

    }

    public AgentNettoyage getAgentNettoyageById(int id) {
        AgentNettoyage agentNettoyage = (AgentNettoyage) personnelDAO.getPersonnelById(id);

        return agentNettoyage;
    }

    public void supprimerAgentNettoyage(int id) {
        personnelDAO.supprimerPersonnel(id);
    }

    public void modifierAgentNettoyage(AgentNettoyage agentNettoyage) {
        personnelDAO.modifierPersonnel(agentNettoyage);

    }

    public Vector<AgentNettoyage> getTousLesAgentsNettoyage() {
        Vector<AgentNettoyage> agentsNettoyage = new Vector<>();
        Vector<Personnel> personnels = personnelDAO.getTousLesPersonnels();

        for (Personnel personnel : personnels) {
            if (personnel instanceof AgentNettoyage) {
                agentsNettoyage.add((AgentNettoyage) personnel);
            }
        }

        return agentsNettoyage;
    }
}
