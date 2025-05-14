package view.dashboard;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.util.FontUtils;
import raven.modal.Drawer;
import view.dashboard.menu.MyDrawerBuilder;
import view.system.FormManager;
import view.utils.DemoPreferences;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public static final String DEMO_VERSION = "2.5.0";

    public Main() {
        init();
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT, true);
        Drawer.installDrawer(this, MyDrawerBuilder.getInstance());
        FormManager.install(this);
        setSize(new Dimension(1366, 768));
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
//        DemoPreferences.init();
        FlatRobotoFont.install();
        FlatMacLightLaf.setup();
//        FlatLaf.registerCustomDefaultsSource("raven.modal.demo.themes");
        FlatLaf.registerCustomDefaultsSource("global.themes");
        UIManager.put("defaultFont", FontUtils.getCompositeFont(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
//        DemoPreferences.setupLaf();
        EventQueue.invokeLater(() -> new Main().setVisible(true));
    }
}
