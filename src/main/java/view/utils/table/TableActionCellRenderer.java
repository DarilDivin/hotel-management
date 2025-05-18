package view.utils.table;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import raven.modal.option.BorderOption;
import raven.modal.option.Option;
import view.login_register.CustomModalBorder;
import view.login_register.Login;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableActionCellRenderer extends JPanel implements TableCellRenderer {
    private JButton btnSupprimer;
    private JButton btnModifier;

    public TableActionCellRenderer() {
        setLayout(new MigLayout("insets 0 20 0 20, ay center, ax trailing"));

        btnModifier = new JButton("Modifier");
        btnSupprimer = new JButton("Supprimer");

        btnModifier.setFocusPainted(false);
        btnSupprimer.setFocusPainted(false);

        // Style des boutons
        btnModifier.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:8;" +
                "background:$Component.accentColor;" +
                "foreground:#FFFFFF;");
        btnSupprimer.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:8;" +
                "background:#FF4242;" +
                "foreground:#FFFFFF;");


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
