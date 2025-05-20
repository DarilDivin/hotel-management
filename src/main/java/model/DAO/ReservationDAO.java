package model.DAO;
import java.sql.*;
import java.util.*;
import java.util.Date;
import model.Reservation;
import model.Receptioniste;
import model.Chambre;
import model.Client;
import model.Sejour;


public class ReservationDAO {
    public void ajouterReservation(Reservation reservation) {
        String sql = "INSERT INTO Reservation (statut, date_debut, date_fin, client_id, receptionniste_id, chambre_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setBoolean(1, reservation.getStatut());
            stmt.setTimestamp(2, new Timestamp(reservation.getDateDebut().getTime()));
            stmt.setTimestamp(3, new Timestamp(reservation.getDateFin().getTime()));
            stmt.setInt(4, reservation.getClient().getId());
            stmt.setInt(5, reservation.getReceptionniste().getId());
            stmt.setInt(6, reservation.getChambre().getId());
            stmt.executeUpdate();

            // Récupérer l'identifiant généré automatiquement
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reservation.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Échec de la récupération de l'identifiant généré.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Reservation getReservationById(int id) {
        String sql = "SELECT * FROM Reservation WHERE id = ?";
        Reservation reservation = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Client client = new ClientDAO().getClientById(rs.getInt("client_id"));
                Receptioniste receptioniste = new Receptioniste(new PersonnelDAO().getPersonnelById(rs.getInt("receptionniste_id")));
                Chambre chambre = new ChambreDAO().getChambreById(rs.getInt("chambre_id"));

                reservation = new Reservation(
                        client,
                        new Date(rs.getTimestamp("date_debut").getTime()),
                        new Date(rs.getTimestamp("date_fin").getTime()),
                        receptioniste,
                        chambre
                );
                reservation.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservation;
    }

    public void supprimerReservation(int id) {
        String sql = "DELETE FROM Reservation WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierReservation(Reservation reservation) {
        String sql = "UPDATE Reservation SET statut = ? , date_debut = ?, date_fin = ?, client_id = ?, receptionniste_id = ?, chambre_id = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, reservation.getStatut());
            stmt.setTimestamp(2, new Timestamp(reservation.getDateDebut().getTime()));
            stmt.setTimestamp(3, new Timestamp(reservation.getDateFin().getTime()));
            stmt.setInt(4, reservation.getClient().getId());
            stmt.setInt(5, reservation.getReceptionniste().getId());
            stmt.setInt(6, reservation.getChambre().getId());
            stmt.setInt(7, reservation.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vector<Reservation> getTousLesReservations() {
        Vector<Reservation> reservations = new Vector<>();
        String sql = "SELECT * FROM Reservation";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Client client = new ClientDAO().getClientById(rs.getInt("client_id"));
                Receptioniste receptioniste = new Receptioniste(new PersonnelDAO().getPersonnelById(rs.getInt("receptionniste_id")));
                Chambre chambre = new ChambreDAO().getChambreById(rs.getInt("chambre_id"));

                Reservation reservation = new Reservation(
                        client,
                        new Date(rs.getTimestamp("date_debut").getTime()),
                        new Date(rs.getTimestamp("date_fin").getTime()),
                        receptioniste,
                        chambre
                );
                reservation.setId(rs.getInt("id"));
                if(rs.getInt("statut") == 1) {
                    reservation.setStatut(true);
                }
                reservations.add(reservation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    public void validerReservation(Reservation reservation) {
        SejourDAO sejourDAO = new SejourDAO();
        reservation.setStatut(true);
        Sejour sejour = new Sejour(reservation);
        this.modifierReservation(reservation);
        sejourDAO.ajouterSejour(sejour);
    }

    public void retirerValidationReservation(Reservation reservation) {
        SejourDAO sejourDAO = new SejourDAO();
        reservation.setStatut(false);
        Sejour sejour = new Sejour(reservation);
        this.modifierReservation(reservation);
        sejourDAO.supprimerSejour(sejour.getId());
    }
}
