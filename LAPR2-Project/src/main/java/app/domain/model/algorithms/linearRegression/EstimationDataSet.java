package app.domain.model.algorithms.linearRegression;

public class EstimationDataSet {
    private double[] meanAges;
    private double[] performedTests;

    public EstimationDataSet(double[] meanAges, double[] performedTests)
    {
        setMeanAges(meanAges);
        setPerformedTests(performedTests);
    }

    public double[] getMeanAges() {
        return meanAges;
    }

    public void setMeanAges(double[] meanAges) {
        if (meanAges == null)
        {
            throw new IllegalArgumentException("Invalid Array");
        }
        this.meanAges = meanAges;
    }

    public double[] getPerformedTests() {
        return performedTests;
    }

    public void setPerformedTests(double[] performedTests) {
        if (performedTests == null)
        {
            throw new IllegalArgumentException("Invalid Array");
        }
        this.performedTests = performedTests;
    }
}
