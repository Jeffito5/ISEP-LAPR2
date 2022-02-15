package app.domain.model.algorithms.linearRegression;

import app.ui.console.utils.MathUtils;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class SimpleLinearRegression {
    /**
     * Intercept.
     */
    private final double intercept;

    /**
     * Slope.
     */
    private final double slope;

    /**
     * R^2.
     */
    private final double r2;

    /**
     * Adjusted R^2.
     */
    private final double adjustedR2;

    /**
     * Standard error.
     */
    private final double svar0;
    private final double svar1;

    /**
     * Number of observations.
     */
    private final int n;

    /**
     * Variance.
     */
    private final double var;

    /**
     * x * x̄ value.
     */
    private double xxbar;

    /**
     * x̄ value.
     */
    private final double xbar;

    /**
     * Variation.
     */
    private double sr;
    private double se;

    /**
     * Performs a linear regression on the data points (y[i], x[i]).
     *
     * @param  x the values of the predictor variable
     * @param  y the corresponding values of the response variable
     * @throws IllegalArgumentException if the lengths of the two arrays are not equal
     */
    public SimpleLinearRegression(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("array lengths are not equal");
        }
        n = x.length;

        if (n <= 2)
        {
            throw new IllegalArgumentException("More observations needed.");
        }
        
        // first pass
        double sumx = 0.0, sumy = 0.0, sumx2 = 0.0;
        for (int i = 0; i < n; i++) {
            sumx  += x[i];
            sumx2 += x[i]*x[i];
            sumy  += y[i];
        }
        xbar = sumx / n;
        double ybar = sumy / n;

        // second pass: compute summary statistics
        xxbar = 0.0;
        double yybar = 0.0, xybar = 0.0;
        for (int i = 0; i < n; i++) {
            xxbar += (x[i] - xbar) * (x[i] - xbar);
            yybar += (y[i] - ybar) * (y[i] - ybar);
            xybar += (x[i] - xbar) * (y[i] - ybar);
        }
        slope  = xybar / xxbar;
        intercept = ybar - slope * xbar;

        // more statistical analysis
        double rss = 0.0;      // residual sum of squares
        double ssr = 0.0;      // regression sum of squares
        for (int i = 0; i < n; i++) {
            double fit = slope*x[i] + intercept;
            rss += (fit - y[i]) * (fit - y[i]);
            ssr += (fit - ybar) * (fit - ybar);
        }

        int degreesOfFreedom = n-2;
        r2    = ssr / yybar;
        double svar  = rss / degreesOfFreedom;
        svar1 = svar / xxbar;
        svar0 = svar/n + xbar*xbar*svar1;

        // R2 adjusted
        adjustedR2 = 1 - ((double) (n - 1) / (n - 2)) * (1 - r2);

        // SR - Explained Variation
        sr = 0;
        for (int i = 0; i < n; i++)
        {
            sr += Math.pow(predict(x[i]) - ybar, 2);
        }

        // SE - Unexplained Variation
        se = 0;
        for (int i = 0; i < n; i++)
        {
            se += Math.pow(y[i] - predict(x[i]), 2);
        }

        // Variance
        var = Math.sqrt( se / (n - 2) );
    }

    /**
     * Returns the y-intercept alpha of the best of the best-fit line y = alpha + beta * x.
     *
     * @return the y-intercept alpha of the best-fit line y = alpha + beta * x
     */
    public double intercept() {
        return intercept;
    }

    /**
     * Returns the slope beta of the best of the best-fit line y = alpha + beta * x.
     *
     * @return the slope beta of the best-fit line y = alpha + beta * x
     */
    public double slope() {
        return slope;
    }

    /**
     * Returns the coefficient of determination R^2.
     *
     * @return the coefficient of determination R^2,
     *         which is a real number between 0 and 1
     */
    public double R2() {
        return r2;
    }

    /**
     * Returns the standard error of the estimate for the intercept.
     *
     * @return the standard error of the estimate for the intercept
     */
    public double interceptStdErr() {
        return Math.sqrt(svar0);
    }

    /**
     * Returns the standard error of the estimate for the slope.
     *
     * @return the standard error of the estimate for the slope
     */
    public double slopeStdErr() {
        return Math.sqrt(svar1);
    }

    public double getAdjustedR2(){
        return adjustedR2;
    }

    /**
     * Returns the expected response y given the value of the predictor
     * variable x.
     *
     * @param  x the value of the predictor variable
     * @return the expected response y given the value of the predictor
     *         variable x
     */
    public double predict(double x) {
        return slope*x + intercept;
    }

    /**
     * Returns the confidence interval for a prediction.
     *
     * @param x0 X value.
     * @param alpha Alpha.
     *
     * @return String containing the confidence interval for prediction.
     */
    public String confidenceIntervalForPrediction (double x0, double alpha)
    {
        // Square root of (1 + 1/n + ((x0-xbar)^2 / Sxx))
        double auxCalc = Math.sqrt(1 + (double) 1 / n + (Math.pow(x0 - xbar, 2)) / xxbar);

        // T-student critical value
        double tStudent = MathUtils.tStudentTable(alpha, n - 2);

        // Δ = tStudent * Variance * aux
        double error = tStudent * var * auxCalc;

        // Estimated Value
        double estimatedValue = predict(x0);

        // Limits
        double lowerLimit = estimatedValue - error;
        double upperLimit = estimatedValue + error;

        return String.format("IC(%.2f, %.2f) = ]%.4f; %.4f[", 1 - alpha, x0, lowerLimit, upperLimit);
    }

    /**
     * Returns the confidence interval for the intercept.
     *
     * @param alpha Alpha.
     *
     * @return String containing the confidence interval for the intercept.
     */
    public String confidenceIntervalForIntercept (double alpha)
    {
        // Square Root of 1/n + xBar^2 / Sxx
        double auxCalc = Math.sqrt((double) 1 / n + (xbar * xbar) / xxbar);

        // T-student critical value
        double tStudent = MathUtils.tStudentTable(alpha, n - 2);

        // Δ = tStudent * Variance * aux
        double error = tStudent * auxCalc;

        // Limits
        double lowerLimit = intercept - error;
        double upperLimit = intercept + error;

        return String.format("IC(%.2f) = ]%.4f; %.4f[", 1 - alpha, lowerLimit, upperLimit);
    }

    /**
     * Returns the confidence interval for the slope.
     *
     * @param alpha Alpha.
     *
     * @return String containing the confidence interval for the slope.
     */
    public String confidenceIntervalForSlope (double alpha)
    {
        // Square Root of 1 / Sxx
        double auxCalc = Math.sqrt(1 / xxbar);

        // T-student critical value
        double tStudent = MathUtils.tStudentTable(alpha, n - 2);

        // Δ = tStudent * Variance * aux
        double error = tStudent * auxCalc;

        // Limits
        double lowerLimit = slope - error;
        double upperLimit = slope + error;

        return String.format("IC(%.2f) = ]%.4f; %.4f[", 1 - alpha, lowerLimit, upperLimit);
    }

    /**
     * Returns the hypothesis test for the intercept.
     *
     * @param a0 test value.
     * @param alpha Alpha.
     *
     * @return String containing the hypothesis test for the intercept.
     */
    public String hypothesisTestForIntercept (double a0, double alpha)
    {
        // T-student critical value
        double tStudent = MathUtils.tStudentTable(alpha, n - 2);

        // Auxiliary calc: Square Root of 1/n + xBar^2 / Sxx
        double auxCalc = Math.sqrt((double) 1 / n + (xbar * xbar) / xxbar);

        // Observed statistical value
        double ta = (intercept - a0) / (var * auxCalc);

        // Conclusion
        if (Math.abs(ta) > tStudent)
        {
            return String.format("H0 rejected. With an alpha of %.2f, " +
                    "there is enough statistical evidence to " +
                    "conclude that the intercept != %.2f", alpha, a0);
        }
        else
        {
            return String.format("H0 not rejected. With an alpha of %.2f, " +
                    "there is enough statistical evidence to " +
                    "conclude that the intercept = %.2f", alpha, a0);
        }
    }

    /**
     * Returns the hypothesis test for the slope.
     *
     * @param b0 test value.
     * @param alpha Alpha.
     *
     * @return String containing the hypothesis test for the slope.
     */
    public String hypothesisTestForSlope (double b0, double alpha)
    {
        // T-student critical value
        double tStudent = MathUtils.tStudentTable(alpha, n - 2);

        // Observed statistical value
        double tb = (slope - b0) / (var / Math.sqrt(xxbar));

        // Conclusion
        if (Math.abs(tb) > tStudent)
        {
            return String.format("H0 rejected. With an alpha of %.2f, " +
                    "there is enough statistical evidence to " +
                    "conclude that the slope != %.2f", alpha, b0);
        }
        else
        {
            return String.format("H0 not rejected. With an alpha of %.2f, " +
                    "there is enough statistical evidence to " +
                    "conclude that the slope = %.2f", alpha, b0);
        }
    }

    /**
     * Returns the significance model Anova for a given alpha.
     *
     * @param alpha Alpha.
     *
     * @return String containing the significance model.
     */
    public String anova (double alpha){
        // Regression
        double regressionDOF = 1;
        double msr = sr / regressionDOF;

        // Error
        double errorDOF = n - 2;
        double mse = se / errorDOF;

        // Total
        double totalDOF = n - 1;
        double st = sr + se;

        // Observed statistic
        double f0 = msr/mse;

        // Fisher-Snedecor Value
        double fisherSnedecor = MathUtils.fisherSnedecorTable(alpha, regressionDOF, errorDOF);

        // Anova Table
        String anovaTable = MathUtils.anovaTableBuilder(sr, msr,
                f0, se, n, mse, st, alpha, fisherSnedecor);

        // Conclusion
        if (f0 > fisherSnedecor)
        {
            return anovaTable + String.format("H0 rejected. With an alpha of %.2f%%, " +
                    "there is enough statistical evidence %n" +
                    "to conclude that the regression model is significant.", alpha);
        }
        else
        {
            return anovaTable + String.format("H0 not rejected. With an alpha of %.2f%%, " +
                    "there is enough statistical evidence to %n" +
                    "conclude that the presented linear regression model" +
                    "is not significant and should not be used.", alpha);
        }
    }

    /**
     * Returns a string representation of the simple linear regression model.
     *
     * @return a string representation of the simple linear regression model,
     *         including the best-fit line and the coefficient of determination
     *         R^2
     */
    public String toString() {
        String s = "Equation:\n" +
                String.format("Y^ = %.2f n + %.2f", slope(), intercept()) + "\n\n" +
                "Other Stats:\n" +
                "R^2 = " + String.format("%.3f", R2()) + "\n" +
                "R2 Adjusted = " + String.format("%.3f", adjustedR2) + "\n" +
                "R = " + String.format("%.3f", Math.sqrt(R2()));
        return s;
    }
}
