package app.ui.gui;

import app.controller.SendNHSCovidReportController;
import com.nhs.report.Report2NHS;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendCovidReportGUIScene implements Initializable {

    @FXML
    private ToggleGroup indVar;

    @FXML
    private AnchorPane paneParent;

    @FXML
    private DatePicker dataSetStartDate;

    @FXML
    private ComboBox<String> linearRegressionModel;

    @FXML
    private MenuItem closeMenu;

    @FXML
    private Label errorMessage;

    @FXML
    private MenuItem aboutMenu;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button createBtn;

    @FXML
    private RadioButton perfTestsOp;

    @FXML
    private RadioButton meanAgeOp;

    @FXML
    private TextField historicalPoints;

    @FXML
    private Label clientLabel;

    @FXML
    private DatePicker dataSetEndDate;

    @FXML
    private DatePicker referenceDate;


    @FXML
    private TextField significanceAlpha;

    @FXML
    private TextField confidenceAlpha;

    @FXML
    private Button goBack;


    /**
     * Main App.
     */
    private MainApp mainApp;

    /**
     * Controller.
     */
    private SendNHSCovidReportController sendNHSCovidReportController;

    private SendCovidReportGUI sendCovidReportGUI;

    private final String SLR = "Simple Linear Regression";
    private final String MLR = "Multiple Linear Regression";
    private final String MEAN_AGES = "Mean Age of Clients";
    private final String PERFORMED = "Performed Covid Tests";

    @FXML
    void menuCloseApp(ActionEvent event) {
        Window window = errorMessage.getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    void menuAbout(ActionEvent event) {
        AlertGUI.createAlert(Alert.AlertType.INFORMATION, "Many Labs", "About.",
                "@Copyright\nTECHGATE ® RELOADED").show();
    }

    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }

    public void setController(SendNHSCovidReportController sendNHSCovidReportController)
    {
        this.sendNHSCovidReportController = sendNHSCovidReportController;
    }

    public void setUI(SendCovidReportGUI ui)
    {
        this.sendCovidReportGUI = ui;
    }

    public void initComboBox() {
        List<String> linearRegressionModels = new ArrayList<>();
        linearRegressionModels.add(SLR);
        linearRegressionModels.add(MLR);

        ObservableList<String> options
                = FXCollections.observableArrayList(linearRegressionModels);



        this.linearRegressionModel.setItems(options);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initComboBox();
        meanAgeOp.setDisable(true);
        perfTestsOp.setDisable(true);

        linearRegressionModel.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                setOption();
            }
        });

        historicalPoints.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,3}([\\.]\\d{0,4})?")) {
                    historicalPoints.setText(oldValue);
                }
            }
        });

        significanceAlpha.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,3}([\\.]\\d{0,4})?")) {
                    significanceAlpha.setText(oldValue);
                }
            }
        });

        confidenceAlpha.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,3}([\\.]\\d{0,4})?")) {
                    confidenceAlpha.setText(oldValue);
                }
            }
        });
    }




    @FXML
    void createNhsReport(ActionEvent event)
    {
        errorMessage.setText("");
        String option = linearRegressionModel.getSelectionModel().getSelectedItem();
        try{
            sendNHSCovidReportController.getLinearRegressionModel(option);

        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e)
        {
            errorMessage.setText("Error Loading the Model");
            Logger.getLogger(SendCovidReportGUIScene.class.getName()).log(Level.SEVERE, null, e);
        }

        Date startDate = getDate(dataSetStartDate);
        Date endDate = getDate(dataSetEndDate);
        Date reference = getDate(referenceDate);
        int points = Integer.parseInt(historicalPoints.getText());
        double confidence = Double.parseDouble(confidenceAlpha.getText());
        double significance = Double.parseDouble(significanceAlpha.getText());
        int independentVariableID = getIndependentVariableId();

        try {
            String report = "";
            if (option.equals(MLR))
            {
                report = sendNHSCovidReportController.getMultipleLinearRegressionReport(reference,
                        startDate, endDate, points, significance, confidence);
            } else if(option.equals(SLR))
            {
                report = sendNHSCovidReportController.
                        getSimpleLinearRegressionReport(reference, startDate,
                                endDate, points, independentVariableID,
                                significance, confidence);
            }
            sendCovidReportGUI.toSendCovidReportPreviewScreen(report);

        } catch (IllegalArgumentException e)
        {
            errorMessage.setText("Error: " + e.getMessage());
        }
    }

    private Date getDate(DatePicker datePicker)
    {
        LocalDate referenceDateValue = datePicker.getValue();
        return java.util.Date.from(referenceDateValue.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }


    public void setOption() {
        String option = linearRegressionModel.getSelectionModel().getSelectedItem();
        if (option.equals(SLR))
        {
            meanAgeOp.setDisable(false);
            perfTestsOp.setDisable(false);
        } else if (option.equals(MLR))
        {
            meanAgeOp.setDisable(true);
            perfTestsOp.setDisable(true);
        }
    }

    public int getIndependentVariableId(){
        if (meanAgeOp.isSelected())
        {
            return 0;
        } else if (perfTestsOp.isSelected())
        {
            return 1;
        }
        return -1;
    }

    @FXML
    void getBack(ActionEvent event) {
        sendCovidReportGUI.toAdminMenu();
    }
}
