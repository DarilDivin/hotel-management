package view.dashboard.forms;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import model.Chambre;
import model.Controllers.ChambreController;
import model.Controllers.HotelController;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import raven.modal.component.SimpleModalBorder;
import raven.modal.option.Location;
import raven.modal.option.Option;
import view.components.RoomCard;
import view.dashboard.menu.MyDrawerBuilder;
import view.forms.CreateChambre;
import view.layout.ResponsiveLayout;
import view.system.Form;
import view.utils.SystemForm;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

@SystemForm(name = "Liste des chambres d'un Hotel", description = "Il s'agit de la liste des chambres d'un Hotel")
public class ChambreForm extends Form {

    private JPanel panelLayout;
    private Vector<Chambre> chambres;

    public ChambreForm() {
        init();
    }

    public void refreshChambreList() {
//         chambres = ChambreController.getTousLesChambres();
        chambres = HotelController.getChambres(MyDrawerBuilder.getInstance().getPersonnel().getHotel());
        panelCard.removeAll();

        for (Chambre chambre : chambres) {
            System.out.println(chambre.getHotel());
            RoomCard card = new RoomCard(chambre, this);
            card.putClientProperty(FlatClientProperties.STYLE, "" +
                    "border:8,8,8,8;" +
                    "arc:$Component.arc;" +
                    "[dark]background:tint($Panel.background,5%);" +
                    "[light]background:fade(@accentColor,5%);");
            panelCard.add(card, "");
        }

        panelCard.revalidate();
        panelCard.repaint();
    }

    private void init() {
        setLayout(new MigLayout("fillx,wrap", "[fill]", "[][fill,grow]"));

        //createPanelLayout();
        headerPanel();

        responsiveLayout = new ResponsiveLayout(ResponsiveLayout.JustifyContent.FIT_CONTENT, new Dimension(280, -1), 10, 10);
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
        //scrollPane.setBorder(new TitledBorder("Example"));
        add(scrollPane, "gapx 7 7, spany, growy");

//        for (int i = 0; i < 8; i++) {
//            RoomCard card = new RoomCard();
//            card.putClientProperty(FlatClientProperties.STYLE, "" +
//                    "border:8,8,8,8;" +
//                    "arc:$Component.arc;"+
//                    "[dark]background:tint($Panel.background,5%);" +
//                    "[light]background:fade(@accentColor,5%);");
//            panelCard.add(card, "");
//        }

        chambres = ChambreController.getTousLesChambres();

        for (Chambre c : chambres) {
            RoomCard card = new RoomCard(c, this);
            card.putClientProperty(FlatClientProperties.STYLE, "" +
                    "border:8,8,8,8;" +
                    "arc:$Component.arc;"+
                    "[dark]background:tint($Panel.background,5%);" +
                    "[light]background:fade(@accentColor,5%);");
            panelCard.add(card, "");
        }

        //split 2, w 280!, h 510!, al leading center, gapbefore 15, gapafter 15, gapy 10 10

    }

    private void headerPanel() {
        JPanel panel = new JPanel(new MigLayout("wrap,fillx", "[fill]"));
        JLabel title = new JLabel("Liste des chambres");
        title.setFont(new Font("", Font.BOLD, 28));
        panel.add(title, "split 3, gapy 10, w 100%-180!");

        JButton cmdCreate = new JButton("Créer une chambre");
        panel.add(cmdCreate, "al right, gapy 10, w 150!");

//        JButton cmdRefresh = new JButton(new FlatSVGIcon("images/refresh.svg", 0.4f).setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.decode("#ffffff"))));
//
//        cmdRefresh.addActionListener(e -> {refreshChambreList();});

//        panel.add(cmdRefresh, "gapy 10");
        add(panel, "gapx 7 7");


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
                    if (action == SimpleModalBorder.OK_OPTION) {
                        refreshChambreList();
                    }
                }), option, CreateChambre.ID);
    }

    private JPanel panelCard;
    private ResponsiveLayout responsiveLayout;
}
