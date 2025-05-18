package view.forms;

import com.formdev.flatlaf.FlatClientProperties;
import jnafilechooser.api.JnaFileChooser;
import model.Hotel;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import raven.modal.Toast;
import view.utils.ToastManager;

import javax.swing.*;

public class CreateHotel extends JPanel {
    public static final String ID = "create_hotel_id";
    private Hotel hotel;

    public CreateHotel() {
        init();
    }
    public CreateHotel(Hotel hotel) {
        this.hotel = hotel;
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

        JLabel lbNom = new JLabel("Nom");
        lbNom.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbNom);

        JTextField txtNom = new JTextField();
        txtNom.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Royal London Hotel");
        add(txtNom);

        JLabel lbAdresse = new JLabel("Adresse");
        lbAdresse.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbAdresse);

        JTextField txtAdresse = new JTextField();
        txtAdresse.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "888 Westminster Way, London");
        add(txtAdresse);

        JLabel lbImage = new JLabel("Image");
        lbImage.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbImage);

        JButton cmdChooseImage = new JButton("Choisir une image") {
            @Override
            public boolean isDefaultButton() {
                return false;
            }
        };
        cmdChooseImage.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#FFFFFF;");
        add(cmdChooseImage, "gapy n 20");

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
        if(hotel != null) {
            txtNom.setText(this.hotel.getNom());
            txtAdresse.setText(this.hotel.getAdresse());
            cmdCreate.setText("Modifier");
        }


        // evenement
        cmdChooseImage.addActionListener((e) -> {
            JnaFileChooser fileChooser = new JnaFileChooser();
            // fileChooser.addFilter("All Files", "*");
            fileChooser.addFilter("Pictures", "jpg", "jpeg", "png");
            boolean action = fileChooser.showOpenDialog(SwingUtilities.getWindowAncestor(this) );
            if(action) {
                System.out.println(fileChooser.getSelectedFile().getAbsolutePath());
                ToastManager.getInstance().showToast(this, Toast.Type.SUCCESS, "Simple swing toast notification");
            }
        });

        cmdCreate.addActionListener((e) -> {
            if (hotel != null) {
                System.out.println("Modifier");
            }
            ModalDialog.closeModal(CreateHotel.ID);
        });
    }
}
