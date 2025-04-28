package view.home.components;
import com.formdev.flatlaf.FlatLightLaf;
import net.miginfocom.swing.MigLayout;
import view.home.RoundedImagePanel;

import javax.swing.*;


public class Gallery extends JPanel{

        public Gallery() {
            // Layout : 3 colonnes égales, espace entre les images
            setLayout(new MigLayout(
                    "insets 20, gap 20, wrap 3", // 20px de marge intérieure, 20px d'espacement entre images, 3 images par ligne
                    "[grow, fill][grow, fill][grow, fill]", // 3 colonnes qui grandissent également
                    ""));

            // Ajouter plusieurs RoundedImagePanel
            add(new RoundedImagePanel("/images/image1.jpeg", 30));
            add(new RoundedImagePanel("/images/image1.jpeg", 30));
            add(new RoundedImagePanel("/images/image1.jpeg", 30));
            add(new RoundedImagePanel("/images/image1.jpeg", 30));
            add(new RoundedImagePanel("/images/image1.jpeg", 30));
            add(new RoundedImagePanel("/images/image1.jpeg", 30));
        }

        // Tester dans une JFrame
        public static void main(String[] args) {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }

            JFrame frame = new JFrame("Galerie Moderne");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);

            // Ajouter dans un JScrollPane pour pouvoir scroller si trop d'images
            JScrollPane scrollPane = new JScrollPane(new Gallery());
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Pas de scroll horizontal
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Scroll vertical si besoin
            scrollPane.setBorder(null); // Pas de bordures moches

            frame.setContentPane(scrollPane);
            frame.setVisible(true);
        }
}


