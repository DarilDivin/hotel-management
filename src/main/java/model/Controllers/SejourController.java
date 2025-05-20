package model.Controllers;

import model.Consommation;
import model.Sejour;
import model.DAO.SejourDAO;
import java.util.Vector;

public class SejourController {
    private static SejourDAO sejourDAO = new SejourDAO();

    public static void ajouterSejour(Sejour sejour) {
        sejourDAO.ajouterSejour(sejour);
    }

    public static Sejour getSejourById(int id) {
        return sejourDAO.getSejourById(id);
    }

    public static void supprimerSejour(int id) {
        sejourDAO.supprimerSejour(id);
    }

    public static void modifierSejour(Sejour sejour) {
        sejourDAO.modifierSejour(sejour);
    }

    public static Vector<Sejour> getTousLesSejours() {
        return sejourDAO.getTousLesSejours();
    }

    public static Vector<Consommation> getConsommations(Sejour sejour) {
        return sejourDAO.getConsommations(sejour);
    }
}
