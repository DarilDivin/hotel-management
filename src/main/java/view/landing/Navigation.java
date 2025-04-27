package view.landing;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import view.login_register.CustomModalBorder;
import view.login_register.Login;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import raven.modal.option.BorderOption;
import raven.modal.option.Option;

import javax.swing.*;
import java.awt.*;

public class Navigation extends JPanel {
    public Navigation() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap 3, fillx, insets 2 0 2 0"));

        JLabel logo = new JLabel("<html><h3 style='font-weight:bold; font-size:16px'>Zen<span style='color:#007bff;'>Hotel</span></h3></html>");
        logo.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:fade(#1aad2c,90%);" +
                "font:bold +2;"
//                "foreground:#22b65a"
        );
        logo.setIcon(new FlatSVGIcon("images/i5.svg", 25, 25).setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.decode("#007bff"))));

        add(logo, "span 1, align left, width 100%-110px");

        JButton signin = new JButton("Se connecter") {
            @Override
            public boolean isDefaultButton() {
                return true;
            }
        };


        // style modal border
        ModalDialog.getDefaultOption()
                .setOpacity(0f)
                .getBorderOption()
                .setShadow(BorderOption.Shadow.MEDIUM);

        signin.addActionListener(actionEvent -> {
            showLogin();
        });
        add(signin, "growx 0, gapx 8");
    }

    private void showLogin() {
        Option option = ModalDialog.createOption()
                .setCloseOnPressedEscape(true)
                .setBackgroundClickType(Option.BackgroundClickType.CLOSE_MODAL)
                .setAnimationEnabled(true)
                .setOpacity(0.5f);
        String icon = "images/login_register/account.svg";
        ModalDialog.showModal(this, new CustomModalBorder(new Login(), "Login", icon), option, Login.ID);
    }
}
