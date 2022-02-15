package app.ui.gui;

import app.controller.ImportClinicalTestsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Luís Araújo
 */
public class ImportClinicalTestsGUI implements Initializable {
    private MainApp mainApp = new MainApp();
    private LabCoordinatorGUI labCoordinatorGUI;
    private ImportClinicalTestsController controller;
    private Stage mainStage = mainApp.getStage();

    @FXML
    private Button btnImport;

    @FXML
    private Button btnBack;

    @FXML
    private AnchorPane paneParent;

    @FXML
    private Label lblInfo;


    /*public ImportClinicalTestsGUI(MainApp mainApp) {
        this.mainApp = mainApp;
        this.ctrl = new AuthController();
        app = App.getInstance();
        labCoordinatorSceneUI = new LabCoordinatorSceneUI();
        //this.loginUI = new LoginUI(mainApp);
    }*/

    public ImportClinicalTestsGUI() {

    }

    @FXML
    void importFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        File selectedFile = fileChooser.showOpenDialog(mainStage);
        if (selectedFile != null) {
            try {
                ImportClinicalTestsSceneGUI importClinicalTestsSceneGUI = (ImportClinicalTestsSceneGUI) this.mainApp.
                        replaceSceneContent("/fxml/ImportedTestsScene.fxml");
                importClinicalTestsSceneGUI.setMainApp(this.mainApp);
                importClinicalTestsSceneGUI.setImportClinicalTestsGUI(this);
                importClinicalTestsSceneGUI.loadFile(selectedFile);
            } catch (Exception ex) {
                Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            lblInfo.setText("Couldn't load the file");
        }
    }

    @FXML
    void goBack(ActionEvent event) {
        this.mainApp.toMainScene();
    }

    public void setController(ImportClinicalTestsController controller) {
        this.controller = controller;
    }

    public void setUI(LabCoordinatorGUI ui) {
        this.labCoordinatorGUI = ui;
    }

    public MainApp getMainApp() {
        return this.mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}