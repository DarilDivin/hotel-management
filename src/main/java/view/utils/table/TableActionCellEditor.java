package view.utils.table;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class TableActionCellEditor extends AbstractCellEditor implements TableCellEditor {
    private JPanel panel;
    private JButton btnSupprimer;
    private JButton btnModifier;
    private JTable table;
    private TableButtonsListener listener;

    public TableActionCellEditor() {
        panel = new JPanel(new MigLayout("insets 0, ay center"));

        btnSupprimer = new JButton("Supprimer");
        btnModifier = new JButton("Modifier");

        // Style des boutons
        btnModifier.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:8;" +
                "background:$Component.accentColor;" +
                "foreground:#FFFFFF;");
        btnSupprimer.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:8;" +
                "background:#FF4242;" +
                "foreground:#FFFFFF;");


        btnModifier.addActionListener(e -> {
            if (listener != null && table != null) {
                listener.onModifier(table.getSelectedRow());
            }
            fireEditingStopped();
        });

        btnSupprimer.addActionListener(e -> {
            if (listener != null && table != null) {
                listener.onSupprimer(table.getSelectedRow());
            }
            fireEditingStopped();
        });


        panel.add(btnModifier, "gap 5");
        panel.add(btnSupprimer, "gap 5");
    }

    public void setTableButtonsListener(TableButtonsListener listener) {
        this.listener = listener;
    }


    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        this.table = table;
        panel.setBackground(table.getSelectionBackground());
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return "";
    }

}
