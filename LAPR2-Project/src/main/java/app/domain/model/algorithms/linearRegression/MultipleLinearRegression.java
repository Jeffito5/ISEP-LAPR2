package app.domain.model.algorithms.linearRegression;
import app.ui.console.utils.MathUtils;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class MultipleLinearRegression {

    /**
     * Individual regression coefficients.
     */
    private final double betaZero;
    private final double betaOne;
    private final double betaTwo;

    /**
     * R2
     */
    private final double r2;
    private final double r2Adjusted;

    /**
     * Regression variance.
     */
    private final double sqr;
    private final double sqe;
    private final double sqt;

    /**
     * Variance.
     */
    private final double var;

    /**
     * Number of observations.
     */
    private final int n;

    /**
     * (X * X^T)^-1 matrix.
     */
    private final double[][] inverseXTX;

    /**
     * Regression coefficients.
     */
    private final double[] regressionCoefficients;

    /**
     * Constructs the Multiple Linear Regression model.
     *
     * @param x Independent X variables.
     * @param y Dependent Y variable.
     * @throws IllegalArgumentException if the lengths of the two arrays are not equal
     * or if the number of observations is not greater than the number of regression coefficients.
     */
    public MultipleLinearRegression(double[][] x, double[] y)
    {
        if (x.length != y.length)
        {
            throw new IllegalArgumentException("Array lengths are not equal");
        }

        // Transposed X matrix
        double[][] xT = MathUtils.transposeMatrix(x);

        // Multiplication of the transposed X matrix with original matrix
        double[][] xTX = MathUtils.multiplyMatrices(xT, x);

        // Inverted XTX Matrix
        RealMatrix auxXTX = new Array2DRowRealMatrix(xTX);
        RealMatrix inverseXTXAux = MatrixUtils.inverse(auxXTX);
        inverseXTX = inverseXTXAux.getData();

        // Multiplication of the transposed X matrix with Y vector
        double[] xTY = MathUtils.multiplyMatrixToArray(xT, y);


        // Regression Coefficients
        regressionCoefficients = MathUtils.multiplyMatrixToArray(inverseXTX, xTY);

        betaZero = regressionCoefficients[0];
        betaOne = regressionCoefficients[1];
        betaTwo = regressionCoefficients[2];

        // Transposed Regression Coefficients
        double[][] rCT = MathUtils.transposeArray(regressionCoefficients);

        // Y Mean
        double yMean = findYAverage(y);

        // n
        n = y.length;

        // "k + 1"
        int betas = regressionCoefficients.length;

        if (n <= betas)
        {
            throw new IllegalArgumentException("More observations needed.");
        }

        // SQT = YT*Y - n*yMean^2
        double[][] yT = MathUtils.transposeArray(y);
        double[] ytyArray = MathUtils.multiplyMatrixToArray(yT, y);
        double yTy = ytyArray[0];
        sqt = yTy - n * Math.pow(yMean, 2);

        // BT * XT * Y
        double btXtY = MathUtils.multiplyMatrixToArray(rCT, xTY)[0];

        // SQR
        sqr = btXtY - n * Math.pow(yMean, 2);

        // SQE
        sqe = yTy - btXtY;

        // R2
        r2 = sqr/sqt;

        // Adjusted R2
        r2Adjusted = 1 - ((sqe / (n - betas)) / (sqt / (n - 1)));

        // Variance
        var = sqe / (n - betas);
    }

    /**
     * Returns the MLR model R^2 value.
     *
     * @return R^2 value
     */
    public double getR2 ()
    {
        return r2;
    }

    /**
     * Returns the MLR model adjusted R^2 value.
     *
     * @return Adjusted R^2 value
     */
    public double getR2Adjusted ()
    {
        return r2Adjusted;
    }

    /**
     * Returns the mean of the dependent Y variable.
     *
     * @param array Y vector.
     *
     * @return Mean of dependent Y variable.
     */
    private double findYAverage(double[] array)
    {
        double sum = 0;
        for (double value : array) {
            sum += value;
        }
        return sum / array.length;
    }

    /**
     * Returns the prediction Y value for given X values.
     *
     * @param x1 X1 value.
     * @param x2 X2 value.
     *
     * @return Mean of dependent Y variable.
     */
    public double predict (double x1, double x2)
    {
        return betaZero + betaOne * x1 + betaTwo * x2;
    }

    /**
     * Returns the confidence interval for a prediction.
     *
     * @param x1 X1 value.
     * @param x2 X2 value.
     * @param alpha Alpha.
     *
     * @return String containing the confidence interval.
     */
    public String confidenceIntervalForPrediction (double x1, double x2, double alpha)
    {
        double[] x0 = {1, x1, x2};
        double[][] x0transposed = MathUtils.transposeArray(x0);

        // Auxiliary calculation
        double x0tCx0 = MathUtils.multiplyMatrixToArray(MathUtils.multiplyMatrices(x0transposed, inverseXTX), x0)[0];
        //System.out.printf("%nx0T * C * x0%n%.4f%n", x0tCx0);

        // T-student critical value
        double tStudent = MathUtils.tStudentTable(alpha, n - 3); // e se n <= 3?
        //System.out.printf("%nT-student%n%.4f%n", tStudent);

        // Δ = tStudent * SquareRoot(variance * (1 + x0T * C * x0))
        double error = tStudent * Math.sqrt(var * (1 + x0tCx0));
        //System.out.printf("%nΔ%n%.4f%n", error);

        // Estimated Value
        double estimatedValue = predict(x1, x2);
        //System.out.printf("%nEstimated value%n%.4f%n", estimatedValue);

        // Limits
        double lowerLimit = estimatedValue - error;
        double upperLimit = estimatedValue + error;
        return String.format("IC(%.2f) = ]%.4f; %.4f[", 1 - alpha, lowerLimit, upperLimit);
    }

    /**
     * Returns the hypothesis test for a given Beta.
     *
     * @param beta Beta.
     * @param alpha Alpha.
     *
     * @return String containing the hypothesis test.
     */
    public String hypothesisTestForBeta (int beta, double alpha)
    {
        if (beta > regressionCoefficients.length - 1 || beta < 0)
        {
            throw new IllegalArgumentException("Invalid regression coefficient");
        }

        // C[2][2]
        double cjj = inverseXTX[beta][beta];
        //System.out.printf("%nCjj%n%.4f%n", cjj);

        // Regression Coefficient
        double regressionCoefficient = regressionCoefficients[beta];

        // Observed test statistic
        double t0 = regressionCoefficient / Math.sqrt(var * cjj);
        //System.out.printf("%nT0%n%.4f%n", t0);

        // T-student critical value
        double tStudent = MathUtils.tStudentTable(alpha, n - regressionCoefficients.length);
        //System.out.printf("%nT-Student%n%.4f%n", tStudent);

        // Conclusion
        if (Math.abs(t0) > tStudent)
        {
            return String.format("H0 rejected. With an alpha of %.2f, " +
                    "there is enough statistical evidence to " +
                    "conclude that β%d != 0", alpha, beta);
        }
        else
        {
            return String.format("H0 not rejected. With an alpha of %.2f, " +
                    "there is enough statistical evidence to " +
                    "conclude that β%d = 0", alpha, beta);
        }
    }

    /**
     * Returns the significance model for a given alpha.
     *
     * @param alpha Alpha.
     *
     * @return String containing the significance model.
     */
    public String significanceModel (double alpha)
    {
        // Regression (sqr = regression minimum square sum)
        int regressionDOF = regressionCoefficients.length - 1;
        double mqr = sqr / regressionDOF;

        // Error (sqe = error minimum square sum)
        int errorDOF = n - regressionCoefficients.length;
        double mqe = sqe / errorDOF;

        // Total
        int totalDOF = n - 1;

        // Observed statistic
        double f0 = mqr / mqe;
        //System.out.printf("%nF0%n%.4f%n", f0);

        // Fisher-Snedecor Value
        double fisherSnedecor = MathUtils.fisherSnedecorTable(alpha, regressionDOF, errorDOF);
        //System.out.printf("%nFisher-Snedecor%n%.4f%n", fisherSnedecor);

        String significanceTable = MathUtils.mlrSignificanceTableBuilder(sqr, regressionCoefficients.length - 1,
                mqr, f0, sqe, n, mqe, sqt, alpha, fisherSnedecor);

        // Conclusion
        if (f0 > fisherSnedecor)
        {
            return significanceTable + String.format("H0 rejected. With an alpha of %.2f%%, " +
                    "there is enough statistical evidence %n" +
                    "to conclude that there is at least an X variable that significantly%n" +
                    "contributes to the variation of the Y variable. %n" +
                    "Thus, the regression model is significant.", alpha);
        }
        else
        {
            return significanceTable + String.format("H0 not rejected. With an alpha of %.2f%%, " +
                    "there is enough statistical evidence to %n" +
                    "conclude that the presented linear regression model" +
                    "is not significant and should not be used.", alpha);
        }
    }

    /**
     * Returns the regression model stats: equation, R2, adjusted R2, R.
     *
     * @return String containing the regression model stats.
     */
    @Override
    public String toString() {
        return String.format("Y = %.2f + %.2fX1 + %.2fX2", betaZero, betaOne, betaTwo) + "\n" +
                "R^2 = " + String.format("%.3f", r2) + "\n" +
                "R2 Adjusted = " + String.format("%.3f", r2Adjusted) + "\n" +
                "R = " + String.format("%.3f", Math.sqrt(r2));
    }
}
