package model.Controllers;

import model.Intervention;
import model.DAO.InterventionDAO;
import java.util.Vector;

public class InterventionController {
    private static InterventionDAO interventionDAO = new InterventionDAO();

    public static void ajouterIntervention(Intervention intervention) {
        interventionDAO.ajouterIntervention(intervention);
    }

    public static Intervention getInterventionById(int id) {
        return interventionDAO.getInterventionById(id);
    }

    public static void supprimerIntervention(int id) {
        interventionDAO.supprimerIntervention(id);
    }

    public static void modifierIntervention(Intervention intervention) {
        interventionDAO.modifierIntervention(intervention);
    }

    public static Vector<Intervention> getTousLesInterventions() {
        return interventionDAO.getTousLesInterventions();
    }
}
