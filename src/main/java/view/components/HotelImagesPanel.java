package view.components;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import view.home.RoundedImagePanel;

import javax.swing.*;

public class HotelImagesPanel extends JPanel {
    public HotelImagesPanel() {
        setLayout(new MigLayout("wrap 2, gap 0", "[grow]", "[grow]"));

        JPanel hotelImgPanel1 = new JPanel();
        hotelImgPanel1.setLayout(new MigLayout("insets 0, fill", "[grow]", "[grow]"));
//        hotelImgPanel1.putClientProperty(FlatClientProperties.STYLE, "" +
//                "background:fade(@accentColor,100%);");
        RoundedImagePanel img1 = new RoundedImagePanel("/images/paris-tour-eiffel.jpg", 5);
        hotelImgPanel1.add(img1, "wmax 595px, w 590!, h 345!");

        add(hotelImgPanel1, "grow, hmin 200");

        JPanel hotelImgPanel2 = getJPanel();

        add(hotelImgPanel2, "grow, hmin 200");
    }

    private static JPanel getJPanel() {
        JPanel hotelImgPanel2 = new JPanel();
//        hotelImgPanel2.putClientProperty(FlatClientProperties.STYLE, "" +
//                "background:fade(@accentColor,50%);");
        hotelImgPanel2.setLayout(new MigLayout("insets 0, wrap 2", "[grow]", "[grow]"));
        RoundedImagePanel img2 = new RoundedImagePanel("/images/paris-tour-eiffel.jpg", 5);
        RoundedImagePanel img3 = new RoundedImagePanel("/images/paris-tour-eiffel.jpg", 5);
        RoundedImagePanel img4 = new RoundedImagePanel("/images/paris-tour-eiffel.jpg", 5);
        RoundedImagePanel img5 = new RoundedImagePanel("/images/paris-tour-eiffel.jpg", 5);

        hotelImgPanel2.add(img2, "wmax 297px, w 285!, h 170!, gap 0");
        hotelImgPanel2.add(img3, "wmax 297px, w 285!, h 170!, gap 0");
        hotelImgPanel2.add(img4, "wmax 297px, w 285!, h 170!, gap 0");
        hotelImgPanel2.add(img5, "wmax 297px, w 285!, h 170!, gap 0");
        return hotelImgPanel2;
    }
}
