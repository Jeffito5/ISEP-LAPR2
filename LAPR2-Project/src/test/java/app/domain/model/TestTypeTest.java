package app.domain.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Luís Araújo
 */
public class TestTypeTest {
    List<ParameterCategory> categories = new ArrayList<ParameterCategory>();
    private ParameterCategory pc1, pc2, pc3, pc4;
    private TestType tt1, tt2, tt3, tt4;

    public void createPC() {
        // Creation of parameter categories
        pc1 = new ParameterCategory("Hemogram", "Blood tests", "12345");
        pc2 = new ParameterCategory("Proteins", "Analyses protein levels", "55555");
        pc3 = new ParameterCategory("Hemogram", "Blood testing", "987654");
        pc4 = new ParameterCategory("Thyroid", "Thyroid tests", "3453");
        // Store of the parameter categories in the array list
        categories.add(pc1);
        categories.add(pc2);
        categories.add(pc3);
        categories.add(pc4);
    }

    @Before
    public void setUp() throws Exception {
        tt1 = new TestType("67812", "Teste 1", "Colheita 1", categories);
        tt2 = new TestType("5612", "Teste 2", "Colheita 2", categories);
        tt3 = new TestType("90271", "Teste 3", "Colheita 3", categories);
        tt4 = new TestType("67812", "Teste 1", "Colheita 4", categories);
    }

    @Test
    public void getCode() {
        //Arrange
        String expectedResult = "67812";
        String expectedResult2 = "5612";
        String expectedResult3 = "90271";

        //Act
        String result = tt1.getCode();
        String result2 = tt2.getCode();
        String result3 = tt3.getCode();
        String result4 = tt4.getCode();

        //Assert
        assertEquals(Integer.parseInt(expectedResult), Integer.parseInt(result), 0.01);
        assertEquals(Integer.parseInt(expectedResult2), Integer.parseInt(result2), 0.01);
        assertEquals(Integer.parseInt(expectedResult3), Integer.parseInt(result3), 0.01);
        assertEquals(Integer.parseInt(expectedResult), Integer.parseInt(result4), 0.01);
    }

    @Test
    public void setCode() {
        String expectedResult = "45";
        String expectedResult2 = "1";
        String expectedResult3 = "2344";
        String expectedResult4 = "67";

        tt1.setCode("45");
        tt2.setCode("1");
        tt3.setCode("2344");
        tt4.setCode("67");

        assertEquals(expectedResult, tt1.getCode());
        assertEquals(expectedResult2, tt2.getCode());
        assertEquals(expectedResult3, tt3.getCode());
        assertEquals(expectedResult4, tt4.getCode());
    }

    @Test
    public void getDescription() {
        //Arrange
        String expectedResult = "Teste 1";
        String expectedResult2 = "Teste 2";
        String expectedResult3 = "Teste 3";

        //Act
        String result = tt1.getDescription();
        String result2 = tt2.getDescription();
        String result3 = tt3.getDescription();
        String result4 = tt4.getDescription();

        //Assert
        assertEquals(expectedResult, result);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult, result4);
    }

    @Test
    public void setDescription() {
        String expectedResult = "Teste1";
        String expectedResult2 = "Teste2";
        String expectedResult3 = "Teste3";
        String expectedResult4 = "Teste4";

        tt1.setDescription("Teste1");
        tt2.setDescription("Teste2");
        tt3.setDescription("Teste3");
        tt4.setDescription("Teste4");

        assertEquals(expectedResult, tt1.getDescription());
        assertEquals(expectedResult2, tt2.getDescription());
        assertEquals(expectedResult3, tt3.getDescription());
        assertEquals(expectedResult4, tt4.getDescription());
    }

    @Test
    public void getCollectingMethod() {
        //Arrange
        String expectedResult = "Colheita 1";
        String expectedResult2 = "Colheita 2";
        String expectedResult3 = "Colheita 3";
        String expectedResult4 = "Colheita 4";

        //Act
        String result = tt1.getCollectingMethod();
        String result2 = tt2.getCollectingMethod();
        String result3 = tt3.getCollectingMethod();
        String result4 = tt4.getCollectingMethod();

        //Assert
        assertEquals(expectedResult, result);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
    }

    @Test
    public void setCollectingMethod() {
        String expectedResult = "C1";
        String expectedResult2 = "C2";
        String expectedResult3 = "C3";
        String expectedResult4 = "C4";

        tt1.setCollectingMethod("C1");
        tt2.setCollectingMethod("C2");
        tt3.setCollectingMethod("C3");
        tt4.setCollectingMethod("C4");

        assertEquals(expectedResult, tt1.getCollectingMethod());
        assertEquals(expectedResult2, tt2.getCollectingMethod());
        assertEquals(expectedResult3, tt3.getCollectingMethod());
        assertEquals(expectedResult4, tt4.getCollectingMethod());
    }

    @Test
    public void testEquals() {
        //Act
        boolean result1 = tt1.equals(tt2);
        boolean result2 = tt2.equals(tt3);
        boolean result3 = tt3.equals(tt4);
        boolean result4 = tt4.equals(tt1);

        //Assert
        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
        assertTrue(result4);
    }

    @Test
    public void isCodeValid1() {
        TestType tt = new TestType();
        String code = "";
        boolean result = tt.isCodeValid(code);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isCodeValid2() {
        TestType tt = new TestType();
        String code = "123456789adcxsdf";
        boolean result = tt.isCodeValid(code);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isCodeValid3() {
        TestType tt = new TestType();
        String code = "1234";
        boolean result = tt.isCodeValid(code);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }
    @Test
    public void isCodeValid4() {
        TestType tt = new TestType();
        String code = null;
        boolean result = tt.isCodeValid(code);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }
    @Test
    public void isCodeValid5() {
        TestType tt = new TestType();
        String code = "1";
        boolean result = tt.isCodeValid(code);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }
    @Test
    public void isCodeValid6() {
        TestType tt = new TestType();
        String code = "111111";
        boolean result = tt.isCodeValid(code);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isDescriptionValid1() {
        TestType tt = new TestType();
        String description = "";
        boolean result = tt.isDescriptionValid(description);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isDescriptionValid2() {
        TestType tt = new TestType();
        String description = "This description cannot be null and has no more than 15 characters";
        boolean result = tt.isDescriptionValid(description);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isDescriptionValid3() {
        TestType tt = new TestType();
        String description = "Description 1";
        boolean result = tt.isDescriptionValid(description);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isCollectingMethodValid1() {
        TestType tt = new TestType();
        String collectingMethod = "";
        boolean result = tt.isCollectingMethodValid(collectingMethod);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isCollectingMethodValid2() {
        TestType tt = new TestType();
        String collectingMethod = "This collecting method cannot be null and has no more than 20 characters";
        boolean result = tt.isCollectingMethodValid(collectingMethod);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isCollectingMethodValid3() {
        TestType tt = new TestType();
        String collectingMethod = "Collecting Method 1";
        boolean result = tt.isCollectingMethodValid(collectingMethod);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isTestTypeValid() {
        boolean result= tt1.isTestTypeValid(tt1);
        assertTrue(result);
    }
}
