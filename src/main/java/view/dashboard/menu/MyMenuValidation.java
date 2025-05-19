package view.dashboard.menu;

import model.Personnel;
import model.utilsModel.ModelUser;
import raven.modal.Drawer;
//import raven.modal.demo.model.utilsModel.ModelUser;
//import raven.modal.demo.system.Form;
import raven.modal.drawer.menu.MenuValidation;
import view.system.Form;

import java.util.Objects;

public class MyMenuValidation extends MenuValidation {

    public static void setUser(ModelUser user) {
        MyMenuValidation.user = user;
    }

    public static void setPersonnel(Personnel personnel) {
        MyMenuValidation.personnel = personnel;
    }
//
    public static ModelUser user;
    public static Personnel personnel;

    @Override
    public boolean menuValidation(int[] index) {
        return validation(index);
    }

    private static boolean checkMenu(int[] index, int[] indexHide) {
        if (index.length == indexHide.length) {
            for (int i = 0; i < index.length; i++) {
                if (index[i] != indexHide[i]) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public static boolean validation(Class<? extends Form> itemClass) {
        int[] index = Drawer.getMenuIndexClass(itemClass);
        if (index == null) {
            return false;
        }
        return validation(index);
    }

    public static boolean validation(int[] index) {
//        if (user == null) {
//            return false;
//        }
//        if (user.getRole() == ModelUser.Role.ADMIN) {
//            return true;
//        }

        if (personnel == null) {
            return false;
        }
        if (Objects.equals(personnel.getRole(), "receptioniste") || Objects.equals(personnel.getRole(), "agent de nettoyage") || Objects.equals(personnel.getRole(), "admin")) {
            return true;
        }

        boolean status
                // `Modal`
                = checkMenu(index, new int[]{2, 0})
                // `Components`->`Toast`
                && checkMenu(index, new int[]{2, 1})
                // `Forms`->`Responsive Layout`
                && checkMenu(index, new int[]{1, 2});

        return status;
    }
}
