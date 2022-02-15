package app.domain.tasks;

import app.domain.model.Company;
import app.domain.model.Configurations;
import app.ui.console.utils.Covid19NHSReport;
import app.domain.model.Test;
import app.domain.model.algorithms.linearRegression.ModelDataSet;
import app.domain.model.algorithms.linearRegression.EstimationDataSet;
import app.domain.model.algorithms.linearRegression.LinearRegression;
import app.domain.store.TestStore;
import app.mappers.dto.LinearRegressionInputDTO;
import app.mappers.dto.LinearRegressionOutputDTO;
import app.ui.console.utils.NHSReportLogEvent;
import app.ui.console.utils.Utils;
import com.nhs.report.Report2NHS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class SendNhsReportTask extends TimerTask{

    /**
     * Company.
     */
    private final Company company;

    /**
     * Test store.
     */
    private final TestStore testStore;

    /**
     * Linear Regression model.
     */
    private LinearRegression linearRegression;

    /**
     * Configurations/ Properties.
     */
    private final Configurations configurations;

    /**
     * Data set start date.
     */
    private Date startDate;

    /**
     * Data set end date.
     */
    private Date endDate;

    /**
     * Historical points for linear regression predictions.
     */
    private int historicalPoints;

    /**
     * Hypothesis Test Alpha.
     */
    private double hypothesisTestAlpha;

    /**
     * Significance Model Alpha.
     */
    private double significanceModelAlpha;

    /**
     * Confidence Interval Alpha.
     */
    private double confidenceAlpha;

    /**
     * Covid-19 Report Instance.
     */
    private int instance;

    /**
     * Constructs an instance of the Send NHS Report class,
     * receiving the company instance.
     */
    public SendNhsReportTask(Company company){
        this.company = company;
        this.testStore = company.getTestStore();
        configurations = new Configurations();
    }

    /**
     * Task run
     */
    @Override
    public void run() {

        try
        {
            getConfigurations();
        }
        catch (ParseException | NumberFormatException ex)
        {
            Logger.getLogger(SendNhsReportTask.class.getName()).log(Level.SEVERE, null, ex);

            // Cancel task
            cancel();
        }

        // Getting the Linear Regression Class (Java Reflection)
        try
        {
            linearRegression = getLinearRegressionModel();
        }
        catch (IllegalAccessException | InstantiationException | ClassNotFoundException ex)
        {
            Logger.getLogger(SendNhsReportTask.class.getName()).log(Level.SEVERE, null, ex);

            // Cancel task
            cancel();
        }

        // DATA SET
        // Work days for data set
        List<Date> dataSetDays = company.getWorkingDaysForIntervals(startDate, endDate);

        // Covid Tests
        List<Test> covidTests = testStore.getCovidTestsInInterval(startDate, endDate);

        // Performed Covid Tests
        double[] performedCovidTests = testStore.getDailyNumberOfCovidTestsInInterval(dataSetDays, covidTests);

        // Positive Covid Tests
        double[] positiveCovidTests = testStore.getDailyNumberOfPositiveCovidTestsForInterval
                (dataSetDays, covidTests);

        // Mean Age of data set of Covid Test clients
        double[] meanAges = testStore.getDailyMeanAgeOfCovidTestsForInterval
                (dataSetDays, covidTests);

        ModelDataSet modelDataSet = new ModelDataSet(meanAges, performedCovidTests, positiveCovidTests);

        // Setting the Linear Regression Model
        //linearRegression.setLinearRegressionModel (modelDataSet);

        // Setting today's date for searching tests in historical points
        Calendar today = Calendar.getInstance();
        today.set(2021, Calendar.MAY, 29);
        Utils.setTimeToZero(today);

        // PREDICTIONS

        // Work Dates for predictions
        List<Date> workDaysHP = company.getWorkingDatesForInterval(today.getTime(), historicalPoints);

        List<Test> testsHP = testStore.getCovidTestsInInterval(workDaysHP.get(0), workDaysHP.get(workDaysHP.size()-1 ));


        double[] meanAgesHP = testStore.getDailyMeanAgeOfCovidTestsForInterval(workDaysHP, testsHP);
        double[] performedHP = testStore.getDailyNumberOfCovidTestsInInterval(workDaysHP, testsHP);

        EstimationDataSet estimationDataSet = new EstimationDataSet(meanAgesHP, performedHP);

        double[] actual = testStore.getDailyNumberOfPositiveCovidTestsForInterval(workDaysHP, testsHP);

        LinearRegressionInputDTO linearRegressionInputDTO = new LinearRegressionInputDTO(modelDataSet, estimationDataSet,
                confidenceAlpha, hypothesisTestAlpha, significanceModelAlpha);

        LinearRegressionOutputDTO linearRegressionOutputDTO = linearRegression.getLinearRegression(linearRegressionInputDTO);

        String report = Covid19NHSReport.createCovidReport(linearRegressionOutputDTO, workDaysHP, actual);

        Report2NHS.writeUsingFileWriter(report);

        logEvent();
    }

    /**
     * Method to get the necessary properties from the configuration file.
     * @throws ParseException if the dates were not successfully parsed.
     * @throws NumberFormatException if the historical points were not successfully read.
     */
    private void getConfigurations()
            throws ParseException, NumberFormatException
    {
        this.startDate = configurations.getRegressionStartDate();
        this.endDate = configurations.getRegressionEndDate();
        this.historicalPoints = configurations.getRegressionHistoricalPoints();
        this.confidenceAlpha = configurations.getRegressionConfidenceIntervalAlpha();
        this.hypothesisTestAlpha = configurations.getRegressionHypothesisTestAlpha();
        this.significanceModelAlpha = configurations.getRegressionSignificanceAlpha();
    }

    /**
     * Instantiates the linear regression model.
     *
     * @return Linear Regression Model class.
     * @throws IllegalAccessException if class could not be accessed.
     * @throws InstantiationException if class could not be instantiated.
     * @throws ClassNotFoundException if class could not be found.
     */
    private LinearRegression getLinearRegressionModel ()
            throws IllegalAccessException, InstantiationException, ClassNotFoundException
    {
        Class<?> oClass = Class.forName(configurations.getRegressionAlgorithm());
        return (LinearRegression) oClass.newInstance();
    }

    /**
     * Logs the event to a text file.
     */
    private void logEvent()
    {
        Date now = new Date();
        String today = new SimpleDateFormat("dd-M-yyyy hh:mm").format(now);
        NHSReportLogEvent.logEvent(instance, today);
        instance++;
    }


}
