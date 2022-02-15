package app.ui.gui;

import app.controller.App;
import app.controller.UpdatePersonalDataController;
import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.store.ClientStore;
import app.mappers.ClientMapper;
import app.mappers.dto.ClientDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Luís Araújo
 */
public class ShowClientDataSceneGUI implements Initializable {
    private MainApp mainApp = new MainApp();
    private ClientSceneGUI clientUI;
    private UpdatePersonalDataController updatePersonalDataController;
    private Client client;
    private ClientStore clientStore;
    private App app;
    private Company company;

    @FXML
    private Button btnBack;

    @FXML
    private TextArea txtAreaInfo;


    @FXML
    void goBack(ActionEvent event) {
        this.mainApp.toMainScene();
    }

    public MainApp getMainApp() {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public ClientSceneGUI getClientUI() {
        return clientUI;
    }

    public void setClientUI(ClientSceneGUI clientUI) {
        this.clientUI = clientUI;
    }

    public UpdatePersonalDataController getUpdatePersonalDataController() {
        return updatePersonalDataController;
    }

    public void setUpdatePersonalDataController(UpdatePersonalDataController updatePersonalDataController) {
        this.updatePersonalDataController = updatePersonalDataController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String email;
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.clientStore = company.getClientStore();
        email = app.getCurrentUserSession().getUserId().getEmail();
        client = clientStore.getClientByEmail(email);
        txtAreaInfo.setText(client.toString());
    }
}
