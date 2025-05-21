package view.components;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import model.Chambre;
import model.Controllers.ChambreController;
import model.Controllers.HotelController;
import model.Controllers.ReceptionisteController;
import model.Hotel;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import raven.modal.Toast;
import raven.modal.component.SimpleModalBorder;
import raven.modal.option.Location;
import raven.modal.option.Option;
import view.dashboard.forms.ChambreForm;
import view.dashboard.forms.ReservationForm;
import view.dashboard.menu.MyDrawerBuilder;
import view.forms.CreateChambre;
import view.forms.CreateHotel;
import view.forms.CreateReservation;
import view.home.RoundedImagePanel;
import view.login_register.CustomModalBorder;
import view.login_register.Login;
import view.utils.ToastManager;

import javax.swing.*;
import java.awt.*;

public class RoomCard extends JPanel{

    private Chambre chambre;
    private ReservationForm reservationForm;
    private ChambreForm form;

//    public RoomCard() {
//        init();
//    }
//
//    public RoomCard(Chambre chambre) {
//        this.chambre = chambre;
//        init();
//    }

    public RoomCard(Chambre chambre, ChambreForm form) {
        this.chambre = chambre;
        this.form = form;
        init();
    }

    public void setReservationForm(ReservationForm form) {
        this.reservationForm = form;
    }

    public void init() {
        setLayout(new MigLayout("insets 0, wrap, fillx", "leading", "top"));

        RoundedImagePanel hotelImg = new RoundedImagePanel("/" + chambre.getImage(), 15);

        add(hotelImg, "w 100%, h 205!");

        JLabel hotelName = new JLabel("Chambre " + chambre.getNumero());
        hotelName.setFont(new Font("", Font.BOLD, 15));
        add(hotelName, "gapbottom 0, top, leading");

        JButton hotelNote = new JButton("8.7");
        hotelNote.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;" +
                "foreground: #ffffff;" +
                "background: #024c10;");

        add(hotelNote, "split 2, w 40!, h 20!");

        JLabel hotelAppreciation = new JLabel("Superbe");
        hotelAppreciation.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#656766;" +
                "font:bold;");
        add(hotelAppreciation, "gapy n 10, al center center");

//        JLabel hotelAdress = new JLabel("17e Arrondissement");
//        hotelAdress.putClientProperty(FlatClientProperties.STYLE, "" +
//                "foreground:#656766");
//        add(hotelAdress, "span 3,gapy n 5, top, leading");


        JLabel roomSpace = new JLabel(chambre.getSuperficie() + " m²");
        roomSpace.setIcon(new FlatSVGIcon("images/space.svg", 15, 15).setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.decode("#000000"))));
        roomSpace.setFont(new Font("", Font.PLAIN, 14));
        roomSpace.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#656766");
        add(roomSpace, "span 3,gapy n 5, top, leading");

        JLabel nbPeople = new JLabel("2 personnes");
        nbPeople.setIcon(new FlatSVGIcon("images/people.svg", 15, 15).setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.decode("#000000"))));
        nbPeople.setFont(new Font("", Font.PLAIN, 14));
        nbPeople.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#656766");
        add(nbPeople, "span 3,gapy n 5, top, leading");

        JLabel nbBed = new JLabel("1 lit double");
        nbBed.setIcon(new FlatSVGIcon("images/bed.svg", 15, 15).setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.decode("#000000"))));
        nbBed.setFont(new Font("", Font.PLAIN, 14));
        nbBed.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#656766");
        add(nbBed, "span 3,gapy n 5, top, leading");

        JLabel wifi = new JLabel("Wifi gratuit disponible");
        wifi.setIcon(new FlatSVGIcon("images/wifi.svg", 15, 15).setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.decode("#000000"))));
        wifi.setFont(new Font("", Font.PLAIN, 14));
        wifi.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#656766");
        add(wifi, "span 3,gapy n 5, top, leading");


        add(new JSeparator(), "gapy 5 5, w 100%, h 10!");

        JLabel roomPrice = new JLabel(chambre.getPrix() + " €");
        roomPrice.setFont(new Font("", Font.BOLD, 20));
//        roomPrice.putClientProperty(FlatClientProperties.STYLE, "" +
//                "foreground:#007bff");
        add(roomPrice, "span 3,gapy n 10, top, trailing");


        JButton bookBtn = new JButton("Réserver");
        bookBtn.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;" +
                "foreground: #ffffff;" +
                "background: #007bff;");
        add(bookBtn, "span, split 3,gapy n 10, top, w 100%");

        JButton cmdModifier = new JButton();
        cmdModifier.setIcon(new FlatSVGIcon("images/pencil.svg", 15, 15)
                .setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.decode("#ffffff"))));
        cmdModifier.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;" +
                "foreground: #ffffff;" +
                "background: #F77A00;");
        add(cmdModifier, "w 40!");


        JButton cmdSupprimer = new JButton();
        cmdSupprimer.setIcon(new FlatSVGIcon("images/trash-2.svg", 15, 15)
                .setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.decode("#ffffff"))));
        cmdSupprimer.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;" +
                "foreground: #ffffff;" +
                "background: #dc3545;");
        add(cmdSupprimer, "w 40!");


        // evenement

        cmdModifier.addActionListener((e) -> {
            showCreateChambreDialog();
        });

        bookBtn.addActionListener((e) -> {
            showCreateReservationDialog();
        });

        cmdSupprimer.addActionListener((e) -> {

            Option option = ModalDialog.createOption()
                    .setAnimationEnabled(true);
            option.getLayoutOption()
                    .setLocation(Location.CENTER, Location.CENTER);

            Component parent = SwingUtilities.getWindowAncestor(this);
            JComponent jParent = (JComponent) SwingUtilities.getAncestorOfClass(JComponent.class, this);


            String titre = "Suppression Chambre";
            String message = "Voulez vous vraiment supprimer cette Chambre ??";

            ModalDialog.showModal(
                    parent,
                    new SimpleMessageModal(
                            SimpleMessageModal.Type.WARNING,
                            message, titre,
                            SimpleModalBorder.YES_NO_OPTION,
                            (controller, action) -> {
                                if(action==SimpleModalBorder.YES_OPTION) {
                                    ChambreController.supprimerChambre(chambre.getId());
                                    form.refreshChambreList();
                                    ToastManager.getInstance().showToast(jParent, Toast.Type.SUCCESS, "Chambre supprimée avec succès");
                                }
                            }),
                    option
            );
        });
    }

    private void showCreateReservationDialog() {
        System.out.println("MyDrawerBuilder personnel Id " + MyDrawerBuilder.getInstance().getPersonnel().getId());
        System.out.println("Receptionniste ID " + ReceptionisteController.getReceptionisteById(MyDrawerBuilder.getInstance().getPersonnel().getId()).getId());
        Option option = ModalDialog.createOption()
                .setCloseOnPressedEscape(true)
                .setBackgroundClickType(Option.BackgroundClickType.CLOSE_MODAL)
                .setAnimationEnabled(true)
                .setOpacity(0.5f);
        ModalDialog.showModal(this,
                new SimpleModalBorder(
                        new CreateReservation(
                            ReceptionisteController.getReceptionisteById(MyDrawerBuilder.getInstance().getPersonnel().getId()),
                            ChambreController.getChambreById(chambre.getId())
                        ),
                        "Créer",
                        SimpleModalBorder.DEFAULT_OPTION,
                        (controller, action) -> {
                            if (action == SimpleModalBorder.OK_OPTION) {
                                this.getParent().revalidate();
                                if (reservationForm != null) {
                                    reservationForm.revalidate();
                                }
                            }
                        }
                ), option, CreateReservation.ID);
    }

    public void showCreateChambreDialog() {
        Option option = ModalDialog.createOption();
        option.getLayoutOption()
                .setSize(WIDTH, 1f)
                .setLocation(Location.TRAILING, Location.TOP)
                .setAnimateDistance(0.7f, 0);

        ModalDialog.showModal(this.getParent(), new SimpleModalBorder(
                new CreateChambre(this.chambre), "Modifier", SimpleModalBorder.DEFAULT_OPTION,
                (controller, action) -> {
                    if (action == SimpleModalBorder.OK_OPTION) {
                        this.getParent().revalidate();
                    }
                }), option, CreateChambre.ID);
    }
}
