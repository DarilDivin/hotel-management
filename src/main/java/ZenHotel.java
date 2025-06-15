import java.io.File;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;

public class ZenHotel {
    public static void main(String[] args) {
        try {
            String dbPath = getDatabasePath();
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            System.out.println("Connexion OK : " + dbPath);

            // 🔁 Lancer ta fenêtre principale ici
            Main.main(args); // remplace par le nom réel de ta classe Swing

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getDatabasePath() throws URISyntaxException {
        File jarFile = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        File jarDir = jarFile.getParentFile();
        File dbFile = new File(jarDir, "db/database.db");
        return dbFile.getAbsolutePath();
    }
}
