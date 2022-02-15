package app.domain.store;

import app.domain.model.*;
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
public class TestStoreTest {
    private TestStore testStore;
    private final List<ParameterCategory> categories = new ArrayList<>();
    private final List<Parameter> parameterList = new ArrayList<>();
    private app.domain.model.Test t1;
    private TestType tt1, tt2;
    private Client client;
    private ClinicalAnalysisLaboratory cal1, cal2;

    @Before
    public void setUp() throws Exception {
        testStore = new TestStore();

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
        cal2 = new ClinicalAnalysisLaboratory("Many Labs Manchester", "3333", "12345654321", "9999999999", "Shirley Road 66", testTypeList);

        //TESTS
        t1 = testStore.createTest(cal1, client, "123456789012", "TestDescription", tt2, parameterList);
        testStore.addTest(t1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTestSameNhsNumber() {
        testStore.createTest(cal1, client, "123456789012", "TestDescription", tt1, parameterList);
    }

    @Test
    public void validateTest() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTestSameNhsNumber() {
        //Arrange
        app.domain.model.Test t2 = new app.domain.model.Test(cal2, client, "123456789012", "TestDescription", tt2, parameterList);

        //Act
        testStore.addTest(t2);
    }

    @Test
    public void getTests() {
        //Act
        List<app.domain.model.Test> tests = testStore.getTests();

        //Assert
        assertNotNull(tests);
    }

    @Test
    public void getTest() {
        String nhsCode = "123456789012";

        app.domain.model.Test t3 = testStore.getTest(nhsCode);

        assertEquals(t1, t3);
    }
}