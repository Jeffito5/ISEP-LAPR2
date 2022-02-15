package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ClinicalChemistryTechnologistUI implements Runnable{
    public ClinicalChemistryTechnologistUI(){
    }

    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Record Test Results ", new RecordTestResultUI()));
        options.add(new MenuItem("Consult Client Tests ", new ConsultClientTestsUI()));

        int option;
        do {

            option = Utils.showAndSelectIndex(options, "\n\nReceptionist Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }
}
