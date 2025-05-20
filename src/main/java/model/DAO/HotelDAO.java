package model.DAO;
import model.Client;
import model.Hotel;

import java.sql.*;
import java.util.*;

public class HotelDAO {

    public Vector<Hotel> getAllHotels() {
        Vector<Hotel> hotels = new Vector<>();
        String sql = "SELECT * FROM Hotel";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Hotel hotel = new Hotel(rs.getString("nom"), rs.getString("adresse"));
                hotel.setId(rs.getInt("id"));
                hotels.add(hotel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotels;
    }

    public void ajouterHotel(Hotel hotel) {
        String sql = "INSERT INTO Hotel (nom, adresse) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, hotel.getNom());
            stmt.setString(2, hotel.getAdresse());
            stmt.executeUpdate();

            // Récupérer l'identifiant généré automatiquement
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    hotel.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Échec de la récupération de l'identifiant généré.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eventuelles erreurs si l'id n'est pas présent dans la table
    public Hotel getHotelById(int id) {
        String sql = "SELECT * FROM Hotel WHERE id = ?";
        Hotel hotel = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                hotel = new Hotel(rs.getString("nom"), rs.getString("adresse"));
                hotel.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotel;
    }

    public Hotel getHotelByNom(String nom) {
        String sql = "SELECT * FROM Hotel WHERE nom = ?";
        Hotel hotel = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                hotel = new Hotel(rs.getString("nom"), rs.getString("adresse"));
                hotel.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotel;
    }

    public void supprimerHotel(int id) {
        String sql = "DELETE FROM Hotel WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierHotel(Hotel hotel) {
        String sql = "UPDATE Hotel SET nom = ?, adresse = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, hotel.getNom());
            stmt.setString(2, hotel.getAdresse());
            stmt.setInt(3, hotel.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vector<Client> getClients(Hotel hotel) {
        ClientDAO clientDAO = new ClientDAO();
        Vector<Client> clients = clientDAO.getAllClients();
        Vector<Client> clientsHotel = new Vector<Client>();

        for (Client client : clients) {
            if(client.getHotel().getId() == hotel.getId()) {
                clientsHotel.add(client);
            }
        }
        return clientsHotel;
    }

}
