package model.Controllers;

import model.Consommation;
import model.DAO.ConsommationDAO;
import java.util.Vector;

public class ConsommationController {
    private ConsommationDAO consommationDAO;

    public ConsommationController(ConsommationDAO consommationDAO) {
        this.consommationDAO = consommationDAO;
    }

    public void ajouterConsommation(Consommation consommation) {
        consommationDAO.ajouterConsommation(consommation);
    }

    public Consommation getConsommationById(int id) {
        return consommationDAO.getConsommationById(id);
    }

    public void supprimerConsommation(int id) {
        consommationDAO.supprimerConsommation(id);
    }

    public void modifierConsommation(Consommation consommation) {
        consommationDAO.modifierConsommation(consommation);
    }

    public Vector<Consommation> getTousLesConsommations() {
        return consommationDAO.getTousLesConsommations();
    }
}
