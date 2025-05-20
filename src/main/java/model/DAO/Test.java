package model.DAO;

import model.*;

import java.util.Date;

import model.Controllers.*;
import java.util.Vector;

public class Test {
    public static void main(String[] args) {

        Hotel hotel = new Hotel("Royal Hotel London", "1 Road of London");
        HotelController.ajouterHotel(hotel);
        TypeChambreController.ajouterTypeChambre(new TypeChambre("Simple"));
        TypeChambreController.ajouterTypeChambre(new TypeChambre("Double"));
        TypeChambreController.ajouterTypeChambre(new TypeChambre("Suite"));
        TypeChambreController.ajouterTypeChambre(new TypeChambre("Penthouse"));

        Personnel p1 = new Personnel("D.", "Daril", "daril@gmail.com", "Baba2004", "administrateur", hotel);
        Personnel p2 = new Personnel("L.", "Angelin", "jose@gmail.com", "Baba2004", "receptioniste", hotel);
        Personnel p3 = new Personnel("G.", "Metus", "metus@gmail.com", "Baba2004", "agent de nettoyage", hotel);
        PersonnelController.ajouterPersonnel(p1);
        PersonnelController.ajouterPersonnel(p2);
        PersonnelController.ajouterPersonnel(p3);



        Produit prod1 = new Produit("Coca Cola",  3);
        Produit prod2 = new Produit("Chips", 2);
        Produit prod3 = new Produit("Sandwich",  5);
        Produit prod4 = new Produit("Eau minérale",  2);
        Produit prod5 = new Produit("Chocolat", 1);
        Produit prod6 = new Produit("Café", 2);
        Produit prod7 = new Produit("Jus d'orange", 4);
        Produit prod8 = new Produit("Croissant",  2);
        Produit prod9 = new Produit("Salade", 9);
        Produit prod10 = new Produit("Glace", 3);

        ProduitController.ajouterProduit(prod1);
        ProduitController.ajouterProduit(prod2);
        ProduitController.ajouterProduit(prod3);
        ProduitController.ajouterProduit(prod4);
        ProduitController.ajouterProduit(prod5);
        ProduitController.ajouterProduit(prod6);
        ProduitController.ajouterProduit(prod7);
        ProduitController.ajouterProduit(prod8);
        ProduitController.ajouterProduit(prod9);
        ProduitController.ajouterProduit(prod10);

    }
}
