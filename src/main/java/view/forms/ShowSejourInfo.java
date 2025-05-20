package view.forms;

import model.Consommation;
import model.Controllers.SejourController;
import model.Sejour;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ShowSejourInfo extends JPanel {
    private Sejour sejour;
    public ShowSejourInfo(Sejour sejour) {
        this.sejour = sejour;
        init();
    }

    private void init() {
        setLayout(new MigLayout("insets n 20 n 20,fillx,wrap,width 380", "[fill]"));

        add(new JLabel("Consommation :"));
        for (Consommation c : SejourController.getConsommations(sejour)) {
            System.out.println("Produit" + c.getProduit());
            String text = c.getProduit().getNom() + " - X" + c.getQuantite() + " - " + (c.getProduit().getPrix() * c.getQuantite()) + "€";
            JLabel lbConso = new JLabel(text);
            add(lbConso, "gapy n 10");
        }

    }
}
