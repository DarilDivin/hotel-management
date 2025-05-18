package model.Controllers;

import model.Chambre;
import model.DAO.ChambreDAO;
import java.util.Vector;

public class ChambreController {
    private static ChambreDAO chambreDAO = new ChambreDAO();

    public static void ajouterChambre(Chambre chambre) {
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
}
