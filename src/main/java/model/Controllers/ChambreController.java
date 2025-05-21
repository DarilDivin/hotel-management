package model.Controllers;

import model.Chambre;
import model.DAO.ChambreDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.Vector;

public class ChambreController {
    private static final String UPLOAD_DIR = "images/chambre/";
    private static final String[] ALLOWED_EXTENSIONS = {".jpg", ".jpeg", ".png", ".gif"};
    private static final ChambreDAO chambreDAO = new ChambreDAO();

    private ChambreController() {
        // Constructeur privé pour empêcher l'instanciation
    }

    public static void ajouterChambre(Chambre chambre, File imageFile) throws IOException {
        if (imageFile != null && imageFile.exists()) {
            // Vérifier l'extension du fichier
            String extension = getFileExtension(imageFile.getName());
            if (!isValidExtension(extension)) {
                throw new IllegalArgumentException("Type de fichier non autorisé. Extensions permises: " + 
                    String.join(", ", ALLOWED_EXTENSIONS));
            }

            // Générer un nom unique pour l'image
            String nomFichier = UUID.randomUUID().toString() + extension;

            // Créer le dossier de destination s'il n'existe pas
            File dossier = new File(UPLOAD_DIR);
            if (!dossier.exists()) {
                if (!dossier.mkdirs()) {
                    throw new IOException("Impossible de créer le dossier de destination");
                }
            }

            // Copier le fichier
            File fichierDestination = new File(dossier, nomFichier);
            try (FileInputStream in = new FileInputStream(imageFile);
                 FileOutputStream out = new FileOutputStream(fichierDestination)) {

                byte[] buffer = new byte[8192];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }

                chambre.setImage(nomFichier);
            } catch (IOException e) {
                throw new IOException("Erreur lors de la sauvegarde de l'image: " + e.getMessage(), e);
            }
        }

        chambreDAO.addChambre(chambre);
    }

    private static String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        return lastDotIndex != -1 ? fileName.substring(lastDotIndex).toLowerCase() : "";
    }

    private static boolean isValidExtension(String extension) {
        for (String allowedExt : ALLOWED_EXTENSIONS) {
            if (allowedExt.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    public static Chambre getChambreById(int id) {
        return chambreDAO.getChambreById(id);
    }

    public static void supprimerChambre(int id) {
        chambreDAO.deleteChambre(id);
    }

    public static void modifierChambre(Chambre chambre) {
        chambreDAO.updateChambre(chambre);
    }

    public static Vector<Chambre> getTousLesChambres() {
        return chambreDAO.getAllChambres();
    }

    public static Chambre getChambreByNumero(String numero) {
        return chambreDAO.getChambreByNumero(numero);
    }
}