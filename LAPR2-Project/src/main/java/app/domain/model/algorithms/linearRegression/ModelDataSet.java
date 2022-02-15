package app.domain.model.algorithms.linearRegression;

public class ModelDataSet {

    private double[] meanAges;
    private double[] performedCovidTests;
    private double[] positiveCovidTests;

    public ModelDataSet(double[] meanAges, double[] performedCovidTests, double[] positiveCovidTests)
    {
        setMeanAges(meanAges);
        setPerformedCovidTests(performedCovidTests);
        setPositiveCovidTests(positiveCovidTests);
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

    public double[] getPerformedCovidTests() {
        return performedCovidTests;
    }

    public void setPerformedCovidTests(double[] performedCovidTests) {
        if (performedCovidTests == null)
        {
            throw new IllegalArgumentException("Invalid Array");
        }
        this.performedCovidTests = performedCovidTests;
    }

    public double[] getPositiveCovidTests() {
        return positiveCovidTests;
    }

    public void setPositiveCovidTests(double[] positiveCovidTests) {
        if (positiveCovidTests == null)
        {
            throw new IllegalArgumentException("Invalid Array");
        }
        this.positiveCovidTests = positiveCovidTests;
    }
}
