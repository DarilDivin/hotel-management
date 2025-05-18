package model.Controllers;

import model.Chambre;
import model.DAO.ChambreDAO;
import java.util.Vector;

public class ChambreController {
    private ChambreDAO chambreDAO;

    public ChambreController(ChambreDAO chambreDAO) {
        this.chambreDAO = chambreDAO;
    }

    public void ajouterChambre(Chambre chambre) {
        chambreDAO.addChambre(chambre);
    }

    public Chambre getChambreById(int id) {
        return chambreDAO.getChambreById(id);
    }

    public void supprimerChambre(int id) {
        chambreDAO.deleteChambre(id);
    }

    public void modifierChambre(Chambre chambre) {
        chambreDAO.updateChambre(chambre);
    }

    public Vector<Chambre> getTousLesChambres() {
        return chambreDAO.getAllChambres();
    }
}
