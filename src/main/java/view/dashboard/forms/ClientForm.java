package view.dashboard.forms;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import model.Client;
import model.Controllers.ClientController;
import model.Controllers.HotelController;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import raven.modal.Toast;
import raven.modal.component.SimpleModalBorder;
import raven.modal.option.Location;
import raven.modal.option.Option;
import sample.SampleData;
import view.components.SimpleMessageModal;
import view.forms.CreateClient;
import view.forms.CreatePersonnel;
import view.system.Form;
import view.utils.ToastManager;
import view.utils.table.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ClientForm extends Form {

    private JTable table;

    public ClientForm() {
        init();
    }

    private void refreshTableData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Client client : ClientController.getTousLesClients()) {
            model.addRow(client.toTableRowCustom(table.getRowCount() + 1));
        }
    }

    private void init() {
        setLayout(new MigLayout("fillx,wrap", "[fill]", "[][fill,grow]"));
        add(createInfo());
        add(createCustomTable(), "gapx 7 7, h 590!, spany, growy");
    }

    private JPanel createInfo() {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap", "[fill]"));
        JLabel lbTitle = new JLabel("Liste des Client");
        JTextPane text = new JTextPane();
        text.setText("Ceci est la liste des Client de l'hotel");
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
        Object[] columns = new Object[]{"Sélection", "#", "Id", "Nom", "Prenoms", "Email", "Actions"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0 || column == 6;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0)
                    return Boolean.class;
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
        table.getColumnModel().getColumn(2).setMaxWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
        table.getColumnModel().getColumn(6).setPreferredWidth(300);

        // disable reordering table column
        table.getTableHeader().setReorderingAllowed(false);

        // apply checkbox custom to the table header
        table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));

        // apply action button cell renderer
        table.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRenderer());

        TableActionCellEditor editor = new TableActionCellEditor();
        editor.setTableButtonsListener(new TableButtonsListener() {
            @Override
            public void onModifier(int row) {
                int id = getClientIdFromRow(row);
                Client clientToEdit = ClientController.getClientById(id);

                Option option = ModalDialog.createOption();
                option.getLayoutOption().setSize(-1, 1f)
                        .setLocation(Location.TRAILING, Location.TOP)
                        .setAnimateDistance(0.7f, 0);

                Component parent = SwingUtilities.getWindowAncestor(table);

                ModalDialog.showModal(parent, new SimpleModalBorder(
                        new CreateClient(clientToEdit), "Modifier", SimpleModalBorder.DEFAULT_OPTION,
                        (controller, action) -> {
                            if (action == SimpleModalBorder.OK_OPTION) {
                                System.out.println("OK");
                                refreshTableData();
                            }

                        }), option, CreatePersonnel.ID);
            }

            @Override
            public void onSupprimer(int row) {
                int id = getClientIdFromRow(row);

                Option option = ModalDialog.createOption()
                        .setAnimationEnabled(true);
                option.getLayoutOption()
                        .setLocation(Location.CENTER, Location.CENTER);

                Component parent = SwingUtilities.getWindowAncestor(table);
                JComponent jParent = (JComponent) SwingUtilities.getAncestorOfClass(JComponent.class, table);


                String titre = "Suppression Client";
                String message = "Voulez vous vraiment supprimer cet élément ??";

                ModalDialog.showModal(
                        parent,
                        new SimpleMessageModal(
                                SimpleMessageModal.Type.WARNING,
                                message, titre,
                                SimpleModalBorder.YES_NO_OPTION,
                                (controller, action) -> {
                                    if(action==SimpleModalBorder.YES_OPTION) {
                                        ClientController.supprimerClient(id);
                                        refreshTableData();
                                        ToastManager.getInstance().showToast(jParent, Toast.Type.SUCCESS, "Client supprimé avec succès");
                                    }
                                }),
                        option
                );
            }
        });
        table.getColumnModel().getColumn(6).setCellEditor(editor);

        // alignment table header
        table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table) {
            @Override
            protected int getAlignment(int column) {
                if (column == 1) {
                    return SwingConstants.CENTER;
                }
                if (column == 6) {
                    return SwingConstants.TRAILING;
                }
                return SwingConstants.LEADING;
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (column == 6) {
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
        JLabel title = new JLabel("Table Client");
        title.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        panel.add(title, "gapx 20");


        panel.add(createHeaderAction());
        panel.add(scrollPane);


//        for (Client d : SampleData.getSampleClientData()) {
//            model.addRow(d.toTableRowCustom(table.getRowCount() + 1));
//        }

        for (Client cl : ClientController.getTousLesClients()) {
            model.addRow(cl.toTableRowCustom(table.getRowCount() + 1));
        }
        return panel;
    }

    private Client getClientFromRow(int row) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        String nom = (String) model.getValueAt(row, 3);
        String prenom = (String) model.getValueAt(row, 4);
        String email = (String) model.getValueAt(row, 5);

        return new Client(nom, prenom, email, SampleData.getSampleHotelData().getFirst());
    }

    private int getClientIdFromRow(int row) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        return (int) model.getValueAt(row, 2);
    }

    private Component createHeaderAction() {
        JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[fill,230]push[][]"));

        JTextField txtSearch = new JTextField();
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("raven/modal/demo/icons/search.svg", 0.4f));
        JButton cmdCreate = new JButton("Create");

        cmdCreate.addActionListener(e -> showModal());
        panel.add(txtSearch);
        panel.add(cmdCreate);

        panel.putClientProperty(FlatClientProperties.STYLE, "background:null;");
        return panel;
    }

    private void showModal() {
        Option option = ModalDialog.createOption();
        option.getLayoutOption().setSize(-1, 1f)
                .setLocation(Location.TRAILING, Location.TOP)
                .setAnimateDistance(0.7f, 0);
        ModalDialog.showModal(this, new SimpleModalBorder(
                new CreateClient(), "Créer", SimpleModalBorder.DEFAULT_OPTION,
                (controller, action) -> {
                    if (action == SimpleModalBorder.OK_OPTION) {
                        System.out.println("OK");
                        refreshTableData();
                    }

                }), option, CreateClient.ID);
    }
}
