package view.forms;

import com.formdev.flatlaf.FlatClientProperties;
import model.Client;
import model.Controllers.ClientController;
import model.Controllers.HotelController;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import raven.modal.Toast;
import raven.modal.component.ModalBorderAction;
import raven.modal.component.SimpleModalBorder;
import view.utils.ToastManager;

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
            if (txtNom.getText().trim().isEmpty() || txtPrenom.getText().trim().isEmpty() || txtEmail.getText().trim().isEmpty()) {
                ToastManager.getInstance().showToast(this, Toast.Type.ERROR, "Veuillez remplir tous les champs obligatoires");
                return;
            }

            if (client != null) {
                client.setNom(txtNom.getText().trim());
                client.setPrenom(txtPrenom.getText().trim());
                client.setEmail(txtEmail.getText().trim());

                ClientController.modifierClient(client);

                ModalBorderAction.getModalBorderAction(this).doAction(SimpleModalBorder.OK_OPTION);
                ToastManager.getInstance().showToast(this, Toast.Type.SUCCESS, "Client modifié avec succès");
            } else {
                Client newClient = new Client(
                        txtNom.getText().trim(),
                        txtPrenom.getText().trim(),
                        txtEmail.getText().trim(),
                        HotelController.getHotelById(1)
                );

                ClientController.ajouterClient(newClient);
                ModalBorderAction.getModalBorderAction(this).doAction(SimpleModalBorder.OK_OPTION);
                ToastManager.getInstance().showToast(this, Toast.Type.SUCCESS, "Client créé avec succès");
            }

            ModalDialog.closeModal(CreateClient.ID);
        });
    }
}
