package app.domain.model;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class ClientTest {
    private Client c1, c2, c3, c4, c5, c6, c7;
    private String name1, name2, name3, name4,
            ccn1, ccn2, ccn3, ccn4, ccn5, ccn6,
            nhsNumber1, nhsNumber2, nhsNumber3, nhsNumber4, nhsNumber5, nhsNumber6,
            tin1, tin2, tin3, tin4, tin5, tin6,
            phoneNumber1, phoneNumber2, phoneNumber3, phoneNumber4, phoneNumber5, phoneNumber6,
            email1, email2, email3, email4, email5, email6;
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
        c1 = new Client("Rui Rocha", date, Client.Sex.MALE, "1234567890123456",
                "1234567890", "1234567890", "12345678909", "ruirocha0@hotmail.com");

        //Same ccn as c1
        c2 = new Client("Rui Rocha", date1, Client.Sex.MALE, "1234567890123456",
                "5364789287", "7487364523", "73648276354", "carlos0@hotmail.com");

        //Unique attributes
        c3 = new Client("Luisa", date1, Client.Sex.FEMALE, "1111111111111111",
                "5555555555", "7777777777", "91645324567", "luisa@hotmail.com");

        //Same nhs number as c2
        c4 = new Client("Luis", date, Client.Sex.OTHER, "6666666666666666",
                "5364789287", "1234567891", "54374652435", "luis@hotmail.com");

        //Same TIN as c1
        c5 = new Client("Paulo", date, Client.Sex.NONE, "4578364789465730",
                "6375846352", "1234567890", "97365432412", "paulo@hotmail.com");

        //Same phone number as c1
        c6 = new Client("Sergio", date, Client.Sex.MALE, "9485673564321456",
                "8374635241", "8374635263", "12345678909", "sergio@hotmail.com");

        //Same email as c1
        c7 = new Client("Jorge", date, Client.Sex.MALE, "7364521846763521",
                "2222222222", "3333333333", "44444444444", "ruirocha0@hotmail.com");

        //region Attributes for Validations Tests
        //Names
        name1 = "Rui Rocha";
        name2 = "Rui Filipe Lobo Rocha Soares Vasques Bernardes";
        name3 = "";
        name4 = null;

        //CCN
        ccn1 = "1234567890123456";   //Valid
        ccn2 = "1234";               //Too short
        ccn3 = "123456789012345678"; //Too long
        ccn4 = "1234567890123f45";   //Has a letter
        ccn5 = "0234567890123445";   //Starts with 0
        ccn6 = null;                 //Null

        //NHS Number
        nhsNumber1 = "1234567890";   //Valid
        nhsNumber2 = "1234";         //Too short
        nhsNumber3 = "123456789012"; //Too long
        nhsNumber4 = "1234f56789";   //Has a letter
        nhsNumber5 = "0234567895";   //Starts with 0
        nhsNumber6 = null;           //Null

        //TIN
        tin1 = "1234567890";   //Valid
        tin2 = "1234";         //Too short
        tin3 = "123456789012"; //Too long
        tin4 = "1234f56789";   //Has a letter
        tin5 = "0234556789";   //Starts with 0
        tin6 = null;           //Null

        //Phone Number
        phoneNumber1 = "12345678901";  //Valid
        phoneNumber2 = "1234";         //Too short
        phoneNumber3 = "123456789012"; //Too long
        phoneNumber4 = "1234f567894";  //Has a letter
        phoneNumber5 = "02345567894";  //Starts with 0
        phoneNumber6 = null;           //Null

        //Email
        email1 = "1200735@isep.ipp.pt";  //Valid
        email2 = "sdfgh";                //Random letters
        email3 = "1200735.com";          //no @
        email4 = "1200735@isep";         //no suffix ".com"
        email5 = ".com@1200735";         //random places
        email6 = null;                   //Null

        //invalid date (too old)
        String strDate2 = "11-07-1800";
        SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
        date2 = df2.parse(strDate2);

        //invalid date (not born yet)
        String strDate3 = "11-07-2025";
        SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy");
        date3 = df3.parse(strDate3);

        // Null date
        date4 = null;
        //endregion
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
    public void getBirthDate() {
        //Act
        Date dateTest = c1.getBirthDate();

        //Assert
        assertNotSame(date, dateTest);
        assertNotNull(dateTest);
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
    public void showOp() {
        //Arrange
        int expectedOp1 = 1;
        int expectedOp2 = 2;
        int expectedOp3 = 3;
        int expectedOp4 = 4;

        //Act
        Client.Sex sex1 = c1.getSex();
        Client.Sex sex2 = c3.getSex();
        Client.Sex sex3 = c4.getSex();
        Client.Sex sex4 = c5.getSex();

        //Assert
        assertEquals(expectedOp1, sex1.showOp());
        assertEquals(expectedOp2, sex2.showOp());
        assertEquals(expectedOp3, sex3.showOp());
        assertEquals(expectedOp4, sex4.showOp());
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
    public void getNhsNumber() {
        //Arrange
        String expectedNhsNumber = "1234567890";

        //Act
        String nhsNumber = c1.getNhsNumber();

        //Assert
        assertEquals(expectedNhsNumber, nhsNumber);
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
    public void getPhoneNumber() {
        //Arrange
        String expectedPhoneNumber = "12345678909";

        //Act
        String phoneNumber = c1.getPhoneNumber();

        //Assert
        assertEquals(expectedPhoneNumber, phoneNumber);
    }

    @Test
    public void getEmail() {
        //Arrange
        String expectedEmail = "ruirocha0@hotmail.com";

        //Act
        String email = c1.getEmail();

        //Assert
        assertEquals(expectedEmail, email);
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


    @Test
    public void testEquals() {
        //Arrange
        Client client = c1;

        //Act
        boolean result1 = c1.equals(c2);
        boolean result2 = c1.equals(c3);
        boolean result3 = c2.equals(c4);
        boolean result4 = c1.equals(c5);
        boolean result5 = c1.equals(c6);
        boolean result6 = c1.equals(c7);

        //Assert
        assertTrue(result1);
        assertFalse(result2);
        assertTrue(result3);
        assertTrue(result4);
        assertTrue(result5);
        assertTrue(result6);
        assertEquals(c1, client);
    }

    @Test
    public void compareTo() {
        //Act
        int result1 = c1.compareTo(c2);
        int result2 = c1.compareTo(c3);
        int result3 = c1.compareTo(c6);

        //Assert
        assertEquals(c1.getName() + " is equal to " + c2.getName(), 0, result1);
        assertTrue( c1.getName() + " comes after " + c2.getName(),result2 > 0);
        assertTrue(c1.getName() + " comes before " + c2.getName(), result3 < 0);
    }


    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        new Client(null, null, null, null, null, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTooLongNameIsNotAllowed() {
        new Client(name2, date, Client.Sex.MALE, ccn1, nhsNumber1, tin1, phoneNumber1, email1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyNameIsNotAllowed() {
        new Client(name3, date, Client.Sex.MALE, ccn1, nhsNumber1, tin1, phoneNumber1, email1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullNameIsNotAllowed() {
        new Client(name4, date, Client.Sex.MALE, ccn1, nhsNumber1, tin1, phoneNumber1, email1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTooShortCodeIsNotAllowed() {
        new Client(name1, date, Client.Sex.MALE, ccn2, nhsNumber1, tin1, phoneNumber1, email1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTooLongCodeIsNotAllowed() {
        new Client(name1, date, Client.Sex.MALE, ccn3, nhsNumber1, tin1, phoneNumber1, email1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCodeWithNonNumericalCharIsNotAllowed() {
        new Client(name1, date, Client.Sex.MALE, ccn4, nhsNumber1, tin1, phoneNumber1, email1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullCodeIsNotAllowed() {
        new Client(name1, date, Client.Sex.MALE, ccn6, nhsNumber1, tin1, phoneNumber1, email1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTooShortNhsNumberIsNotAllowed() {
        new Client(name1, date, Client.Sex.MALE, ccn1, nhsNumber2, tin1, phoneNumber1, email1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTooLongNhsNumberIsNotAllowed() {
        new Client(name1, date, Client.Sex.MALE, ccn1, nhsNumber3, tin1, phoneNumber1, email1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNhsNumberWithNonNumericalCharIsNotAllowed() {
        new Client(name1, date, Client.Sex.MALE, ccn1, nhsNumber4, tin1, phoneNumber1, email1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNhsNumberStartsWithZeroIsNotAllowed() {
        new Client(name1, date, Client.Sex.MALE, ccn1, nhsNumber5, tin1, phoneNumber1, email1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullNhsNumberIsNotAllowed() {
        new Client(name1, date, Client.Sex.MALE, ccn1, nhsNumber6, tin1, phoneNumber1, email1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTooShortTinIsNotAllowed() {
        new Client(name1, date, Client.Sex.MALE, ccn1, nhsNumber1, tin2, phoneNumber1, email1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTooLongTinIsNotAllowed() {
        new Client(name1, date, Client.Sex.MALE, ccn1, nhsNumber1, tin3, phoneNumber1, email1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTinWithNonNumericalCharIsNotAllowed() {
        new Client(name1, date, Client.Sex.MALE, ccn1, nhsNumber1, tin4, phoneNumber1, email1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTinStartsWithZeroIsNotAllowed() {
        new Client(name1, date, Client.Sex.MALE, ccn1, nhsNumber1, tin5, phoneNumber1, email1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullTinIsNotAllowed() {
        new Client(name1, date, Client.Sex.MALE, ccn1, nhsNumber1, tin6, phoneNumber1, email1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTooShortPhoneNumberIsNotAllowed() {
        new Client(name1, date, Client.Sex.MALE, ccn1, nhsNumber1, tin1, phoneNumber2, email1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTooLongPhoneNumberIsNotAllowed() {
        new Client(name1, date, Client.Sex.MALE, ccn1, nhsNumber1, tin1, phoneNumber3, email1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensurePhoneNumberWithNonNumericalCharIsNotAllowed() {
        new Client(name1, date, Client.Sex.MALE, ccn1, nhsNumber1, tin1, phoneNumber4, email1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensurePhoneNumberStartsWithZeroIsNotAllowed() {
        new Client(name1, date, Client.Sex.MALE, ccn1, nhsNumber1, tin1, phoneNumber5, email1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullPhoneNumberIsNotAllowed() {
        new Client(name1, date, Client.Sex.MALE, ccn1, nhsNumber1, tin1, phoneNumber6, email1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureInvalidEmailIsNotAllowed() {
        new Client(name1, date, Client.Sex.MALE, ccn1, nhsNumber1, tin1, phoneNumber1, email2);
        new Client(name1, date, Client.Sex.MALE, ccn1, nhsNumber1, tin1, phoneNumber1, email3);
        new Client(name1, date, Client.Sex.MALE, ccn1, nhsNumber1, tin1, phoneNumber1, email4);
        new Client(name1, date, Client.Sex.MALE, ccn1, nhsNumber1, tin1, phoneNumber1, email5);
        new Client(name1, date, Client.Sex.MALE, ccn1, nhsNumber1, tin1, phoneNumber1, email6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureInvalidBirthDateIsNotAllowed() {
        new Client(name1, date2, Client.Sex.MALE, ccn1, nhsNumber1, tin1, phoneNumber1, email1);
        new Client(name1, date3, Client.Sex.MALE, ccn1, nhsNumber1, tin1, phoneNumber1, email1);
        new Client(name1, date4, Client.Sex.MALE, ccn1, nhsNumber1, tin1, phoneNumber1, email1);
    }
}