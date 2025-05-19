package model.DAO;
import java.sql.*;
import java.util.*;
import model.AgentNettoyage;
import model.Personnel;


public class AgentNettoyageDAO {

    private PersonnelDAO personnelDAO = new PersonnelDAO();

    public void ajouterAgentNettoyage(Personnel personnel) {
        String sql = "INSERT INTO Personnel (nom, prenom, email, password, role , hotel_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, personnel.getNom());
            stmt.setString(2, personnel.getPrenom());
            stmt.setString(3, personnel.getEmail());
            stmt.setString(4, personnel.getPassword());
            stmt.setString(5, personnel.getRole());
            stmt.setInt(6, personnel.getHotel().getId()); // Utiliser l'ID de l'hôtel
            stmt.executeUpdate();

            HotelDAO hotelDAO = new HotelDAO();
            // Récupérer l'identifiant généré automatiquement
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                personnel.setHotel(hotelDAO.getHotelById(personnel.getHotel().getId()));
                if (generatedKeys.next()) {
                    personnel.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Échec de la récupération de l'identifiant généré.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public AgentNettoyage getAgentNettoyageById(int id) {
        Personnel personelAgentNettoyage = personnelDAO.getAgentNettoyageById(id);
        AgentNettoyage agentNettoyage = new AgentNettoyage(personelAgentNettoyage);

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
            if (personnel.getRole() == "agent de nettoyage") {
                AgentNettoyage agentNettoyage = new AgentNettoyage(personnel);
                agentsNettoyage.add(agentNettoyage);
            }
        }

        return agentsNettoyage;
    }
}
