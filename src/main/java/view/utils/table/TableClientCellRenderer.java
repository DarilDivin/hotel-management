package view.utils.table;

import com.formdev.flatlaf.FlatClientProperties;
import model.Client;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableClientCellRenderer extends JPanel implements TableCellRenderer {
    private final TableCellRenderer delegate;

    public TableClientCellRenderer(JTable table) {
        delegate = table.getDefaultRenderer(Object.class);
        init();
    }

    public void init() {
        setLayout(new MigLayout("ay center,insets 7 0 7 0, wrap", "", "[sg h,bottom][sg h,top]"));
        labelNom = new JLabel();
        labelPrenom = new JLabel();
        labelEmail = new JLabel();
        labelEmail.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:$Label.disabledForeground;");

        add(labelNom, "split 2, gap 2");
        add(labelPrenom, "wrap");
        add(labelEmail, "cell 0 1");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel com = (JLabel) delegate.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value instanceof Client) {
            Client client = (Client) value;
            labelNom.setText(client.getNom());
            labelPrenom.setText(client.getPrenom());
            labelEmail.setText(client.getEmail());
            setBackground(com.getBackground());
            setBorder(com.getBorder());
            return this;
        }
        return com;
    }

    private JLabel labelNom;
    private JLabel labelPrenom;
    private JLabel labelEmail;
}
