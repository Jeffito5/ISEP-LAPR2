package app.domain.model.algorithms.linearRegression.adapters;

import app.domain.model.algorithms.linearRegression.EstimationDataSet;
import app.domain.model.algorithms.linearRegression.ModelDataSet;
import app.domain.model.algorithms.linearRegression.LinearRegression;
import app.mappers.dto.LinearRegressionInputDTO;
import app.mappers.dto.LinearRegressionOutputDTO;
import app.ui.console.utils.MathUtils;
import app.domain.model.algorithms.linearRegression.MultipleLinearRegression;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class MultipleLinearRegressionAdapter implements LinearRegression {

    /**
     * Multiple Linear Regression model.
     */
    private MultipleLinearRegression multipleLinearRegression;

    /**
     * Method to retrieve statistics from a given data set.
     *
     * @param linearRegressionInputDTO Linear Regression Input DTO.
     */
    @Override
    public LinearRegressionOutputDTO getLinearRegression(LinearRegressionInputDTO linearRegressionInputDTO)
    {
        // Data set
        ModelDataSet modelDataSet = linearRegressionInputDTO.getModelDataSet();

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

        // Predictions and confidence intervals
        double[] predictions = predict(toEstimateMeanAges, toEstimatePerformedTests);
        List<String> confidenceIntervals = getConfidenceIntervals(toEstimateMeanAges,
                toEstimatePerformedTests, confidenceIntervalAlpha);
        String hypothesisTest = getHypothesisTests(hypothesisTestAlpha);
        String significanceModel = getSignificanceModel(significanceModelAlpha);

        // Stats
        String stats = multipleLinearRegression.toString();

        // Outputting
        return new LinearRegressionOutputDTO(stats, significanceModel, hypothesisTest,
                confidenceIntervalAlpha, predictions, confidenceIntervals);
    }

    /**
     * Constructs an instance of the Multiple Linear Regression
     * model, receiving two independent X variables (mean ages, performed covid tests)
     * and a dependent Y variable (positive covid tests) as a data set.
     *
     * @param modelDataSet Data Set with x and y variables.
     */
    @Override
    public void setLinearRegressionModel(ModelDataSet modelDataSet)
    {
        double[] meanAges = modelDataSet.getMeanAges();
        double[] performedCovidTests = modelDataSet.getPerformedCovidTests();
        double[] positiveCovidTests = modelDataSet.getPositiveCovidTests();

        double[][] x = MathUtils.getXMatrix(meanAges, performedCovidTests);
        multipleLinearRegression = new MultipleLinearRegression(x,positiveCovidTests);
    }


    /**
     * Method that returns the Linear Regression's prediction
     * for given x variable values.
     *
     * @param meanAge client's mean age.
     * @param numberCovidTests number of performed covid tests.
     *
     * @return prediction for the Y variable.
     */
    @Override
    public double predict(double meanAge, double numberCovidTests)
    {
        return multipleLinearRegression.predict(meanAge, numberCovidTests);
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

        for (int i = 0; i < meanAges.length; i++ )
        {
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
        return "H0: B0 = 0  |  H1: B0 != 0\n\n" + multipleLinearRegression.hypothesisTestForBeta(0, alpha);
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
        return "H0: B0 = 0  |  H1: B0 != 0\n\n" + multipleLinearRegression.significanceModel(alpha);
    }

    /**
     * Method that returns the Linear Regression's confidence interval
     * for a given prediction and alpha.
     *
     * @param alpha Confidence interval alpha.
     * @param numberCovidTests number of performed covid tests.
     * @param meanAge client's mean age.
     *
     * @return String containing the confidence interval for a certain prediction.
     */
    private String getConfidenceInterval(double meanAge, double numberCovidTests,
                                        double alpha)
    {
        return multipleLinearRegression.confidenceIntervalForPrediction(meanAge, numberCovidTests, alpha);
    }

    @Override
    public List<String> getConfidenceIntervals (double[] meanAges, double[] performed,
                                                 double alpha)
    {
        List<String> confidenceIntervals = new ArrayList<>();
        for (int i = 0; i < meanAges.length; i++)
        {
            confidenceIntervals.add(getConfidenceInterval(meanAges[i], performed[i], alpha));
        }
        return confidenceIntervals;
    }
}
