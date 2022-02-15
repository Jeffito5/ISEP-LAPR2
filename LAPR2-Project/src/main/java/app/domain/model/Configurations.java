package app.domain.model;

import app.controller.App;
import app.domain.shared.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class Configurations {

    /**
     * Properties.
     */
    private final Properties props;

    /**
     * Constructor.
     */
    public Configurations (){
        props = getProperties();
    }

    /**
     * Method that reads the properties from the configurations file.
     *
     * @return properties object.
     */
    private Properties getProperties()
    {
        Properties props = new Properties();

        // Read configured values
        try
        {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        }
        catch(IOException ex)
        {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return props;
    }

    /**
     * Returns the Sorting Algorithm property.
     *
     * @return sorting algorithm property.
     */
    public String getSortingAlgorithm(){
        return props.getProperty(Constants.PARAMS_SORTING_ALGORITHM);
    }

    /**
     * Returns the Regression Model property.
     *
     * @return regression model property.
     */
    public String getRegressionAlgorithm()
    {
        return props.getProperty(Constants.PARAMS_REGRESSION_MODEL);
    }

    /**
     * Returns the Regression Model data set Start date.
     *
     * @return Data set start date.
     */
    public Date getRegressionStartDate() throws ParseException {
        String strDate = props.getProperty(Constants.PARAMS_REGRESSION_START_DATE);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.parse(strDate);
    }

    /**
     * Returns the Regression Model data set End date.
     *
     * @return Data set end date.
     */
    public Date getRegressionEndDate() throws ParseException {
        String strDate = props.getProperty(Constants.PARAMS_REGRESSION_END_DATE);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.parse(strDate);
    }

    /**
     * Returns the number of historical points to be used for the Regression Model.
     *
     * @return historical points to be used by the Regression Model.
     */
    public int getRegressionHistoricalPoints ()
    {
        String strPoints = props.getProperty(Constants.PARAMS_REGRESSION_HISTORICAL_POINTS);
        return Integer.parseInt(strPoints);
    }

    /**
     * Returns the Regression Model hypothesis tests' alpha.
     *
     * @return Regression Model hypothesis tests' alpha.
     */
    public double getRegressionHypothesisTestAlpha()
    {
        String strAlpha = props.getProperty(Constants.PARAMS_REGRESSION_HYPOTHESIS_TESTS_ALPHA);
        return Double.parseDouble(strAlpha);
    }

    /**
     * Returns the Regression Model significance alpha.
     *
     * @return Regression Model significance alpha.
     */
    public double getRegressionSignificanceAlpha()
    {
        String strAlpha = props.getProperty(Constants.PARAMS_REGRESSION_SIGNIFICANCE_ALPHA);
        return Double.parseDouble(strAlpha);
    }

    /**
     * Returns the Regression Model confidence interval alpha.
     *
     * @return Regression Model confidence interval alpha.
     */
    public double getRegressionConfidenceIntervalAlpha()
    {
        String strAlpha = props.getProperty(Constants.PARAMS_REGRESSION_CONFIDENCE_ALPHA);
        return Double.parseDouble(strAlpha);
    }

    /**
     * Returns the Benchmark Algorithm property for performance evaluation.
     *
     * @return Benchmark algorithm for performance evaluation.
     */
    public String getBenchmark(){return props.getProperty(Constants.PARAMS_BENCHMARK);}

    /**
     * Returns the Brute force algorithm property for performance evaluation.
     *
     * @return Brute force algorithm for performance evaluation.
     */
    public String getBruteForce(){return props.getProperty(Constants.PARAMS_BRUTEFORCE);}

}
