package app.ui.gui;

import app.controller.AuthController;
import app.controller.ImportClinicalTestsController;
import app.controller.PerformanceController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Luís Araújo
 */
public class LabCoordinatorGUI implements Initializable {
    private AuthController ctrl = new AuthController();
    private PerformanceController performanceController = new PerformanceController();
    private ImportClinicalTestsController importClinicalTestsController = new ImportClinicalTestsController();
    private MainApp mainApp = new MainApp();
    private LoginUI loginUI;
    @FXML
    private Button btnImportTests;

    @FXML
    private Button back;

    @FXML
    private Button btnOverview;

    public LabCoordinatorGUI() {
    }

    /*public LabCoordinatorGUI(MainApp mainApp) {
        this.mainApp = mainApp;
        this.ctrl = new AuthController();
    }*/

    @FXML
    void importTests(ActionEvent event) {
        try {
            ImportClinicalTestsGUI importClinicalTestsGUI = (ImportClinicalTestsGUI) this.mainApp.
                    replaceSceneContent("/fxml/ChooseTestTypeToImportScene.fxml");
            importClinicalTestsGUI.setMainApp(this.mainApp);
            importClinicalTestsGUI.setController(this.importClinicalTestsController);
            importClinicalTestsGUI.setUI(this);

        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /*public void toLabCoordGUI() {
        try {
            LabCoordinatorGUI labCoordinatorGUI = (LabCoordinatorGUI) this.mainApp.
                    replaceSceneContent("/fxml/LabCoordinatorGUI.fxml");
            labCoordinatorGUI.setMainApp(this.mainApp);
            labCoordinatorGUI.setController(this.importClinicalTestsController);
            labCoordinatorGUI.setUI(this);

        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    @FXML
    void goBack(ActionEvent event) {
        loginUI.doLogout();
        this.mainApp.toMainScene();
    }

    @FXML
    void overview(ActionEvent event) {
        try {

            OverviewAnalysePerformanceUI overviewAnalysePerformanceUI = (OverviewAnalysePerformanceUI) this.mainApp.
                    replaceSceneContent("/fxml/OverviewAnalysePerformance.fxml");
            overviewAnalysePerformanceUI.setMainApp(this.mainApp);
            overviewAnalysePerformanceUI.setController(this.performanceController);
            overviewAnalysePerformanceUI.setUI(this);

        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public AuthController getController() {
        return this.ctrl;
    }

    public MainApp getMainApp() {
        return this.mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setLoginUI(LoginUI loginUI) {
        this.loginUI = loginUI;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //this.ctrl = new AuthController();
    }
}

