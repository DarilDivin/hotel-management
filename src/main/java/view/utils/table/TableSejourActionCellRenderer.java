package view.utils.table;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableSejourActionCellRenderer extends JPanel implements TableCellRenderer {
    private JButton btnAjouterConso;
    private JButton btnModifier;
    private JButton btnSupprimer;
    private JButton btnPrint;

    public TableSejourActionCellRenderer() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("insets 0 20 0 20, ay center, ax trailing"));

        btnAjouterConso = new JButton("Ajouter");
        btnModifier = new JButton("Modifier");
        btnSupprimer = new JButton("Supprimer");
        btnPrint = new JButton("Facturer");

        btnAjouterConso.setFocusPainted(false);
        btnModifier.setFocusPainted(false);
        btnSupprimer.setFocusPainted(false);
        btnPrint.setFocusPainted(false);

        // Style des boutons
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

        add(btnAjouterConso, "gap 5");
        add(btnPrint, "gap 5");
        add(btnModifier, "gap 5");
        add(btnSupprimer, "gap 5");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setBackground(table.getSelectionBackground());
        } else {
            setBackground(table.getBackground());
        }

        return this;
    }
}
