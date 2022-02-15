package app.ui.gui;

import app.controller.ConsultClientTestsController;
import org.apache.commons.math3.analysis.function.Log;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class ConsultClientTestsGUI {
    /**
     * Main App.
     */
    private MainApp mainApp;

    /**
     * Consult Client Tests Controller.
     */
    private ConsultClientTestsController controller;

    private LoginUI loginUI;

    /**
     * Constructs an instance of the Consult Client Tests GUI.
     *
     * @param mainApp Main App.
     */
    public ConsultClientTestsGUI (MainApp mainApp) {
        this.mainApp = mainApp;
        this.controller = new ConsultClientTestsController();
    }

    /**
     * Returns the Main App.
     *
     * @return Main App.
     */
    public MainApp getMainApp() {
        return this.mainApp;
    }

    /**
     * Returns the Consult Client Tests Controller.
     *
     * @return Consult Client Tests Controller.
     */
    public ConsultClientTestsController getController() {
        return this.controller;
    }

    public void setLoginUI (LoginUI loginUI)
    {
        this.loginUI = loginUI;
    }

    /**
     * Redirects to Scene 1 (Client list view).
     */
    public void toRegisterEmployeeScene1() {
        try {
            ConsultClientTestsGUIScene1 consultClientTestsGUIScene1 = (ConsultClientTestsGUIScene1) this.mainApp.
                    replaceSceneContent("/fxml/ConsultClientTests.fxml");
            consultClientTestsGUIScene1.setMainApp(this.mainApp);
            consultClientTestsGUIScene1.setController(this.controller);
            consultClientTestsGUIScene1.setUI(this);

        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Redirects to Scene 2 (Client confirmation).
     */
    public void toRegisterEmployeeScene2(String clientTin) {
        try {
            ConsultClientTestsGUIScene2 consultClientTestsGUIScene2 = (ConsultClientTestsGUIScene2) this.mainApp.
                    replaceSceneContent("/fxml/ConsultClientTests2.fxml");
            consultClientTestsGUIScene2.setMainApp(this.mainApp);
            consultClientTestsGUIScene2.setController(this.controller);
            consultClientTestsGUIScene2.setUI(this);
            consultClientTestsGUIScene2.showClientInfo(clientTin);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Redirects to Scene 3 (Test list view).
     */
    public void toRegisterEmployeeScene3() {
        try {
            ConsultClientTestsGUIScene3 consultClientTestsGUIScene3 = (ConsultClientTestsGUIScene3) this.mainApp.
                    replaceSceneContent("/fxml/ConsultClientTests3.fxml");
            consultClientTestsGUIScene3.setMainApp(this.mainApp);
            consultClientTestsGUIScene3.setController(this.controller);
            consultClientTestsGUIScene3.setUI(this);
            consultClientTestsGUIScene3.showTestList();
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Redirects to Scene 4 (Test results view).
     */
    public void toRegisterEmployeeScene4(String testNhsCode) {
        try {
            ConsultClientTestsGUIScene4 consultClientTestsGUIScene4 = (ConsultClientTestsGUIScene4) this.mainApp.
                    replaceSceneContent("/fxml/ConsultClientTests4.fxml");
            consultClientTestsGUIScene4.setMainApp(this.mainApp);
            consultClientTestsGUIScene4.setController(this.controller);
            consultClientTestsGUIScene4.setUI(this);
            consultClientTestsGUIScene4.showTestResults(testNhsCode);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toClinicalChemistryTechnologistMenu()
    {
        loginUI.toCliChemTechMenu();
    }
}
