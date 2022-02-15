package app.ui.gui;

import app.controller.App;
import app.controller.RegisterClientController;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Luís Araújo
 */
public class UpdatePersonalDataGUI implements Initializable {
    private MainApp mainApp = new MainApp();
    private RegisterClientController registerClientController;
    private ClientSceneGUI clientUI;
    private UpdatePersonalDataController updatePersonalDataController;
    private ClientDto clientDto;
    private Client client;
    private ClientStore clientStore;
    private App app;
    private Company company;
    private ClientMapper clientMapper = new ClientMapper();
    private List<Client> clients = new ArrayList<>();

    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtBirthDate;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnUpdate;
    @FXML
    private AnchorPane paneParent;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSex;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtNHS;
    @FXML
    private TextField txtTIN;
    @FXML
    private TextField txtCCN;
    @FXML
    private TextField txtInfo;

    public UpdatePersonalDataGUI() {
        registerClientController = new RegisterClientController();
        clientUI = new ClientSceneGUI();
        updatePersonalDataController = new UpdatePersonalDataController();
    }

    public UpdatePersonalDataController getUpdatePersonalDataController() {
        return updatePersonalDataController;
    }

    public void setUpdatePersonalDataController(UpdatePersonalDataController updatePersonalDataController) {
        this.updatePersonalDataController = updatePersonalDataController;
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

    @FXML
    void goBack(ActionEvent event) {
        clientUI.getMainApp().toMainScene();
    }

    @FXML
    void cancel(ActionEvent event) {
        txtInfo.setText("Canceled.");
        txtEmail.setText("");
        txtBirthDate.setText("");
        txtCCN.setText("");
        txtName.setText("");
        txtNHS.setText("");
        txtPhoneNumber.setText("");
        txtSex.setText("");
        txtTIN.setText("");
    }

    @FXML
    void updateData(ActionEvent event) {
        clients = clientStore.getClients();
        boolean flag = true;
        try {
            if (!txtBirthDate.getText().isEmpty()) {
                String birthDate = txtBirthDate.getText();
                Date date = new Date(birthDate);
                clientDto.setBirthDate(date);
                txtInfo.setText("Updated Client");
                client.setBirthDate(clientDto.getBirthDate());
            }

            if (!txtName.getText().isEmpty()) {
                String name = txtName.getText();
                clientDto.setName(name);
                txtInfo.setText("Updated Client");
                client.setName(clientDto.getName());
            }

            if (!txtCCN.getText().isEmpty()) {
                String ccn = txtCCN.getText();
                for (Client client : clients) {
                    if (client.getCcn().equals(ccn)) {
                        flag = false;
                        txtInfo.setText("Client's citizen card number already registered.");
                    }

                }
                if (flag) {
                    clientDto.setCcn(ccn);
                    txtInfo.setText("Updated Client");
                    client.setCcn(clientDto.getCcn());
                }
            }

            if (!txtSex.getText().isEmpty()) {
                String sex = txtSex.getText();
                if (sex.equalsIgnoreCase(Client.Sex.MALE.toString())) {
                    clientDto.setSex(ClientDto.Sex.MALE);
                }
                if (sex.equalsIgnoreCase(Client.Sex.FEMALE.toString())) {
                    clientDto.setSex(ClientDto.Sex.FEMALE);
                }
                if (sex.equalsIgnoreCase(Client.Sex.OTHER.toString())) {
                    clientDto.setSex(ClientDto.Sex.OTHER);
                }
                if (sex.equalsIgnoreCase(Client.Sex.NONE.toString())) {
                    clientDto.setSex(ClientDto.Sex.NONE);
                }
                txtInfo.setText("Updated Client");
                client.setSex(clientDto.getSex());
            }

            if (!txtNHS.getText().isEmpty()) {
                flag = true;
                String nhs = txtNHS.getText();
                for (Client client : clients) {
                    if (client.getNhsNumber().equals(nhs)) {
                        flag = false;
                        txtInfo.setText("Client's nhs number already registered.");
                    }
                }
                if (flag) {
                    clientDto.setNhsNumber(nhs);
                    txtInfo.setText("Updated Client");
                    client.setNhsNumber(clientDto.getNhsNumber());
                }
            }

            if (!txtTIN.getText().isEmpty()) {
                flag = true;
                String tin = txtTIN.getText();
                for (Client client : clients) {
                    if (client.getTin().equals(tin)) {
                        flag = false;
                        txtInfo.setText("Client's tin number already registered.");
                    }
                }
                if (flag) {
                    clientDto.setTin(tin);
                    txtInfo.setText("Updated Client");
                    client.setTin(clientDto.getTin());
                }
            }

            if (!txtPhoneNumber.getText().isEmpty()) {
                flag = true;
                String phoneNumber = txtPhoneNumber.getText();
                for (Client client : clients) {
                    if (client.getPhoneNumber().equals(phoneNumber)) {
                        flag = false;
                        txtInfo.setText("Client's phone number already registered.");
                    }
                }
                if (flag) {
                    clientDto.setPhoneNumber(phoneNumber);
                    txtInfo.setText("Updated Client");
                    client.setPhoneNumber(clientDto.getPhoneNumber());
                }
            }

            if (!txtEmail.getText().isEmpty()) {
                flag = true;
                String email = txtEmail.getText();
                for (Client client : clients) {
                    if (client.getEmail().equals(email)) {
                        flag = false;
                        txtInfo.setText("Client's email already registered.");
                    }
                }
                if (flag) {
                    clientDto.setEmail(email);
                    txtInfo.setText("Updated Client");
                    client.setEmail(clientDto.getEmail());
                }
            }
        } catch (Exception e) {
            txtInfo.setText("Client's data not updated");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String email;
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.clientStore = company.getClientStore();
        email = app.getCurrentUserSession().getUserId().getEmail();
        client = clientStore.getClientByEmail(email);
        clientDto = clientMapper.toDTO(client);
    }
}
