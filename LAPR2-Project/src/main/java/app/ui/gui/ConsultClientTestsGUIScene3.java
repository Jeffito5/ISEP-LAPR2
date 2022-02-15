package app.ui.gui;

import app.controller.ConsultClientTestsController;
import app.mappers.dto.TestDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class ConsultClientTestsGUIScene3 implements Initializable {

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button goBack;

    @FXML
    private ListView<TestDto> testList;

    @FXML
    private Button showTest;

    @FXML
    private AnchorPane paneParent;

    @FXML
    private Label menuLabel;

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
     * Activates the "NEXT" button upon clicking on a test of the list.
     */
    @FXML
    void activateNextBtn(MouseEvent event) {
        this.showTest.setDisable(false);
    }

    /**
     * Redirects to Scene 4.
     */
    @FXML
    void showTest(ActionEvent event) {
        TestDto testDto = testList.getSelectionModel().getSelectedItem();
        String testNhsCode = testDto.getNhsCode();
        consultClientTestsGUI.toRegisterEmployeeScene4(testNhsCode);
    }

    /**
     * Initialization.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.showTest.setDisable(testList.getSelectionModel().getSelectedItems().isEmpty());
    }

    /**
     * Shows list of tests.
     */
    public void showTestList()
    {
        ObservableList<TestDto> tests = FXCollections.observableArrayList(consultClientTestsController.getClientTests());
        testList.setItems(tests);
    }
}

