package view.login_register;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import view.login_register.components.ButtonLink;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JPanel {
    public static final String ID = "login_id";

    public Login() {
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
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Entrer votre email");
        add(txtEmail);

        JLabel lbPassword = new JLabel("Mot de passe");
        lbPassword.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbPassword, "gapy 10 n");

        JPasswordField txtPassword = new JPasswordField();
        installRevealButton(txtPassword);
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Entrer votre mot de passe");

        add(txtPassword);

        add(new JCheckBox("Remember me"), "split 2,gapy 10 10");
        ButtonLink cmdForgotPassword = new ButtonLink("Mot de passe oublié ?");
        add(cmdForgotPassword, "gapx push n");

        JButton cmdLogin = new JButton("Login") {
            @Override
            public boolean isDefaultButton() {
                return true;
            }
        };
        cmdLogin.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#FFFFFF;");
        add(cmdLogin);

        add(new JSeparator(), "gapy 15 15");

        add(new JLabel("Vous n'avez pas de compte ?"), "split 2,gapx push n");
        ButtonLink cmdSignUp = new ButtonLink("S'inscrire");
        add(cmdSignUp, "gapx n push");

        // event
        cmdSignUp.addActionListener(actionEvent -> {
            String icon = "images/login_register/signup.svg";
            ModalDialog.pushModal(new CustomModalBorder(new SignUp(), "S'inscrire'", icon), ID);
        });

        cmdForgotPassword.addActionListener(actionEvent -> {
            String icon = "images/login_register/forgot_password.svg";
            ModalDialog.pushModal(new CustomModalBorder(new ForgotPassword(), "Mot de passe oublié", icon), ID);
        });
    }

    private void installRevealButton(JPasswordField txt) {
        FlatSVGIcon iconEye = new FlatSVGIcon("images/login_register/eye.svg", 0.3f);
        FlatSVGIcon iconHide = new FlatSVGIcon("images/login_register/hide.svg", 0.3f);

        JToolBar toolBar = new JToolBar();
        toolBar.putClientProperty(FlatClientProperties.STYLE, "" +
                "margin:0,0,0,5;");
        JButton button = new JButton(iconEye);

        button.addActionListener(new ActionListener() {

            private char defaultEchoChart = txt.getEchoChar();
            private boolean show;

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                show = !show;
                if (show) {
                    button.setIcon(iconHide);
                    txt.setEchoChar((char) 0);
                } else {
                    button.setIcon(iconEye);
                    txt.setEchoChar(defaultEchoChart);
                }
            }
        });
        toolBar.add(button);
        txt.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_COMPONENT, toolBar);
    }
}
