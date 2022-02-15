package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.mappers.dto.TestDto;
import net.sourceforge.barbecue.BarcodeException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author
 */
public class RegisterSampleControllerTest {
    private final List<ParameterCategory> categories = new ArrayList<>();
    private final List<Parameter> parameterList = new ArrayList<>();
    Sample sample;
    App app = App.getInstance();
    Company company = app.getCompany();
    private RegisterSampleController registerSampleController;
    private String barcode;
    private Client client;
    private app.domain.model.Test t1;
    private TestType tt1;
    private ClinicalAnalysisLaboratory cal1;
    private TestDto testDto;

    @Before
    public void setUp() throws Exception {
        barcode = "12345678765";
        registerSampleController = new RegisterSampleController();
        sample = new Sample();

        //CLIENT
        String strDate = "11-07-1997";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        client = new Client("Luís Araújo", date, Client.Sex.MALE, "1234567890123456",
                "1234567890", "1234567890", "12345678909", "luisaraujo5555@gmail.com");

        //PARAMETER CATEGORIES
        ParameterCategory pc1 = new ParameterCategory("Hemogram", "Blood tests", "12345");

        categories.add(pc1);

        //TEST TYPES
        List<String> testTypeList = new ArrayList<>();
        tt1 = new TestType("67812", "Covid", "Swab", categories);
        testTypeList.add(tt1.getDescription());

        //PARAMETER
        Parameter p1 = new Parameter("RBC", "12345", "RedBloodCells");

        parameterList.add(p1);

        //CLINICAL ANALYSIS LABORATORIES
        cal1 = new ClinicalAnalysisLaboratory("Many Labs London", "1234", "12345678909", "1234567330", "Oxford Street 41", testTypeList);

        //TESTS
        t1 = new app.domain.model.Test(cal1, client, "123456789035", "TestDescription", tt1, parameterList);
        testDto = new TestDto(t1.getClinicalAnalysisLaboratory(), t1.getClient(), t1.getNhsCode(), t1.getDescription(), t1.getTestType(), t1.getTestParameterList());

    }

    @Test
    public void createUPCA() throws BarcodeException {
        //Act
        boolean result = registerSampleController.createUPCA(barcode);

        //Assert
        assertTrue(result);
    }

    @Test
    public void addSampleUPCA() {
        //Act
        boolean result = registerSampleController.addSampleUPCA();

        //Assert
        assertTrue(result);
    }

//    @Ignore
//    //Fix
    @Test
    public void getTestsRegisted() {
        String code = "1234";
        new TestDto(code);
        List<TestDto> testsRegistered = registerSampleController.getTestsRegisted(code);
        assertNotNull(testsRegistered);
    }

    @Test
    public void getClinicalAnalysisLaboratoryDto() {
        //Arrange
        String expectedResult = "Many Labs London";

        //Act
        String name = testDto.getClinicalAnalysisLaboratory().getName();

        //Assert
        assertEquals(expectedResult, name);
    }

    @Test
    public void getNhsCodeDto() {
        //Arrange
        String expectedResult = "123456789035";

        //Act
        String nhsCode = testDto.getNhsCode();

        //Assert
        assertEquals(expectedResult, nhsCode);
    }

    @Test
    public void getDescriptionDto() {
        //Arrange
        String expectedResult = "TestDescription";

        //Act
        String description = testDto.getDescription();

        //Assert
        assertEquals(expectedResult, description);
    }

    @Test
    public void getTestTypeDto() {
        //Arrange
        String expectedResult = "67812";

        //Act
        String code = testDto.getTestType().getCode();

        //Assert
        assertEquals(expectedResult, code);
    }

    @Test
    public void getParameterListDto() {
        List<TestParameter> result = testDto.getParameterList();

        //Assert
        assertNotNull(result);
        assertEquals(result.size(), parameterList.size());
    }

    @Ignore
    @Test
    public void addTestSample() {
        String nhsCode = "123456789012";
        t1 = company.getTestStore().getTest(nhsCode);
        t1.addTestSample(sample);
    }


    @Test
    public void createSample() throws BarcodeException {
        //Act
        boolean result = registerSampleController.createSample(barcode);

        //Assert
        assertTrue(result);
    }

    @Test
    public void getTests() {
        List<TestDto> testList = registerSampleController.getTests();
        assertNotNull(testList);
    }

    @Test
    public void setTestStateToCollected() {
        //Arrange
        String expectedResult1 = "Created";

        //Act
        t1.setCurrentState(Constants.STATE_CREATED);

        //Assert
        assertEquals(expectedResult1, t1.getCurrentState());
    }
}