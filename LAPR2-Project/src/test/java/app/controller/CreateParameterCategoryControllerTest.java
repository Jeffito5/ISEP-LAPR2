package app.controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateParameterCategoryControllerTest {
    private CreateParameterCategoryController controller;
    private String name1, name2, name3,
            description1, description2, description3, description4,
            code1, code2, code3, code4, code5;

    @Before
    public void setUp() throws Exception {
        controller = new CreateParameterCategoryController();

        //Names
        name1 = "Hemogram"; //Valid
        name2 = "";         //Empty
        name3 = null;       //Null

        //Descriptions
        description1 = "Blood analysis";        //Valid
        description2 = "This parameter category contains parameters " +
                "relating to blood analysis";   //Too long
        description3 = "";                      //Empty description
        description4 = null;                    //Null

        //Codes
        code1 = "12345";        //Valid
        code2 = "12";           //Too short
        code3 = "123456789";    //Too long
        code4 = "123?3";        //Invalid characters
        code5 = null;           //Null

    }

    @Test
    public void createParameterCategoryValid() {
        //Act
        boolean result = controller.createParameterCategory(name1, description1, code1);

        //Assert
        assertTrue(result);
    }

    @Test
    public void createParameterCategoryInvalidName() {
        //Act
        boolean result1 = controller.createParameterCategory(name2, description1, code1);
        boolean result2 = controller.createParameterCategory(name3, description1, code1);

        //Assert
        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void createParameterCategoryInvalidDescription() {
        //Act
        boolean result1 = controller.createParameterCategory(name1, description2, code1);
        boolean result2 = controller.createParameterCategory(name1, description3, code1);
        boolean result3 = controller.createParameterCategory(name1, description4, code1);

        //Assert
        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
    }

    @Test
    public void createParameterCategoryInvalidCode() {
        //Act
        boolean result1 = controller.createParameterCategory(name1, description1, code2);
        boolean result2 = controller.createParameterCategory(name1, description1, code3);
        boolean result3 = controller.createParameterCategory(name1, description1, code4);
        boolean result4 = controller.createParameterCategory(name1, description1, code5);

        //Assert
        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
        assertFalse(result4);
    }
}