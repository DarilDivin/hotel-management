package view.home.components;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import view.home.RoundedImagePanel;

import javax.swing.*;
import java.awt.*;

public class HotelCard extends JPanel {

    public HotelCard() {
        setLayout(new MigLayout("insets 0, wrap, fill", "leading", "top"));

        RoundedImagePanel hotelImg = new RoundedImagePanel("/images/hotel1.jpg", 15);

        add(hotelImg, "w 100%, h 205!");

        JLabel hotelName = new JLabel("Hotel Victor Hugo Paris Kléber");
        hotelName.setFont(new Font("", Font.BOLD, 18));
        hotelName.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold");
        add(hotelName, "gapbottom 0, top, leading");

        JLabel hotelAdress = new JLabel("17e Arrondissement");
        hotelAdress.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#656766");
        add(hotelAdress, "span 3,gapy n 10, top, leading");

        JButton hotelNote = new JButton("8.7");
        hotelNote.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;" +
                "foreground: #ffffff;" +
                "background: #024c10;");

        add(hotelNote, "split 2, w 40!, h 20!");

        JLabel hotelAppreciation = new JLabel("17e Arrondissement");
        hotelAppreciation.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#656766;" +
                "font:bold;");
        add(hotelAppreciation, "gapy n 10, al center center");
    }
}
