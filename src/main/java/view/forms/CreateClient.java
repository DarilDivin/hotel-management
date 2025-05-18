package view.forms;

import com.formdev.flatlaf.FlatClientProperties;
import model.Client;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;

import javax.swing.*;

public class CreateClient extends JPanel{

    public static final String ID = "create_client_id";
    private Client client;

    public CreateClient(Client client) {
        this.client = client;
        init();
    }
    public CreateClient() {
        init();
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
        add(txtEmail, "gapy n 20");


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
        if(client != null) {
            txtNom.setText(this.client.getNom());
            txtPrenom.setText(this.client.getPrenom());
            txtEmail.setText(this.client.getEmail());
            cmdCreate.setText("Modifier");
        }

        // evenement

        cmdCreate.addActionListener((e) -> {
            if (client != null) {
                System.out.println("Modifier");
            }
            ModalDialog.closeModal(CreatePersonnel.ID);
        });
    }
}
