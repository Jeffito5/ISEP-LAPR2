package app.ui.gui;

import app.ui.console.RecordTestResultUI;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import javafx.stage.WindowEvent;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class ClinicalChemistryTechnologistGUI implements Initializable {

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button consultTests;

    @FXML
    private MenuItem logout;

    @FXML
    private AnchorPane paneParent;

    @FXML
    private Label nameId;

    @FXML
    private Label welcomeLabel;


    @FXML
    private Button recordTest;

    private MainApp mainApp;

    private LoginUI loginUI;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public MainApp getMainApp() {
        return this.mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setLoginUI (LoginUI loginUI)
    {
        this.loginUI = loginUI;
    }

    @FXML
    void doLogout(ActionEvent event) {
        loginUI.doLogout();
        loginUI.toLoginScene1();
    }


    @FXML
    void menuCloseApp(ActionEvent event) {
        Window window = nameId.getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    void menuAbout(ActionEvent event) {
        AlertGUI.createAlert(Alert.AlertType.INFORMATION, "Many Labs", "About.",
                "@Copyright\nTECHGATE ® RELOADED").show();
    }

    @FXML
    void recordTestResults(ActionEvent event) {
        RecordTestResultUI recordTestResultUI = new RecordTestResultUI();
        recordTestResultUI.run();
    }

    @FXML
    void consultClientTests(ActionEvent event) {
        ConsultClientTestsGUI consultClientTestsGUI = new ConsultClientTestsGUI(this.mainApp);
        consultClientTestsGUI.setLoginUI(this.loginUI);
        consultClientTestsGUI.toRegisterEmployeeScene1();
    }
}
