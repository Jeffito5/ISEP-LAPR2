package app.ui.gui;

import app.controller.PerformanceController;
import app.controller.ViewResultsController;
import app.mappers.dto.ClientResultsTestDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewResultsGUI implements Initializable {
    private MainApp mainApp;
    private ClientSceneGUI clientSceneGUI;
    private ViewResultsController controller;

    private List<ClientResultsTestDTO> clientTestList;

    @FXML
    private Label lblTestIndex;

    @FXML
    private Label lblTestResults;

    @FXML
    private Button btnViewResults;

    @FXML
    private TextField txtIndexTest;

    @FXML
    void viewResults(ActionEvent event){
    String nhsCode=clientTestList.get(Integer.parseInt(txtIndexTest.getText())).getNhsCode();
    lblTestResults.setText(controller.showTestResults(nhsCode));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int i=1;
        String toLabel="";
        clientTestList= controller.showClientTests();
        for(ClientResultsTestDTO o : clientTestList){
            i++;
            toLabel=toLabel+String.format("%d - %s%n",i,o.getNhsCode());
        }
        lblTestIndex.setText(toLabel);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setController (ViewResultsController controller)
    {
        this.controller=controller;
    }

    public void setUI (ClientSceneGUI ui)
    {
        this.clientSceneGUI=ui;
    }
}
