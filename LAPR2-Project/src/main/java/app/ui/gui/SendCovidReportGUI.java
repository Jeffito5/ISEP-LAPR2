package app.ui.gui;


import app.controller.ConsultClientTestsController;
import app.controller.SendNHSCovidReportController;
import app.ui.Main;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SendCovidReportGUI {
    /**
     * Main App.
     */
    private MainApp mainApp;

    /**
     * Consult Client Tests Controller.
     */
    private SendNHSCovidReportController controller;

    private LoginUI loginUI;

    /**
     * Constructs an instance of the Consult Client Tests GUI.
     *
     */
    public SendCovidReportGUI () {
        this.controller = new SendNHSCovidReportController();
    }


    public void setMainApp(MainApp mainApp)
    {
       this.mainApp = mainApp;
    }

    public void setLoginUI (LoginUI loginUI)
    {
        this.loginUI = loginUI;
    }

    public void toSendCovidReportScene1(){
        try {
            SendCovidReportGUIScene sendCovidReportGUIScene = (SendCovidReportGUIScene) this.mainApp.
                    replaceSceneContent("/fxml/SendCovidReport1.fxml");

            sendCovidReportGUIScene.setMainApp(this.mainApp);
            sendCovidReportGUIScene.setController(this.controller);
            sendCovidReportGUIScene.setUI(this);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toSendCovidReportPreviewScreen(String report){
        try {
            SendCovidReportGUIScene2 sendCovidReportGUIScene2 = (SendCovidReportGUIScene2) this.mainApp.
                    replaceSceneContent("/fxml/SendCovidReport2.fxml");

            sendCovidReportGUIScene2.setMainApp(this.mainApp);
            sendCovidReportGUIScene2.setController(this.controller);
            sendCovidReportGUIScene2.setUI(this);
            sendCovidReportGUIScene2.setReportPreview(report);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toAdminMenu()
    {
        loginUI.toAdminMenu();
    }

}
