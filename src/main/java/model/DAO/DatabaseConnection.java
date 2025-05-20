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

        String createHotelsTable = "CREATE TABLE IF NOT EXISTS Hotel (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    adresse TEXT NOT NULL, \n" +
                "    nom TEXT NOT NULL\n" +
                ");";

        String createTypeChambresTable = "CREATE TABLE IF NOT EXISTS TypeChambre (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    `type` TEXT NOT NULL\n" +
                ");";

        String createProduitsTable = "CREATE TABLE IF NOT EXISTS Produit (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    nom TEXT NOT NULL,\n" +
                "    prix FLOAT NOT NULL\n" +
                ");";

        String createChambresTable = "CREATE TABLE IF NOT EXISTS Chambre (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    numero TEXT NOT NULL,\n" +
                "    image TEXT NOT NULL,\n" +
                "    prix FLOAT NOT NULL,\n" +
                "    superficie FLOAT NOT NULL,\n" +
                "    type_id INTEGER NOT NULL,\n" +
                "    hotel_id INTEGER NOT NULL, \n" +
                "    FOREIGN KEY (type_id) REFERENCES TypeChambre(id)\n" +
                "    FOREIGN KEY (hotel_id) REFERENCES Hotel(id)\n" +
                ");";

        String createClientsTable = "CREATE TABLE IF NOT EXISTS Client (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    nom TEXT NOT NULL,\n" +
                "    prenom TEXT NOT NULL,\n" +
                "    email TEXT,\n" +
                "    hotel_id INTEGER NOT NULL,\n" +
                "    FOREIGN KEY (hotel_id) REFERENCES Hotel(id)\n" +
                ");";

        String createPersonnelsTable = "CREATE TABLE IF NOT EXISTS Personnel (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    nom TEXT NOT NULL,\n" +
                "    prenom TEXT NOT NULL,\n" +
                "    email TEXT NOT NULL,\n" +
                "    password TEXT NOT NULL,\n" +
                "    hotel_id INTEGER NOT NULL,\n" +
                "    `role` TEXT NOT NULL,\n" +
                "    FOREIGN KEY (hotel_id) REFERENCES Hotel(id)\n" +
                ");";

        String createReservationsTable = "CREATE TABLE IF NOT EXISTS Reservation (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    statut INTEGER CHECK (colonne1 IN (0, 1)),"+
                "    date_debut DATETIME NOT NULL,\n" +
                "    date_fin DATETIME NOT NULL,\n" +
                "    client_id INTEGER NOT NULL,\n" +
                "    receptionniste_id INTEGER NOT NULL,\n" +
                "    chambre_id INTEGER NOT NULL,\n" +
                "    FOREIGN KEY (client_id) REFERENCES Client(id),\n" +
                "    FOREIGN KEY (receptionniste_id) REFERENCES Personnel(id),\n" +
                "    FOREIGN KEY (chambre_id) REFERENCES Chambre(id)\n" +
                ");\n";

        String createSejoursTable = "CREATE TABLE IF NOT EXISTS Sejour (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    reservation_id INTEGER NOT NULL,\n" +
                "    FOREIGN KEY (reservation_id) REFERENCES Reservation(id)\n" +
                ");";

        String createConsommationsTable = "CREATE TABLE IF NOT EXISTS Consommation (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    sejour_id INTEGER NOT NULL,\n" +
                "    produit_id INTEGER NOT NULL,\n" +
                "    FOREIGN KEY (sejour_id) REFERENCES Sejour(id),\n" +
                "    FOREIGN KEY (produit_id) REFERENCES Produit(id)\n" +
                ");";

        String createInterventionsTable = "CREATE TABLE IF NOT EXISTS Intervention (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    date DATETIME NOT NULL,\n" +
                "    chambre_id INTEGER NOT NULL,\n" +
                "    agent_id INTEGER NOT NULL,\n" +
                "    FOREIGN KEY (chambre_id) REFERENCES Chambre(id),\n" +
                "    FOREIGN KEY (agent_id) REFERENCES Personnel(id)\n" +
                ");";

        try (Connection conn = getConnection();

             Statement stmt = conn.createStatement()) {

            stmt.execute(createHotelsTable);
            stmt.execute(createTypeChambresTable);
            stmt.execute(createProduitsTable);
            stmt.execute(createChambresTable);
            stmt.execute(createClientsTable);
            stmt.execute(createPersonnelsTable);
            stmt.execute(createReservationsTable);
            stmt.execute(createSejoursTable);
            stmt.execute(createConsommationsTable);
            stmt.execute(createInterventionsTable);

            System.out.println("Tables créées avec succès.");

        } catch (SQLException e) {
            System.err.println("Erreur lors de la création des tables : " + e.getMessage());
        }
    }
}
