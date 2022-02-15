package app.ui.gui;

import app.controller.ConsultClientTestsController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class ConsultClientTestsGUIScene2 implements Initializable {

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button goBack;

    @FXML
    private Button confirmButton;

    @FXML
    private TextArea clientInfoArea;

    @FXML
    private Label clientLabel;

    @FXML
    private AnchorPane paneParent;

    /**
     * Consult Client Tests Controller.
     */
    private ConsultClientTestsController consultClientTestsController;

    /**
     * Main app.
     */
    private MainApp mainApp;

    /**
     * Consult Client Tests GUI.
     */
    private ConsultClientTestsGUI consultClientTestsGUI;

    /**
     * Sets the Main App.
     *
     * @param mainApp Main app.
     */
    public void setMainApp (MainApp mainApp)
    {
        this.mainApp = mainApp;
    }

    /**
     * Sets the Consult Client Tests Controller.
     *
     * @param consultClientTestsController Consult Client Tests Controller.
     */
    public void setController (ConsultClientTestsController consultClientTestsController)
    {
        this.consultClientTestsController = consultClientTestsController;
    }

    /**
     * Sets Consult Client Tests GUI.
     *
     * @param consultClientTestsGUI Consult Client Tests GUI.
     */
    public void setUI (ConsultClientTestsGUI consultClientTestsGUI)
    {
        this.consultClientTestsGUI = consultClientTestsGUI;
    }

    /**
     * Redirects to Scene 1.
     */
    @FXML
    void getBack(ActionEvent event) {
        consultClientTestsGUI.toRegisterEmployeeScene1();
    }

    /**
     * Event to confirm the client choice, redirecting to Scene 3.
     */
    @FXML
    void confirm(ActionEvent event) {
        consultClientTestsGUI.toRegisterEmployeeScene3();
    }


    /**
     * Initialization.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     * Shows the client's info.
     */
    public void showClientInfo (String clientTin) {
        consultClientTestsController.getClient(clientTin);
        this.clientInfoArea.setText(consultClientTestsController.getClientInfo());
    }


}
