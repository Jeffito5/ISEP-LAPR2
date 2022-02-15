package app.ui;

import app.controller.App;
import app.domain.tasks.SendNhsReportTask;
import app.domain.tasks.TaskManager;
import app.ui.console.MainMenuUI;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Main {

    public static void main(String[] args)
    {

        try
        {
            MainMenuUI menu = new MainMenuUI();

            menu.run();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
        System.exit(0);
        //App.getInstance().stopTasks();
    }
}
