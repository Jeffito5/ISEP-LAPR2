package app.ui.gui;

import app.controller.SendNHSCovidReportController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SendCovidReportGUIScene2 implements Initializable {

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button goBack;

    @FXML
    private AnchorPane paneParent;

    @FXML
    private Label clientLabel;

    @FXML
    private TextArea reportPreview;

    @FXML
    private MenuItem closeMenu;

    @FXML
    private Button confirmButton;

    @FXML
    private MenuItem aboutMenu;

    private MainApp mainApp;

    private SendNHSCovidReportController sendNHSCovidReportController;

    private SendCovidReportGUI sendCovidReportGUI;

    private String report;

    @FXML
    void menuCloseApp(ActionEvent event) {
        Window window = reportPreview.getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    void menuAbout(ActionEvent event) {
        AlertGUI.createAlert(Alert.AlertType.INFORMATION, "Many Labs", "About.",
                "@Copyright\nTECHGATE ® RELOADED").show();
    }

    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }

    public void setController(SendNHSCovidReportController sendNHSCovidReportController)
    {
        this.sendNHSCovidReportController = sendNHSCovidReportController;
    }

    public void setUI(SendCovidReportGUI ui)
    {
        this.sendCovidReportGUI = ui;
    }

    @FXML
    void getBack(ActionEvent event) {
        sendCovidReportGUI.toSendCovidReportScene1();
    }

    @FXML
    void confirm(ActionEvent event) {
        sendNHSCovidReportController.sendNHSReport(report);
        AlertGUI.createAlert(Alert.AlertType.INFORMATION, "Admin", "NHS COVID-19 REPORT",
                "NHS Report sent successfully.").show();
        sendCovidReportGUI.toSendCovidReportScene1();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reportPreview.setStyle("-fx-font-family: monospace");
    }

    public void setReportPreview(String report)
    {
        this.report = report;
        reportPreview.setText(report);
    }
}
