package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class AdminUI implements Runnable {
    public AdminUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<>();
        /**
         * Set of options that an admin can choose
         */

        options.add(new MenuItem("Register a new employee. ", new CreateEmployeeUI()));
        options.add(new MenuItem("Register a new clinical analysis laboratory. ", new ClinicalAnalysisLaboratoryUI()));
        options.add(new MenuItem("Specify a new type of test and its collecting methods. ", new TestTypeUI()));
        options.add(new MenuItem("Specify a new parameter category. ", new CreateParameterCategoryUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }
}
