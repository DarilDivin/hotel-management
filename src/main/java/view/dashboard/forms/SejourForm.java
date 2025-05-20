package view.dashboard.forms;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import model.*;
import model.Controllers.ReceptionisteController;
import model.Controllers.ReservationController;
import model.Controllers.SejourController;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import raven.modal.component.SimpleModalBorder;
import raven.modal.option.Location;
import raven.modal.option.Option;
import sample.SampleData;
import view.forms.CreateSejour;
import view.system.Form;
import view.utils.table.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;

public class SejourForm extends Form {
    private JTable table;
    public SejourForm() {
        init();
    }

    public void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Sejour d : SejourController.getTousLesSejours()) {
            model.addRow(d.toTableRowCustom(table.getRowCount() + 1));
        }
    }

    private void init() {
        setLayout(new MigLayout("fillx,wrap", "[fill]", "[][fill,grow]"));
        add(createInfo());
        add(createCustomTable(), "gapx 7 7, h 590!, spany, growy");
        refreshTable();
    }

    private JPanel createInfo() {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap", "[fill]"));
        JLabel lbTitle = new JLabel("Liste des réservationss");
        JTextPane text = new JTextPane();
        text.setText("Ceci est la liste des Rréservations");
        text.setEditable(false);
        text.setBorder(BorderFactory.createEmptyBorder());
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "font:bold +" + (4 - 1));
        panel.add(lbTitle);
        panel.add(text, "width 500");
        return panel;
    }

    private Component createCustomTable() {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap,insets 10 0 10 0", "[fill]", "[][]0[fill,grow]"));

        /* creer le modele de table */
        Object[] columns = new Object[]{"Sélection", "#", "Id", "Client", "Chambre", "Date de Début", "Date de Fin", "Actions"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // autoriser la modification des cellules uniquement dans la colonne 0 pour la case à cocher
                return column == 0 || column == 7;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {

                if (columnIndex == 0)
                    return Boolean.class;

//                if (columnIndex == 2) {
//                    return Client.class;
//                }
//
//                if (columnIndex == 3) {
//                    return Chambre.class;
//                }
                return super.getColumnClass(columnIndex);
            }
        };

        // create table
        this.table = new JTable(model);

        // table scroll
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // table option
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(1).setMaxWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.getColumnModel().getColumn(7).setMinWidth(250);

        // disable reordering table column
        table.getTableHeader().setReorderingAllowed(false);

        // apply client cell renderer
        table.setDefaultRenderer(Client.class, new TableClientCellRenderer(table));

        // apply Chambre cell renderer
        table.setDefaultRenderer(Chambre.class, new TableChambreCellRenderer(table));

        // apply checkbox custom to the table header
        table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));

        // apply action button cell renderer
        table.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRenderer());

        TableSejourActionCellEditor editor = new TableSejourActionCellEditor();
        editor.setTableButtonsListener(new TableSejourActionCellListener() {
            @Override
            public void onModifier(int row) {
                int id = getSejourIdFromRow(row);
                Sejour r = SejourController.getSejourById(id);

                Option option = ModalDialog.createOption();
                option.getLayoutOption().setSize(-1, 1f)
                        .setLocation(Location.TRAILING, Location.TOP)
                        .setAnimateDistance(0.7f, 0);

                Component parent = SwingUtilities.getWindowAncestor(table);

                ModalDialog.showModal(parent, new SimpleModalBorder(
                        new CreateSejour(r), "Modifier", SimpleModalBorder.DEFAULT_OPTION,
                        (controller, action) -> {
                            if(action == SimpleModalBorder.OK_OPTION) {
                                refreshTable();
                            }
                        }), option, CreateSejour.ID);

            }

            @Override
            public void onSupprimer(int row) {
                System.out.println("Suppression de la ligne " + row);
            }

            @Override
            public void onAjouterConso(int row) {
                System.out.println("Ajout de la ligne " + row);
            }

            @Override
            public void onPrint(int row) {
                System.out.println("Print de la ligne " + row);
            }
        });
        table.getColumnModel().getColumn(7).setCellEditor(editor);


        // alignment table header
        table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table) {
            @Override
            protected int getAlignment(int column) {
                if (column == 1) {
                    return SwingConstants.CENTER;
                }
                if (column == 7) {
                    return SwingConstants.TRAILING;
                }
                return SwingConstants.LEADING;
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (column == 7) {
                    ((JLabel) component).setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
                }
                return component;
            }
        });

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

        // create title
        JLabel title = new JLabel("Table Séjours");
        title.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        panel.add(title, "gapx 20");

        // create header
        panel.add(createHeaderAction());
        panel.add(scrollPane);

        // sample data
        for (Sejour d : SejourController.getTousLesSejours() ) {
            model.addRow(d.toTableRowCustom(table.getRowCount() + 1));
        }
        return panel;
    }

//    private Sejour getSejourFromRow(int row) {
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
//
//        Client client = (Client) model.getValueAt(row, 2);
//        Chambre chambre = (Chambre) model.getValueAt(row, 3);
//        Date dateDebut = (Date) model.getValueAt(row, 4);
//        Date dateFin = (Date) model.getValueAt(row, 5);
//
//        Hotel hotel = new Hotel( "Grand Hotel Paris", "123 Rue de Rivoli, 75001 Paris");
//        Receptioniste receptioniste = ReceptionisteController.getReceptionisteById(1);
//
//
//        return new Sejour(new Reservation(client, dateDebut, dateFin, receptioniste, chambre));
//    }

    private int getSejourIdFromRow(int row) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        return (int) model.getValueAt(row, 2);
    }
    private Component createHeaderAction() {
        JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[fill,230]push[][]"));

        JTextField txtSearch = new JTextField();
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("images/search.svg", 0.4f));
        JButton cmdCreate = new JButton("Create");
        JButton cmdRefresh = new JButton(new FlatSVGIcon("images/refresh.svg", 0.4f).setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.decode("#ffffff"))));

        cmdCreate.addActionListener(e -> showModal());
        cmdRefresh.addActionListener(e -> refreshTable());
        panel.add(txtSearch);
        panel.add(cmdCreate);
        panel.add(cmdRefresh);

        panel.putClientProperty(FlatClientProperties.STYLE, "background:null;");
        return panel;
    }

    private void showModal() {
        Option option = ModalDialog.createOption();
        option.getLayoutOption().setSize(-1, 1f)
                .setLocation(Location.TRAILING, Location.TOP)
                .setAnimateDistance(0.7f, 0);
        ModalDialog.showModal(this, new SimpleModalBorder(
                new CreateSejour(), "Créer", SimpleModalBorder.DEFAULT_OPTION,
                (controller, action) -> {
                    if(action == SimpleModalBorder.OK_OPTION) {
                        refreshTable();
                    }
                }), option, CreateSejour.ID);
    }
}
