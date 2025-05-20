package model.DAO;
import java.sql.*;
import java.util.*;
import java.util.Date;
import model.Intervention;
import model.Chambre;
import model.AgentNettoyage;

public class InterventionDAO {
    public void ajouterIntervention(Intervention intervention) {
        String sql = "INSERT INTO Intervention (date, chambre_id, agent_id) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setTimestamp(1, new Timestamp(intervention.getDate().getTime()));
            stmt.setInt(2, intervention.getChambre().getId());
            stmt.setInt(3, intervention.getAgentNettoyage().getId());
            stmt.executeUpdate();

            // Récupérer l'identifiant généré automatiquement
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    intervention.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Échec de la récupération de l'identifiant généré.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Intervention getInterventionById(int id) {
        String sql = "SELECT * FROM Intervention WHERE id = ?";
        Intervention intervention = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Chambre chambre = new ChambreDAO().getChambreById(rs.getInt("chambre_id"));
                AgentNettoyage agentNettoyage = (AgentNettoyage) new PersonnelDAO().getPersonnelById(rs.getInt("agent_id"));

                intervention = new Intervention(
                        chambre,
                        agentNettoyage,
                        new Date(rs.getTimestamp("date").getTime())
                );
                intervention.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return intervention;
    }

    public void supprimerIntervention(int id) {
        String sql = "DELETE FROM Intervention WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierIntervention(Intervention intervention) {
        String sql = "UPDATE Intervention SET date = ?, chambre_id = ?, agent_id = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, new Timestamp(intervention.getDate().getTime()));
            stmt.setInt(2, intervention.getChambre().getId());
            stmt.setInt(3, intervention.getAgentNettoyage().getId());
            stmt.setInt(4, intervention.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vector<Intervention> getTousLesInterventions() {
        Vector<Intervention> interventions = new Vector<>();
        String sql = "SELECT * FROM Intervention";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Chambre chambre = new ChambreDAO().getChambreById(rs.getInt("chambre_id"));
                AgentNettoyage agentNettoyage = (AgentNettoyage) new PersonnelDAO().getPersonnelById(rs.getInt("agent_id"));

                Intervention intervention = new Intervention(
                        chambre,
                        agentNettoyage,
                        new Date(rs.getTimestamp("date").getTime())
                );
                intervention.setId(rs.getInt("id"));
                interventions.add(intervention);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return interventions;
    }

public Vector<Intervention> creerInterventions(AgentNettoyage agentNettoyage) {
    Vector<Intervention> interventions = new Vector<Intervention>();
    
    // Vérification des interventions pour le jour actuel
    Date aujourdhui = new Date();
    if (existeInterventionsPourDate(aujourdhui)) {
        return interventions; // Retourne un vecteur vide si des interventions existent déjà
    }
    
    HotelDAO hotelDAO = new HotelDAO();
    Vector<Chambre> chambresHotel = hotelDAO.getChambres(agentNettoyage.getHotel());
    int nbr_chambres = chambresHotel.size();
    
    if (nbr_chambres == 0) {
        return interventions;
    }

    Random random = new Random();
    int nbrInterventions = random.nextInt(nbr_chambres) + 1;
    
    for (int i = 0; i < nbrInterventions; i++) {
        int indexChambre = random.nextInt(nbr_chambres);
        Chambre chambreAleatoire = chambresHotel.get(indexChambre);
        
        Intervention intervention = new Intervention(
            chambreAleatoire,
            agentNettoyage,
            aujourdhui
        );
        this.ajouterIntervention(intervention);
        interventions.add(intervention);
    }
    
    return interventions;
}

    // Méthode auxiliaire pour vérifier l'existence d'interventions à une date donnée
    private boolean existeInterventionsPourDate(Date date) {
        String sql = "SELECT COUNT(*) FROM Intervention WHERE DATE(date) = DATE(?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, new Timestamp(date.getTime()));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}