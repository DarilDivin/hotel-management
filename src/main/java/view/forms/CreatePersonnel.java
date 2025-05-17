package view.forms;

import com.formdev.flatlaf.FlatClientProperties;
import model.utilsModel.ModelUser;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import view.dashboard.menu.MyDrawerBuilder;
import view.login_register.Login;
import view.login_register.components.ButtonLink;
import view.system.FormManager;

import javax.swing.*;

public class CreatePersonnel extends JPanel {

    public static final String ID = "create_personnel_id";

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
        add(txtEmail, "gapy n 20" );

//        JLabel lbPassword = new JLabel("Mot de passe");
//        lbPassword.putClientProperty(FlatClientProperties.STYLE, "" +
//                "font:bold;");
//        add(lbPassword, "gapy 10 n");
//
//        JPasswordField txtPassword = new JPasswordField();
//        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Minimum 8 caractères");
//        add(txtPassword);


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

        cmdCreate.addActionListener((e) -> {
            ModalDialog.closeModal(CreatePersonnel.ID);
        });

    }
}
