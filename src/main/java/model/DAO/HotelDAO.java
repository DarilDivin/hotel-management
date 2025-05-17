package model.DAO;
import model.Hotel;

import java.sql.*;
import java.util.*;

public class HotelDAO {
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
}
