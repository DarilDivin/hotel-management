package view.dashboard.menu;

import com.formdev.flatlaf.FlatClientProperties;
import model.ModelUser;
import raven.modal.drawer.DrawerPanel;
import raven.modal.drawer.item.Item;
import raven.modal.drawer.item.MenuItem;
//import raven.modal.drawer.menu.MenuAction;
//import raven.modal.drawer.menu.MenuEvent;
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
import view.dashboard.forms.ChambreForm;
import view.dashboard.forms.DashboardForm;
import view.dashboard.forms.FormInput;
import view.system.AllForms;
import view.system.Form;
import view.system.FormManager;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
//import java.util.Arrays;

public class MyDrawerBuilder extends SimpleDrawerBuilder {
    private static MyDrawerBuilder instance;
    private ModelUser user;

    public static MyDrawerBuilder getInstance() {
        if (instance == null) {
            instance = new MyDrawerBuilder();
        }
        return instance;
    }

    public ModelUser getUser() {
        return user;
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

    private final int SHADOW_SIZE = 12;

    private MyDrawerBuilder() {
        super(createSimpleMenuOption());
        LightDarkButtonFooter lightDarkButtonFooter = (LightDarkButtonFooter) getFooter();
        lightDarkButtonFooter.addModeChangeListener(isDarkMode -> {
            // event for light dark mode changed
        });
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


    private static MenuOption createSimpleMenuOption() {
        // create simple menu option
        MenuOption simpleMenuOption = new MenuOption();

        MenuItem items[] = new MenuItem[]{
                new Item.Label("Principal"),
                new Item("Dashboard", "dashboard.svg", DashboardForm.class),
                new Item.Label("Hotel"),
                new Item("Hotel", "dashboard.svg")
                        .subMenu("Chambre", ChambreForm.class),
//                        .subMenu("Table", FormTable.class)
//                        .subMenu("Responsive Layout", FormResponsiveLayout.class),
                new Item("Réservation", "space.svg")
                        .subMenu("Modal", FormInput.class),
//                        .subMenu("Toast", FormToast.class)
//                        .subMenu("Date Time", FormDateTime.class)
//                        .subMenu("Avatar Icon", FormAvatarIcon.class)
//                        .subMenu("Slide Pane", FormSlidePane.class),
//                new Item("Email", "email.svg", FormInput.class),
//                new Item("Chat", "chat.svg"),
//                new Item("Calendar", "calendar.svg"),
                new Item.Label("Autres"),
//                new Item("Setting", "setting.svg" /*,FormSetting.class*/),
//                new Item("About", "about.svg"),
                new Item("Se déconnecter", "log-out.svg")
        };

        simpleMenuOption.setMenuStyle(new MenuStyle() {

            @Override
            public void styleMenuItem(JButton menu, int[] index, boolean isMainItem) {
                boolean isTopLevel = index.length == 1;
                if (isTopLevel) {
                    // adjust item menu at the top level because it's contain icon
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
//                if (i == 8) {
//                    action.consume();
//                    FormManager.showAbout();
//                    return;
//                } else if (i == 9) {
//                    action.consume();
//                    FormManager.logout();
//                    return;
//                }
                if (i == 3) {
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
