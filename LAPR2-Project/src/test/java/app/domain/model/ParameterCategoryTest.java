package app.domain.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParameterCategoryTest {

    private ParameterCategory pc1, pc2, pc3, pc4, pc5;

    @Before
    public void setUp() throws Exception {
        pc1 = new ParameterCategory("Hemogram", "Blood tests", "12345");
        pc2 = new ParameterCategory("Proteins", "Analyses protein levels", "55555");
        pc3 = new ParameterCategory("Hemogram", "Blood testing", "987654");
        pc4 = new ParameterCategory("Thyroid", "Thyroid tests", "12345");
        pc5 = new ParameterCategory("Nutrients", "Thyroid tests", "6666");
    }

    @Test
    public void getName() {
        //Arrange
        String expectedName = "Hemogram";

        //Act
        String name = pc1.getName();

        //Assert
        assertEquals(expectedName, name);
    }

    @Test
    public void getDescription() {
        //Arrange
        String expectedDescription = "Blood tests";

        //Act
        String description = pc1.getDescription();

        //Assert
        assertEquals(expectedDescription, description);
    }

    @Test
    public void getCode() {
        //Arrange
        String expectedCode = "12345";

        //Act
        String code = pc1.getCode();

        //Assert
        assertEquals(expectedCode, code);
    }



    @Test
    public void testEquals() {
        //Act
        boolean result1 = pc1.equals(pc2);
        boolean result2 = pc1.equals(pc3);
        boolean result3 = pc1.equals(pc4);
        boolean result4 = pc4.equals(pc5);

        //Assert
        assertFalse(result1);
        assertTrue(result2);
        assertTrue(result3);
        assertFalse(result4);
    }
}