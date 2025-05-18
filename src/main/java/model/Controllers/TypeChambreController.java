package model.Controllers;

import model.TypeChambre;
import model.DAO.TypeChambreDAO;
import java.util.Vector;

public class TypeChambreController {
    private static TypeChambreDAO typeChambreDAO = new TypeChambreDAO();

    public static void ajouterTypeChambre(TypeChambre typeChambre) {
        typeChambreDAO.ajouterTypeChambre(typeChambre);
    }

    public static TypeChambre getTypeChambreById(int id) {
        return typeChambreDAO.getTypeChambreById(id);
    }

    public static void supprimerTypeChambre(int id) {
        typeChambreDAO.supprimerTypeChambre(id);
    }

    public static void modifierTypeChambre(TypeChambre typeChambre) {
        typeChambreDAO.modifierTypeChambre(typeChambre);
    }

    public static Vector<TypeChambre> getTousLesTypesChambre() {
        return typeChambreDAO.getTousLesTypesChambre();
    }

    public static TypeChambre getTypeChambreByType(String type) {
        return typeChambreDAO.getTypeChambreByType(type);
    }
}