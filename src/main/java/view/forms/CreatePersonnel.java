package view.forms;

import com.formdev.flatlaf.FlatClientProperties;
import model.Chambre;
import model.Controllers.HotelController;
import model.Controllers.PersonnelController;
import model.Hotel;
import model.Personnel;
import model.TypeChambre;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import raven.modal.Toast;
import raven.modal.component.ModalBorderAction;
import raven.modal.component.SimpleModalBorder;
import view.utils.ToastManager;

import javax.swing.*;
import java.util.Objects;

public class CreatePersonnel extends JPanel {

    public static final String ID = "create_personnel_id";
    private Personnel personnel;

    public CreatePersonnel(Personnel personnel) {
        this.personnel = personnel;
        init();
    }

    public CreatePersonnel() {
        init();
    }

    public void init() {
        setLayout(new MigLayout("insets n 20 n 20,fillx,wrap,width 380", "[fill]"));

        JTextArea text = new JTextArea("Lorem ipsummmmmmmmmmmmm");
        text.setEditable(false);
        text.setFocusable(false);
        text.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:0,0,0,0;" +
                "background:null;");
        add(text);

        add(new JSeparator(), "gapy 15 15");

        JLabel lbNom = new JLabel("Nom");
        lbNom.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbNom);

        JTextField txtNom = new JTextField();
        txtNom.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "L.");
        add(txtNom);

        JLabel lbPrenom = new JLabel("Prénoms");
        lbPrenom.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbPrenom);

        JTextField txtPrenom = new JTextField();
        txtPrenom.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "José-Marie");
        add(txtPrenom);

        JLabel lbEmail = new JLabel("Email");
        lbEmail.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbEmail);

        JTextField txtEmail = new JTextField();
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "example@mail.com");
        add(txtEmail, "" );

//        JLabel lbPassword = new JLabel("Mot de passe");
//        lbPassword.putClientProperty(FlatClientProperties.STYLE, "" +
//                "font:bold;");
//        add(lbPassword, "gapy 10 n");
//
//        JPasswordField txtPassword = new JPasswordField();
//        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Minimum 8 caractères");
//        add(txtPassword);

        JLabel lbRole = new JLabel("Role");
        lbRole.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbRole);

        JComboBox<String> cboRole = new JComboBox<String>();
        cboRole.addItem("receptioniste");
        cboRole.addItem("agent de nettoyage");
        cboRole.addItem("administrateur");

        add(cboRole, "gapy n 20");

        JLabel lbHotel = new JLabel("Hotel");
        lbHotel.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbHotel);

        JComboBox<String> cboHotel = new JComboBox<String>();
        for (Hotel h : HotelController.getTousLesHotels()) {
            cboHotel.addItem(h.getNom());
            if(this.personnel != null && this.personnel.getHotel().getId() == h.getId()) {
                cboHotel.setSelectedItem(h.getNom());
            }
        }

        add(cboHotel, "gapy n 20");


        JButton cmdCreate = new JButton("Créer") {
            @Override
            public boolean isDefaultButton() {
                return true;
            }
        };
        cmdCreate.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#FFFFFF;");
        add(cmdCreate);

        //formulaire pour modification
        if(personnel != null) {
            txtNom.setText(this.personnel.getNom());
            txtPrenom.setText(this.personnel.getPrenom());
            txtEmail.setText(this.personnel.getEmail());
            cboRole.setSelectedItem(this.personnel.getRole());
            cmdCreate.setText("Modifier");
        }


        // evenement

        cmdCreate.addActionListener((e) -> {
            if (txtNom.getText().trim().isEmpty() || txtPrenom.getText().trim().isEmpty() || txtEmail.getText().trim().isEmpty()) {
                ToastManager.getInstance().showToast(this, Toast.Type.ERROR, "Veuillez remplir tous les champs obligatoires");
                return;
            }

            if (personnel != null) {
                personnel.setNom(txtNom.getText().trim());
                personnel.setPrenom(txtPrenom.getText().trim());
                personnel.setEmail(txtEmail.getText().trim());
                personnel.setRole((String) cboRole.getSelectedItem());
                personnel.setHotel(HotelController.getHotelByNom(Objects.requireNonNull(cboHotel.getSelectedItem()).toString()));

                PersonnelController.modifierPersonnel(personnel);

                ModalBorderAction.getModalBorderAction(this).doAction(SimpleModalBorder.OK_OPTION);
                ToastManager.getInstance().showToast(this, Toast.Type.SUCCESS, "Personnel modifié avec succès");
            } else {
                Personnel newPersonnel = new Personnel(
                        txtNom.getText().trim(),
                        txtPrenom.getText().trim(),
                        txtEmail.getText().trim(),
                        "Abcd1234",
                        (String) cboRole.getSelectedItem(),
                        HotelController.getHotelByNom(Objects.requireNonNull(cboHotel.getSelectedItem()).toString())
                );

                PersonnelController.ajouterPersonnel(newPersonnel);
                ModalBorderAction.getModalBorderAction(this).doAction(SimpleModalBorder.OK_OPTION);
                ToastManager.getInstance().showToast(this, Toast.Type.SUCCESS, "Personnel créé avec succès");
            }
            ModalDialog.closeModal(CreatePersonnel.ID);
        });

    }
}
