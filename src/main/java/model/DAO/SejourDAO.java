package model.DAO;
import java.sql.*;
import java.util.*;
import model.Sejour;
import model.Reservation;

public class SejourDAO {
    public void ajouterSejour(Sejour sejour) {
        String sql = "INSERT INTO Sejour (reservation_id) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, sejour.getReservation().getId());
            stmt.executeUpdate();

            // Récupérer l'identifiant généré automatiquement
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    sejour.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Échec de la récupération de l'identifiant généré.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Sejour getSejourById(int id) {
        String sql = "SELECT * FROM Sejour WHERE id = ?";
        Sejour sejour = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Reservation reservation = new ReservationDAO().getReservationById(rs.getInt("reservation_id")); // Récupérer la réservation par ID

                sejour = new Sejour(reservation);
                sejour.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sejour;
    }

    public void supprimerSejour(int id) {
        String sql = "DELETE FROM Sejour WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierSejour(Sejour sejour) {
        String sql = "UPDATE Sejour SET reservation_id = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sejour.getReservation().getId());
            stmt.setInt(2, sejour.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vector<Sejour> getTousLesSejours() {
        Vector<Sejour> sejours = new Vector<>();
        String sql = "SELECT * FROM Sejour";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Reservation reservation = new ReservationDAO().getReservationById(rs.getInt("reservation_id")); // Récupérer la réservation par ID

                Sejour sejour = new Sejour(reservation);
                sejour.setId(rs.getInt("id"));
                sejours.add(sejour);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sejours;
    }
}
