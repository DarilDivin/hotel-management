package model.DAO;
import java.sql.*;
import java.util.*;
import model.Personnel;
import model.Hotel;

public class PersonnelDAO {
    public void ajouterPersonnel(Personnel personnel) {
        String sql = "INSERT INTO Personnel (nom, prenom, email, password, hotel_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, personnel.getNom());
            stmt.setString(2, personnel.getPrenom());
            stmt.setString(3, personnel.getEmail());
            stmt.setString(4, personnel.getPassword());
            stmt.setInt(5, personnel.getHotel().getId()); // Utiliser l'ID de l'hôtel
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

    public Personnel getPersonnelById(int id) {
        String sql = "SELECT * FROM Personnel WHERE id = ?";
        Personnel personnel = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Hotel hotel = new HotelDAO().getHotelById(rs.getInt("hotel_id")); // Récupérer l'hôtel par ID
                personnel = new Personnel(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("password"),
                        hotel
                );
                personnel.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personnel;
    }

    public void supprimerPersonnel(int id) {
        String sql = "DELETE FROM Personnel WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierPersonnel(Personnel personnel) {
        String sql = "UPDATE Personnel SET nom = ?, prenom = ?, email = ?, password = ?, hotel_id = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, personnel.getNom());
            stmt.setString(2, personnel.getPrenom());
            stmt.setString(3, personnel.getEmail());
            stmt.setString(4, personnel.getPassword());
            stmt.setInt(5, personnel.getHotel().getId()); // Utiliser l'ID de l'hôtel
            stmt.setInt(6, personnel.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vector<Personnel> getTousLesPersonnels() {
        Vector<Personnel> personnels = new Vector<>();
        String sql = "SELECT * FROM Personnel";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Hotel hotel = new HotelDAO().getHotelById(rs.getInt("hotel_id")); // Récupérer l'hôtel par ID
                Personnel personnel = new Personnel(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("password"),
                        hotel
                );
                personnel.setId(rs.getInt("id"));
                personnels.add(personnel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personnels;
    }
}