package app.controller;


import app.domain.model.*;
import app.domain.store.ClientStore;
import app.domain.store.TestTypeStore;
import app.mappers.dto.ParameterCategoryDto;
import app.mappers.dto.ParameterDto;
import app.mappers.dto.TestTypeDto;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class RegisterTestControllerTest {
    private RegisterTestController registerTestController;
    private final List<ParameterCategory> categories = new ArrayList<>();
    private final List<Parameter> parameterList = new ArrayList<>();


    @Before
    public void setUp() throws Exception {
        // Singleton
        App app = App.getInstance();
        Company company = app.getCompany();

        //Stores
        ClientStore clientStore = company.getClientStore();
        TestTypeStore testTypeStore = company.getTestTypeStore();

        //Controller
        registerTestController = new RegisterTestController();

        //CLIENT
        String strDate = "11-07-1997";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("Rui Rocha", date, Client.Sex.MALE, "1234567890123456",
                "1234567890", "1234567890", "12345678909", "ruirocha0@hotmail.com");

        clientStore.addClient(client);

        //PARAMETER CATEGORIES
        ParameterCategory pc1 = new ParameterCategory("Hemogram", "Blood tests", "12345");
        ParameterCategory pc2 = new ParameterCategory("Proteins", "Analyses protein levels", "55555");

        categories.add(pc1);
        categories.add(pc2);

        //TEST TYPES
        List<String> testTypeList = new ArrayList<>();
        TestType tt1 = new TestType("67812", "Covid", "Swab", categories);
        TestType tt2 = new TestType("34556", "Blood", "Needle", categories);
        testTypeStore.addTestType(tt1);
        testTypeStore.addTestType(tt2);
        testTypeList.add(tt1.getDescription());
        testTypeList.add(tt2.getDescription());

        //PARAMETER
        Parameter p1 = new Parameter("RBC", "RBC00", "RedBloodCells");
        Parameter p2 = new Parameter("WBC", "11111", "WhiteBloodCells");

        parameterList.add(p1);
        parameterList.add(p2);
    }

    @Test
    public void hasClient() {

        boolean hasClient = registerTestController.hasClient("1234567890");

        assertTrue(hasClient);
    }

    @Test
    public void getTestTypes() {
        List<TestTypeDto> testTypeDtos = registerTestController.getTestTypes();
        assertNotNull(testTypeDtos);
    }

    @Test
    public void getParameterCategories() {
        List<ParameterCategoryDto> parameterCategoryDtos = registerTestController.getParameterCategories("34556");
        assertNotNull(parameterCategoryDtos);
    }

    @Test
    public void getParameters() {
        //Arrange
        registerTestController.getParameterCategories("34556");

        //Act
        List<ParameterDto> parameters = registerTestController.getParameters("12345");

        //Assert
        assertNotNull(parameters);
    }


    @Ignore
    @Test
    public void createTest() {
        //Arrange
        registerTestController.hasClient("1234567890");
        registerTestController.getParameterCategories("888");
        registerTestController.getParameters("12345");
        registerTestController.addParametersForTest("RBC00");

        //Act
        boolean created = registerTestController.createTest("1234", "123456789074");

        //Assert
        assertTrue(created);
    }

    @Ignore
    @Test
    public void addTest() {
        //Arrange
        registerTestController.hasClient("1234567890");
        registerTestController.getParameterCategories("888");
        registerTestController.getParameters("12345");
        registerTestController.addParametersForTest("RBC00");
        registerTestController.createTest("1234", "123456789066");

        //Act
        boolean added = registerTestController.addTest();

        //Assert
        assertTrue(added);
    }
}