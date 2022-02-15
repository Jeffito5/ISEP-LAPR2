package app.domain.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Luís Araújo
 */
public class ClinicalAnalysisLaboratoryTest {
    private ClinicalAnalysisLaboratory cal1, cal2, cal3, cal4;

    public List<String> test_types1() {
        List<String> tt1 = new ArrayList<>();
        tt1.add("9875");
        tt1.add("Teste 1");
        tt1.add("Colheita 1");
        return tt1;
    }

    public List<String> test_types2() {
        List<String> tt2 = new ArrayList<>();
        tt2.add("5678");
        tt2.add("Teste 2");
        tt2.add("Colheita 2");
        return tt2;
    }

    @Before
    public void setUp() throws Exception {
        cal1 = new ClinicalAnalysisLaboratory("Pedro Maia", "12345", "25897217332", "3454323789", "Rua Norton de Matos");
        cal2 = new ClinicalAnalysisLaboratory("Joaquim Chaves", "45788", "28360017251", "6371900045", "Rua General Conde Rodrigues");

        cal3 = new ClinicalAnalysisLaboratory("Abelino Vieira", "14212", "18902861715", "1740771533", "Praça da Ribeira", test_types1());
        cal4 = new ClinicalAnalysisLaboratory("Maria Amélia", "12567", "19264718555", "9826178175", "Rua da Alegria", test_types2());

    }

    @Test
    public void getName() {
        //Arrange
        String expectedResult = "Pedro Maia";
        String expectedResult2 = "Joaquim Chaves";
        String expectedResult3 = "Abelino Vieira";
        String expectedResult4 = "Maria Amélia";

        //Act
        String result = cal1.getName();
        String result2 = cal2.getName();
        String result3 = cal3.getName();
        String result4 = cal4.getName();

        //Assert
        assertEquals(expectedResult, result);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
    }

    @Test
    public void setName() {
        String expectedResult = "Abel Maia";
        String expectedResult2 = "Maria Chaves";
        String expectedResult3 = "Marta Vieira";
        String expectedResult4 = "Carmo Amélia";

        cal1.setName("Abel Maia");
        cal2.setName("Maria Chaves");
        cal3.setName("Marta Vieira");
        cal4.setName("Carmo Amélia");

        assertEquals(expectedResult, cal1.getName());
        assertEquals(expectedResult2, cal2.getName());
        assertEquals(expectedResult3, cal3.getName());
        assertEquals(expectedResult4, cal4.getName());
    }

    @Test
    public void getLaboratoryId() {
        //Arrange
        String expectedResult = "12345";
        String expectedResult2 = "45788";
        String expectedResult3 = "14212";
        String expectedResult4 = "12567";

        //Act
        String result = cal1.getLaboratoryId();
        String result2 = cal2.getLaboratoryId();
        String result3 = cal3.getLaboratoryId();
        String result4 = cal4.getLaboratoryId();

        //Assert
        assertEquals(Integer.parseInt(expectedResult), Integer.parseInt(result), 0.01);
        assertEquals(Integer.parseInt(expectedResult2), Integer.parseInt(result2), 0.01);
        assertEquals(Integer.parseInt(expectedResult3), Integer.parseInt(result3), 0.01);
        assertEquals(Integer.parseInt(expectedResult4), Integer.parseInt(result4), 0.01);
    }

    @Test
    public void setLaboratoryId() {
        String expectedResult = "456";
        String expectedResult2 = "12";
        String expectedResult3 = "8";
        String expectedResult4 = "123";

        cal1.setLaboratoryId("456");
        cal2.setLaboratoryId("12");
        cal3.setLaboratoryId("8");
        cal4.setLaboratoryId("123");

        assertEquals(expectedResult, cal1.getLaboratoryId());
        assertEquals(expectedResult2, cal2.getLaboratoryId());
        assertEquals(expectedResult3, cal3.getLaboratoryId());
        assertEquals(expectedResult4, cal4.getLaboratoryId());
    }

    @Test
    public void getPhoneNumber() {
        //Arrange
        String expectedResult = "25897217332";
        String expectedResult2 = "28360017251";
        String expectedResult3 = "18902861715";
        String expectedResult4 = "19264718555";

        //Act
        String result = cal1.getPhoneNumber();
        String result2 = cal2.getPhoneNumber();
        String result3 = cal3.getPhoneNumber();
        String result4 = cal4.getPhoneNumber();

        //Assert
        assertEquals(expectedResult, result);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
    }

    @Test
    public void setPhoneNumber() {
        String expectedResult = "25897267332";
        String expectedResult2 = "28365017251";
        String expectedResult3 = "18902861015";
        String expectedResult4 = "19261718555";

        cal1.setPhoneNumber("25897267332");
        cal2.setPhoneNumber("28365017251");
        cal3.setPhoneNumber("18902861015");
        cal4.setPhoneNumber("19261718555");

        assertEquals(expectedResult, cal1.getPhoneNumber());
        assertEquals(expectedResult2, cal2.getPhoneNumber());
        assertEquals(expectedResult3, cal3.getPhoneNumber());
        assertEquals(expectedResult4, cal4.getPhoneNumber());
    }

    @Test
    public void getTinNumber() {
        //Arrange
        String expectedResult = "3454323789";
        String expectedResult2 = "6371900045";
        String expectedResult3 = "1740771533";
        String expectedResult4 = "9826178175";

        //Act
        String result = cal1.getTinNumber();
        String result2 = cal2.getTinNumber();
        String result3 = cal3.getTinNumber();
        String result4 = cal4.getTinNumber();

        //Assert
        assertEquals(expectedResult, result);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
    }

    @Test
    public void setTinNumber() {
        String expectedResult = "2589726733";
        String expectedResult2 = "2836501721";
        String expectedResult3 = "1890286101";
        String expectedResult4 = "1926171855";

        cal1.setTinNumber("2589726733");
        cal2.setTinNumber("2836501721");
        cal3.setTinNumber("1890286101");
        cal4.setTinNumber("1926171855");

        assertEquals(expectedResult, cal1.getTinNumber());
        assertEquals(expectedResult2, cal2.getTinNumber());
        assertEquals(expectedResult3, cal3.getTinNumber());
        assertEquals(expectedResult4, cal4.getTinNumber());
    }

    @Test
    public void getAddress() {
        //Arrange
        String expectedResult = "Rua Norton de Matos";
        String expectedResult2 = "Rua General Conde Rodrigues";
        String expectedResult3 = "Praça da Ribeira";
        String expectedResult4 = "Rua da Alegria";

        //Act
        String result = cal1.getAddress();
        String result2 = cal2.getAddress();
        String result3 = cal3.getAddress();
        String result4 = cal4.getAddress();

        //Assert
        assertEquals(expectedResult, result);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
    }

    @Test
    public void setAddress() {
        String expectedResult = "Rua Pedro de Matos";
        String expectedResult2 = "Rua General Conde Silva";
        String expectedResult3 = "Praça da Ribeira Grande";
        String expectedResult4 = "Rua da Alegria de Cima";

        cal1.setAddress("Rua Pedro de Matos");
        cal2.setAddress("Rua General Conde Silva");
        cal3.setAddress("Praça da Ribeira Grande");
        cal4.setAddress("Rua da Alegria de Cima");

        assertEquals(expectedResult, cal1.getAddress());
        assertEquals(expectedResult2, cal2.getAddress());
        assertEquals(expectedResult3, cal3.getAddress());
        assertEquals(expectedResult4, cal4.getAddress());
    }

    @Test
    public void testEquals() {
        //Act
        boolean result1 = cal1.equals(cal2);
        boolean result2 = cal3.equals(cal4);

        //Assert
        assertFalse(result1);
        assertFalse(result2);

    }

    @Test
    public void isNameValid1() {
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory();
        String name = "";
        boolean result = cal.isNameValid(name);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isNameValid2() {
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory();
        String name = "Checks the validity of the Clinical Analysis Laboratory's name by checking that it's not empty and has no more than 20 characters.";
        boolean result = cal.isNameValid(name);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isNameValid3() {
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory();
        String name = "Lab 1";
        boolean result = cal.isNameValid(name);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isNameValid4() {
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory();
        String name = null;
        boolean result = cal.isNameValid(name);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }
    @Test
    public void isNameValid5() {
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory();
        String name = "WWWWWWWWWWWWWWWWWWWW";
        boolean result = cal.isNameValid(name);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isLaboratoryIdValid1() {
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory();
        String labID = null;
        boolean result = cal.isLaboratoryIdValid(labID);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isLaboratoryIdValid2() {
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory();
        String labID = "This id can't be null and has no more the five alphanumeric characters";
        boolean result = cal.isLaboratoryIdValid(labID);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isLaboratoryIdValid3() {
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory();
        String labID = "234";
        boolean result = cal.isLaboratoryIdValid(labID);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }
    @Test
    public void isLaboratoryIdValid4() {
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory();
        String labID = "";
        boolean result = cal.isLaboratoryIdValid(labID);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }
    @Test
    public void isLaboratoryIdValid5() {
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory();
        String labID = "11111";
        boolean result = cal.isLaboratoryIdValid(labID);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isPhoneNumberValid1() {
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory();
        String phone_number2 = "";
        boolean result = cal.isPhoneNumberValid(phone_number2);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isPhoneNumberValid2() {
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory();
        String phone_number2 = "017281526191";
        boolean result = cal.isPhoneNumberValid(phone_number2);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isPhoneNumberValid3() {
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory();
        String phone_number2 = "15845782567";
        boolean result = cal.isPhoneNumberValid(phone_number2);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isTinNumberValid1() {
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory();
        String tin_number2 = "";
        boolean result = cal.isTINValid(tin_number2);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isTinNumberValid2() {
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory();
        String tin_number2 = "017281526191";
        boolean result = cal.isTINValid(tin_number2);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isTinNumberValid3() {
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory();
        String tin_number2 = "9647395671";
        boolean result = cal.isTINValid(tin_number2);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isAddressValid1() {
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory();
        String address = "";
        boolean result = cal.isAddressValid(address);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isAddressValid2() {
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory();
        String address = "Checks the validity of the address by checking that it's not empty and has no more than 30 characters.";
        boolean result = cal.isAddressValid(address);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isAddressValid3() {
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory();
        String address = "Lab 1";
        boolean result = cal.isAddressValid(address);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }

    @Test
    public void isAddressValid4() {
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory();
        String address = null;
        boolean result = cal.isAddressValid(address);
        boolean expectedResult = false;
        assertEquals(expectedResult, result);
    }
    @Test
    public void isAddressValid5() {
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory();
        String address = "qwertyuioplkjhgfdsazxcvbnmlkjh";
        boolean result = cal.isAddressValid(address);
        boolean expectedResult = true;
        assertEquals(expectedResult, result);
    }
}
