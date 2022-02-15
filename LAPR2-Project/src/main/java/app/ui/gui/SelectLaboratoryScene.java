package app.ui.gui;

import app.controller.ReceptionistController;
import app.mappers.dto.ClinicalAnalysisLaboratoryDto;
import app.ui.console.utils.Utils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SelectLaboratoryScene implements Initializable {

    @FXML
    private ComboBox<String> calCombo;

    @FXML
    private Button goBack;

    @FXML
    private AnchorPane paneParent;

    @FXML
    private MenuBar menuBar1;


    @FXML
    private Button confirmBtn;

    @FXML
    private MenuItem logout1;

    private ReceptionistController receptionistController;

    private MainApp mainApp;

    private ReceptionistGUI receptionistGUI;

    private LoginUI loginUI;

    List<ClinicalAnalysisLaboratoryDto> laboratories;

    public void setReceptionistController(ReceptionistController receptionistController)
    {
        this.receptionistController = receptionistController;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    public void setLoginUI(LoginUI loginUI){
        this.loginUI = loginUI;
    }

    @FXML
    void menuCloseApp(ActionEvent event) {
        Window window = confirmBtn.getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    void doLogout(ActionEvent event) {
        loginUI.toLoginScene1();
    }

    @FXML
    void menuAbout(ActionEvent event) {
        AlertGUI.createAlert(Alert.AlertType.INFORMATION, "Many Labs", "About.",
                "@Copyright\nTECHGATE ® RELOADED").show();
    }

    @FXML
    void getBack(ActionEvent event) {
    }

    public void initCombo (){
        laboratories = receptionistController.getClinicalAnalysisLaboratories();
        System.out.println(laboratories);

        ObservableList<String> clients = FXCollections.observableArrayList(Utils.calDtoListToStringList(laboratories));

        calCombo.setItems(clients);
    }

    @FXML
    void confirm(ActionEvent event) {
        int index = calCombo.getSelectionModel().getSelectedIndex();
        ClinicalAnalysisLaboratoryDto cal = laboratories.get(index);
        String code = cal.getLaboratoryId();
        loginUI.toReceptionistMenu(code);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.confirmBtn.setDisable(true);
        calCombo.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                if (calCombo.getSelectionModel().getSelectedItem() != null)
                {
                    confirmBtn.setDisable(false);
                }
            }
        });
    }

}
