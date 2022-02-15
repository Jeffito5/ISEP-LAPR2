package app.ui.gui;

import app.controller.ConsultClientTestsController;
import app.controller.PerformanceController;
import app.controller.ViewResultsController;
import app.mappers.dto.StatisticDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class OverviewAnalysePerformanceUI implements Initializable {
    private MainApp mainApp;
    private LabCoordinatorGUI labCoordinatorGUI;
    private PerformanceController controller;

    @FXML
    private TextField beginningDateTxt;

    @FXML
    private TextField endDateTxt;

    @FXML
    private TextField algorithmTxt;

    @FXML
    private Button btnBack;

    @FXML
    private Label numberOfClientsLbl;

    @FXML
    private Label numberOfTestsWaitingResultsLbl;

    @FXML
    private Label numberOfTestsWaitingDiagnosisLbl;

    @FXML
    private Label numberOfTestsProcessedLbl;

    @FXML
    private LineChart<?, ?> Graph;


    @FXML
    private String endDate;

    @FXML
    private String beginningDate;

    @FXML
    private int index;

    @FXML
    private Button viewDataBtn;

    @FXML
    void sendData(ActionEvent event) {
        beginningDate=beginningDateTxt.getText();
        endDate=endDateTxt.getText();
        index=Integer.parseInt(algorithmTxt.getText());
        StatisticDTO dto=controller.getData(beginningDate,endDate,index);

        numberOfClientsLbl.setText(String.valueOf(dto.getNumberClients()));
        numberOfTestsWaitingResultsLbl.setText(String.valueOf(dto.getNumberTestsWaitingResults()));
        numberOfTestsWaitingDiagnosisLbl.setText(String.valueOf(dto.getNumberTestsWaitingDiagnosis()));
        numberOfTestsProcessedLbl.setText(String.valueOf(dto.getNumberTestsProcessed()));
        int[] arraySum=dto.getSumArray();



NumberAxis xAxis=new NumberAxis();
NumberAxis yAxis=new NumberAxis();

XYChart.Series series=new XYChart.Series();

        for(int i=0;i<arraySum.length;i++){
            series.getData().add(new XYChart.Data(Integer.toString(i+1),arraySum[i]));
        }


        Graph.getData().add(series);
    }

    @FXML
    void goBack(ActionEvent event) {
        //labCoordinatorGUI.toLabCoordGUI();
        this.mainApp.toMainScene();
    }




    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setController (PerformanceController controller)
    {
        this.controller=controller;
    }

    public void setUI (LabCoordinatorGUI ui)
    {
        this.labCoordinatorGUI=ui;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
