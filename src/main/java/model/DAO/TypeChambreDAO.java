package model.DAO;
import java.sql.*;
import java.util.*;
import model.TypeChambre;

public class TypeChambreDAO {
    public void ajouterTypeChambre(TypeChambre typeChambre) {
        String sql = "INSERT INTO TypeChambre (type) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, typeChambre.getType());
            stmt.executeUpdate();

            // Récupérer l'identifiant généré automatiquement
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    typeChambre.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Échec de la récupération de l'identifiant généré.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TypeChambre getTypeChambreById(int id) {
        String sql = "SELECT * FROM TypeChambre WHERE id = ?";
        TypeChambre typeChambre = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                typeChambre = new TypeChambre(rs.getString("type"));
                typeChambre.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return typeChambre;
    }

    public TypeChambre getTypeChambreByType(String type) {
        String sql = "SELECT * FROM TypeChambre WHERE type = ?";
        TypeChambre typeChambre = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, type);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                typeChambre = new TypeChambre(rs.getString("type"));
                typeChambre.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return typeChambre;
    }

    public void supprimerTypeChambre(int id) {
        String sql = "DELETE FROM TypeChambre WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierTypeChambre(TypeChambre typeChambre) {
        String sql = "UPDATE TypeChambre SET type = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, typeChambre.getType());
            stmt.setInt(2, typeChambre.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vector<TypeChambre> getTousLesTypesChambre() {
        Vector<TypeChambre> typesChambre = new Vector<>();
        String sql = "SELECT * FROM TypeChambre";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TypeChambre typeChambre = new TypeChambre(rs.getString("type"));
                typeChambre.setId(rs.getInt("id"));
                typesChambre.add(typeChambre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return typesChambre;
    }
}
