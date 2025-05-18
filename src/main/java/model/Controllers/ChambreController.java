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
    private static ChambreDAO chambreDAO = new ChambreDAO();

    public static void ajouterChambre(Chambre chambre, File imageFile) {
        if (imageFile != null && imageFile.exists()) {
            // Générer un nom aléatoire pour l'image
            String extension = imageFile.getName().substring(imageFile.getName().lastIndexOf("."));
            String nomFichier = UUID.randomUUID().toString() + extension;

            // Définir le dossier de destination (par exemple, dans /uploads/chambres/)
            String dossierDestination = "/ressources/images/chambre/";
            File dossier = new File(dossierDestination);
            if (!dossier.exists()) {
                dossier.mkdirs(); // créer le dossier s’il n’existe pas
            }

            // Copier le fichier dans ce dossier
            File fichierDestination = new File(dossier, nomFichier);
            try (FileInputStream in = new FileInputStream(imageFile);
                 FileOutputStream out = new FileOutputStream(fichierDestination)) {

                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }

                // Mettre à jour l'objet Chambre avec le nom du fichier image
                chambre.setImage(nomFichier);

            } catch (IOException e) {
                System.err.println("Erreur lors de la sauvegarde de l'image : " + e.getMessage());
                return; // ou lancer une exception
            }
        }

        chambreDAO.addChambre(chambre);
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
