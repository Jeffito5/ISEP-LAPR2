package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ManageClientUI implements Runnable{

    @Override
    public void run(){
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register New Client ", new RegisterClientUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nManage Clients:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }
}
