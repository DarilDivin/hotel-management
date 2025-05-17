package model.DAO;

import model.Hotel;
import model.Chambre;
import model.TypeChambre;

import java.util.Vector;

public class Test {
    public static void main(String[] args) {
        TypeChambre simple = new TypeChambre("Chambre simple");
        simple.setId(1);

        Chambre chambre = new Chambre(simple, "15AB200", 1516.16 , 10.5);
        ChambreDAO chambreDAO = new ChambreDAO();

        chambreDAO.addChambre(chambre);
        System.out.println(chambre.getTypeChambre().getType());

        chambre.setNumero("5525");
        chambreDAO.updateChambre(chambre);
    }
}
