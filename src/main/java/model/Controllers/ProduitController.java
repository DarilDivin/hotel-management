package model.Controllers;

import model.Produit;
import model.DAO.ProduitDAO;
import java.util.Vector;

public class ProduitController {
    private ProduitDAO produitDAO;

    public ProduitController(ProduitDAO produitDAO) {
        this.produitDAO = produitDAO;
    }

    public void ajouterProduit(Produit produit) {
        produitDAO.addProduit(produit);
    }

    public Produit getProduitById(int id) {
        return produitDAO.getProduitById(id);
    }

    public void supprimerProduit(int id) {
        produitDAO.deleteProduit(id);
    }

    public void modifierProduit(Produit produit) {
        produitDAO.updateProduit(produit);
    }

    public Vector<Produit> getTousLesProduits() {
        return produitDAO.getAllProduits();
    }
}
