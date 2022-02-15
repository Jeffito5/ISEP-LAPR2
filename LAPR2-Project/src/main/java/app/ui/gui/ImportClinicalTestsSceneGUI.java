package app.ui.gui;

import app.controller.AuthController;
import app.controller.ImportClinicalTestsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Luís Araújo
 */
public class ImportClinicalTestsSceneGUI implements Initializable {
    private AuthController ctrl = new AuthController();
    private MainApp mainApp = new MainApp();
    private ImportClinicalTestsGUI importClinicalTestsGUI;
    private ImportClinicalTestsController importClinicalTestsController = new ImportClinicalTestsController();

    @FXML
    private Button btnBack;

    @FXML
    private AnchorPane paneParent;


    @FXML
    private TextArea txtAreaInfo;

    @FXML
    private Label lblInfo;

    @FXML
    private Button btnConfirm;


    public AuthController getController() {
        return this.ctrl;
    }

    public MainApp getMainApp() {
        return this.mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setImportClinicalTestsGUI(ImportClinicalTestsGUI importClinicalTestsGUI) {
        this.importClinicalTestsGUI = importClinicalTestsGUI;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //this.ctrl = new AuthController();
    }

    @FXML
    void goBack(ActionEvent event) {
        this.mainApp.toMainScene();
    }

    public void loadFile(File file) {
        String filePath = file.getAbsolutePath();
        try {
            importClinicalTestsController.readTests(filePath);
        } catch (IOException e) {
            txtAreaInfo.setText("Couldn't load the file");
        }
        List<String[]> clientsFailure = importClinicalTestsController.getClientsFailure();
        if (clientsFailure.isEmpty()) {
            txtAreaInfo.setText("Success");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Clients with error\n");
            for (String[] clients : clientsFailure) {
                stringBuilder.append(String.format("%-40s%-20s%n", clients[0], clients[1].trim()));
            }
            txtAreaInfo.setText(stringBuilder.toString());
        }
    }

    @FXML
    void confirmSaveData(ActionEvent event) {
        if (importClinicalTestsController.saveData()) {
            lblInfo.setText("Saved");
        } else {
            lblInfo.setText("Error saving");
        }
    }
}
