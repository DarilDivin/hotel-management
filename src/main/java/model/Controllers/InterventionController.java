package model.Controllers;

import model.Intervention;
import model.DAO.InterventionDAO;
import java.util.Vector;

public class InterventionController {
    private InterventionDAO interventionDAO;

    public InterventionController(InterventionDAO interventionDAO) {
        this.interventionDAO = interventionDAO;
    }

    public void ajouterIntervention(Intervention intervention) {
        interventionDAO.ajouterIntervention(intervention);
    }

    public Intervention getInterventionById(int id) {
        return interventionDAO.getInterventionById(id);
    }

    public void supprimerIntervention(int id) {
        interventionDAO.supprimerIntervention(id);
    }

    public void modifierIntervention(Intervention intervention) {
        interventionDAO.modifierIntervention(intervention);
    }

    public Vector<Intervention> getTousLesInterventions() {
        return interventionDAO.getTousLesInterventions();
    }
}
