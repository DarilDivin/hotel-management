package view.dashboard.forms;

import com.formdev.flatlaf.FlatClientProperties;
import model.AgentNettoyage;
import model.Controllers.InterventionController;
import model.Intervention;
import net.miginfocom.swing.MigLayout;
import view.components.InterventionCard;
import view.dashboard.menu.MyDrawerBuilder;
import view.layout.ResponsiveLayout;
import view.system.Form;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class InterventionForm extends Form {
    private JPanel panelCard;
    private Vector<Intervention> interventions;

    public InterventionForm() {
        init();
    }

//    public void refreshInterventionList() {
//        interventions = InterventionController.creerInterventions((AgentNettoyage) MyDrawerBuilder.getInstance().getPersonnel());
//        panelCard.removeAll();
//
//        for (Intervention intervention : interventions) {
//            InterventionCard card = new InterventionCard(intervention);
//            card.putClientProperty(FlatClientProperties.STYLE, "" +
//                    "border:8,8,8,8;" +
//                    "arc:$Component.arc;" +
//                    "[dark]background:tint($Panel.background,5%);" +
//                    "[light]background:fade(@accentColor,5%);" +
//                    "[light]background:fade(#fdfdf6,100%);" +
//                    "[dark]borderColor:#333333;" +
//                    "[dark]borderColor:#dddddd;");
//
//            panelCard.add(card, "");
//        }
//        
//        panelCard.revalidate();
//        panelCard.repaint();
//    }

    private void init() {
        setLayout(new MigLayout("fillx,wrap", "[fill]", "[][fill,grow]"));
        headerPanel();
        createPanelLayout();

    }

    private void headerPanel() {
        JPanel panel = new JPanel(new MigLayout("wrap,fillx", "[fill]"));
        JLabel title = new JLabel("Liste des Interventions");
        title.setFont(new Font("", Font.BOLD, 28));
        panel.add(title, "split 2, gapy 10, w 100%-180!");

//        JButton cmdCreate = new JButton("Créer une chambre");
//        panel.add(cmdCreate, "al right, gapy 10, w 150!");
        add(panel, "gapx 7 7");

    }

    private void createPanelLayout() {
        ResponsiveLayout responsiveLayout = new ResponsiveLayout(ResponsiveLayout.JustifyContent.FIT_CONTENT, new Dimension(280, -1), 10, 10);
        panelCard = new JPanel(responsiveLayout);
        panelCard.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:10,10,10,10;");
        JScrollPane scrollPane = new JScrollPane(panelCard);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.getHorizontalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" +
                "trackArc:$ScrollBar.thumbArc;" +
                "thumbInsets:0,0,0,0;" +
                "width:5;");
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" +
                "trackArc:$ScrollBar.thumbArc;" +
                "thumbInsets:0,0,0,0;" +
                "width:5;");
        add(scrollPane, "gapx 7 7, spany, growy");
        
        interventions = InterventionController.creerInterventions(new AgentNettoyage(MyDrawerBuilder.getInstance().getPersonnel()));

        for (Intervention intervention : interventions) {
            InterventionCard card = new InterventionCard(intervention);
            card.putClientProperty(FlatClientProperties.STYLE, "" +
                    "border:8,8,8,8;" +
                    /*"borderWidth:1;" +*/
                    "arc:$Component.arc;" +
                    "[dark]background:tint($Panel.background,5%);" +
                    "[light]background:fade(@accentColor,5%);" +
                    "[light]background:fade(#fdfdf6,100%);" /*+
                    "borderColor:lighten($Panel.background,10%);"*/);

            panelCard.add(card, "");
        }
    }
}
