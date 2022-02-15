package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luís Araújo
 */
public class ClientUI implements Runnable{
    public ClientUI() {
    }

    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<>();
        //options.add(new MenuItem("Update personal data ", new UpdatePersonalDataUI()));
        //options.add(new MenuItem("View the results of Tests ", new ViewResultsUI()));

        int option;
        do {

            option = Utils.showAndSelectIndex(options, "\n\nClient Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }
}
