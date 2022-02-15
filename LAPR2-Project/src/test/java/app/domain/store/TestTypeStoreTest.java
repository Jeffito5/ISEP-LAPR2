package app.domain.store;

import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Luís Araújo
 */
public class TestTypeStoreTest {
    TestTypeStore tts;
    List<ParameterCategory> categories = new ArrayList<ParameterCategory>();
    private ParameterCategory pc1;

    public void createPC() {
        // Creation of parameter category
        pc1 = new ParameterCategory("Hemogram", "Blood tests", "12345");
        // Store of the parameter category in the array list
        categories.add(pc1);
    }

    @Before
    public void setUp() throws Exception {
        tts = new TestTypeStore();
    }

    @Test
    public void createTestType() {
        TestType result = tts.createTestType("67812", "Teste 1", "Colheita 1", categories);
        TestType expectedResult = new TestType("67812", "Teste 1", "Colheita 1", categories);
        assertEquals(expectedResult.getCode(), result.getCode());
        assertEquals(expectedResult.getDescription(), result.getDescription());
        assertEquals(expectedResult.getCollectingMethod(), result.getCollectingMethod());
        assertEquals(expectedResult.getCategories(), result.getCategories());
    }

    @Test
    public void addTestType() {
        TestType aux = tts.createTestType("67812", "Teste 1", "Colheita 1", categories);
        boolean result = tts.addTestType(aux);
        boolean expectedResult = true;
        assertEquals(result, expectedResult);
    }

    @Test
    public void validateTestType() {
        TestType aux = tts.createTestType("67812", "Teste 1", "Colheita 1", categories);
        boolean result = tts.validateTestType(aux);
        boolean expectedResult = true;
        assertEquals(result, expectedResult);
    }

    @Test
    public void showTestType() {
        TestType tt1 = new TestType("67812", "Teste 1", "Colheita 1", categories);
        String test_type1 = tts.showTestType(tt1);
        assertEquals(test_type1.toString().equalsIgnoreCase(String.valueOf(tt1)), true);
    }

    @Test
    public void getTestTypes() {
        List<TestType> testTypeList=new ArrayList<>();
        testTypeList=tts.getTestTypes();
        assertNotNull(testTypeList);
    }
}