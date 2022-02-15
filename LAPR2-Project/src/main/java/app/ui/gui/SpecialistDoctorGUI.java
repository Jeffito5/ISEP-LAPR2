package app.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class SpecialistDoctorGUI implements Initializable {

    private MainApp mainApp;
    private LoginUI loginUI;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane paneParent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void doLogout(ActionEvent event) {
        loginUI.doLogout();
        loginUI.toLoginScene1();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setLoginUI (LoginUI loginUI)
    {
        this.loginUI = loginUI;
    }

}
