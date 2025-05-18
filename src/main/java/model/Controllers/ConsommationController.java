package model.Controllers;

import model.Consommation;
import model.DAO.ConsommationDAO;
import java.util.Vector;

public class ConsommationController {
    private static ConsommationDAO consommationDAO = new ConsommationDAO();

    public static void ajouterConsommation(Consommation consommation) {
        consommationDAO.ajouterConsommation(consommation);
    }

    public static Consommation getConsommationById(int id) {
        return consommationDAO.getConsommationById(id);
    }

    public static void supprimerConsommation(int id) {
        consommationDAO.supprimerConsommation(id);
    }

    public static void modifierConsommation(Consommation consommation) {
        consommationDAO.modifierConsommation(consommation);
    }

    public static Vector<Consommation> getTousLesConsommations() {
        return consommationDAO.getTousLesConsommations();
    }
}
