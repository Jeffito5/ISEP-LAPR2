package app.ui.gui;

import app.ui.console.ConsultClientTestsUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class BootstrapGUI implements Initializable {

    @FXML
    private AnchorPane paneParent;

    @FXML
    private Button consultTestsBtn;

    @FXML
    private Button goBack;

    @FXML
    private Button btnViewResults;

    private MainApp mainApp;

    public void setMainApp (MainApp mainApp)
    {
        this.mainApp = mainApp;
    }


    @FXML
    void consultClientTestsBtn(ActionEvent event) {
        ConsultClientTestsGUI consultClientTestsGUI = new ConsultClientTestsGUI(this.mainApp);
        consultClientTestsGUI.toRegisterEmployeeScene1();
    }

    @FXML
    void viewResultsClient(ActionEvent event) {
        ViewResultsGUI viewResultsGUI = new ViewResultsGUI();
        viewResultsGUI.setMainApp(this.mainApp);
    }



    @FXML
    void goBackBtn(ActionEvent event) {
        this.mainApp.toMainScene();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
