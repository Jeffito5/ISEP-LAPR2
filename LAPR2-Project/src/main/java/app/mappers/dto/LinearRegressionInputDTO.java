package app.mappers.dto;

import app.domain.model.algorithms.linearRegression.EstimationDataSet;
import app.domain.model.algorithms.linearRegression.ModelDataSet;

public class LinearRegressionInputDTO {

    private ModelDataSet modelDataSet;
    private EstimationDataSet estimationDataSet;
    private double confidenceIntervalAlpha;
    private double hypothesisTestAlpha;
    private double significanceModelAlpha;
    private static final int X_VARIABLE_BY_DEFAULT = -1;
    private int xVariableId;

    public LinearRegressionInputDTO(ModelDataSet modelDataSet, EstimationDataSet estimationDataSet,
                                    double confidenceIntervalAlpha, double hypothesisTestAlpha,
                                    double significanceModelAlpha)
    {
        setModelDataSet(modelDataSet);
        setEstimationDataSet(estimationDataSet);
        setConfidenceIntervalAlpha(confidenceIntervalAlpha);
        setHypothesisTestAlpha(hypothesisTestAlpha);
        setSignificanceModelAlpha(significanceModelAlpha);
        xVariableId = X_VARIABLE_BY_DEFAULT;
    }

    public ModelDataSet getModelDataSet() {
        return modelDataSet;
    }

    public void setModelDataSet(ModelDataSet modelDataSet) {
        if (modelDataSet == null)
        {
            throw new IllegalArgumentException("Invalid Model Data Set");
        }
        this.modelDataSet = modelDataSet;
    }

    public EstimationDataSet getEstimationDataSet() {
        return estimationDataSet;
    }

    public void setEstimationDataSet(EstimationDataSet estimationDataSet) {
        if (estimationDataSet == null)
        {
            throw new IllegalArgumentException("Invalid Estimation Data Set");
        }
        this.estimationDataSet = estimationDataSet;
    }

    public double getConfidenceIntervalAlpha() {
        return confidenceIntervalAlpha;
    }

    public void setConfidenceIntervalAlpha(double confidenceIntervalAlpha) {
        this.confidenceIntervalAlpha = confidenceIntervalAlpha;
    }

    public double getHypothesisTestAlpha() {
        return hypothesisTestAlpha;
    }

    public void setHypothesisTestAlpha(double hypothesisTestAlpha) {
        this.hypothesisTestAlpha = hypothesisTestAlpha;
    }

    public double getSignificanceModelAlpha() {
        return significanceModelAlpha;
    }

    public void setSignificanceModelAlpha(double significanceModelAlpha) {
        this.significanceModelAlpha = significanceModelAlpha;
    }

    public void setXVariable(int xVariableId)
    {
        this.xVariableId = xVariableId;
    }

    public int getXVariable ()
    {
        return xVariableId;
    }
}
