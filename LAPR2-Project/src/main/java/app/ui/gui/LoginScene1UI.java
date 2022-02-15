package app.ui.gui;

import app.controller.AuthController;
import app.domain.shared.Constants;
import app.ui.console.ReceptionistUI;
import app.ui.console.RegisterSampleUI;
import auth.mappers.dto.UserRoleDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author Luís Araújo
 * @author Rui Rocha
 */
public class LoginScene1UI implements Initializable {
    private AuthController ctrl;
    private MainApp mainApp;
    private LoginUI loginUI;
    private LoginScene1UI loginScene1UI;

    @FXML
    private MenuBar menuBar;

    @FXML
    private PasswordField txtPass;

    @FXML
    private Label warningLabel;


    @FXML
    private Button btnCancel;

    @FXML
    private AnchorPane paneParent;

    @FXML
    private Button btnNext;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextArea txtAreaRoles;

    public LoginScene1UI() {
        ctrl = new AuthController();
    }

    @FXML
    void menuCloseApp(ActionEvent event) {
        Window window = txtEmail.getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    void menuAbout(ActionEvent event) {
        AlertGUI.createAlert(Alert.AlertType.INFORMATION, "Many Labs", "About.",
                "@Copyright\nTECHGATE ® RELOADED").show();
    }

    public AuthController getController() {
        return this.ctrl;
    }

    public void setController(AuthController authController) {
        this.ctrl = authController;
    }

    public MainApp getMainApp() {
        return this.mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public LoginUI getLoginUI() {
        return this.loginUI;
    }

    public void setLoginUI(LoginUI loginUI) {
        this.loginUI = loginUI;
    }

    public LoginScene1UI getLoginScene1UI() {
        return this.loginScene1UI;
    }


    @FXML
    public void doLogin(ActionEvent event) {

        boolean success;
        String email = txtEmail.getText();
        String password = txtPass.getText();

        success = ctrl.doLogin(email, password);
        if (success) {
            List<UserRoleDTO> roles = this.ctrl.getUserRoles();

            if ((roles == null) || (roles.isEmpty())) {
                this.warningLabel.setText("User does not have any role assigned.");
            } else {
                UserRoleDTO role = selectsRole(roles);

                if (!Objects.isNull(role)) {
                    goToUIFor(role);
                } else {
                    this.warningLabel.setText("User did not select a role.");
                }
            }

        } else {
            this.warningLabel.setText("Invalid UserId and/or Password.");
            cleanFields();
        }
    }

    public void cleanFields(){
        txtEmail.setText("");
        txtPass.setText("");
    }

    @FXML
    public void goBack(ActionEvent event) {
        loginUI.getMainApp().toMainScene();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //this.ctrl = new AuthController();
        this.loginScene1UI = new LoginScene1UI();
        this.loginUI = new LoginUI(mainApp);
        txtEmail.setEditable(true);
        txtPass.setEditable(true);
        txtEmail.setPromptText("email");
        txtPass.setPromptText("password");
    }


    public UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
        if (roles.size() == 1)
            return roles.get(0);
        else
            for (UserRoleDTO role : roles) {
                txtAreaRoles.setText("Select the role you want to adopt in this session:");
                txtAreaRoles.setText(String.valueOf(role));
            }
        return (UserRoleDTO) roles;
    }

    public void goToUIFor(UserRoleDTO role) {
        switch (role.getDescription()) {
            case Constants.ROLE_CLI_CHEM_TECH:
                loginUI.toCliChemTechMenu();
                break;
            case Constants.ROLE_RECEPTIONIST:
                ReceptionistUI receptionistUI = new ReceptionistUI();
                receptionistUI.run();
                loginUI.doLogout();
                cleanFields();
                break;
            case Constants.ROLE_ADMIN:
                loginUI.toAdminMenu();
                break;
            case Constants.ROLE_SPEC_DOC:
                loginUI.toSpecDocMenu();
                break;
            case Constants.ROLE_LAB_COORD:
                loginUI.toLabCoordinatorMenu();
                break;
            case Constants.ROLE_CLIENT:
                loginUI.toClientMenu();
                break;
            case Constants.ROLE_MED_LAB_TECH:
                RegisterSampleUI registerSampleUI = new RegisterSampleUI();
                registerSampleUI.run();
                loginUI.doLogout();
                cleanFields();
            default:
                break;
        }
    }
}