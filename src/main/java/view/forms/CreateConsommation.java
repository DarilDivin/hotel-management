package view.forms;

import com.formdev.flatlaf.FlatClientProperties;
import model.Consommation;
import model.Controllers.ConsommationController;
import model.Controllers.ProduitController;
import model.Produit;
import model.Sejour;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import raven.modal.Toast;
import raven.modal.component.ModalBorderAction;
import raven.modal.component.SimpleModalBorder;
import view.utils.ToastManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class CreateConsommation extends JPanel {
    public static final String ID = "create_consommation_id";
    private Sejour sejour;
    
    public CreateConsommation(Sejour sejour) {
        init();
        this.sejour = sejour;
    }
    
    private void init() {
        setLayout(new MigLayout("insets n 20 n 20,fillx,wrap,width 380", "[fill]"));


        JLabel lbProduit = new JLabel("Produit");
        lbProduit.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbProduit);

        JComboBox<Produit> cboProduit = new JComboBox<Produit>();
        for (Produit p : ProduitController.getTousLesProduits()) {
            cboProduit.addItem(p);
        }
        cboProduit.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Produit) {
                    Produit p = (Produit) value;
                    value = p.getNom() + " - (" + p.getPrix() + "€)";
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        add(cboProduit, "gapy n 20");
        
        JLabel lbQuantite = new JLabel("Quantité");
        lbQuantite.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbQuantite);
        
        JTextField txtQuantite = new JTextField();
        txtQuantite.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "5");
        add(txtQuantite, "gapy n 20" );

        JButton cmdCreate = new JButton("Créer") {
            @Override
            public boolean isDefaultButton() {
                return true;
            }
        };
        cmdCreate.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#FFFFFF;");
        add(cmdCreate);
        
        cmdCreate.addActionListener((e) -> {
            Consommation newConsommation = new Consommation(
                    this.sejour,
                    (Produit) cboProduit.getSelectedItem(),
                    Integer.parseInt(txtQuantite.getText().trim())
            );

            ConsommationController.ajouterConsommation(newConsommation);
            ModalBorderAction.getModalBorderAction(this).doAction(SimpleModalBorder.OK_OPTION);
            ToastManager.getInstance().showToast(this, Toast.Type.SUCCESS, "Consommation ajouté avec succès");

            ModalDialog.closeModal(CreateConsommation.ID);
        });
    }
}
