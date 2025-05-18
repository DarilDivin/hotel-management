package view.dashboard.forms;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import raven.modal.component.SimpleModalBorder;
import raven.modal.option.Location;
import raven.modal.option.Option;
import view.components.RoomCard;
import view.forms.CreateChambre;
import view.forms.CreateHotel;
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
        headerPanel();

        responsiveLayout = new ResponsiveLayout(ResponsiveLayout.JustifyContent.FIT_CONTENT, new Dimension(280, 515), 10, 10);
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
                    "[dark]background:tint($Panel.background,5%);" +
                    "[light]background:fade(@accentColor,5%);");
            panelCard.add(card, "split 2, w 280!, h 510!, al leading center, gapbefore 15, gapafter 15, gapy 10 10");
        }

    }

    private void headerPanel() {
        JPanel panel = new JPanel(new MigLayout("wrap,fillx", "[center]"));
        JLabel title = new JLabel("Liste des chambres");
        title.setFont(new Font("", Font.BOLD, 28));
        panel.add(title, "split 2, gapbefore 10, gapafter 10, gapy 10, w 950!");

        JButton cmdCreate = new JButton("Créer une chambre");
        panel.add(cmdCreate, "al right, gapbefore 10, gapafter 10, gapy 10");
        add(panel, BorderLayout.NORTH);

        // evenement
        cmdCreate.addActionListener((e) -> {
            showCreateChambreDialog();
        });
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

    private void showCreateChambreDialog() {
        Option option = ModalDialog.createOption();
        option.getLayoutOption().setSize(-1, 1f)
                .setLocation(Location.TRAILING, Location.TOP)
                .setAnimateDistance(0.7f, 0);
        ModalDialog.showModal(this, new SimpleModalBorder(
                new CreateChambre(), "Create", SimpleModalBorder.DEFAULT_OPTION,
                (controller, action) -> {

                }), option, CreateChambre.ID);
    }

    private JPanel panelCard;
    private ResponsiveLayout responsiveLayout;
}
