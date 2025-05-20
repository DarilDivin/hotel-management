package view.utils.table;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class TableSejourActionCellEditor extends AbstractCellEditor implements TableCellEditor {
    private JPanel panel;
    private JButton btnAjouterConso;
    private JButton btnModifier;
    private JButton btnSupprimer;
    private JButton btnPrint;
    private JTable table;
    private TableSejourActionCellListener listener;

    public TableSejourActionCellEditor() {
        panel = new JPanel(new MigLayout("insets 0 20 0 20, ay center, ax trailing"));

        btnAjouterConso = new JButton("Ajouter");
        btnModifier = new JButton("Modifier");
        btnSupprimer = new JButton("Supprimer");
        btnPrint = new JButton("Facturer");

        btnAjouterConso.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:8;" +
                "background:#82F782;" +
                "foreground:#FFFFFF;");
        btnModifier.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:8;" +
                "background:#F7BD82;" +
                "foreground:#FFFFFF;");
        btnSupprimer.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:8;" +
                "background:#FF4242;" +
                "foreground:#FFFFFF;");
        btnPrint.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:8;" +
                "background:$Component.accentColor;" +
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

        btnAjouterConso.addActionListener(e -> {
            if (listener != null && table != null) {
                listener.onAjouterConso(table.getSelectedRow());
            }
            fireEditingStopped();
        });

        btnPrint.addActionListener(e -> {
            if (listener != null && table != null) {
                listener.onPrint(table.getSelectedRow());
            }
            fireEditingStopped();
        });

        panel.add(btnAjouterConso, "gap 5");
        panel.add(btnPrint, "gap 5");
        panel.add(btnModifier, "gap 5");
        panel.add(btnSupprimer, "gap 5");
    }

    public void setTableButtonsListener(TableSejourActionCellListener listener) {
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
