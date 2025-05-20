package model.DAO;
import java.sql.*;
import java.util.*;
import model.Receptioniste;
import model.Personnel;
import model.Reservation;
import model.Sejour;

public class ReceptionisteDAO {
    private PersonnelDAO personnelDAO = new PersonnelDAO();

    public void ajouterReceptioniste(Personnel personnel) {
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

    public Receptioniste getReceptionisteById(int id) {
        Personnel personnelReceptioniste = personnelDAO.getReceptionisteById(id);
        Receptioniste receptioniste = new Receptioniste(personnelReceptioniste);
        return receptioniste;
    }

    public void supprimerReceptioniste(int id) {
        personnelDAO.supprimerPersonnel(id);
    }

    public void modifierReceptioniste(Receptioniste receptioniste) {
        personnelDAO.modifierPersonnel(receptioniste);

    }

    public Vector<Receptioniste> getTousLesReceptionistes() {
        Vector<Receptioniste> receptionistes = new Vector<>();
        Vector<Personnel> personnels = personnelDAO.getTousLesPersonnels();

        for (Personnel personnel : personnels) {
            if (personnel.getRole() == "receptioniste") {
                Receptioniste receptioniste = new Receptioniste(personnel);
                receptionistes.add(receptioniste);
            }
        }

        return receptionistes;
    }

    public Vector<Sejour> getSejours(Receptioniste receptioniste) {
        SejourDAO sejourDAO = new SejourDAO();
        Vector<Sejour> sejours = sejourDAO.getTousLesSejours();

        Vector<Sejour> sejoursReceptioniste = new Vector<Sejour>();

        for(Sejour sejour : sejours) {
            if(sejour.getReceptioniste().getId() == receptioniste.getId()) {
                sejoursReceptioniste.add(sejour);
            }
        }
        return sejoursReceptioniste;
    }

    public Vector<Reservation> getReservations(Receptioniste receptioniste) {
        ReservationDAO reservationDAO = new ReservationDAO();
        Vector<Reservation> reservations = reservationDAO.getTousLesReservations();
        Vector<Reservation> reservationsReceptioniste = new Vector<Reservation>();
        for(Reservation reservation : reservations) {
            if(reservation.getReceptionniste().getId() == receptioniste.getId()) {
                reservationsReceptioniste.add(reservation);
            }
        }
        return reservationsReceptioniste;
    }
}
