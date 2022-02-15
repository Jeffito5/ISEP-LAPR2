package app.ui.gui;

import app.ui.console.ClinicalAnalysisLaboratoryUI;
import app.ui.console.CreateEmployeeUI;
import app.ui.console.CreateParameterCategoryUI;
import app.ui.console.TestTypeUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class AdministratorGUI implements Initializable {

    private MainApp mainApp;
    private LoginUI loginUI;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane paneParent;

    @FXML
    private Button newTestType;

    @FXML
    private Label welcomeLabel;


    @FXML
    private Button registerNewEmployee;

    @FXML
    private Button newLaboratory;

    @FXML
    private Button newParameterCategory;

    @FXML
    private MenuItem logout1;

    @FXML
    private Button sendCovidReport;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        Window window = welcomeLabel.getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }


    @FXML
    void menuAbout(ActionEvent event) {
        AlertGUI.createAlert(Alert.AlertType.INFORMATION, "Many Labs", "About.",
                "@Copyright\nTECHGATE ® RELOADED").show();
    }

    @FXML
    void menuRegisterNewLaboratory(ActionEvent event) {
        ClinicalAnalysisLaboratoryUI clinicalAnalysisLaboratoryUI = new ClinicalAnalysisLaboratoryUI();
        clinicalAnalysisLaboratoryUI.run();
    }

    @FXML
    void menuSendCovidReport(ActionEvent event) {
        SendCovidReportGUI sendCovidReportGUI = new SendCovidReportGUI();
        sendCovidReportGUI.setLoginUI(loginUI);
        sendCovidReportGUI.setMainApp(mainApp);
        sendCovidReportGUI.toSendCovidReportScene1();
    }

    @FXML
    void menuRegisterNewEmployee(ActionEvent event) {
        CreateEmployeeUI createEmployeeUI = new CreateEmployeeUI();
        createEmployeeUI.run();
    }

    @FXML
    void menuNewTestType(ActionEvent event) {
        TestTypeUI testTypeUI = new TestTypeUI();
        testTypeUI.run();

    }

    @FXML
    void menuNewParameterCategory(ActionEvent event) {
        CreateParameterCategoryUI createParameterCategoryUI = new CreateParameterCategoryUI();
        createParameterCategoryUI.run();
    }


}
