package model.DAO;
import java.sql.*;
import java.util.*;
import model.Client;
import model.Hotel;

public class ClientDAO {
    public void addClient(Client client) {
        String sql = "INSERT INTO Client (nom, prenom, email, hotel_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getPrenom());
            stmt.setString(3, client.getEmail());
            stmt.setInt(4, client.getHotel().getId()); // Utiliser l'ID de l'hôtel
            stmt.executeUpdate();

            HotelDAO hotelDAO = new HotelDAO();
            // Récupérer l'identifiant généré automatiquement
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                // Mettre à jour l'hotel en le resélectionnant dans la base de données
                client.setHotel(hotelDAO.getHotelById(client.getHotel().getId()));
                if (generatedKeys.next()) {
                    client.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Échec de la récupération de l'identifiant généré.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Client getClientById(int id) {
        String sql = "SELECT * FROM Client WHERE id = ?";
        Client client = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Hotel hotel = new HotelDAO().getHotelById(rs.getInt("hotel_id")); // Récupérer l'hôtel par ID
                client = new Client(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        hotel
                );
                client.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return client;
    }

    public void deleteClient(int id) {
        String sql = "DELETE FROM Client WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateClient(Client client) {
        String sql = "UPDATE Client SET nom = ?, prenom = ?, email = ?, hotel_id = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getPrenom());
            stmt.setString(3, client.getEmail());
            stmt.setInt(4, client.getHotel().getId()); // Utiliser l'ID de l'hôtel
            stmt.setInt(5, client.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vector<Client> getAllClients() {
        Vector<Client> clients = new Vector<>();
        String sql = "SELECT * FROM Client";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Hotel hotel = new HotelDAO().getHotelById(rs.getInt("hotel_id")); // Récupérer l'hôtel par ID
                Client client = new Client(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        hotel
                );
                client.setId(rs.getInt("id"));
                clients.add(client);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }
}
