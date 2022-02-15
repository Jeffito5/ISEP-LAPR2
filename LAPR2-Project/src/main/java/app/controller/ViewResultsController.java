package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.model.TestParameter;
import app.domain.store.ClientStore;
import app.domain.store.TestStore;
import app.mappers.dto.ClientResultsTestDTO;
import auth.UserSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author João Violante
 */

public class ViewResultsController {
    /**
     * Company
     */
    private Company company;

    /**
     *User session
     */
    private UserSession userSession;

    /**
     * Client's email
     */
    private String email;

    /**
     * Client
     */
    private Client client;

    /**
     * Client store
     */
    private ClientStore clientStore;

    /**
     * Test Store
     */
    private TestStore testStore;

    /**
     * Test
     */
    private Test test;

    /**
     * List of the Test's NHS Code
     */




    private List<String> testListCode;
    public ViewResultsController(){
        App app=App.getInstance();
        userSession=app.getCurrentUserSession();
        email=userSession.getUserId().getEmail();
        company=app.getCompany();
        clientStore=company.getClientStore();
        testStore=company.getTestStore();
        testListCode=new ArrayList<>();
    }

    /**
     * Gets a Client instance associated with a certain email
     * @return
     */
    private Client getClientByEmail(){
    return clientStore.getClientByEmail(email);
    }

    /**
     * Tries to get a List of Tests associated with a certain client, if it succeeds returns
     * the client's Test list in reverse, if it fails, returns a Exception
     * @return reversed Client's Test List or Exception
     */
    private List<Test> getTestList(){

        try{
            client=getClientByEmail();

        }catch (IllegalArgumentException e){
            System.out.format("ERROR: %s%n",e.getMessage());
        }
        List<Test> listTestReversed;

        listTestReversed=testStore.getClientTestList(client);
        Collections.reverse(listTestReversed);
        return listTestReversed;
    }

    /**
     * Gets a list of the Client's Tests and returns the a list of its Test's NHS Code
     * @return Client's Test's nhs Code List
     */
    public List<String> getTestListCode(){
        testListCode.clear();
         List<Test> testList=getTestList();
        for (Test test: testList) {

            testListCode.add(test.getNhsCode());
        }
        return testListCode;
    }

    /**
     * Shows the client's Test's result in a certain format
     * @param nhsCode Test's NHS Code
     * @return client's test's result
     */
    public String showTestResults(String nhsCode){

        test=getTestByNhsCode(nhsCode);
        String parametersToString=getTestParameterList(test);
        return String.format("TEST RESULTS:%n%n%s%n%nREPORT%n%n%s%n",parametersToString,test.getReport().toString());

    }

    /**
     * Gets a Test's parameters List and returns this parameters List in a string
     * @param test Client's Test
     * @return parameters List in a string
     */
    public String getTestParameterList(Test test){

        List<TestParameter> listTestParameter=test.getTestParameterList();
        String parametersToString="";


        for (TestParameter a : listTestParameter){
            parametersToString=String.format("%s%n%n",parametersToString+a.toString());

        }

        return parametersToString;
    }

    /**
     * Gets the List of Client's Tests DTO and returns this list reversed
     * @return Clients Tests DTO list reversed
     */
    public List<ClientResultsTestDTO> showClientTests(){
        List<ClientResultsTestDTO> listReversed=testStore.showClientTests();
        Collections.reverse(listReversed);
        return listReversed;
    }

    /**
     * Gets a Test instance associated with a NHS Code
     * @param nhsCode Test's NHS Code
     * @return Test instance associated with NHS Code
     */
    private Test getTestByNhsCode(String nhsCode){

        return testStore.getTest(nhsCode);
    }
}
