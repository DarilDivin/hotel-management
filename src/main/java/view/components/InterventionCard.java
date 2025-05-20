package view.components;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import model.Intervention;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class InterventionCard extends JPanel {
    public InterventionCard(Intervention intervention) {
        setLayout(new MigLayout("insets 0, wrap, fill", "[grow]", "[grow]"));

        JLabel lbName = new JLabel(intervention.getAgentNettoyage().getNom() + " " + intervention.getAgentNettoyage().getPrenom());
        lbName.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +2;");
        lbName.setIcon(new FlatSVGIcon("images/user-round.svg", 0.7f).setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.decode("#969696"))));
        add(lbName, "gapbottom 30, top, leading");

        JLabel chambreLabel = new JLabel("Chambre");
        chambreLabel.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:$Label.disabledForeground;");
        add(chambreLabel, "gapbottom 1, top, leading");
        JLabel lbChambre = new JLabel("A 1010");
        lbChambre.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +1;");
        lbChambre.setFont(new Font("", Font.BOLD, 28));
        add(lbChambre, "gapbottom 5, top, leading");

        JLabel lbDate = new JLabel("2020-01-01");
//        lbDate.putClientProperty(FlatClientProperties.STYLE, "" +
//                "foreground:fade(#00f763,90%);" +
//                "background:fade(#1f9669,10%)");
        lbDate.putClientProperty(FlatClientProperties.STYLE_CLASS, "greenBadge small");
        lbDate.setIcon(new FlatSVGIcon("images/calendar-days.svg", 0.5f).setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.decode("#00f763"))));

        JButton button = new JButton("", new FlatSVGIcon("images/check.svg", 0.8f).setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.decode("#007bff"))));
        button.addActionListener(e -> {

        });
        button.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:15;" +
                "margin:3,25,3,25;" +
                "borderWidth:1;" +
                "focusWidth:0;" +
                "innerFocusWidth:0;" +
                "background:null;");


        add(lbDate, "split 2, growx");
        add(button, "gapleft 10, gapright 10, top, trailing");

    }
}
