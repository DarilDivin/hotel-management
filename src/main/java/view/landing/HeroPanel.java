package view.landing;

import javax.swing.*;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.components.FlatButton;

import java.awt.*;
import java.util.Objects;

public class HeroPanel extends JPanel {

    public HeroPanel() {
        setLayout(new MigLayout("wrap 1, align center, gapy 20", "[center]", "[]20[]20[]"));

        // Logo ou illustration
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/banniere-paris.png"))); // Mets le bon chemin
        Image scaledImage = imageIcon.getImage().getScaledInstance(1280, 350, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        add(imageLabel, "center");

        // Badge
        JLabel badge = new JLabel("⚡ Optimisez vos opérations de 30%");
        badge.putClientProperty(FlatClientProperties.STYLE, "font: bold; borderRadius:20; padding:5 15 5 15;");
        add(badge, "center");

        // Titre
        JLabel title = new JLabel("<html><div style='text-align:center;'><span style='font-size:30px;'>Simplifiez la gestion hôtelière <br>avec un <span style='color:#007bff;'>Tableau de bord intelligent</span></span></div></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, "center, gapy 10");

        // Description
        JLabel desc = new JLabel("<html><div style='text-align:center;'>Gérez les réservations, suivez les chambres et optimisez les opérations<br>— le tout à partir d’un seul tableau de bord intuitif.</div></html>");
        desc.setHorizontalAlignment(SwingConstants.CENTER);
        add(desc, "center");

        // Boutons
        JPanel buttonPanel = new JPanel(new MigLayout("insets 0, gap 15", "[]15[]", ""));
        FlatButton startButton = new FlatButton();
        startButton.setText("Commencer");
        startButton.putClientProperty(FlatClientProperties.STYLE, "background:#007bff; foreground:#ffffff; borderRadius:10; font: bold; padding:8 20 8 20;");

        FlatButton demoButton = new FlatButton();
        demoButton.setText("Voir une démo");
        demoButton.putClientProperty(FlatClientProperties.STYLE, "background:#333333; borderRadius:10; padding:8 20 8 20;");

        buttonPanel.add(startButton);
        buttonPanel.add(demoButton);
        add(buttonPanel, "center");
    }

    public static void main(String[] args) {
        FlatLightLaf.setup(); // Assure-toi d'avoir FlatLaf dans tes dépendances

        JFrame frame = new JFrame("Hero Section Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        HeroPanel hero = new HeroPanel();
        frame.add(hero, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
