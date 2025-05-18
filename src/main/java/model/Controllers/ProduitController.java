package model.Controllers;

import model.Produit;
import model.DAO.ProduitDAO;
import java.util.Vector;

public class ProduitController {
    private static ProduitDAO produitDAO = new ProduitDAO();

    public static void ajouterProduit(Produit produit) {
        produitDAO.addProduit(produit);
    }

    public static Produit getProduitById(int id) {
        return produitDAO.getProduitById(id);
    }

    public static void supprimerProduit(int id) {
        produitDAO.deleteProduit(id);
    }

    public static void modifierProduit(Produit produit) {
        produitDAO.updateProduit(produit);
    }

    public static Vector<Produit> getTousLesProduits() {
        return produitDAO.getAllProduits();
    }
}
