package view.landing;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Hero extends JPanel {
    public Hero() {
        setLayout(new MigLayout("wrap, fillx", "[center]"));


        Navigation navBar = new Navigation();
        navBar.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:8,8,8,8;" +
                "arc:$Component.arc;"+
                "background:fade(@accentColor,0%);");

        add(navBar, "width 100%, wmax 1300px, gapy 0 20");


        JLabel title = new JLabel("<html><p>Profitez des meilleures</p></html>");
        title.setFont(new Font("", Font.BOLD, 48));
        JLabel title2 = new JLabel("<html><p style='font-size:36px; font-weight:900;'>et plus belles <span style='color:#007bff'>Chambres du Monde</span></p></html>");
        title2.setFont(new Font("",Font.BOLD, 48));

        JLabel desc = new JLabel("<html><div style='color:gray;'>Réservez les meilleures chambres du monde au prix les plus généreux possibles</div></html>");
        desc.setFont(new Font("",Font.PLAIN, 20));

        JButton getStarted = new JButton("Parcourir");
        JButton getDemo = new JButton("Un autre bouton");


        add(title, "align center center, gapy 20");
        add(title2, "align center center");
        add(desc, "align center center, gapy 20");
        add(getStarted, "split 2, center center");
        add(getDemo);


        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/banniere.jpg")));
        Image scaledImage = imageIcon.getImage().getScaledInstance(1300, 418, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.putClientProperty(FlatClientProperties.COMPONENT_ROUND_RECT, true);
        add(imageLabel, "center");

    }
}
