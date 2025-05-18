package model.Controllers;

import model.Sejour;
import model.DAO.SejourDAO;
import java.util.Vector;

public class SejourController {
    private SejourDAO sejourDAO;

    public SejourController(SejourDAO sejourDAO) {
        this.sejourDAO = sejourDAO;
    }

    public void ajouterSejour(Sejour sejour) {
        sejourDAO.ajouterSejour(sejour);
    }

    public Sejour getSejourById(int id) {
        return sejourDAO.getSejourById(id);
    }

    public void supprimerSejour(int id) {
        sejourDAO.supprimerSejour(id);
    }

    public void modifierSejour(Sejour sejour) {
        sejourDAO.modifierSejour(sejour);
    }

    public Vector<Sejour> getTousLesSejours() {
        return sejourDAO.getTousLesSejours();
    }
}
