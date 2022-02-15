package app.domain.model.algorithms.linearRegression.adapters;

import app.domain.model.algorithms.linearRegression.EstimationDataSet;
import app.domain.model.algorithms.linearRegression.ModelDataSet;
import app.domain.model.algorithms.linearRegression.LinearRegression;
import app.domain.model.algorithms.linearRegression.SimpleLinearRegression;
import app.mappers.dto.LinearRegressionInputDTO;
import app.mappers.dto.LinearRegressionOutputDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class SimpleLinearRegressionAdapter implements LinearRegression {

    /**
     * Multiple Linear Regression model.
     */
    private SimpleLinearRegression x;

    /**
     * X variable ID for filtering.
     */
    private int xVariable;

    /**
     * X0 variable ID.
     */
    private final int VARIABLE_X0 = 0;

    /**
     * X1 variable ID.
     */
    private final int VARIABLE_X1 = 1;

    /**
     * Method to retrieve statistics from a given data set.
     *
     * It measures the R2 for each X variable and sets the model as the best one.
     *
     * @param linearRegressionInputDTO Linear Regression Input DTO.
     */
    @Override
    public LinearRegressionOutputDTO getLinearRegression(LinearRegressionInputDTO linearRegressionInputDTO)
    {
        // Data set
        ModelDataSet modelDataSet = linearRegressionInputDTO.getModelDataSet();

        int xVarId = linearRegressionInputDTO.getXVariable();

        if(xVarId == -1)
        {
            setLinearRegressionModel(modelDataSet);
        } else {
            setLinearRegressionModelOneVariable(modelDataSet, xVarId);
        }

        // Setting the model
        setLinearRegressionModel(modelDataSet);

        // X independent variables
        EstimationDataSet estimationDataSet = linearRegressionInputDTO.getEstimationDataSet();
        double[] toEstimateMeanAges = estimationDataSet.getMeanAges();
        double[] toEstimatePerformedTests = estimationDataSet.getPerformedTests();

        // Alphas
        double confidenceIntervalAlpha = linearRegressionInputDTO.getConfidenceIntervalAlpha();
        double hypothesisTestAlpha = linearRegressionInputDTO.getHypothesisTestAlpha();
        double significanceModelAlpha = linearRegressionInputDTO.getSignificanceModelAlpha();

        // Predictions
        double[] predictions = predict(toEstimateMeanAges, toEstimatePerformedTests);

        // Confidence Intervals
        List<String> confidenceIntervals = getConfidenceIntervals(toEstimateMeanAges,
                toEstimatePerformedTests, confidenceIntervalAlpha);

        //Hypothesis Tests
        String hypothesisTest = getHypothesisTests(hypothesisTestAlpha);

        // Significance Model / Anova
        String significanceModel = getSignificanceModel(significanceModelAlpha);

        // Stats
        String stats = x.toString();

        // Outputting
        return new LinearRegressionOutputDTO(stats, significanceModel, hypothesisTest,
                confidenceIntervalAlpha, predictions, confidenceIntervals);
    }

    /**
     * Constructs an instance of the Simple Linear Regression
     * model, receiving two independent X variables (mean ages, performed covid tests)
     * and a dependent Y variable (positive covid tests) as a data set.
     *
     * It then measures the R2 for each X variable and sets the model as the best one.
     *
     * @param modelDataSet Data Set with x and y variables.
     */
    @Override
    public void setLinearRegressionModel(ModelDataSet modelDataSet)
    {
        double[] meanAges = modelDataSet.getMeanAges();
        double[] performedCovidTests = modelDataSet.getPerformedCovidTests();
        double[] positiveCovidTests = modelDataSet.getPositiveCovidTests();

        SimpleLinearRegression meanAgesSLR = new SimpleLinearRegression(meanAges, positiveCovidTests);
        SimpleLinearRegression performedCovidTestsSLR = new SimpleLinearRegression(performedCovidTests, positiveCovidTests);

        if (meanAgesSLR.R2() < performedCovidTestsSLR.R2())
        {
            x = performedCovidTestsSLR;
            xVariable = VARIABLE_X1;
        }
        else
        {
            x = meanAgesSLR;
            xVariable = VARIABLE_X0;
        }
    }



    /**
     * Method that returns the Linear Regression's prediction
     * for given x variable values, according to the model.
     *
     * @param meanAge client's mean age.
     * @param performedCovidTests number of performed covid tests.
     *
     * @return prediction for the Y variable.
     */
    @Override
    public double predict(double meanAge, double performedCovidTests) {
        if (xVariable == VARIABLE_X1)
        {
            return x.predict(performedCovidTests);
        }
        else if (xVariable == VARIABLE_X0)
        {
            return x.predict(meanAge);
        }
        throw new IllegalArgumentException("Could not predict.");
    }

    /**
     * Method that returns the Linear Regression's prediction
     * for given arrays of x variable values.
     *
     * @param meanAges client's mean ages.
     * @param performedCovidTests number of performed covid tests.
     *
     * @return array with corresponding predictions for the Y variable.
     */
    @Override
    public double[] predict(double[] meanAges,
                            double[] performedCovidTests)
    {
        if ( meanAges.length != performedCovidTests.length)
        {
            throw new IllegalArgumentException("Arrays lengths are not equal.");
        }

        double[] result = new double[meanAges.length];

        for (int i = 0; i < meanAges.length; i++) {
            result[i] = predict(meanAges[i], performedCovidTests[i]);
        }
        return result;
    }

    /**
     * Method that returns the Linear Regression's hypothesis tests,
     * receiving an alpha.
     *
     * @param alpha Hypothesis test's alpha.
     *
     * @return String containing the hypothesis test results.
     */
    @Override
    public String getHypothesisTests(double alpha)
    {
        String hypothesisTestForSlope = x.hypothesisTestForSlope(0, alpha);
        String hypothesisTestForIntercept = x.hypothesisTestForIntercept(0, alpha);
        return "Slope:\n\nH0 = 0  |  H1 != 0 \n\n" + hypothesisTestForSlope +
                "\nIntercept:\n\nH0 = 0  |  H1 != 0 \n\n" + hypothesisTestForIntercept;
    }

    /**
     * Method that returns the Linear Regression's Significance model.
     *
     * @param alpha Significance model's alpha.
     *
     * @return String containing the significance model results.
     */
    @Override
    public String getSignificanceModel(double alpha)
    {
        return "H0: b = 0  |  H1: b != 0\n\n" + x.anova(alpha);
    }

    /**
     * Method that returns the Linear Regression's confidence interval
     * for a given prediction and alpha.
     *
     * @param alpha Confidence interval alpha.
     * @param xVar x variable.
     *
     * @return String containing the confidence interval for a certain prediction.
     */
    private String getConfidenceInterval(double xVar,
                                        double alpha)
    {
        return x.confidenceIntervalForPrediction(xVar, alpha);
    }

    @Override
    public List<String> getConfidenceIntervals(double[] meanAges, double[] performed, double alpha) {
        List<String> confidenceIntervals = new ArrayList<>();

        if (xVariable == VARIABLE_X1)
        {
            for (int i = 0; i < meanAges.length; i++)
            {
                confidenceIntervals.add(getConfidenceInterval(performed[i], alpha));
            }
        }
        else
        {
            for (double meanAge : meanAges) {
                confidenceIntervals.add(getConfidenceInterval(meanAge, alpha));
            }
        }
        return confidenceIntervals;

    }

    public void setXVariable (int xVariable)
    {
        this.xVariable = xVariable;
    }

    public void setLinearRegressionModelOneVariable(ModelDataSet modelDataSet, int xVarId){

        double[] xVariable;

        if (xVarId == VARIABLE_X1)
        {
            xVariable = modelDataSet.getPerformedCovidTests();
        } else {
            xVariable = modelDataSet.getMeanAges();
        }

        double[] positiveCovidTests = modelDataSet.getPositiveCovidTests();

        x = new SimpleLinearRegression(xVariable, positiveCovidTests);
    }


}
