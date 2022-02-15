package app.domain.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class TestParameterResult implements Serializable {
    private final Double value;
    private final String metric;
    private final ReferenceValue referenceValue;
    private final Date createdAt;

    /**
     * Constructor with the attributes: value, metric, Reference Value object.
     *
     * @param value Result value.
     * @param metric Result metric.
     * @param referenceValue Reference value object.
     */
    public TestParameterResult(Double value, String metric, ReferenceValue referenceValue)
    {
        this.value = value;
        this.metric = metric;
        this.referenceValue = referenceValue;
        this.createdAt = new Date();
    }

    /**
     * Method to return the result value.
     *
     * @return Test parameter result value.
     */
    public Double getValue()
    {
        return value;
    }

    /**
     * Method to return the result metric.
     *
     * @return Test parameter result metric.
     */
    public String getMetric()
    {
        return metric;
    }

    /**
     * Method to return the test parameter reference values.
     *
     * @return Test parameter reference values.
     */
    public ReferenceValue getReferenceValue()
    {
        return referenceValue;
    }

    /**
     * Method to return the result creation date.
     *
     * @return Test parameter result creation date.
     */
    public Date getCreatedAt()
    {
        return (Date) createdAt.clone();
    }

    /**
     * Method to return a string containing the test parameter result info.
     *
     * @return String containing the test parameter result info.
     */
    @Override
    public String toString()
    {
        return String.format("Result: %.2f %s%nReference Values: %s",
                value, metric, referenceValue.toString());
    }
}
