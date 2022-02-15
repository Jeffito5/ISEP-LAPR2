package app.domain.store;

import app.controller.App;
import app.domain.model.Client;
import app.domain.model.Company;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.awt.PageAttributes.MediaType.C1;
import static org.junit.Assert.*;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class ClientStoreTest {
    private ClientStore clientStore;
    private Date date;

    @Before
    public void setUp() throws Exception {
        App app = App.getInstance();
        Company company = app.getCompany();
        clientStore = company.getClientStore();

        //DATES
        String strDate = "11-07-1997";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        date = df.parse(strDate);

        //CLIENTS

        Client c1 = new Client("Rui Rocha", date, Client.Sex.MALE, "1234567890123456",
                "1234567890", "1234567890", "12345678909", "ruirocha0@hotmail.com");

        clientStore.addClient(c1);
    }
    @Ignore
    //TODO fix test
    @Test
    public void addClient() {
        //Arrange
        Client c7 = new Client("Jorge", date, Client.Sex.MALE, "2222222222222222",
                "1111111111", "1111111111", "11111111111", "jorge@hotmail.com");

        //Act
        boolean result = clientStore.addClient(c7);

        //Assert
        assertTrue(result);
    }

    @Test
    public void addClientSameCcn() {
        //Arrange
        Client c2 = new Client("Pedro Carvalho", date, Client.Sex.MALE, "1234567890123456",
                "5364789287", "7487364523", "73648276354", "pedro0@hotmail.com");

        //Act
        boolean result = clientStore.addClient(c2);

        //Assert
        assertFalse(result);
    }

    @Test
    public void addClientSameNhsNumber() {
        //Arrange
        Client c3 = new Client("Luisa Moreira", date, Client.Sex.FEMALE, "8888888888888888",
                "1234567890", "7487364523", "73648276354", "luisa0@hotmail.com");

        //Act
        boolean result = clientStore.addClient(c3);

        //Assert
        assertFalse(result);
    }

    @Test
    public void addClientSameTin() {
        //Arrange
        Client c4 = new Client("Carlos Carvalho", date, Client.Sex.MALE, "7777777777777777",
                "5364789287", "1234567890", "73648276354", "carlos0@hotmail.com");

        //Act
        boolean result = clientStore.addClient(c4);

        //Assert
        assertFalse(result);
    }

    @Ignore
    @Test
    public void addClientSamePhoneNumber() {
        //Arrange
        Client c5 = new Client("Maria", date, Client.Sex.FEMALE, "3333333333333333",
                "5364789287", "7487364523", "12345678909", "maria@hotmail.com");

        //Act
        boolean result = clientStore.addClient(c5);

        //Assert
        assertFalse(result);
    }

    @Test
    public void addClientSameEmail() {
        //Arrange
        Client c6 = new Client("Filipe", date, Client.Sex.MALE, "4444444444444444",
                "5364789287", "7487364523", "73648276354", "ruirocha0@hotmail.com");

        //Act
        boolean result = clientStore.addClient(c6);

        //Assert
        assertFalse(result);
    }
    @Ignore
    //TODO fix test
    @Test
    public void testGetClientByEmail() {
        Client c1 = new Client("Rui Rocha", date, Client.Sex.MALE, "1234567890123456",
                "1234567890", "1234567890", "12345678909", "ruirocha0@hotmail.com");
        clientStore.addClient(c1);
        assertEquals(clientStore.getClientByEmail("ruirocha0@hotmail.com").equals(c1),true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetClientByEmailException() {
        assertEquals(clientStore.getClientByEmail("juanjua12365yggb@gmail.com"),false);

    }
}