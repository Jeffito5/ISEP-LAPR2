package app.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Luís Araújo
 */

public class ShowTeamUI implements Initializable {
    private MainApp mainApp;
    private ShowTeamUI showTeamUI;
    @FXML
    private Button btnBack;

    @FXML
    private AnchorPane paneParent;

    public ShowTeamUI() {

    }

    @FXML
    void goBack(ActionEvent event) {
        this.mainApp.toMainScene();
    }

    public MainApp getMainApp() {
        return this.mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Initializes the UI class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.showTeamUI = new ShowTeamUI();
    }
}
