package view.dashboard.forms;

import net.miginfocom.swing.MigLayout;
import view.system.Form;
import view.utils.SystemForm;

@SystemForm(name = "Liste des chambres d'un Hotel", description = "Il s'agit de la liste des chambres d'un Hotel")
public class ChambreForm extends Form {
    public ChambreForm() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("al center center", "[grow]", "[grow]"));

    }
}
