package app.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Luís Araújo
 */
public class MainUI implements Initializable {
    private MainApp mainApp;
    @FXML
    private Button btnLogin;

    @FXML
    private Button btnTeam;

    @FXML
    private MenuBar menuBar1;


    @FXML
    private AnchorPane paneParent;

    public MainUI() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainApp = new MainApp();
    }

    public MainApp getMainApp() {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void btnDoLogin(ActionEvent event) {
        LoginUI loginUI = new LoginUI(this.mainApp);
        loginUI.setMainApp(this.mainApp);
        loginUI.toLoginScene1();
    }

    @FXML
    void btnShowTeam(ActionEvent event) {
        try {
            ShowTeamUI showTeamUI = (ShowTeamUI) this.mainApp.
                    replaceSceneContent("/fxml/ShowTeam.fxml");
            showTeamUI.setMainApp(this.mainApp);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void menuCloseApp(ActionEvent event) {
        Window window = btnLogin.getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    void menuAbout(ActionEvent event) {
        AlertGUI.createAlert(Alert.AlertType.INFORMATION, "Many Labs", "About.",
                "@Copyright\nTECHGATE ® RELOADED").show();
    }

}

