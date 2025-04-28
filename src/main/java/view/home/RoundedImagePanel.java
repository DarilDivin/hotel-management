package view.home;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Objects;

public class RoundedImagePanel extends JPanel {

    private final Image image;
    private final int arcSize; // Arrondi des coins

    public RoundedImagePanel(String imagePath, int arcSize) {
        this.arcSize = arcSize;

        // Charger l'image
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath)));
        this.image = icon.getImage();

        // Définir MigLayout
        setLayout(new MigLayout("insets 0, fill, center", "[grow]", "[grow]"));

        // Important pour rendre le fond transparent
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Définir le clip avec des coins arrondis
        Shape clip = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcSize, arcSize);
        g2.setClip(clip);

        // Dessiner l'image redimensionnée au panneau
        g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);

        g2.dispose();
    }

}
