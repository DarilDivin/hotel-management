package view.dashboard.menu;

import raven.modal.Drawer;
import view.system.FormManager;

import javax.swing.*;

public class Dashboard extends JPanel {
    private JFrame parent;
    
    public Dashboard(JFrame frame) {
        // Initialisation basique du panneau
        super();

        this.parent = frame;
    }
    
    @Override
    public void addNotify() {
        super.addNotify();
        // Installation du Drawer après que le composant soit ajouté à son parent
        Drawer.installDrawer(this, MyDrawerBuilder.getInstance());
        FormManager.install(parent);

    }
}