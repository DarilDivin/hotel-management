package view.utils.table;

import com.formdev.flatlaf.FlatClientProperties;
import model.Chambre;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableChambreCellRenderer extends JPanel implements TableCellRenderer {

    private final TableCellRenderer delegate;

    public TableChambreCellRenderer(JTable table) {
        delegate = table.getDefaultRenderer(Object.class);
        init();
    }

    private void init() {
        setLayout(new MigLayout("ay center,insets 7 0 7 0", "", "[sg h,bottom][sg h,top]"));
        labelNumero = new JLabel();
        labelPrix = new JLabel();
        labelPrix.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:$Label.disabledForeground;");

        add(labelNumero, "cell 1 0");
        add(labelPrix, "cell 1 1");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel com = (JLabel) delegate.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value instanceof Chambre) {
          Chambre chambre = (Chambre) value;
          labelNumero.setText(chambre.getNumero());
          labelPrix.setText("$" + chambre.getPrix());
          setBackground(com.getBackground());
          setBorder(com.getBorder());
          return this;
        }
        return com;
    }

    private JLabel labelNumero;
    private JLabel labelPrix;
}
