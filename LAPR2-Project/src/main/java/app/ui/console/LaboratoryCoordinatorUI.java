package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luís Araújo
 */
public class LaboratoryCoordinatorUI implements Runnable {
    public LaboratoryCoordinatorUI() {
    }

    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<>();
        //options.add(new MenuItem("Overview of all tests and company's performance ", /*US16*/));
        //options.add(new MenuItem("Import clinical tests (CSV file) ",new ImportClinicalTestsUI()));
        //options.add(new MenuItem("Overview/Analyse Performance (CSV file) ",new PerformanceUI()));

        int option;
        do {

            option = Utils.showAndSelectIndex(options, "\n\nLaboratory Coordinator Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }
}
