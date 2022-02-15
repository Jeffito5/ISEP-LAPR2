package app.mappers.dto;

import app.domain.model.Client;
import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.TestParameter;
import app.domain.model.TestType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luís Araújo
 */
public class TestDto {
    private final List<TestParameter> testParameters = new ArrayList<>();
    private Client client;
    /**
     * Test's clinical analysis laboratory
     */
    private ClinicalAnalysisLaboratory clinicalAnalysisLaboratory;
    /**
     * Test's code
     */
    private final String Nhscode;
    /**
     * Test's description
     */
    private String description;
    /**
     * Test's test type
     */
    private TestType testType;
    /**
     * Test's parameter list
     */
    private List<TestParameter> parameterList;

    public TestDto(String nhscode) {
        this.Nhscode = nhscode;
    }

    public TestDto(ClinicalAnalysisLaboratory clinicalAnalysisLaboratory, Client client, String Nhscode, String description,
                   TestType testType, List<TestParameter> parameterList) {
        this.clinicalAnalysisLaboratory = clinicalAnalysisLaboratory;
        this.client=client;
        this.Nhscode = Nhscode;
        this.description = description;
        this.testType = testType;
        this.parameterList = parameterList;
    }

    public ClinicalAnalysisLaboratory getClinicalAnalysisLaboratory() {
        return clinicalAnalysisLaboratory;
    }

    public String getNhsCode() {
        return Nhscode;
    }

    public String getDescription() {
        return description;
    }

    public TestType getTestType() {
        return testType;
    }

    public List<TestParameter> getParameterList() {
        return parameterList;
    }

    /**
     * Creates string with a list of parameters to be tested
     *
     * @return list of test parameters
     */
    private String printTestParameters() {
        StringBuilder t = new StringBuilder();
        t.append("Parameters: ").append("\n");
        if (!testParameters.isEmpty()) {
            for (TestParameter testParameter : testParameters) {
                t.append(" - ").append(testParameter.getParameter().getName()).append("\n");
            }
        } else {
            t.append("No test parameters.\n");
        }
        return t.toString();
    }

    @Override
    public String toString() {
        return String.format("TEST %nLaboratory: %s%nClient's name: %s%nNHS Code: %s %n"
                        + "Description: %s %nTest Type: %s %n",
                clinicalAnalysisLaboratory.getName(), client.getName(), Nhscode, description,
                testType.getDescription(), printTestParameters());
    }
}


