package model.DAO;

import java.sql.*;
import java.util.*;
import java.io.File;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:src/main/java/db/database.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initializeDatabase() {
        createTables();
    }

    private static void createTables() {
        String createHotelsTable = "CREATE TABLE IF NOT EXISTS hotels (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nom TEXT NOT NULL," +
                "adresse TEXT NOT NULL" +
                ")";

        String createChambresTable = "CREATE TABLE IF NOT EXISTS chambres (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hotel_id INTEGER," +
                "numero TEXT NOT NULL," +
                "type TEXT NOT NULL," +
                "FOREIGN KEY (hotel_id) REFERENCES hotels (id)" +
                ")";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createHotelsTable);
            stmt.execute(createChambresTable);

            System.out.println("Tables créées avec succès.");

        } catch (SQLException e) {
            System.err.println("Erreur lors de la création des tables : " + e.getMessage());
        }
    }
}
