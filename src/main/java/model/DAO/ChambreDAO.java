package model.DAO;
import java.sql.*;
import java.util.*;
import model.Chambre;
import model.TypeChambre;

public class ChambreDAO {
    public void addChambre(Chambre chambre) {
        String sql = "INSERT INTO Chambre (numero, image, prix, superficie, type_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, chambre.getNumero());
            stmt.setString(1, chambre.getImage());
            stmt.setDouble(3, chambre.getPrix());
            stmt.setDouble(4, chambre.getSuperficie());
            stmt.setInt(5, chambre.getTypeChambre().getId());
            stmt.executeUpdate();

            TypeChambreDAO typeChambreDAO = new TypeChambreDAO();

            // Récupérer l'identifiant généré automatiquement
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                chambre.setTypeChambre(typeChambreDAO.getTypeChambreById(chambre.getTypeChambre().getId()));
                if (generatedKeys.next()) {
                    chambre.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Échec de la récupération de l'identifiant généré.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Chambre getChambreById(int id) {
        String sql = "SELECT * FROM Chambre WHERE id = ?";
        Chambre chambre = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                TypeChambreDAO typeChambreDAO = new TypeChambreDAO();
                chambre = new Chambre(
                        typeChambreDAO.getTypeChambreById(rs.getInt("type_id")),
                        rs.getString("numero"),
                        rs.getString("image"),
                        rs.getFloat("prix"),
                        rs.getFloat("superficie")
                );
                chambre.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chambre;
    }

    public void deleteChambre(int id) {
        String sql = "DELETE FROM Chambre WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateChambre(Chambre chambre) {
        String sql = "UPDATE Chambre SET numero = ?, image = ? , prix = ?, superficie = ?, type_id = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, chambre.getNumero());
            stmt.setString(2, chambre.getImage());
            stmt.setDouble(3, chambre.getPrix());
            stmt.setDouble(4, chambre.getSuperficie());
            stmt.setInt(5, chambre.getTypeChambre().getId());
            stmt.setInt(6, chambre.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vector<Chambre> getAllChambres() {
        Vector<Chambre> chambres = new Vector<>();
        String sql = "SELECT * FROM Chambre";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            TypeChambreDAO typeChambreDAO = new TypeChambreDAO();

            while (rs.next()) {
                Chambre chambre = new Chambre(
                        typeChambreDAO.getTypeChambreById(rs.getInt("type_id")),
                        rs.getString("numero"),
                        rs.getString("image"),
                        rs.getFloat("prix"),
                        rs.getFloat("superficie")
                );
                chambre.setId(rs.getInt("id"));
                chambres.add(chambre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chambres;
    }
}
