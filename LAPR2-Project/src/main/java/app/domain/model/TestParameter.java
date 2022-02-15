package app.domain.model;

import java.io.Serializable;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class TestParameter implements Serializable {
    private TestParameterResult testParameterResult;
    private Parameter parameter;

    /**
     * Constructor with the attribute: parameter.
     *
     * @param parameter Parameter object.
     */
    TestParameter(Parameter parameter)
    {
        this.parameter = parameter;
    }

    /**
     * Method to return the Parameter object.
     *
     * @return parameter object.
     */
    public Parameter getParameter()
    {
        return parameter;
    }

    /**
     * Method to return the Test Parameter result.
     *
     * @return test parameter result object.
     */
    public TestParameterResult getTestParameterResult()
    {
        return testParameterResult;
    }

    /**
     * Method to modify the Parameter object
     *
     * @param parameter parameter object.
     */
    public void setParameter(Parameter parameter)
    {
        this.parameter = parameter;
    }

    /**
     * Method to add result to the test parameter.
     *
     * @param result Test parameter result value.
     * @param metric Test parameter result metric.
     * @param refValue Reference value object.
     */
    public void addResult(Double result, String metric, ReferenceValue refValue)
    {
        testParameterResult = new TestParameterResult(result, metric, refValue);
    }

    /**
     * Method to return a string containing the test parameter info.
     *
     * @return String containing the test parameter info.
     */
    @Override
    public String toString()
    {
        return String.format("Parameter: %s%n%s",
                parameter.getCode(), testParameterResult.toString());
    }
}
