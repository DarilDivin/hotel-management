package view.home.components;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;
import raven.datetime.DatePicker;
import raven.datetime.event.DateSelectionEvent;
import raven.datetime.event.DateSelectionListener;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SearchBar extends JPanel {

    public SearchBar() {
        setLayout(new MigLayout(
                "insets 0 20 0 20, gap 5, wrap 4, fill", // 20px de marge intérieure, 20px d'espacement entre images, 3 images par ligne
                "[grow, fill][grow, fill][grow, fill][100px, fill]", // 2 colonnes qui grandissent également, dernière colonne fixe
                "[grow, center]"));
        putClientProperty(FlatClientProperties.STYLE, "" +
                "border:8,8,8,8;" +      // Ligne de bordure grise
                "arc:$Component.arc;" +
                "background:fade(@accentColor,5%);"
        );


        JTextField placeField = new JTextField();

        placeField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Paris, France");
        JLabel lbplace = new JLabel();
        lbplace.setIcon(new FlatSVGIcon("images/map.svg", 10, 16));
        lbplace.putClientProperty(FlatClientProperties.STYLE, "" + "border:0,8,0,0;");
        placeField.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_COMPONENT, lbplace);


        JFormattedTextField dateEditor = new JFormattedTextField();
        DatePicker datePicker = new DatePicker();
        datePicker.setDateSelectionMode(DatePicker.DateSelectionMode.BETWEEN_DATE_SELECTED);
        datePicker.setSeparator(" au ");
        datePicker.setDateSelectionAble(localDate -> !localDate.isBefore(LocalDate.now()));
        datePicker.addDateSelectionListener(new DateSelectionListener() {
            @Override
            public void dateSelected(DateSelectionEvent dateSelectionEvent) {
                LocalDate[] dates = datePicker.getSelectedDateRange();
                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                if (dates != null) {
                    System.out.println(df.format(dates[0]) + " - " + df.format(dates[1]));
                }
            }
        });
        datePicker.setEditor(dateEditor);


        JTextField txtAmount = new JTextField();

        txtAmount.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "0.00");
        JLabel lbDollar = new JLabel("$");
        lbDollar.putClientProperty(FlatClientProperties.STYLE, "" + "border:0,8,0,0;");
        txtAmount.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_COMPONENT, lbDollar);


        JButton searchBtn = new JButton("Rechercher") {
            @Override
            public boolean isDefaultButton() {
                return true;
            }
        };

        add(placeField);
        add(txtAmount);
        add(dateEditor);
        add(searchBtn);

//        setBounds(450, 170, 300, 40); // Positionnement en (x, y) sur l'image


    }

}
