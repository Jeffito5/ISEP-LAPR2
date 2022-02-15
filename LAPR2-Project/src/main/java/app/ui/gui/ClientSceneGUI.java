package app.ui.gui;

import app.controller.AuthController;
import app.controller.UpdatePersonalDataController;
import app.controller.ViewResultsController;
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
public class ClientSceneGUI implements Initializable {
    private AuthController ctrl = new AuthController();
    private MainApp mainApp = new MainApp();
    private LoginUI loginUI;
    private UpdatePersonalDataController updatePersonalDataController = new UpdatePersonalDataController();
    private ViewResultsController viewResultsController = new ViewResultsController();

    @FXML
    private Button btnViewResults;

    @FXML
    private Button btnUpdatePersonalData;

    @FXML
    private Button btnShowClientData;

    @FXML
    private Button btnBack;

    public ClientSceneGUI() {
    }

    public ClientSceneGUI(MainApp mainApp) {
        this.mainApp = mainApp;
        this.ctrl = new AuthController();
    }

    @FXML
    void updatePersonalData(ActionEvent event) {
        try {
            UpdatePersonalDataGUI updatePersonalDataGUI = (UpdatePersonalDataGUI) this.mainApp.
                    replaceSceneContent("/fxml/UpdatePersonalDataScene.fxml");
            updatePersonalDataGUI.setMainApp(this.mainApp);
            updatePersonalDataGUI.setUpdatePersonalDataController(this.updatePersonalDataController);
            updatePersonalDataGUI.setClientUI(this);

        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void viewResults(ActionEvent event) {
        try {

            ViewResultsGUI viewResultsGUI = (ViewResultsGUI) this.mainApp.
                    replaceSceneContent("/fxml/ViewResultsGUI.fxml");
            viewResultsGUI.setMainApp(this.mainApp);
            viewResultsGUI.setController(this.viewResultsController);
            viewResultsGUI.setUI(this);

        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void showClientData(ActionEvent event) {
        try {
            ShowClientDataSceneGUI showClientDataSceneGUI = (ShowClientDataSceneGUI) this.mainApp.
                    replaceSceneContent("/fxml/SeePersonalDataScene.fxml");
            showClientDataSceneGUI.setMainApp(this.mainApp);
            showClientDataSceneGUI.setUpdatePersonalDataController(this.updatePersonalDataController);
            showClientDataSceneGUI.setClientUI(this);

        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void goBack(ActionEvent event) {
        this.mainApp.toMainScene();
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

    }
}
