package app.domain.model;

import java.io.Serializable;

public class ReferenceValue implements Serializable {
    private double minimumValue;
    private double maximumValue;
    private String metric;

    public ReferenceValue(double minimumValue, double maximumValue, String metric){
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
        this.metric = metric;
    }

    public double getMinimumValue() {
        return minimumValue;
    }

    public void setMinimumValue(double minimumValue) {
        this.minimumValue = minimumValue;
    }

    public double getMaximumValue() {
        return maximumValue;
    }

    public void setMaximumValue(double maximumValue) {
        this.maximumValue = maximumValue;
    }

    @Override
    public String toString() {
        return String.format("[%.2f %s - %.2f %s]",
                minimumValue, metric,
                maximumValue, metric);
    }
}
