package view.components;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;
import view.home.RoundedImagePanel;

import javax.swing.*;
import java.awt.*;

public class RoomCard extends JPanel{
    public RoomCard() {
        setLayout(new MigLayout("insets 0, wrap, fillx", "leading", "top"));

        RoundedImagePanel hotelImg = new RoundedImagePanel("/images/hotel1.jpg", 15);

        add(hotelImg, "w 100%, h 205!");

        JLabel hotelName = new JLabel("Hotel Victor Hugo Paris Kléber");
        hotelName.setFont(new Font("", Font.BOLD, 15));
        add(hotelName, "gapbottom 0, top, leading");

        JButton hotelNote = new JButton("8.7");
        hotelNote.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;" +
                "foreground: #ffffff;" +
                "background: #024c10;");

        add(hotelNote, "split 2, w 40!, h 20!");

        JLabel hotelAppreciation = new JLabel("Superbe");
        hotelAppreciation.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#656766;" +
                "font:bold;");
        add(hotelAppreciation, "gapy n 10, al center center");

//        JLabel hotelAdress = new JLabel("17e Arrondissement");
//        hotelAdress.putClientProperty(FlatClientProperties.STYLE, "" +
//                "foreground:#656766");
//        add(hotelAdress, "span 3,gapy n 5, top, leading");


        JLabel roomSpace = new JLabel("56 m²");
        roomSpace.setIcon(new FlatSVGIcon("images/space.svg", 15, 15).setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.decode("#000000"))));
        roomSpace.setFont(new Font("", Font.PLAIN, 14));
        roomSpace.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#656766");
        add(roomSpace, "span 3,gapy n 5, top, leading");

        JLabel nbPeople = new JLabel("2 personnes");
        nbPeople.setIcon(new FlatSVGIcon("images/people.svg", 15, 15).setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.decode("#000000"))));
        nbPeople.setFont(new Font("", Font.PLAIN, 14));
        nbPeople.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#656766");
        add(nbPeople, "span 3,gapy n 5, top, leading");

        JLabel nbBed = new JLabel("1 lit double");
        nbBed.setIcon(new FlatSVGIcon("images/bed.svg", 15, 15).setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.decode("#000000"))));
        nbBed.setFont(new Font("", Font.PLAIN, 14));
        nbBed.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#656766");
        add(nbBed, "span 3,gapy n 5, top, leading");

        JLabel wifi = new JLabel("Wifi gratuit disponible");
        wifi.setIcon(new FlatSVGIcon("images/wifi.svg", 15, 15).setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.decode("#000000"))));
        wifi.setFont(new Font("", Font.PLAIN, 14));
        wifi.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#656766");
        add(wifi, "span 3,gapy n 5, top, leading");


        add(new JSeparator(), "gapy 5 5, w 100%, h 10!");

        JLabel roomPrice = new JLabel("450 €");
        roomPrice.setFont(new Font("", Font.BOLD, 20));
//        roomPrice.putClientProperty(FlatClientProperties.STYLE, "" +
//                "foreground:#007bff");
        add(roomPrice, "span 3,gapy n 10, top, trailing");


        JButton bookBtn = new JButton("Réserver");
        bookBtn.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;" +
                "foreground: #ffffff;" +
                "background: #007bff;");
        add(bookBtn, "span,gapy n 10, top, w 100%");




    }
}
