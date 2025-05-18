package view.utils;


import raven.modal.Toast;
import raven.modal.option.Location;
import raven.modal.toast.option.ToastBorderStyle;
import raven.modal.toast.option.ToastLocation;
import raven.modal.toast.option.ToastOption;
import raven.modal.toast.option.ToastStyle;

import javax.swing.*;

public class ToastManager {
    private static ToastManager instance;

    private ToastManager() {}

    public static ToastManager getInstance() {
        if (instance == null) {
            instance = new ToastManager();
        }
        return instance;
    }

    public void showToast(JComponent component, Toast.Type type, String message) {
        Toast.show(component, type, message, getDefaultToastOption());
    }

    private ToastOption getDefaultToastOption() {
        ToastOption option = Toast.createOption();
        Location h = Location.TRAILING;
        Location v = Location.BOTTOM;
        ToastStyle.BackgroundType backgroundType = ToastStyle.BackgroundType.GRADIENT;
        ToastBorderStyle.BorderType borderType = ToastBorderStyle.BorderType.BOTTOM_LINE;

        option.setAnimationEnabled(true)
                .setPauseDelayOnHover(true)
                .setAutoClose(true)
                .setCloseOnClick(false)
                .setHeavyWeight(false);

        option.getLayoutOption()
                .setLocation(ToastLocation.from(h, v))
                .setRelativeToOwner(false);

        option.getStyle().setBackgroundType(backgroundType)
                .setShowLabel(true)
                .setIconSeparateLine(true)
                .setShowCloseButton(true)
                .setPaintTextColor(true)
                .setPromiseLabel("Saving...")
                .getBorderStyle()
                .setBorderType(borderType);

        return option;
    }

}
