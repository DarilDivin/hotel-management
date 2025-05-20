package view.dashboard.menu;

import com.formdev.flatlaf.FlatClientProperties;
import model.Personnel;
import model.utilsModel.ModelUser;
import raven.modal.drawer.DrawerPanel;
import raven.modal.drawer.item.Item;
import raven.modal.drawer.item.MenuItem;
import raven.modal.drawer.menu.MenuAction;
import raven.modal.drawer.menu.MenuEvent;
import raven.modal.drawer.menu.MenuOption;
import raven.modal.drawer.menu.MenuStyle;
import raven.modal.drawer.renderer.DrawerStraightDotLineStyle;
import raven.modal.drawer.simple.SimpleDrawerBuilder;
import raven.modal.drawer.simple.footer.LightDarkButtonFooter;
import raven.modal.drawer.simple.footer.SimpleFooterData;
import raven.modal.drawer.simple.header.SimpleHeader;
import raven.modal.drawer.simple.header.SimpleHeaderData;
import raven.modal.option.Option;
import view.dashboard.forms.*;
import view.system.AllForms;
import view.system.Form;
import view.system.FormManager;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class MyDrawerBuilder extends SimpleDrawerBuilder {
    private static MyDrawerBuilder instance;
    private ModelUser user;
    private Personnel personnel;

    public static MyDrawerBuilder getInstance() {
        if (instance == null) {
            instance = new MyDrawerBuilder();
        }
        return instance;
    }

    public ModelUser getUser() {
        return user;
    }
    public Personnel getPersonnel() {
        return personnel;
    }


    public void setUser(ModelUser user) {
        boolean updateMenuItem = this.user == null || this.user.getRole() != user.getRole();

        this.user = user;

        // set user to menu validation
        MyMenuValidation.setUser(user);

        // setup drawer header
        SimpleHeader header = (SimpleHeader) getHeader();
        SimpleHeaderData data = header.getSimpleHeaderData();
//        AvatarIcon icon = (AvatarIcon) data.getIcon();
//        String iconName = user.getRole() == ModelUser.Role.ADMIN ? "avatar_male.svg" : "avatar_female.svg";

//        icon.setIcon(new FlatSVGIcon("raven/modal/demo/drawer/image/" + iconName, 100, 100));
        data.setTitle(user.getUserName());
        data.setDescription(user.getMail());
        header.setSimpleHeaderData(data);

        if (updateMenuItem) {
            rebuildMenu();
        }
    }
    public void setPersonnel(Personnel personnel) {
        boolean updateMenuItem = this.personnel == null || this.personnel.getRole() != personnel.getRole();

        this.personnel = personnel;

        // set personnel to menu validation
        MyMenuValidation.setPersonnel(personnel);

        // setup drawer header
        SimpleHeader header = (SimpleHeader) getHeader();
        SimpleHeaderData data = header.getSimpleHeaderData();
        data.setTitle(personnel.getPrenom() + " " + personnel.getNom() + " (" + personnel.getRole() + ")" );
        data.setDescription(personnel.getEmail());
        header.setSimpleHeaderData(data);

        if (updateMenuItem) {
            System.out.println("Drawer menu updated");
            rebuildMenu();
            System.out.println(this.personnel.getRole());
        }

    }

    private final int SHADOW_SIZE = 12;

    private MyDrawerBuilder() {
        super(createSimpleMenuOption());
        LightDarkButtonFooter lightDarkButtonFooter = (LightDarkButtonFooter) getFooter();
        lightDarkButtonFooter.addModeChangeListener(isDarkMode -> {
            // event for light dark mode changed
        });
    }

    private static MenuOption createSimpleMenuOption() {
        MenuOption simpleMenuOption = new MenuOption();

        MenuItem items[] = new MenuItem[]{
                new Item.Label("Administrateur"),
                    new Item("Dashboard", "dashboard.svg", DashboardForm.class),
                    new Item("Gestion d'Hotel", "dashboard.svg")
                            .subMenu("Hotels", HotelForm.class)
                            .subMenu("Utilisateurs", AdminUserForm.class),

                new Item.Label("Personnel"),
                    new Item("Réceptionniste", "dashboard.svg")
                            .subMenu("Client", ClientForm.class)
                            .subMenu("Chambre", ChambreForm.class)
                            .subMenu("Réservation", ReservationForm.class)
                            .subMenu("Séjour", SejourForm.class),
                    new Item("Personnel de chambre", "space.svg")
                            .subMenu("Intervention", InterventionForm.class),

                new Item.Label("Autres"),
                    new Item("Se déconnecter", "log-out.svg")
        };

        simpleMenuOption.setMenuStyle(new MenuStyle() {

            @Override
            public void styleMenuItem(JButton menu, int[] index, boolean isMainItem) {
                boolean isTopLevel = index.length == 1;
                if (isTopLevel) {
                    menu.putClientProperty(FlatClientProperties.STYLE, "" +
                            "margin:-1,0,-1,0;");
                }
            }

            @Override
            public void styleMenu(JComponent component) {
                component.putClientProperty(FlatClientProperties.STYLE, getDrawerBackgroundStyle());
            }
        });

        simpleMenuOption.getMenuStyle().setDrawerLineStyleRenderer(new DrawerStraightDotLineStyle());
        simpleMenuOption.setMenuValidation(new MyMenuValidation());

        simpleMenuOption.addMenuEvent(new MenuEvent() {
            @Override
            public void selected(MenuAction action, int[] index) {
                System.out.println("Drawer menu selected " + Arrays.toString(index));
                Class<?> itemClass = action.getItem().getItemClass();
                int i = index[0];

                if (i == 4) {
                    action.consume();
                    FormManager.logout();
                    return;
                }
                if (itemClass == null || !Form.class.isAssignableFrom(itemClass)) {
                    action.consume();
                    return;
                }
                try {
                    Class<? extends Form> formClass = (Class<? extends Form>) itemClass;
                    Form form = AllForms.getForm(formClass);
                    FormManager.showForm(form);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        simpleMenuOption.setMenus(items)
                .setBaseIconPath("images/dashboard/icons")
                .setIconScale(0.50f);

        return simpleMenuOption;
    }



    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        return new SimpleHeaderData()
            .setTitle("Daril D.")
            .setDescription("daril@gmail.com");
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData()
                .setTitle("ZenHotel")
                .setDescription("Version 1.0.0");
    }

    @Override
    public Option createOption() {
        Option option = super.createOption();
        option.setOpacity(0.3f);
        option.getBorderOption()
                .setShadowSize(new Insets(0, 0, 0, SHADOW_SIZE));
        return option;
    }

    @Override
    public int getDrawerWidth() {
        return 270 + SHADOW_SIZE;
    }

    @Override
    public int getDrawerCompactWidth() {
        return 80 + SHADOW_SIZE;
    }

    @Override
    public int getOpenDrawerAt() {
        return 1000;
    }

    @Override
    public boolean openDrawerAtScale() {
        return false;
    }

    @Override
    public void build(DrawerPanel drawerPanel) {
        drawerPanel.putClientProperty(FlatClientProperties.STYLE, getDrawerBackgroundStyle());
    }

    private static String getDrawerBackgroundStyle() {
        return "" +
                "[light]background:tint($Panel.background,20%);" +
                "[dark]background:tint($Panel.background,5%);";
    }
}
