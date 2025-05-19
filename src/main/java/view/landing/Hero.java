package view.landing;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import view.system.Form;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Hero extends Form {

    private void createHero() {
        setLayout(new BorderLayout());

        // Panel pour le contenu
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new MigLayout("wrap, fillx", "[center]"));
//        contentPanel.setPreferredSize(new Dimension(1300, 1200)); // Forcer une hauteur minimum

        Navigation navBar = new Navigation();
        navBar.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:8,8,8,8;" +
                "arc:$Component.arc;" +
                "background:fade(@accentColor,0%);");

        contentPanel.add(navBar, "width 100%, wmax 1300px, gapy 0 20");

        JLabel title = new JLabel("<html><p>Gérez votre hotel</p></html>");
        title.setFont(new Font("", Font.BOLD, 48));
        JLabel title2 = new JLabel("<html><p style='font-size:36px; font-weight:900;'>avec le <span style='color:#007bff'>meilleur outil</span></p></html>");
        title2.setFont(new Font("", Font.BOLD, 48));

        JLabel desc = new JLabel("<html><div style='color:gray;'>Gérez vos réservations, votre personnel et vos chambres en toute simplicité avec notre solution complète</div></html>");
        desc.setFont(new Font("", Font.PLAIN, 20));

        JButton getStarted = new JButton("Parcourir");
        JButton getDemo = new JButton("Un autre bouton");

        contentPanel.add(title, "align center center, gapy 20");
        contentPanel.add(title2, "align center center");
        contentPanel.add(desc, "align center center, gapy 20");
//        contentPanel.add(getStarted, "split 2, center center");
//        contentPanel.add(getDemo);

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/banniere.jpg")));
        Image scaledImage = imageIcon.getImage().getScaledInstance(1300, 418, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.putClientProperty(FlatClientProperties.COMPONENT_ROUND_RECT, true);
        contentPanel.add(imageLabel, "center, wrap");

        // Configuration du JScrollPane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
    }

    public Hero() {
        createHero();
    }
}