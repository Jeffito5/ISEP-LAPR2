package app.mappers.dto;

import java.util.List;

public class LinearRegressionOutputDTO {

    private String stats;
    private String significanceModel;
    private String hypothesisTests;
    private double confidenceIntervalAlpha;
    private double[] predictedValues;
    private List<String> confidenceIntervals;

    public LinearRegressionOutputDTO(String stats, String significanceModel, String hypothesisTests,
                                     double confidenceIntervalAlpha, double[]predictedValues,
                                     List<String> confidenceIntervals)
    {
        setStats(stats);
        setSignificanceModel(significanceModel);
        setHypothesisTests(hypothesisTests);
        this.confidenceIntervalAlpha = confidenceIntervalAlpha;
        setPredictedValues(predictedValues);
        setConfidenceIntervals(confidenceIntervals);
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        if (stats == null)
        {
            throw new IllegalArgumentException("Invalid stats.");
        }
        this.stats = stats;
    }

    public String getSignificanceModel() {
        return significanceModel;
    }

    public void setSignificanceModel(String significanceModel) {
        if(significanceModel == null)
        {
            throw new IllegalArgumentException("Invalid Significance Model.");
        }
        this.significanceModel = significanceModel;
    }

    public String getHypothesisTests() {
        return hypothesisTests;
    }

    public void setHypothesisTests(String hypothesisTests) {
        this.hypothesisTests = hypothesisTests;
    }

    public double getConfidenceIntervalAlpha() {
        return confidenceIntervalAlpha;
    }

    public void setConfidenceIntervalAlpha(double confidenceIntervalAlpha) {
        this.confidenceIntervalAlpha = confidenceIntervalAlpha;
    }

    public double[] getPredictedValues() {
        return predictedValues;
    }

    public void setPredictedValues(double[] predictedValues) {
        if(predictedValues == null)
        {
            throw new IllegalArgumentException("Invalid predicted values");
        }
        this.predictedValues = predictedValues;
    }

    public List<String> getConfidenceIntervals() {
        return confidenceIntervals;
    }

    public void setConfidenceIntervals(List<String> confidenceIntervals) {
        if (confidenceIntervals == null)
        {
            throw new IllegalArgumentException("Invalid List");
        }
        this.confidenceIntervals = confidenceIntervals;
    }
}
