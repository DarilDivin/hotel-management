package view.dashboard.forms;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import model.Controllers.PersonnelController;
import model.Personnel;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import raven.modal.Toast;
import raven.modal.component.SimpleModalBorder;
import raven.modal.option.Location;
import raven.modal.option.Option;
import view.components.SimpleMessageModal;
import view.forms.CreatePersonnel;
import view.system.Form;
import view.utils.ToastManager;
import view.utils.table.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminUserForm extends Form {
    public AdminUserForm() {
        init();
    }

    private void refreshTableData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Personnel p : PersonnelController.getTousLesPersonnels()) {
            model.addRow(p.toTableRowCustom(table.getRowCount() + 1));
        }
    }
    private JTable table;

    private void init() {
        setLayout(new MigLayout("fillx,wrap", "[fill]", "[][fill,grow]"));
        add(createInfo());
        add(createCustomTable(), "gapx 7 7, h 590!, spany, growy");
    }

    private JPanel createInfo() {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap", "[fill]"));
        JLabel lbTitle = new JLabel("Liste des utilisateurs");
        JTextPane text = new JTextPane();
        text.setText("Ceci est la liste des Utilisateurs");
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
        Object[] columns = new Object[]{"Sélection", "#", "Id", "Nom", "Prenoms", "Email", "Hotel", "Rôle", "Actions"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // autoriser la modification des cellules uniquement dans la colonne 0 pour la case à cocher
                return column == 0 || column == 8;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // use boolean type at column 0 for a checkbox
                if (columnIndex == 0)
                    return Boolean.class;
//                // use profile class
//                if (columnIndex == 2) {
//                    return ModelProfile.class;
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
        table.getColumnModel().getColumn(2).setMaxWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
        table.getColumnModel().getColumn(6).setPreferredWidth(150);
        table.getColumnModel().getColumn(7).setPreferredWidth(150);
        table.getColumnModel().getColumn(8).setPreferredWidth(300);

        // disable reordering table column
        table.getTableHeader().setReorderingAllowed(false);

        // apply profile cell renderer
//        table.setDefaultRenderer(ModelProfile.class, new TableProfileCellRenderer(table));

        // apply checkbox custom to the table header
        table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));

        // apply action button cell renderer
        table.getColumnModel().getColumn(8).setCellRenderer(new TableActionCellRenderer());
//

        TableActionCellEditor editor = new TableActionCellEditor();
        editor.setTableButtonsListener(new TableButtonsListener() {
            @Override
            public void onModifier(int row) {
                int id = getPersonnelId(row);
                Personnel p = PersonnelController.getPersonnelById(id);

                Option option = ModalDialog.createOption();
                option.getLayoutOption().setSize(-1, 1f)
                        .setLocation(Location.TRAILING, Location.TOP)
                        .setAnimateDistance(0.7f, 0);

                Component parent = SwingUtilities.getWindowAncestor(table);

                ModalDialog.showModal(parent, new SimpleModalBorder(
                        new CreatePersonnel(p), "Modifier", SimpleModalBorder.DEFAULT_OPTION,
                        (controller, action) -> {
                            if (action == SimpleModalBorder.OK_OPTION) {
                                refreshTableData();
                            }
                        }), option, CreatePersonnel.ID);
            }

            @Override
            public void onSupprimer(int row) {
                int id = getPersonnelId(row);

                Option option = ModalDialog.createOption()
                        .setAnimationEnabled(true);
                option.getLayoutOption()
                        .setLocation(Location.CENTER, Location.CENTER);

                Component parent = SwingUtilities.getWindowAncestor(table);
                JComponent jParent = (JComponent) SwingUtilities.getAncestorOfClass(JComponent.class, table);


                String titre = "Suppression Personnel";
                String message = "Voulez vous vraiment supprimer cet élément ??";

                ModalDialog.showModal(
                        parent,
                        new SimpleMessageModal(
                                SimpleMessageModal.Type.WARNING,
                                message, titre,
                                SimpleModalBorder.YES_NO_OPTION,
                                (controller, action) -> {
                                    if(action==SimpleModalBorder.YES_OPTION) {
                                        PersonnelController.supprimerPersonnel(id);
                                        refreshTableData();
                                        ToastManager.getInstance().showToast(jParent, Toast.Type.SUCCESS, "Client supprimé avec succès");
                                    }
                                }),
                        option
                );
            }
        });
        table.getColumnModel().getColumn(8).setCellEditor(editor);


        // alignment table header
        table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table) {
            @Override
            protected int getAlignment(int column) {
                if (column == 1) {
                    return SwingConstants.CENTER;
                }
                if (column == 8) {
                    return SwingConstants.TRAILING;
                }
                return SwingConstants.LEADING;
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (column == 8) {
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
        JLabel title = new JLabel("Table Utilisateur");
        title.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        panel.add(title, "gapx 20");

        // create header
        panel.add(createHeaderAction());
        panel.add(scrollPane);


        for (Personnel d : PersonnelController.getTousLesPersonnels() ) {
            model.addRow(d.toTableRowCustom(table.getRowCount() + 1));
        }
        return panel;
    }

    private int getPersonnelId(int row) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        return (int) model.getValueAt(row, 2);
    }

    private Component createHeaderAction() {
        JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[fill,230]push[][]"));

        JTextField txtSearch = new JTextField();
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("images/search.svg", 0.4f));
        JButton cmdCreate = new JButton("Créer");

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
                new CreatePersonnel(), "Créer", SimpleModalBorder.DEFAULT_OPTION,
                (controller, action) -> {
                    if (action == SimpleModalBorder.OK_OPTION) {
                        System.out.println("OK");
                        refreshTableData();
                    }
                }), option, CreatePersonnel.ID);
    }

}
