package view.hotel;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.util.FontUtils;
import net.miginfocom.swing.MigLayout;
import view.components.HotelCard;
import view.components.HotelImagesPanel;
import view.components.RoomCard;
import view.components.SearchBar;
import view.dashboard.Main;
import view.home.RoundedImagePanel;
import view.landing.Navigation;

import javax.swing.*;
import java.awt.*;

public class ShowHotel extends JPanel {

    public ShowHotel() {
        setLayout(new MigLayout("insets 0 83 0 83, wrap, fillx", "[center]"));

        Navigation navBar = new Navigation();
        navBar.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:8,8,8,8;" +
                "arc:$Component.arc;"+
                "background:fade(@accentColor,5%);");

        add(navBar, "width 100%, wmax 1200px, gapy 10, wrap");

        JLabel allHosting = new JLabel("Voir tous les hébergements");
        allHosting.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:fade(@accentColor,100%);");
        allHosting.setIcon(new FlatSVGIcon("images/arrow-back.svg", 18, 18).setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.decode("#007bff"))));


        add(allHosting, "split 2, growx, al left");

        JButton shareBtn = new JButton("Partager");
        shareBtn.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:fade(@accentColor,100%);");
        shareBtn.setIcon(new FlatSVGIcon("images/share.svg", 15, 15).setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.decode("#007bff"))));

        add(shareBtn, "al right, wrap");

        HotelImagesPanel hotelImagesPanel = new HotelImagesPanel();

        add(hotelImagesPanel, "growx, wmax 1200px, h 360!, gapy 10");

        JLabel title = new JLabel("Hotel Victor Hugo Paris Kléber");
        title.setFont(new Font("", Font.BOLD, 28));

        add(title, "leading, gapbefore 10");

        JTextArea text = new JTextArea("" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, " +
                "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum " +
                "dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        text.setEditable(false);
        text.setFocusable(false);
        text.setFont(new Font("", Font.PLAIN, 14));
        text.setLineWrap(true); // Permet le wrapping à la ligne
        text.setWrapStyleWord(true); // Coupe uniquement entre les mots

        text.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:0,0,0,0;" +
                "background:null;");
        add(text, "leading, growx, gapbefore 10");


//        for (int i = 0; i < 8; i++) {
//            RoomCard card = new RoomCard();
//            card.putClientProperty(FlatClientProperties.STYLE, "" +
//                    "border:8,8,8,8;" +
//                    "arc:$Component.arc;"+
//                    "background:fade(@accentColor,5%);");
//            add(card, "split 4, w 280!, h 510!, al leading center, gapbefore 15, gapafter 15, gapy 10 10");
//        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ShowHotel");
        FlatRobotoFont.install();
        FlatMacLightLaf.setup();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new ShowHotel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        FlatLaf.registerCustomDefaultsSource("global.themes");
        UIManager.put("defaultFont", FontUtils.getCompositeFont(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        frame.setVisible(true);

    }
}
