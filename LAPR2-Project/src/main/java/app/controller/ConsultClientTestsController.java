package app.controller;

import app.domain.model.*;
import app.domain.model.algorithms.sorting.Sort;
import app.ui.console.utils.comparators.ClientNameComparator;
import app.ui.console.utils.comparators.ClientTinComparator;
import app.domain.shared.Constants;
import app.domain.store.ClientStore;
import app.domain.store.TestStore;
import app.mappers.ClientMapper;
import app.mappers.TestMapper;
import app.mappers.dto.ClientDto;
import app.mappers.dto.TestDto;

import java.util.List;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class ConsultClientTestsController {

    /**
     * Stores.
     */
    private final TestStore testStore;
    private final ClientStore clientStore;

    /**
     * Mappers.
     */
    private final ClientMapper clientMapper;
    private final TestMapper testMapper;

    /**
     * Configuration files class.
     */
    private Configurations configurations;

    /**
     * Client.
     */
    private Client client;

    /**
     * Constructs an instance of the Controller,
     * using singleton to access the app instance;
     */
    public ConsultClientTestsController()
    {
        // Singleton
        App app = App.getInstance();
        Company company = app.getCompany();

        //Stores
        this.testStore = company.getTestStore();
        this.clientStore = company.getClientStore();

        // Mappers
        this.clientMapper = new ClientMapper();
        this.testMapper = new TestMapper();

        //configurations = new Configurations();

    }

    /**
     * Retrieves the client with a specific Tax Identification Number.
     *
     * @param tin Client's Tax Identification Number.
     */
    public void getClient (String tin)
    {
        client = clientStore.getClient(tin);
    }

    /**
     * Retrieves the client's info as a String.
     *
     * @return string with client info.
     *
     */
    public String getClientInfo ()
    {
        return client.toString();
    }

    /**
     * Retrieves a list of clients from the Client store.
     *
     * @return list of client Dto's.
     *
     */
    public List<ClientDto> getClientListAsString() {
        return clientMapper.toDTO(clientStore.getClients());
    }

    /**
     * Retrieves the a list of clients sorted by name.
     *
     * @return list of sorted Client Dtos.
     *
     */
    public List<ClientDto> getClientListSortedByName() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        configurations = new Configurations();

        List<Client> clientList = clientStore.getClients();
        List<ClientDto> clientDtos = clientMapper.toDTO(clientList);

        Class<?> oClass = Class.forName(configurations.getSortingAlgorithm());
        Sort sorter = (Sort) oClass.newInstance();

        //long start = System.nanoTime();
        sorter.sort(clientDtos, new ClientNameComparator());
        //long end = System.nanoTime();

        //System.out.println("Took (in ns)" + (end - start));

        return clientDtos;
    }

    /**
     * Retrieves the a list of clients sorted by tax identification number.
     *
     * @return list of sorted Client Dtos.
     *
     */
    public List<ClientDto> getClientListSortedByTin() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        configurations = new Configurations();
        List<Client> clientList = clientStore.getClients();
        List<ClientDto> clientDtos = clientMapper.toDTO(clientList);

        Class<?> oClass = Class.forName(configurations.getSortingAlgorithm());
        Sort sorter = (Sort) oClass.newInstance();

        sorter.sort(clientDtos, new ClientTinComparator());

        return clientDtos;
    }

    /**
     * Retrieves a list of tests pertaining to a specific client.
     *
     * @param clientTin Client's Tax Identification Number.
     * @return list of Client's tests.
     *
     */
    public List<TestDto> getClientTests(String clientTin){
        Client client = clientStore.getClient(clientTin);
        List<Test> testList = testStore.getClientTestListByState(client, Constants.STATE_VALIDATED);
        return testMapper.toDTO(testList);
    }

    /**
     * Retrieves the a list of clients pertaining to a specific client, previously retrieved.
     *
     * @return list of client's tests.
     *
     */
    public List<TestDto> getClientTests(){
        List<Test> testList = testStore.getClientTestListByState(client, Constants.STATE_VALIDATED);
        return testMapper.toDTO(testList);
    }

    /**
     * Retrieves the client's Test info as a String.
     *
     * @return string with test info.
     *
     */
    public String getTestInfo(String testNHSCode){
        return testStore.getTest(testNHSCode).toString();
    }

    /**
     * Retrieves the client's test results as a String.
     *
     * @return string with test results.
     *
     */
    public String getTestResults(String testNHSCode) {
        return testStore.getTest(testNHSCode).getTestResults();

    }

}
