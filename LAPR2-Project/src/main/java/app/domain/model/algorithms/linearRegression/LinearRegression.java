package app.domain.model.algorithms.linearRegression;

import app.mappers.dto.LinearRegressionInputDTO;
import app.mappers.dto.LinearRegressionOutputDTO;
import java.util.List;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public interface LinearRegression {

    LinearRegressionOutputDTO getLinearRegression(LinearRegressionInputDTO linearRegressionInputDTO);
    /**
     * Constructs an instance of a Linear Regression model
     * model, receiving two independent X variables (mean ages, performed covid tests)
     * and a dependent Y variable (positive covid tests) as a data set.
     *
     * It then measures the R2 for each X variable and sets the model as the best one.
     *
     * @param modelDataSet Data Set with x and y variables.
     */
    void setLinearRegressionModel (ModelDataSet modelDataSet);


    /**
     * Method that returns the Linear Regression's prediction
     * for given x variable values.
     *
     * @param meanAge client's mean age.
     * @param numberCovidTests number of performed covid tests.
     *
     * @return prediction for the Y variable.
     */
    double predict (double meanAge, double numberCovidTests);

    /**
     * Method that returns the Linear Regression's prediction
     * for given arrays of x variable values.
     *
     * @param meanAges client's mean ages.
     * @param performedCovidTests number of performed covid tests.
     *
     * @return array with corresponding predictions for the Y variable.
     */
    double[] predict (double[] meanAges,
                      double[] performedCovidTests);

    /**
     * Method that returns the Linear Regression's hypothesis tests,
     * receiving an alpha.
     *
     * @param alpha Hypothesis test's alpha.
     *
     * @return String containing the hypothesis test results.
     */
    String getHypothesisTests(double alpha);

    /**
     * Method that returns the Linear Regression's Significance model.
     *
     * @param alpha Significance model's alpha.
     *
     * @return String containing the significance model results.
     */
    String getSignificanceModel(double alpha);

    /**
     * Method that returns the Linear Regression's confidence interval
     * for predictions.
     *
     * @param alpha Confidence interval alpha.
     * @param performedTests number of performed covid tests.
     * @param meanAges client's mean age.
     *
     * @return String containing the confidence interval for predictions.
     */
    List<String> getConfidenceIntervals (double[] meanAges, double[] performedTests, double alpha);
}
