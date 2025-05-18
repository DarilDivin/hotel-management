package view.forms;

import com.formdev.flatlaf.FlatClientProperties;
import model.Chambre;
import model.Sejour;
import model.TypeChambre;
import net.miginfocom.swing.MigLayout;
import raven.datetime.DatePicker;
import raven.datetime.event.DateSelectionEvent;
import raven.datetime.event.DateSelectionListener;
import raven.modal.ModalDialog;

import javax.swing.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CreateSejour extends JPanel {
    public static final String ID = "create_sejour_id";

    private Sejour sejour;

    private Date dateDebut;
    private Date dateFin;

    public CreateSejour(Sejour sejour) {
        this.sejour = sejour;
        init();
    }

    public CreateSejour() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("insets n 20 n 20,fillx,wrap,width 380", "[fill]"));

        JTextArea text = new JTextArea("Lorem ipsum");
        text.setEditable(false);
        text.setFocusable(false);
        text.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:0,0,0,0;" +
                "background:null;");
        add(text);

        add(new JSeparator(), "gapy 15 15");

        JLabel lbdateDebut = new JLabel("Période de Séjour");
        lbdateDebut.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbdateDebut);



        JFormattedTextField dateEditor = new JFormattedTextField();
        DatePicker datePicker = new DatePicker();
        datePicker.setDateSelectionMode(DatePicker.DateSelectionMode.BETWEEN_DATE_SELECTED);
        datePicker.setSeparator(" au ");
        datePicker.setDateSelectionAble(localDate -> !localDate.isBefore(LocalDate.now()));
        datePicker.addDateSelectionListener(new DateSelectionListener() {
            @Override
            public void dateSelected(DateSelectionEvent dateSelectionEvent) {
                LocalDate[] dates = datePicker.getSelectedDateRange();
                if (dates != null) {
                    dateDebut = Date.from(dates[0].atStartOfDay(ZoneId.systemDefault()).toInstant());
                    dateFin = Date.from(dates[1].atStartOfDay(ZoneId.systemDefault()).toInstant());

                    // Pour l'affichage uniquement
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    System.out.println(df.format(dates[0]) + " - " + df.format(dates[1]));
                }

            }
        });
        datePicker.setEditor(dateEditor);


        add(dateEditor, "gapy n 10");
        JLabel lbChambre = new JLabel("Chambre");
        lbChambre.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");

        JComboBox<String> cboChambre = new JComboBox<String>();
        // Ajouter les types de chambre disponibles
        cboChambre.addItem((new Chambre(new TypeChambre("Simple"), "A-102", 400, 12.5)).getNumero());
        cboChambre.addItem((new Chambre(new TypeChambre("Double"), "A-105", 600, 12.5)).getNumero());
        cboChambre.addActionListener(e -> {
            String selectedType = (String) cboChambre.getSelectedItem();
            // Traiter la sélection
        });

        if(sejour != null) {
            add(lbChambre);
            add(cboChambre);
        }

        JButton cmdCreate = new JButton("Créer") {
            @Override
            public boolean isDefaultButton() {
                return true;
            }
        };
        cmdCreate.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#FFFFFF;");
        add(cmdCreate);

        // formulaire pour modification
        if(sejour != null) {
//            dateDebut = this.sejour.getDateDebut();
//            dateFin = this.sejour.getDateFin();
            cmdCreate.setText("Modifier");
        }


        // evenement

        cmdCreate.addActionListener((e) -> {
            if (sejour != null) {
                System.out.println("Modifier");
            }
            ModalDialog.closeModal(CreateHotel.ID);
        });
    }
}
