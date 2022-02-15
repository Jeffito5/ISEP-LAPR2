package app.mappers.dto;

import app.domain.model.Client;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author
 */
public class ClientDtoTest {
    private ClientDto c1, c2, c3, c4, c5, c6, c7;
    private Date date, date2, date3, date4;

    @Before
    public void setUp() throws Exception {
        //DATES
        String strDate = "11-07-1997";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        date = df.parse(strDate);

        String strDate1 = "11-01-1997";
        SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = df1.parse(strDate1);

        //CLIENTS
        c1 = new ClientDto("Rui Rocha", date, ClientDto.Sex.MALE, "1234567890123456",
                "1234567890", "1234567890", "12345678909", "ruirocha0@hotmail.com");

        //Same ccn as c1
        c2 = new ClientDto("Rui Rocha", date1, ClientDto.Sex.MALE, "1234567890123456",
                "5364789287", "7487364523", "73648276354", "carlos0@hotmail.com");

        //Unique attributes
        c3 = new ClientDto("Luisa", date1, ClientDto.Sex.FEMALE, "1111111111111111",
                "5555555555", "7777777777", "91645324567", "luisa@hotmail.com");

        //Same nhs number as c2
        c4 = new ClientDto("Luis", date, ClientDto.Sex.OTHER, "6666666666666666",
                "5364789287", "1234567891", "54374652435", "luis@hotmail.com");

        //Same TIN as c1
        c5 = new ClientDto("Paulo", date, ClientDto.Sex.NONE, "4578364789465730",
                "6375846352", "1234567890", "97365432412", "paulo@hotmail.com");

        //Same phone number as c1
        c6 = new ClientDto("Sergio", date, ClientDto.Sex.MALE, "9485673564321456",
                "8374635241", "8374635263", "12345678909", "sergio@hotmail.com");

        //Same email as c1
        c7 = new ClientDto("Jorge", date, ClientDto.Sex.MALE, "7364521846763521",
                "2222222222", "3333333333", "44444444444", "ruirocha0@hotmail.com");

    }

    @Test
    public void getName() {
        //Arrange
        String expectedName = "Luisa";

        //Act
        String name = c3.getName();

        //Assert
        assertEquals(expectedName, name);
    }

    @Test
    public void setName() {
        String expectedResult = "Abel Maia";

        c1.setName("Abel Maia");

        assertEquals(expectedResult, c1.getName());

    }

    @Test
    public void getBirthDate() {
        //Act
        Date dateTest = c1.getBirthDate();

        //Assert
        assertNotSame(date, dateTest);
        assertNotNull(dateTest);
    }

    @Test
    public void setBirthDate() throws ParseException {
        String expectedDate = "20-07-2016";
        Date dateTest1;
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        dateTest1 = df.parse(expectedDate);

        String strDate2 = "20-07-2016";
        Date dateTest;
        SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
        dateTest = df2.parse(strDate2);
        c1.setBirthDate(dateTest);

        assertEquals(dateTest1, c1.getBirthDate());

    }

    @Test
    public void getSex() {
        //Arrange
        String expectedSex1 = "Male";
        String expectedSex2 = "Female";
        String expectedSex3 = "Other";
        String expectedSex4 = "None";

        //Act
        Client.Sex sex1 = c1.getSex();
        Client.Sex sex2 = c3.getSex();
        Client.Sex sex3 = c4.getSex();
        Client.Sex sex4 = c5.getSex();

        //Assert
        assertEquals(expectedSex1, sex1.toString());
        assertEquals(expectedSex2, sex2.toString());
        assertEquals(expectedSex3, sex3.toString());
        assertEquals(expectedSex4, sex4.toString());
    }

    @Test
    public void setSex() {
        String expectedResult = "Male";

        c3.setSex(ClientDto.Sex.MALE);

        assertEquals(expectedResult, c3.getSex().toString());
    }

    @Test
    public void getCcn() {
        //Arrange
        String expectedCcn = "1234567890123456";

        //Act
        String ccn = c1.getCcn();

        //Assert
        assertEquals(expectedCcn, ccn);
    }

    @Test
    public void setCcn() {
        String expectedResult = "1234567890123454";

        c1.setCcn("1234567890123454");

        assertEquals(expectedResult, c1.getCcn());
    }

    @Test
    public void getNhsNumber() {
        //Arrange
        String expectedNhsNumber = "1234567890";

        //Act
        String nhsNumber = c1.getNhsNumber();

        //Assert
        assertEquals(expectedNhsNumber, nhsNumber);
    }

    @Test
    public void setNhsNumber() {
        String expectedResult = "1234567891";

        c1.setNhsNumber("1234567891");

        assertEquals(expectedResult, c1.getNhsNumber());
    }

    @Test
    public void getTin() {
        //Arrange
        String expectedTin = "1234567890";

        //Act
        String tin = c1.getTin();

        //Assert
        assertEquals(expectedTin, tin);
    }

    @Test
    public void setTin() {
        String expectedResult = "1234567898";

        c1.setTin("1234567898");

        assertEquals(expectedResult, c1.getTin());
    }

    @Test
    public void getPhoneNumber() {
        //Arrange
        String expectedPhoneNumber = "12345678909";

        //Act
        String phoneNumber = c1.getPhoneNumber();

        //Assert
        assertEquals(expectedPhoneNumber, phoneNumber);
    }

    @Test
    public void setPhoneNumber() {
        String expectedResult = "12345678904";

        c1.setPhoneNumber("12345678904");

        assertEquals(expectedResult, c1.getPhoneNumber());
    }

    @Test
    public void getEmail() {
        //Arrange
        String expectedEmail = "luis@hotmail.com";

        //Act
        String email = c4.getEmail();

        //Assert
        assertEquals(expectedEmail, email);
    }

    @Test
    public void setEmail() {
        String expectedResult = "luisaraujo@gmail.com";

        c1.setEmail("luisaraujo@gmail.com");

        assertEquals(expectedResult, c1.getEmail());
    }

    @Test
    public void getAge() {
        //Arrange
        int expectedAge = 23;
        int expectedAge2 = 24;

        //Act
        int result = c1.getAge();
        int result2 = c2.getAge();

        //Assert
        assertEquals(expectedAge, result, 0.01);
        assertEquals(expectedAge2, result2, 0.01);
    }
}