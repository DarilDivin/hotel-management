package view.forms;

import com.formdev.flatlaf.FlatClientProperties;
import jnafilechooser.api.JnaFileChooser;
import model.*;
import model.Chambre;
import model.Controllers.ClientController;
import model.Controllers.ReceptionisteController;
import model.Controllers.ReservationController;
import net.miginfocom.swing.MigLayout;
import raven.datetime.DatePicker;
import raven.datetime.event.DateSelectionEvent;
import raven.datetime.event.DateSelectionListener;
import raven.modal.ModalDialog;
import raven.modal.Toast;
import raven.modal.component.ModalBorderAction;
import raven.modal.component.SimpleModalBorder;
import view.utils.ToastManager;

import javax.swing.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CreateReservation extends JPanel {
    public static final String ID = "create_reservation_id";
    private Reservation reservation;

    private Receptioniste receptioniste;
    private Chambre chambre;

    private Date dateDebut;
    private Date dateFin;

    public CreateReservation(Reservation reservation) {
        this.reservation = reservation;
        init();
    }

    public CreateReservation(Receptioniste r, Chambre c) {
        init();
    }

    public void init() {
        setLayout(new MigLayout("insets n 20 n 20,fillx,wrap,width 380", "[fill]"));

        JTextArea text = new JTextArea("Lorem ipsum");
        text.setEditable(false);
        text.setFocusable(false);
        text.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:0,0,0,0;" +
                "background:null;");
        add(text);

        add(new JSeparator(), "gapy 15 15");

        JLabel lbdateDebut = new JLabel("Période de résevation");
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
        cboChambre.addItem(new Chambre(new TypeChambre("Simple"), "A-102", 400, 12.5).getNumero());
        cboChambre.addItem(new Chambre(new TypeChambre("Double"), "A-105", 600, 12.5).getNumero());
        cboChambre.addActionListener(e -> {
            String selectedType = (String) cboChambre.getSelectedItem();
            // Traiter la sélection
        });

//        if(reservation != null) {
//            add(lbChambre);
//            add(cboChambre);
//        }

        JComboBox<String> cboClient = new JComboBox<String>();
        // Ajouter les types de chambre disponibles
        for (Client c : ClientController.getTousLesClients()) {
            cboClient.addItem(c.getId() + " - " + c.getNom() + " " + c.getPrenom() + " (" + c.getEmail() + ")" );
        }
        cboChambre.addActionListener(e -> {
            String selectedType = (String) cboChambre.getSelectedItem();
            // Traiter la sélection
        });
        

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
        if(reservation != null) {
            dateDebut = this.reservation.getDateDebut();
            dateFin = this.reservation.getDateFin();
            cmdCreate.setText("Modifier");
        }


        // evenement

        cmdCreate.addActionListener((e) -> {
            if (reservation != null) {
                reservation.setDateDebut(dateDebut);
                reservation.setDateFin(dateFin);

                ReservationController.modifierReservation(reservation);
                ModalBorderAction.getModalBorderAction(this).doAction(SimpleModalBorder.OK_OPTION);
                ToastManager.getInstance().showToast(this, Toast.Type.SUCCESS, "Réservation modifier avec succès");
                System.out.println("Modifier");
            } else {
                String selectedClient = (String) cboClient.getSelectedItem();
                int clientId = Integer.parseInt(selectedClient.substring(0, selectedClient.indexOf(" -")));
                Reservation newReservation = new Reservation(
                        ClientController.getClientById(clientId),
                        dateDebut,
                        dateFin,
                        ReceptionisteController.getReceptionisteById(1),
                        chambre
                );

                ReservationController.ajouterReservation(newReservation);
                ModalBorderAction.getModalBorderAction(this).doAction(SimpleModalBorder.OK_OPTION);
                ToastManager.getInstance().showToast(this, Toast.Type.SUCCESS, "Réservation ajoutée avec succès");
            }

            ModalDialog.closeModal(CreateReservation.ID);
        });
    }
}
