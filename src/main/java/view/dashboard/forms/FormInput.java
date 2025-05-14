package view.dashboard.forms;

import net.miginfocom.swing.MigLayout;
import view.system.Form;
import view.utils.SystemForm;

import javax.swing.*;

@SystemForm(name = "Form Input", description = "input form not yet update")
public class FormInput extends Form {

    public FormInput() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("al center center"));
        JLabel text = new JLabel("Input");
        add(text);
    }
}
