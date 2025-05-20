package view.forms;

import com.formdev.flatlaf.FlatClientProperties;
import jnafilechooser.api.JnaFileChooser;
import model.Chambre;
import model.Controllers.ChambreController;
import model.Controllers.HotelController;
import model.Controllers.TypeChambreController;
import model.TypeChambre;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import raven.modal.Toast;
import raven.modal.component.ModalBorderAction;
import raven.modal.component.SimpleModalBorder;
import view.dashboard.menu.MyDrawerBuilder;
import view.utils.ToastManager;

import javax.swing.*;
import java.io.File;
import java.util.Objects;

public class CreateChambre extends JPanel {
    
    public static final String ID = "create_chambre_id";
    
    private Chambre chambre;
    private File imageFile;
    
    public CreateChambre() {
        init();
    }
    
    public CreateChambre(Chambre chambre) {
        this.chambre = chambre;
    }
    

    public void init() {
        setLayout(new MigLayout("insets n 20 n 20,fillx,wrap,width 380", "[fill]"));

        JTextArea text = new JTextArea("Lorem ipsum");
        text.setEditable(false);
        text.setFocusable(false);
        text.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:0,0,0,0;" +
                "background:null;");
        add(text);

        add(new JSeparator(), "gapy 15 15");

        JLabel lbNumero = new JLabel("Numero de chambre");
        lbNumero.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbNumero);

        JTextField txtNumero = new JTextField();
        txtNumero.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "A-102");
        add(txtNumero);

        JLabel lbPrix = new JLabel("Prix");
        lbPrix.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbPrix);

        JTextField txtPrix = new JTextField();
        txtPrix.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "$100");
        add(txtPrix);

        JLabel lbSuperficie = new JLabel("Superficie");
        lbSuperficie.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbSuperficie);

        JTextField txtSuperficie = new JTextField();
        txtSuperficie.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "100m²");
        add(txtSuperficie);

        JLabel lbTypeChambre = new JLabel("Type de chambre");
        lbTypeChambre.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbTypeChambre);

        JComboBox<String> cboTypeChambre = new JComboBox<String>();
        // Ajouter les types de chambre disponibles
        for(TypeChambre tc : TypeChambreController.getTousLesTypesChambre()) {
            cboTypeChambre.addItem(tc.getType());
        }
//        cboTypeChambre.addItem(new TypeChambre("Chambre Simple").getType());
//        cboTypeChambre.addItem(new TypeChambre("Chambre Double").getType());
        cboTypeChambre.addActionListener(e -> {
            TypeChambre selectedType = (TypeChambre) TypeChambreController.getTypeChambreByType(Objects.requireNonNull(cboTypeChambre.getSelectedItem()).toString());
            // Traiter la sélection
        });
        add(cboTypeChambre);

        JLabel lbImage = new JLabel("Image");
        lbImage.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbImage);

        JButton cmdChooseImage = new JButton("Choisir une image") {
            @Override
            public boolean isDefaultButton() {
                return false;
            }
        };
        cmdChooseImage.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#FFFFFF;");
        add(cmdChooseImage, "gapy n 20");

        JButton cmdCreate = new JButton("Créer") {
            @Override
            public boolean isDefaultButton() {
                return true;
            }
        };
        cmdCreate.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#FFFFFF;");
        add(cmdCreate);

        // evenement
        cmdChooseImage.addActionListener((e) -> {
            JnaFileChooser fileChooser = new JnaFileChooser();
            // fileChooser.addFilter("All Files", "*");
            fileChooser.addFilter("Pictures", "jpg", "jpeg", "png");
            boolean action = fileChooser.showOpenDialog(SwingUtilities.getWindowAncestor(this) );
            if(action) {
                System.out.println(fileChooser.getSelectedFile().getAbsolutePath());
                imageFile = fileChooser.getSelectedFile();
//                ToastManager.getInstance().showToast(this, Toast.Type.SUCCESS, "Image uploadé avec succès");
            }
        });

        // formulaire de modification
        if(chambre != null) {
            txtNumero.setText(this.chambre.getNumero());
            txtPrix.setText(String.valueOf(this.chambre.getPrix()));
            txtSuperficie.setText(String.valueOf(this.chambre.getSuperficie()));
            cboTypeChambre.setSelectedItem(this.chambre.getTypeChambre().getType());
            cmdCreate.setText("Modifier");
        }

        cmdCreate.addActionListener((e) -> {
            if (chambre != null) {
                chambre.setNumero(txtNumero.getText().trim());
                chambre.setPrix(Float.parseFloat(txtPrix.getText().trim()));
                chambre.setSuperficie(Float.parseFloat(txtSuperficie.getText().trim()));
                chambre.setTypeChambre(TypeChambreController.getTypeChambreByType(Objects.requireNonNull(cboTypeChambre.getSelectedItem()).toString()));

                ChambreController.modifierChambre(chambre);

                ModalBorderAction.getModalBorderAction(this).doAction(SimpleModalBorder.OK_OPTION);
                ToastManager.getInstance().showToast(this, Toast.Type.SUCCESS, "Chambre modifié avec succès");
            } else {
                Chambre newChambre = new Chambre(
                        TypeChambreController.getTypeChambreByType(Objects.requireNonNull(cboTypeChambre.getSelectedItem()).toString()),
                        txtNumero.getText().trim(),
                        Float.parseFloat(txtPrix.getText().trim()),
                        Float.parseFloat(txtSuperficie.getText().trim()),
                        MyDrawerBuilder.getInstance().getPersonnel().getHotel()
                );

                ChambreController.ajouterChambre(newChambre, imageFile);

                ModalBorderAction.getModalBorderAction(this).doAction(SimpleModalBorder.OK_OPTION);
                ToastManager.getInstance().showToast(this, Toast.Type.SUCCESS, "Chambre créé avec succès");
            }


            ModalDialog.closeModal(CreateChambre.ID);
        });
    }
}
