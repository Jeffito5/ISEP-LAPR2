package app.controller;

import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.model.algorithms.linearRegression.EstimationDataSet;
import app.domain.model.algorithms.linearRegression.LinearRegression;
import app.domain.model.algorithms.linearRegression.ModelDataSet;
import app.domain.shared.Constants;
import app.domain.store.TestStore;
import app.mappers.dto.LinearRegressionInputDTO;
import app.mappers.dto.LinearRegressionOutputDTO;
import app.ui.console.utils.Covid19NHSReport;
import app.ui.console.utils.Utils;
import com.nhs.report.Report2NHS;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class SendNHSCovidReportController {
    /**
     * Company.
     */
    private final Company company;

    /**
     * Test store.
     */
    private final TestStore testStore;

    private static final String SLR_ID = "Simple Linear Regression";
    private static final String MLR_ID = "Multiple Linear Regression";
    private LinearRegression linearRegression;
    private List<Date> workDaysForPredictions;
    private List<Test> testsForPredictions;


    /**
     * Constructs an instance of the Controller,
     * using singleton to access the app instance;
     */
    public SendNHSCovidReportController(){
        // Singleton
        App app = App.getInstance();
        company = app.getCompany();

        this.testStore = company.getTestStore();
    }

    public void getLinearRegressionModel (String linearRegressionModelId)
            throws IllegalAccessException, ClassNotFoundException, InstantiationException
    {
        String linearRegressionModel;
        if (linearRegressionModelId.equals(SLR_ID))
        {
            linearRegressionModel = Constants.PARAMS_SIMPLE_LINEAR_REGRESSION;
        } else if (linearRegressionModelId.equals(MLR_ID))
        {
            linearRegressionModel = Constants.PARAMS_MULTIPLE_LINEAR_REGRESSION;
        } else
        {
            throw new IllegalArgumentException("Error Loading Linear Regression Model.");
        }
        setLinearRegressionModel(linearRegressionModel);
    }

    public void setLinearRegressionModel(String modelName)
            throws IllegalAccessException,
            InstantiationException,
            ClassNotFoundException
    {
        Class<?> oClass = Class.forName(modelName);
        linearRegression =  (LinearRegression) oClass.newInstance();
    }



    public ModelDataSet setModelData(Date dataSetStartDate, Date dataSetEndDate)
    {
        List<Date> workingDays = company.getWorkingDaysForIntervals(dataSetStartDate, dataSetEndDate);

        if (workingDays.size() == 1)
        {
            throw new IllegalArgumentException("Data set needs to be bigger than one day.");
        }

        List<Test> covidTestsInInterval = testStore.getCovidTestsInInterval(dataSetStartDate, dataSetEndDate);

        if (covidTestsInInterval.isEmpty())
        {
            throw new IllegalArgumentException("No tests in this interval");
        }

        double[] performedCovidTests = testStore.getDailyNumberOfCovidTestsInInterval(workingDays, covidTestsInInterval);
        double[] positiveCovidTests = testStore.getDailyNumberOfPositiveCovidTestsForInterval(workingDays, covidTestsInInterval);
        double[] meanAges = testStore.getDailyMeanAgeOfCovidTestsForInterval(workingDays, covidTestsInInterval);

        return new ModelDataSet(meanAges, performedCovidTests, positiveCovidTests);
    }

    public EstimationDataSet setEstimationData(Date referenceDate, int historicalPoints)
    {
        Calendar date = Calendar.getInstance();
        date.setTime(referenceDate);
        Utils.setTimeToZero(date);

        workDaysForPredictions = company.getWorkingDatesForInterval(date.getTime(), historicalPoints);

        testsForPredictions = testStore.getCovidTestsInInterval(workDaysForPredictions.get(0), workDaysForPredictions.get(workDaysForPredictions.size()-1 ));


        double[] meanAgesHP = testStore.getDailyMeanAgeOfCovidTestsForInterval(workDaysForPredictions, testsForPredictions);
        double[] performedHP = testStore.getDailyNumberOfCovidTestsInInterval(workDaysForPredictions, testsForPredictions);

        return new EstimationDataSet(meanAgesHP, performedHP);
    }


    public String getSimpleLinearRegressionReport(Date referenceDate, Date dataSetStartDate, Date dataSetEndDate,
                                                int historicalPoints, int independentVariableID,
                                                double significanceLevel, double confidenceLevel)
    {
        ModelDataSet modelDataSet = setModelData(dataSetStartDate, dataSetEndDate);
        EstimationDataSet estimationDataSet = setEstimationData(referenceDate, historicalPoints);
        LinearRegressionInputDTO linearRegressionInputDTO = new LinearRegressionInputDTO(modelDataSet, estimationDataSet, confidenceLevel, significanceLevel, significanceLevel);
        linearRegressionInputDTO.setXVariable(independentVariableID);
        return createReport(linearRegressionInputDTO);
    }

    public String getMultipleLinearRegressionReport(Date referenceDate, Date dataSetStartDate, Date dataSetEndDate,
                                                  int historicalPoints, double significanceLevel, double confidenceLevel)
    {
        ModelDataSet modelDataSet = setModelData(dataSetStartDate, dataSetEndDate);
        EstimationDataSet estimationDataSet = setEstimationData(referenceDate, historicalPoints);
        LinearRegressionInputDTO linearRegressionInputDTO = new LinearRegressionInputDTO(modelDataSet, estimationDataSet, confidenceLevel, significanceLevel, significanceLevel);
        return createReport(linearRegressionInputDTO);
    }

    public String createReport(LinearRegressionInputDTO linearRegressionInputDTO)
    {
        double[] observedValues = testStore.getDailyNumberOfPositiveCovidTestsForInterval(workDaysForPredictions, testsForPredictions);
        LinearRegressionOutputDTO linearRegressionOutputDTO = linearRegression.getLinearRegression(linearRegressionInputDTO);
        return Covid19NHSReport.createCovidReport(linearRegressionOutputDTO, workDaysForPredictions, observedValues);
    }

    public void sendNHSReport(String report)
    {
        Report2NHS.writeUsingFileWriter(report);
    }
}
