package view.dashboard.forms;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;
import view.system.Form;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HotelForm extends Form {

    public HotelForm() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx,wrap", "[fill]", "[][fill,grow]"));
        add(createInfo());
        add(createCustomTable(), "gapx 7 7, h 590!, spany, growy");
    }

    private JPanel createInfo() {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap", "[fill]"));
        JLabel lbTitle = new JLabel("Liste des Hôtels");
        JTextPane text = new JTextPane();
        text.setText("Voici la liste complète des hôtels");
        text.setEditable(false);
        text.setBorder(BorderFactory.createEmptyBorder());
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "font:bold +" + (4 - 1));
        panel.add(lbTitle);
        panel.add(text, "width 500");
        return panel;
    }

    private Component createCustomTable() {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap,insets 10 0 10 0", "[fill]", "[][]0[fill,grow]"));

        // Définition des colonnes
        Object[] columns = new Object[]{"#", "Nom", "Adresse", "Ville", "Étoiles", "Nb Chambres", "Actions"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6; // Seule la colonne Actions est éditable
            }
        };

        // Création de la table
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Configuration des colonnes
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(4).setMaxWidth(100);
        table.getColumnModel().getColumn(5).setMaxWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(200);

        // Styles
        configureTableStyle(table, scrollPane, panel);

        // Titre
        JLabel title = new JLabel("Liste des Hôtels");
        title.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        panel.add(title, "gapx 20");

        // En-tête avec recherche et bouton création
        panel.add(createHeaderAction());
        panel.add(scrollPane);

        return panel;
    }

    private void configureTableStyle(JTable table, JScrollPane scrollPane, JPanel panel) {
        panel.putClientProperty(FlatClientProperties.STYLE, "arc:20;background:$Table.background;");
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE,
                "height:30;hoverBackground:null;pressedBackground:null;separatorColor:$TableHeader.background;");
        table.putClientProperty(FlatClientProperties.STYLE,
                "rowHeight:40;showHorizontalLines:true;intercellSpacing:0,1;");
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE,
                "trackArc:$ScrollBar.thumbArc;trackInsets:3,3,3,3;thumbInsets:3,3,3,3;background:$Table.background;");
    }

    private Component createHeaderAction() {
        JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[fill,230]push[][]"));

        JTextField txtSearch = new JTextField();
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Rechercher un hôtel...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON,
                new FlatSVGIcon("raven/modal/demo/icons/search.svg", 0.4f));

        JButton cmdCreate = new JButton("Nouvel Hôtel");
        cmdCreate.addActionListener(e -> showCreateHotelDialog());

        panel.add(txtSearch);
        panel.add(cmdCreate);
        panel.putClientProperty(FlatClientProperties.STYLE, "background:null;");

        return panel;
    }

    private void showCreateHotelDialog() {
        // À implémenter : Afficher une boîte de dialogue pour créer un nouvel hôtel
        JOptionPane.showMessageDialog(this, "Fonctionnalité à implémenter");
    }
}
