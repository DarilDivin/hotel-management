package view.dashboard.menu;

import com.formdev.flatlaf.FlatClientProperties;
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
import raven.modal.drawer.simple.header.SimpleHeaderData;
import raven.modal.option.Option;
import view.dashboard.forms.FormInput;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class MyDrawerBuilder extends SimpleDrawerBuilder {
    private static MyDrawerBuilder instance;

    public static MyDrawerBuilder getInstance() {
        if (instance == null) {
            instance = new MyDrawerBuilder();
        }
        return instance;
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
                new Item.Label("MAIN"),
                new Item("Dashboard", "dashboard.svg" ,Stat.class),
//                new Item.Label("SWING UI"),
//                new Item("Forms", "forms.svg")
//                        .subMenu("Input", FormInput.class)
//                        .subMenu("Table", FormTable.class)
//                        .subMenu("Responsive Layout", FormResponsiveLayout.class),
//                new Item("Components", "components.svg")
//                        .subMenu("Modal", FormModal.class)
//                        .subMenu("Toast", FormToast.class)
//                        .subMenu("Date Time", FormDateTime.class)
//                        .subMenu("Avatar Icon", FormAvatarIcon.class)
//                        .subMenu("Slide Pane", FormSlidePane.class),
                new Item("Email", "email.svg", FormInput.class),
                new Item("Chat", "chat.svg"),
                new Item("Calendar", "calendar.svg"),
                new Item.Label("OTHER"),
                new Item("Setting", "setting.svg" /*,FormSetting.class*/),
                new Item("About", "about.svg"),
                new Item("Logout", "logout.svg")
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
//        simpleMenuOption.setMenuValidation(new MyMenuValidation());

//        simpleMenuOption.addMenuEvent(new MenuEvent() {
//            @Override
//            public void selected(MenuAction action, int[] index) {
//                System.out.println("Drawer menu selected " + Arrays.toString(index));
//                Class<?> itemClass = action.getItem().getItemClass();
//                int i = index[0];
//                if (i == 8) {
//                    action.consume();
//                    FormManager.showAbout();
//                    return;
//                } else if (i == 9) {
//                    action.consume();
//                    FormManager.logout();
//                    return;
//                }
//                if (itemClass == null || !Form.class.isAssignableFrom(itemClass)) {
//                    action.consume();
//                    return;
//                }
//                Class<? extends Form> formClass = (Class<? extends Form>) itemClass;
//                FormManager.showForm(AllForms.getForm(formClass));
//            }
//        });

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
