package view.login_register;

import com.formdev.flatlaf.FlatClientProperties;
import view.dashboard.menu.MyDrawerBuilder;
import view.login_register.components.ButtonLink;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import view.system.FormManager;

import javax.swing.*;

public class SignUp extends JPanel {
    public SignUp() {
        setLayout(new MigLayout("insets n 20 n 20,fillx,wrap,width 380", "[fill]"));

        JTextArea text = new JTextArea("Devenez membre et profitez d'offres exclusives,\nde promotions, d'invitations et de récompenses.");
        text.setEditable(false);
        text.setFocusable(false);
        text.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:0,0,0,0;" +
                "background:null;");
        add(text);

        add(new JSeparator(), "gapy 15 15");

        JLabel lbEmail = new JLabel("Email");
        lbEmail.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbEmail);

        JTextField txtEmail = new JTextField();
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "example@mail.com");
        add(txtEmail);

        JLabel lbPassword = new JLabel("Mot de passe");
        lbPassword.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbPassword, "gapy 10 n");

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Minimum 8 caractères");
        add(txtPassword);

        JLabel lbDateOfBirth = new JLabel("Date de Naissance");
        lbDateOfBirth.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbDateOfBirth, "gapy 10 n");

        JTextField txtDateOfBirth = new JTextField();
        txtDateOfBirth.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "MM / DD / YYYY");
        add(txtDateOfBirth);

        JLabel lbNote = new JLabel("Nous souhaitons vous offrir un avantage spécial pour votre anniversaire");
        lbNote.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:-1;" +
                "foreground:$Label.disabledForeground;");
        add(lbNote);

        add(new JCheckBox("Accepter les conditions d'utilisations"), "gapy 10 10");

        JButton cmdSignUp = new JButton("S'inscrire") {
            @Override
            public boolean isDefaultButton() {
                return true;
            }
        };
        cmdSignUp.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#FFFFFF;");
        add(cmdSignUp);

        add(new JSeparator(), "gapy 15 15");

        add(new JLabel("VOus avez déjà un compte ?"), "split 2, gapx push n");

        ButtonLink cmdBackLogin = new ButtonLink("Connectez-vous");
        add(cmdBackLogin, "gapx n push");

        // évenement
        cmdBackLogin.addActionListener(actionEvent -> {
            ModalDialog.popModel(Login.ID);
        });

        cmdSignUp.addActionListener(actionEvent -> {
            String userName = txtEmail.getText();
            String password = String.valueOf(txtPassword.getPassword());
            ModelUser user = getUser(userName, password);
            MyDrawerBuilder.getInstance().setUser(user);
            FormManager.login();
        });
    }

    private ModelUser getUser(String user, String password) {

        // just testing.
        // input any user and password is admin by default
        // user='staff' password='123' if we want to test validation menu for role staff

        if (user.equals("staff") && password.equals("123")) {
            return new ModelUser("Justin White", "justinwhite@gmail.com", ModelUser.Role.STAFF);
        }
        return new ModelUser("Ra Ven", "raven@gmail.com", ModelUser.Role.ADMIN);
    }
}
