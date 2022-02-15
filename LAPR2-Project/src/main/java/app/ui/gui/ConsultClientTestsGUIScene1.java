package app.ui.gui;
import app.controller.ConsultClientTestsController;
import app.domain.model.Client;
import app.mappers.dto.ClientDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class ConsultClientTestsGUIScene1 implements Initializable {


    @FXML
    private AnchorPane paneParent;

    /**
     * Main App.
     */
    private MainApp mainApp;

    /**
     * Consult Client Tests Controller.
     */
    private ConsultClientTestsController consultClientTestsController;

    /**
     * Consult Client Tests GUI.
     */
    private ConsultClientTestsGUI consultClientTestsGUI;

/*    public ConsultClientTestsGUIScene1(MainApp mainApp)
    {
        this.mainApp = mainApp;
        this.consultClientTestsController = new ConsultClientTestsController();
    }*/

    /**
     * Constructor.
     */
    public ConsultClientTestsGUIScene1()
    {
        this.consultClientTestsController = new ConsultClientTestsController();
    }

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button showButton;

    @FXML
    private Label clientLabel;

    @FXML
    private ListView<ClientDto> clientList;

    @FXML
    private SplitMenuButton filterMenu;

    /**
     * Initialization.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConsultClientTests.fxml"));
//            //Parent root = loader.load();
//
//            //Scene scene = new Scene(root);
//
//            updateClientListArea();
//
//        } catch (IOException ex) {
//            AlertaUI.criarAlerta(Alert.AlertType.ERROR, "Many Labs", "Erro.", ex.getMessage());
//        }
        this.showButton.setDisable(clientList.getSelectionModel().getSelectedItems().isEmpty());
        updateClientListArea();
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
     * Activates the "NEXT" button upon clicking on a client of the list.
     */
    @FXML
    void activateNextBtn(MouseEvent event) {
        this.showButton.setDisable(false);
    }

    /**
     * Updates the Client List View.
     */
    public void updateClientListArea() {
        ObservableList<ClientDto> clients = FXCollections.observableArrayList(consultClientTestsController.getClientListAsString());
        clientList.setItems(clients);

        //clientLabel.setText("hiii");
    }

    /**
     * Retrieves the Client tests.
     */
    @FXML
    void getClientTests(ActionEvent event) {
        ClientDto clientDto = clientList.getSelectionModel().getSelectedItem();
        String clientTin = clientDto.getTin();
        consultClientTestsGUI.toRegisterEmployeeScene2(clientTin);
    }

    @FXML
    void getSortingFilters(ActionEvent event) {

    }

    /**
     * Sorts the Client List by name.
     */
    @FXML
    public void sortClientListByName(ActionEvent event) {

        ObservableList<ClientDto> clients;
        try
        {
            clients = FXCollections.observableArrayList(consultClientTestsController.getClientListSortedByName());
            clientList.setItems(clients);
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sorts the Client List by Tax Identification Number.
     */
    @FXML
    void sortClientListByTIN(ActionEvent event) {
        ObservableList<ClientDto> clients;
        try
        {
            clients = FXCollections.observableArrayList(consultClientTestsController.getClientListSortedByTin());
            clientList.setItems(clients);
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the Main App.
     *
     * @return Main app.
     */
    public MainApp getMainApp() {
        return this.mainApp;
    }

    /**
     * Sets the Main App.
     *
     * @param mainApp Main app.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Redirects to Main scene.
     */
    @FXML
    void getBack(ActionEvent event) {
        this.consultClientTestsGUI.toClinicalChemistryTechnologistMenu();
    }

}
