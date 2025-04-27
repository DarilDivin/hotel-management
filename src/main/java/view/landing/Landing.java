package view.landing;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import view.login_register.CustomModalBorder;
import view.login_register.Login;
import raven.modal.ModalDialog;
import raven.modal.option.Option;

import javax.swing.*;
import java.awt.*;

public class Landing extends JFrame {

    public Landing() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ZenHotel");
        setSize(new Dimension(1366, 768));
        setLocationRelativeTo(null);
//        setLayout(new MigLayout("wrap, fillx, insets 0 0 0 0", "[center]"));
        setLayout(new BorderLayout());
        FlatSVGIcon icon = new FlatSVGIcon("images/i5.svg", 1.5f);
        icon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.decode("#007bff")));
        setIconImage(icon.getImage());

        Hero hero = new Hero();
//        add(hero);
        JScrollPane scrollPane = new JScrollPane(hero);
        scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(1366, 768));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane,  BorderLayout.CENTER);

    }


    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("global.themes");
//        FlatMacDarkLaf.setup();
        FlatMacLightLaf.setup();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        EventQueue.invokeLater(() -> new Landing().setVisible(true));
    }
}
