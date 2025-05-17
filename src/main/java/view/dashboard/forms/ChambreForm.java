package view.dashboard.forms;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import view.components.RoomCard;
import view.layout.ResponsiveLayout;
import view.system.Form;
import view.utils.SystemForm;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

@SystemForm(name = "Liste des chambres d'un Hotel", description = "Il s'agit de la liste des chambres d'un Hotel")
public class ChambreForm extends Form {

    private JPanel panelLayout;

    public ChambreForm() {
        init();
    }

    private void init() {
//        setLayout(new MigLayout("wrap, fillx", "[center]"));
        setLayout(new BorderLayout(0, 0));
        createPanelLayout();

        responsiveLayout = new ResponsiveLayout(ResponsiveLayout.JustifyContent.FIT_CONTENT, new Dimension(300, 515), 10, 10);
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
//        scrollPane.setBorder(new TitledBorder("Example"));
        add(scrollPane);

        for (int i = 0; i < 8; i++) {
            RoomCard card = new RoomCard();
            card.putClientProperty(FlatClientProperties.STYLE, "" +
                    "border:8,8,8,8;" +
                    "arc:$Component.arc;"+
                    "background:fade(@accentColor,5%);");
            panelCard.add(card, "split 3, w 320!, h 510!, al leading center, gapbefore 15, gapafter 15, gapy 10 10");
        }

    }

    private void createPanelLayout() {
        panelLayout = new JPanel(new MigLayout("wrap,fillx", "[center]"));
        JScrollPane scrollPane = new JScrollPane(panelLayout);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" +
                "width:5;" +
                "trackArc:$ScrollBar.thumbArc;" +
                "trackInsets:0,0,0,0;" +
                "thumbInsets:0,0,0,0;");
        add(scrollPane);
    }

    private JPanel panelCard;
    private ResponsiveLayout responsiveLayout;
}
