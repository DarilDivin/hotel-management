package model.Controllers;

import model.TypeChambre;
import model.DAO.TypeChambreDAO;
import java.util.Vector;

public class TypeChambreController {
    private TypeChambreDAO typeChambreDAO;

    public TypeChambreController(TypeChambreDAO typeChambreDAO) {
        this.typeChambreDAO = typeChambreDAO;
    }

    public void ajouterTypeChambre(TypeChambre typeChambre) {
        typeChambreDAO.ajouterTypeChambre(typeChambre);
    }

    public TypeChambre getTypeChambreById(int id) {
        return typeChambreDAO.getTypeChambreById(id);
    }

    public void supprimerTypeChambre(int id) {
        typeChambreDAO.supprimerTypeChambre(id);
    }

    public void modifierTypeChambre(TypeChambre typeChambre) {
        typeChambreDAO.modifierTypeChambre(typeChambre);
    }

    public Vector<TypeChambre> getTousLesTypesChambre() {
        return typeChambreDAO.getTousLesTypesChambre();
    }
}
