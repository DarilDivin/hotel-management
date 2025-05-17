package view.dashboard.forms;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import model.Hotel;
import model.utilsModel.ModelProfile;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import raven.modal.component.SimpleModalBorder;
import raven.modal.option.Location;
import raven.modal.option.Option;
import sample.SampleData;
import view.forms.CreateHotel;
import view.forms.CreatePersonnel;
import view.system.Form;
import view.utils.table.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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
        Object[] columns = new Object[]{"Sélection", "#", "Nom", "Adresse", "Actions"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0 || column == 4; // Seule la colonne Actions est éditable
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // use boolean type at column 0 for a checkbox
                if (columnIndex == 0)
                    return Boolean.class;
                return super.getColumnClass(columnIndex);
            }
        };

        // Création de la table
        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Configuration des colonnes
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(1).setMaxWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
        table.getColumnModel().getColumn(3).setPreferredWidth(300);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);

        // disable reordering table column
        table.getTableHeader().setReorderingAllowed(false);

        //apply checkbox custom to the table header
        table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));

        // apply action button cell renderer
        table.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRenderer());

        TableActionCellEditor editor = new TableActionCellEditor();
        editor.setTableButtonsListener(new TableButtonsListener() {
            @Override
            public void onModifier(int row) {
                // Votre code pour la modification
                System.out.println("Modification de la ligne " + row);
            }

            @Override
            public void onSupprimer(int row) {
                // Votre code pour la suppression
                System.out.println("Suppression de la ligne " + row);
            }
        });
        table.getColumnModel().getColumn(4).setCellEditor(editor);

        // alignment table header
        table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table) {
            @Override
            protected int getAlignment(int column) {
                if (column == 1) {
                    return SwingConstants.CENTER;
                }
                if (column == 4) {
                    return SwingConstants.TRAILING;
                    
                }
                return SwingConstants.LEADING;
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (column == 4) {
                    ((JLabel) component).setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
                }
                return component;
            }

        });

        // Pour les cellules du tableau également
//        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//                if (column == 4) {
//                    setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
//                }
//                return component;
//            }
//        });


        // style
        panel.putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "background:$Table.background;");
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "height:30;" +
                "hoverBackground:null;" +
                "pressedBackground:null;" +
                "separatorColor:$TableHeader.background;");
        table.putClientProperty(FlatClientProperties.STYLE, "rowHeight:70;" +
                "showHorizontalLines:true;" +
                "intercellSpacing:0,1;" +
                "cellFocusColor:$TableHeader.hoverBackground;" +
                "selectionBackground:$TableHeader.hoverBackground;" +
                "selectionInactiveBackground:$TableHeader.hoverBackground;" +
                "selectionForeground:$Table.foreground;");
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "trackArc:$ScrollBar.thumbArc;" +
                "trackInsets:3,3,3,3;" +
                "thumbInsets:3,3,3,3;" +
                "background:$Table.background;");
        // Titre
        JLabel title = new JLabel("Liste des Hôtels");
        title.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        panel.add(title, "gapx 20");

        // En-tête avec recherche et bouton création
        panel.add(createHeaderAction());
        panel.add(scrollPane);

        // sample data
        for (Hotel d : SampleData.getSampleHotelData()) {
            model.addRow(d.toTableRowCustom(table.getRowCount() + 1));
        }
        return panel;
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
//        JOptionPane.showMessageDialog(this, "Fonctionnalité à implémenter");

        Option option = ModalDialog.createOption();
        option.getLayoutOption().setSize(-1, 1f)
                .setLocation(Location.TRAILING, Location.TOP)
                .setAnimateDistance(0.7f, 0);
        ModalDialog.showModal(this, new SimpleModalBorder(
                new CreateHotel(), "Create", SimpleModalBorder.DEFAULT_OPTION,
                (controller, action) -> {

                }), option, CreateHotel.ID);
    }
}
