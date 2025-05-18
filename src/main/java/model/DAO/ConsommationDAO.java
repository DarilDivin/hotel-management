package model.DAO;
import java.sql.*;
import java.util.*;
import model.Consommation;
import model.Sejour;
import model.Produit;

public class ConsommationDAO {
    public void ajouterConsommation(Consommation consommation) {
        String sql = "INSERT INTO Consommation (sejour_id, produit_id, quantite) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, consommation.getSejour().getId());
            stmt.setInt(2, consommation.getProduit().getId());
            stmt.setInt(3, consommation.getQuantite());
            stmt.executeUpdate();

            // Récupérer l'identifiant généré automatiquement
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    consommation.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Échec de la récupération de l'identifiant généré.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Consommation getConsommationById(int id) {
        String sql = "SELECT * FROM Consommation WHERE id = ?";
        Consommation consommation = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Sejour sejour = new SejourDAO().getSejourById(rs.getInt("sejour_id")); // Récupérer le séjour par ID
                Produit produit = new ProduitDAO().getProduitById(rs.getInt("produit_id")); // Récupérer le produit par ID

                consommation = new Consommation(
                        sejour,
                        produit,
                        rs.getInt("quantite")
                );
                consommation.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consommation;
    }

    public void supprimerConsommation(int id) {
        String sql = "DELETE FROM Consommation WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierConsommation(Consommation consommation) {
        String sql = "UPDATE Consommation SET sejour_id = ?, produit_id = ?, quantite = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, consommation.getSejour().getId());
            stmt.setInt(2, consommation.getProduit().getId());
            stmt.setInt(3, consommation.getQuantite());
            stmt.setInt(4, consommation.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vector<Consommation> getTousLesConsommations() {
        Vector<Consommation> consommations = new Vector<>();
        String sql = "SELECT * FROM Consommation";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Sejour sejour = new SejourDAO().getSejourById(rs.getInt("sejour_id")); // Récupérer le séjour par ID
                Produit produit = new ProduitDAO().getProduitById(rs.getInt("produit_id")); // Récupérer le produit par ID

                Consommation consommation = new Consommation(
                        sejour,
                        produit,
                        rs.getInt("quantite")
                );
                consommation.setId(rs.getInt("id"));
                consommations.add(consommation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consommations;
    }
}
