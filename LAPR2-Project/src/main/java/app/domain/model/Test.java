package app.domain.model;

import app.domain.model.externalModules.ExternalModule;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 * @author João Violante
 */
public class Test implements Serializable {

    //region Attributes
    /**
     * Test type's NHS code length.
     */
    private static final int NHS_CODE_LENGTH = 12;
    /**
     * Regular Expression for alphanumerical characters.
     */
    private static final String ALPHANUMERICAL_REGEX = "^[a-zA-Z0-9]*$";
    /**
     * Test's default code.
     */
    private static final String DEFAULT_CODE = "";
    /**
     * List of parameters to be tested.
     */
    private final List<TestParameter> testParameters = new ArrayList<>();
    /**
     * List of parameters to be tested.
     */
    private final List<Sample> testSamples = new ArrayList<>();
    /**
     * Clinical Analysis Laboratory associated to the test.
     */
    private ClinicalAnalysisLaboratory clinicalAnalysisLaboratory;
    /**
     * Client associated to the test.
     */
    private Client client;
    /**
     * Test's internal code.
     */
    private String code;
    /**
     * Test's NHS code.
     */
    private String nhsCode;
    /**
     * Test's type.
     */
    private TestType testType;
    /**
     * Test's description identifying the collection method.
     */
    private String description;
    /**
     * Test's report.
     */
    private Report report;
    /**
     * Test's state.
     */
    private State currentState;
    /**
     * Test registration date.
     */
    private Date createdAt;

    /**
     * Sample collection date.
     */
    private Date sampleCollectedAt;

    /**
     * Test chemical analysis date.
     */
    private Date analysedAt;

    /**
     * Test chemical diagnosis date.
     */
    private Date diagnosedAt;

    /**
     * Test validation date.
     */
    private Date validatedAt;

    /**
     * Constructs an instance of Test, receiving the Code, NHS code,
     * Test Type and Description.
     *
     * @param nhsCode     nhs code.
     * @param testType    test type.
     * @param description description identifying the collection method.
     */
    public Test(ClinicalAnalysisLaboratory clinicalAnalysisLaboratory, Client client, String nhsCode,
                String description, TestType testType, List<Parameter> parameterList) {
        setClinicalAnalysisLaboratory(clinicalAnalysisLaboratory);
        setClient(client);
        setNhsCode(nhsCode);
        setTestType(testType);
        setDescription(description);
        setTestParameterList(parameterList);
        currentState = State.CREATED;
        this.createdAt = new Date();
        this.code = DEFAULT_CODE;
    }
    //endregion

    //region Constructors

    /**
     * Method to return the associated Clinical Analysis Laboratory.
     *
     * @return Clinical Analysis Laboratory.
     */
    public ClinicalAnalysisLaboratory getClinicalAnalysisLaboratory() {
        return clinicalAnalysisLaboratory;
    }
    //endregion

    //region Getters and Setters

    private void setClinicalAnalysisLaboratory(ClinicalAnalysisLaboratory clinicalAnalysisLaboratory) {
        if (clinicalAnalysisLaboratory == null) {
            throw new IllegalArgumentException("No associated laboratory.");
        }
        this.clinicalAnalysisLaboratory = clinicalAnalysisLaboratory;

    }

    /**
     * Method to return the associated Client.
     *
     * @return Client.
     */
    public Client getClient() {
        return client;
    }

    /**
     * Method to assign the the client.
     *
     * @param client Client.
     * @throws IllegalArgumentException If Client is null.
     */
    private void setClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("No associated client.");
        }
        this.client = client;
    }

    /**
     * Method to return the automatically generated sequential internal code.
     *
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Method to generate the internal test code.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Method to return the NHS code.
     *
     * @return NHS Code.
     */
    public String getNhsCode() {
        return nhsCode;
    }

    /**
     * Method to modify the NHS code.
     *
     * @param nhsCode NHS code.
     */
    public void setNhsCode(String nhsCode) {
        validateNhsCode(nhsCode);
        this.nhsCode = nhsCode;
    }

    /**
     * Method to return the type of test
     *
     * @return type of test
     */
    public TestType getTestType() {
        return new TestType(testType);
    }

    /**
     * Method to modify the type of test
     *
     * @param testType Type of Test.
     */
    private void setTestType(TestType testType) {
        this.testType = testType;
    }

    /**
     * Method to return the description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method to modify the description.
     *
     * @param description Description identifying the collection method.
     * @throws IllegalArgumentException If description is null.
     */
    public void setDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("No description.");
        }
        this.description = description;
    }

    /**
     * Method to return the list of Parameters.
     *
     * @return Parameter's List.
     */
    public List<TestParameter> getTestParameterList() {
        return testParameters;
    }

    /**
     * Method to modify the parameter's list.
     *
     * @param parameterList list of parameters.
     */
    private void setTestParameterList(List<Parameter> parameterList) {
        for (Parameter parameter : parameterList) {
            TestParameter testParameter = new TestParameter(parameter);
            testParameters.add(testParameter);
        }
    }

    /**
     * Method to return the Test report.
     *
     * @return Test's report.
     */
    public Report getReport() {
        return report;
    }

    /**
     * Method to add a report.
     *
     * @param report Test report.
     */
    public void setReport(Report report) {
        this.report = report;
    }

    /**
     * Method to return a string of a date.
     *
     * @return Date.
     */
    private String getDateString(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormatter.format(date);
    }

    /**
     * Method to return a string of time.
     *
     * @return Time.
     */
    private String getTimeString(Date date) {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        return timeFormatter.format(date);
    }

    /**
     * Returns the test current state
     *
     * @return test's current state.
     */
    public String getCurrentState() {
        return currentState.toString();
    }

    /**
     * Method to modify current state.
     *
     * @param state Test state.
     */
    public void setCurrentState(String state) {
        this.currentState = State.valueOf(state);
    }

    /**
     * Method to return the registration date.
     *
     * @return Registration Date.
     */
    public Date getRegistrationDate() {
        return (Date) createdAt.clone();
    }

    /**
     * Method to set the registration date.
     */
    public void setRegistrationDate(Date date) {
        this.createdAt = (Date) date.clone();
    }

    /**
     * Method to return the Sample Collection Date.
     *
     * @return Sample Collection Date.
     */
    public Date getCollectionDate() {
        return (Date) sampleCollectedAt.clone();
    }

    /**
     * Method to set the sample collection date.
     */
    public void setCollectionDate() {
        this.sampleCollectedAt = new Date();
    }

    /**
     * Method to return Test Analysis date.
     *
     * @return Test Analysis date.
     */
    public Date getAnalysisDate() {
        return analysedAt;
    }

    /**
     * Method to set the Test Analysis date..
     */
    public void setAnalysisDate() {
        this.analysedAt = new Date();
    }

    /**
     * Method to return Test Diagnosis Date.
     *
     * @return Test Diagnosis Date.
     */
    public Date getDiagnosisDate() {
        return (Date) diagnosedAt.clone();
    }

    /**
     * Method to modify the Test diagnosis date.
     */
    public void setDiagnosisDate(Date date) {
        this.diagnosedAt = (Date) date.clone();
    }

    /**
     * Method to set the Test diagnosis date.
     */
    public void setDiagnosisDate() {
        this.diagnosedAt = new Date();
    }

    /**
     * Method to return Test Validation Date.
     *
     * @return Test Validation Date.
     */
    public Date getValidationDate() {
        return (Date) validatedAt.clone();
    }

    /**
     * Method to modify the test validation date.
     */
    public void setValidationDate(Date date) {
        this.validatedAt = (Date) date.clone();
    }

    /**
     * Method to modify the test validation date.
     */
    public void setValidationDate() {
        this.validatedAt = new Date();
    }

    /**
     * Method to return list of Test Samples
     *
     * @return Test Samples List.
     */
    public List<Sample> getTestSamples() {
        return testSamples;
    }

    public String getTestResults() {
        StringBuilder t = new StringBuilder();
        t.append("RESULTS: ").append("\n");
        if (!testParameters.isEmpty()) {
            for (TestParameter testParameter : testParameters) {
                t.append(testParameter.getParameter().getName()).append("\n");
                t.append(testParameter.getTestParameterResult().toString()).append("\n\n");
            }
        } else {
            t.append("No recorded results.\n");
        }
        return t.toString();
    }

    public boolean hasSample(String sampleCode) {
        for (Sample sample : testSamples) {
            String barcode = String.valueOf(sample.getBarcode_number());
            if (barcode.equals(sampleCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to return a string containing the test info.
     *
     * @return String containing the test info.
     */
    @Override
    public String toString() {
        return String.format("TEST %nDate: %s%nTime: %s%nLaboratory: %s%nClient: %s %nCode: %s %nNHS Code: %s %n"
                        + "Description: %s %nTest Type: %s %n%s%n",
                getDateString(createdAt), getTimeString(createdAt), clinicalAnalysisLaboratory.getName(),
                client.getName(), code, nhsCode, description, testType.getDescription(), printTestParameters());
    }
    //endregion

    /**
     * Compares the Test with other Test
     *
     * @param otherObject the object used to compare the test.
     * @return true if input object represents an equivalent test
     * to this test. Otherwise returns false.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        Test otherTest = (Test) otherObject;
        return nhsCode.equals(otherTest.nhsCode);
    }

    /**
     * Checks the validity of the NHS number, by checking that it consists
     * of 12 alphanumerical characters.
     *
     * @throws IllegalArgumentException If Test's NHS code is null
     *                                  or it doesn't consist of 12 alphanumerical characters.
     */
    private void validateNhsCode(String nhsCode) {
        if (nhsCode == null || nhsCode.length() != NHS_CODE_LENGTH) {
            throw new IllegalArgumentException("NHS Number must have 12 digits.");
        }
        if (!nhsCode.matches(ALPHANUMERICAL_REGEX)) {
            throw new IllegalArgumentException("NHS number has invalid characters.");
        }
    }

    /**
     * Creates string with a list of parameters to be tested
     *
     * @return String of list of parameters contained in the test.
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

    /**
     * Adds a new sample to the sample list.
     *
     * @param sample New sample.
     */
    public void addTestSample(Sample sample) {
        testSamples.add(sample);
    }

    /**
     * Creates an instance of report
     *
     * @param diagnostic report's diagnostic
     */
    public void createReport(String diagnostic) {
        report = new Report(diagnostic);
    }

    /**
     * Saves the instance of report by changing its state
     */
    public void saveReport() {
        currentState = State.VALIDATED;
    }

    /**
     * Shows the information of report
     *
     * @return Report's information
     */
    public String showReport() {
        return report.toString();
    }

    /**
     * Adds a test result.
     *
     * @param parameterCode Parameter Code.
     * @param result        Test Parameter result.
     * @param metric        Test Parameter metric.
     */
    public void addTestResult(String parameterCode, double result, String metric) {
        TestParameter testParameter = getTestParameterFor(parameterCode);
        Parameter parameter = testParameter.getParameter();

        // Retrieves the external module from the test type
        ExternalModule em;
        try {
            em = testType.getExternalModule();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            System.out.println("External Module Not Found");
            return;
        }

        // Obtaining reference values from the External Module
        ReferenceValue referenceValue = em.getReferenceValue(parameter);

        testParameter.addResult(result, metric, referenceValue);
        //System.out.println("\n" + testParameter.toString());
    }

    /**
     * Method to get a Test Parameter.
     *
     * @param parameterCode Parameter Code.
     * @throws IllegalArgumentException If the Parameter is not found.
     */
    public TestParameter getTestParameterFor(String parameterCode) {
        for (TestParameter testParameter : testParameters) {
            Parameter parameter = testParameter.getParameter();
            if (parameter.getCode().equals(parameterCode)) {
                return testParameter;
            }
        }
        throw new IllegalArgumentException("Parameter Not Found");
    }

    /**
     * States.
     */
    private enum State {
        CREATED("CREATED") {
            @Override
            public String toString() {
                return "Created";
            }
        },
        REGISTERED("REGISTERED") {
            @Override
            public String toString() {
                return "Registered";
            }
        },
        SAMPLES_COLLECTED("SAMPLES_COLLECTED") {
            @Override
            public String toString() {
                return "Samples Collected";
            }
        },
        SAMPLES_ANALYZED("SAMPLES_ANALYZED") {
            @Override
            public String toString() {
                return "Samples Analyzed";
            }
        },
        VALIDATED("VALIDATED") {
            @Override
            public String toString() {
                return "Validated";
            }
        };

        String state;

        State(String state) {
            this.state = state;
        }
    }
}
