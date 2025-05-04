package view.home;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import view.components.HotelCard;
import view.components.SearchBar;
import view.landing.Navigation;

import javax.swing.*;
import java.awt.*;

public class Home extends JPanel {

    public Home() {
        setLayout(new MigLayout("insets 0 83 0 83, wrap, fill", "[center]", "[top]"));

        Navigation navBar = new Navigation();
        navBar.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:8,8,8,8;" +
                "arc:$Component.arc;"+
                "background:fade(@accentColor,5%);");

        add(navBar, "width 100%, wmax 1200px, gapy 10");


        RoundedImagePanel imagePanel = new RoundedImagePanel("/images/paris-tour-eiffel.jpg", 20);
        imagePanel.setLayout(new MigLayout("insets 0 20 0 20, wrap, fill", "[center]", "[center]")); // Utilisation d'un layout null pour la position absolue

        SearchBar searchBarPanel = new SearchBar();

        imagePanel.add(searchBarPanel,"growx, h 55!, gap 10");

        add(imagePanel, "w 1200!, h 400!");


        JLabel title = new JLabel("Découvrez votre nouvel hébergement préféré");
        title.setFont(new Font("", Font.BOLD, 28));

        add(title/*, "leading"*/);

        for (int i = 0; i < 8; i++) {
            HotelCard card = new HotelCard();
            card.putClientProperty(FlatClientProperties.STYLE, "" +
                    "border:8,8,8,8;" +
                    "arc:$Component.arc;"+
                    "background:fade(@accentColor,5%);");
            add(card, "split 4, w 280!, h 320!, al leading center, gapbefore 15, gapafter 15, gapy 10 10");
        }
    }
}

