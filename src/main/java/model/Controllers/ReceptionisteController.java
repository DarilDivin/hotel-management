package model.Controllers;

import model.Receptioniste;
import model.DAO.ReceptionisteDAO;
import java.util.Vector;

public class ReceptionisteController {
    private ReceptionisteDAO receptionisteDAO;

    public ReceptionisteController(ReceptionisteDAO receptionisteDAO) {
        this.receptionisteDAO = receptionisteDAO;
    }

    public void ajouterReceptioniste(Receptioniste receptioniste) {
        receptionisteDAO.ajouterReceptioniste(receptioniste);
    }

    public Receptioniste getReceptionisteById(int id) {
        return receptionisteDAO.getReceptionisteById(id);
    }

    public void supprimerReceptioniste(int id) {
        receptionisteDAO.supprimerReceptioniste(id);
    }

    public void modifierReceptioniste(Receptioniste receptioniste) {
        receptionisteDAO.modifierReceptioniste(receptioniste);
    }

    public Vector<Receptioniste> getTousLesReceptionistes() {
        return receptionisteDAO.getTousLesReceptionistes();
    }
}
