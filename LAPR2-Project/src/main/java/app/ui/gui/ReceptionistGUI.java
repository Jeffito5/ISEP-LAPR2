package app.ui.gui;

import app.controller.ReceptionistController;
import app.controller.RegisterTestController;
import app.ui.console.RegisterTestUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class ReceptionistGUI implements Initializable {

    private MainApp mainApp;
    private LoginUI loginUI;

    @FXML
    private MenuBar menuBar;

    @FXML
    private AnchorPane paneParent;

    @FXML
    private Button logout;

    @FXML
    private Button registerNewClient;

    @FXML
    private MenuBar menuBar1;

    @FXML
    private Button newTest;

    @FXML
    private MenuItem logout1;

    private ReceptionistController receptionistController;

    private String clinicalAnalysisLaboratoryId;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        receptionistController = new ReceptionistController();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setLoginUI (LoginUI loginUI)
    {
        this.loginUI = loginUI;
    }

    public void setLabCode(String clinicalAnalysisLaboratoryId){
        this.clinicalAnalysisLaboratoryId = clinicalAnalysisLaboratoryId;
    }

    @FXML
    void doLogout(ActionEvent event) {
        loginUI.doLogout();
        loginUI.toLoginScene1();
    }


    @FXML
    void menuRegisterNewTest(ActionEvent event) {
        RegisterTestUI registerTestUI = new RegisterTestUI(clinicalAnalysisLaboratoryId);
        registerTestUI.run();
    }

    @FXML
    void menuRegisterNewClient(ActionEvent event) {

    }

    @FXML
    void menuCloseApp(ActionEvent event) {

    }

    @FXML
    void menuAbout(ActionEvent event) {

    }
}