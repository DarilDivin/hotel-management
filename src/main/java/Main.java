import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.FlatClientProperties;
import model.DAO.DatabaseConnection;
import net.miginfocom.swing.MigLayout;
import raven.modal.Drawer;
import view.dashboard.menu.Dashboard;
import view.dashboard.menu.MyDrawerBuilder;
import view.home.Home;
import view.hotel.ShowHotel;
import view.landing.Hero;
import view.system.FormManager;

import javax.swing.*;
import java.awt.*;

import model.DAO.DatabaseConnection;

public class Main extends JFrame{
    public Main() {

        //Initialisation de la base de données
        DatabaseConnection.initializeDatabase();


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT, false);
        setTitle("ZenHotel");
        setSize(new Dimension(1366, 768));
        setLocationRelativeTo(null);

        setLayout(new MigLayout("wrap, fillx, insets 0 0 0 0", "[center,fill]"));

        FlatSVGIcon icon = new FlatSVGIcon("images/i5.svg", 1.5f);
        icon.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.decode("#007bff")));
        setIconImage(icon.getImage());

        Drawer.installDrawer(this, MyDrawerBuilder.getInstance());
        FormManager.install(this);

//        Hero hero = new Hero();
//        Home home = new Home();
//        ShowHotel showHotel = new ShowHotel();
//        Dashboard dashboard = new Dashboard(this);



//        JScrollPane scrollPane = new JScrollPane();
//        scrollPane.putClientProperty(FlatClientProperties.SCROLL_PANE_SMOOTH_SCROLLING, true);
//        scrollPane.setBorder(null);
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
//        add(scrollPane, "w 1366!");
    }


    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("global.themes");
        FlatMacDarkLaf.setup();
//        FlatMacLightLaf.setup();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        EventQueue.invokeLater(() -> new Main().setVisible(true));
    }
}
