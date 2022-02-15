package app.domain.model;

import app.domain.shared.Constants;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class TestTest {
    private final List<ParameterCategory> categories = new ArrayList<>();
    private final List<Parameter> parameterList = new ArrayList<>();
    private app.domain.model.Test t1, t2,t3;
    private TestType tt1, tt2;
    private Client client;
    private ClinicalAnalysisLaboratory cal1;

    @Before
    public void setUp() throws Exception
    {
        //CLIENT
        String strDate = "11-07-1997";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        client = new Client("Rui Rocha", date, Client.Sex.MALE, "1234567890123456",
                "1234567890", "1234567890", "12345678909", "ruirocha0@hotmail.com");

        //PARAMETER CATEGORIES
        ParameterCategory pc1 = new ParameterCategory("Hemogram", "Blood tests", "12345");
        ParameterCategory pc2 = new ParameterCategory("Proteins", "Analyses protein levels", "55555");

        categories.add(pc1);
        categories.add(pc2);

        //TEST TYPES
        List<String> testTypeList = new ArrayList<>();
        tt1 = new TestType("67812", "Covid", "Swab", categories);
        tt2 = new TestType("34556", "Blood", "Needle", categories);
        testTypeList.add(tt1.getDescription());
        testTypeList.add(tt2.getDescription());

        //PARAMETER
        Parameter p1 = new Parameter("RBC", "RBC00", "RedBloodCells");
        Parameter p2 = new Parameter("WBC", "11111", "WhiteBloodCells");

        parameterList.add(p1);
        parameterList.add(p2);

        //CLINICAL ANALYSIS LABORATORIES
        cal1 = new ClinicalAnalysisLaboratory("Many Labs London", "1234", "12345678909", "1234567890", "Oxford Street 41", testTypeList);
        ClinicalAnalysisLaboratory cal2 = new ClinicalAnalysisLaboratory("Many Labs Manchester", "3333", "12345654321", "9999999999", "Shirley Road 66", testTypeList);


        //TESTS
        t1 = new app.domain.model.Test(cal1, client, "123456789012", "TestDescription", tt2, parameterList);
        t2 = new app.domain.model.Test(cal2, client, "111111111111", "Covid Test", tt1, parameterList);
        t3 = new app.domain.model.Test(cal1, client, "123456789012", "TestDescription", tt2, parameterList);



        String diagnostic1="oioi";
        String diagnosticc2="olaola";

        t1.createReport(diagnostic1);
        t2.createReport(diagnosticc2);
        t3.createReport(diagnostic1);

    }

    @Test
    public void getNhsCode() {
        //Arrange
        String expectedNhsCode = "123456789012";

        //Act
        String nhsCode = t1.getNhsCode();

        //Assert
        assertEquals(expectedNhsCode, nhsCode);
    }

    @Test
    public void getDescription() {
        //Arrange
        String expectedDescription = "Covid Test";

        //Act
        String description = t2.getDescription();

        //Assert
        assertEquals(expectedDescription, description);
    }

    @Test
    public void getCurrentStateCreated() {
        //Arrange
        t1.setCurrentState(Constants.STATE_CREATED);
        String expectedState = "Created";

        //Act
        String currentState = t1.getCurrentState();

        //Assert
        assertEquals(expectedState, currentState);
    }

    @Test
    public void getCurrentStateRegistered() {
        //Arrange
        t1.setCurrentState(Constants.STATE_REGISTERED);
        String expectedState = "Registered";

        //Act
        String currentState = t1.getCurrentState();

        //Assert
        assertEquals(expectedState, currentState);
    }

    @Test
    public void getCurrentStateSamplesCollected() {
        //Arrange
        t1.setCurrentState(Constants.STATE_SAMPLES_COLLECTED);
        String expectedState = "Samples Collected";

        //Act
        String currentState = t1.getCurrentState();

        //Assert
        assertEquals(expectedState, currentState);
    }

    @Test
    public void getCurrentStateSamplesAnalyzed() {
        //Arrange
        t1.setCurrentState(Constants.STATE_SAMPLES_ANALYZED);
        String expectedState = "Samples Analyzed";

        //Act
        String currentState = t1.getCurrentState();

        //Assert
        assertEquals(expectedState, currentState);
    }

    @Test
    public void getCurrentStateValidated() {
        //Arrange
        t1.setCurrentState(Constants.STATE_VALIDATED);
        String expectedState = "Validated";

        //Act
        String currentState = t1.getCurrentState();

        //Assert
        assertEquals(expectedState, currentState);
    }

    @Test
    public void getTestParametersList() {
        //Act
        List<TestParameter> testParameterList = t1.getTestParameterList();

        //Assert
        assertNotNull(testParameterList);
        assertEquals(testParameterList.size(), parameterList.size());
    }

    @Test
    public void getRegistrationDate() {
        //Act
        Date registrationDate = t1.getRegistrationDate();

        //Assert
        assertNotNull(registrationDate);
    }

    @Test
    public void getAndSetCollectionDate() {
        //Act
        t1.setCollectionDate();
        Date collectionDate = t1.getCollectionDate();

        //Assert
        assertNotNull(collectionDate);
    }

    @Test
    public void getAndSetAnalysisDate() {
        //Act
        t1.setAnalysisDate();
        Date analysisDate = t1.getAnalysisDate();

        //Assert
        assertNotNull(analysisDate);
    }

    @Test
    public void getAndSetValidationDate() {
        //Act
        t1.setValidationDate();
        Date validationDate = t1.getValidationDate();

        //Assert
        assertNotNull(validationDate);
    }

    @Test
    public void getAndSetDiagnosisDate() {
        //Act
        t1.setDiagnosisDate();
        Date diagnosisDate = t1.getDiagnosisDate();

        //Assert
        assertNotNull(diagnosisDate);
    }

    @Test
    public void getTestSamples() {
        // Arrange
        Sample sample = new Sample();
        t1.addTestSample(sample);

        //Act
        List<Sample> sampleList = t1.getTestSamples();

        //Assert
        assertNotNull(sampleList);
    }

    @Test
    public void getClinicalAnalysisLaboratory() {
        //Act
        ClinicalAnalysisLaboratory clinicalAnalysisLaboratory = t1.getClinicalAnalysisLaboratory();

        //Assert
        assertNotNull(clinicalAnalysisLaboratory);
    }

    @Test
    public void getClient() {
        //Act
        Client client1 = t1.getClient();

        //Assert
        assertNotNull(client1);
    }

    @Test
    public void getCode() {
        //Act
        String code = t1.getCode();

        //Assert
        assertNotNull(code);
    }

    @Test
    public void getTestType() {
        //Act
        TestType testType = t1.getTestType();

        //Assert
        assertNotNull(testType);
        assertEquals(testType, tt2);
    }

    @Test
    public void getReport() {
        //Arrange
        String diagnostic = "High Colesterol";
        t1.createReport(diagnostic);

        //Act
        Report report = t1.getReport();
        String actualDiagnostic = t1.getReport().getReport();

        //Assert
        assertNotNull(report);
        assertEquals(diagnostic, actualDiagnostic);
    }

    @Test
    public void reportStateChangeToValidated() {
        //Arrange
        t1.createReport("High Colesterol");
        t1.saveReport();
        String expectedState = "Validated";

        //Act
        String actualState = t1.getCurrentState();

        //Assert
        assertEquals(expectedState, actualState);
    }

    @Test
    public void showReport() {
        //Arrange
        t1.createReport("High Colesterol");
        t1.saveReport();

        //Act
        String report = t1.showReport();

        //Assert
        assertNotNull(report);
    }

    @Test
    public void toStringTest() {
        String testInfo = t1.toString();
        assertNotNull(testInfo);
    }

    @Test
    public void testEquals() {
        //Arrange
        app.domain.model.Test t4 = t1;

        //Same NHS code
        assertEquals(t1, t3);

        //Different NHS code
        assertNotEquals(t1, t2);

        //Asserts
        assertEquals(t4, t1);
        assertNotEquals(t1, null);
    }

    @Test
    public void addTestResult() {
        //Arrange
        String parameterCode = "RBC00";
        double result = 4.6;
        String metric = "mg";

        //Act
        t1.addTestResult(parameterCode, result, metric);
        double resultValue = t1.getTestParameterFor(parameterCode).getTestParameterResult().getValue();

        //Assert
        assertEquals(result, resultValue, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        new app.domain.model.Test(null, null,null, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullNhsCodeIsNotAllowed() {
        new app.domain.model.Test(cal1, client, null, "Description", tt1, parameterList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyNhsCodeIsNotAllowed() {
        new app.domain.model.Test(cal1, client, "", "Description", tt1, parameterList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTooShortNhsCodeIsNotAllowed() {
        new app.domain.model.Test(cal1, client,"12345", "Description", tt1, parameterList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTooLongNhsCodeIsNotAllowed() {
        new app.domain.model.Test(cal1, client, "12345678909876", "Description", tt1, parameterList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNhsCodeWithNonAlphanumericalCharIsNotAllowed() {
        new app.domain.model.Test(cal1, client, "123456?78912", "Description", tt1, parameterList);
    }






    @Test(expected = IllegalArgumentException.class)
    public void testCreateReport() {

        String diagnosticInv="";
        t1.createReport(diagnosticInv);
        t1.createReport(null);


    }


    @Test
    public void testShowReport() {
        assertEquals(t1, t3);
        assertNotEquals(t1, t2);
    }
}