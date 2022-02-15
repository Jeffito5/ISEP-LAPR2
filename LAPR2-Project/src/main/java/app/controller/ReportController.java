package app.controller;

import app.domain.model.Company;

import app.domain.model.Test;
import app.domain.model.TestParameter;
import app.domain.shared.Constants;
import app.domain.store.TestStore;
import app.mappers.dto.unreportedTestDTO;

import java.util.List;

/**
 * @author João Violante
 */
public class ReportController {

    private final App app;

    /**
     * Test store
     */
    private final TestStore testStore;

    /**
     * company
     */
    private final Company company;




    /**
     * Test
     */
    private Test test;


    public ReportController(){
    this.app=App.getInstance();
    this.company=app.getCompany();
    this.testStore=company.getTestStore();

    }

    /**
     * Tries to create an instance of report, if it succeeds, returns true, if it fails,
     * returns an Exception Message and false
     * @param diagnosis Test's diagnosis
     * @return true, if report is created, if not, returns an exception message and false
     */


    public boolean createReport(String diagnosis){

        try{


            test.createReport(diagnosis);
            test.setDiagnosisDate();
            return true;
        }catch(Exception e){
            System.out.format("ERROR IN REPORT CREATION %nCAUSE:%s%n",e.getMessage());
            return false;
        }



    }

    /**
     * Finds a Test with a certain NHS Code and returns it
     * @param nhsCode Test NHS Code
     * @return Instance of Test
     */
    public Test getTestByNHSCode(String nhsCode){
        return testStore.getTest(nhsCode);
    }

    /**
     * Shows report's information
     * @return report's information
     */
    public String showReport(){
        return  test.showReport();
    }

    /**
     * Saves Report
     */
    public void saveReport(){

        test.saveReport();
        test.setCurrentState(Constants.STATE_VALIDATED);
        test.setValidationDate();
    }

    /**
     * Returns and DTO List containing NHS Code and Index on the store
     * of Tests that are ready to have report created
     * @return DTO list containing NHS code and Index
     */
    public List<unreportedTestDTO> unreportedTestList(){

        return testStore.showUnreportedTestList();

    }


    /**
     * Returns an Test's NHS Code by searching for said Tests with the Index of store
     * @param index Test's index in DTO list
     * @return Test's NHS Code
     */
    public String getNhsCodeByTestIndex(int index){
        int indexOriginalList=getUnreportedTestDtoIndex(index);

        return testStore.findNHSCodeByIndex(indexOriginalList);
    }

    /**
     * Returns the Index of the Test selected in the UI in the store
     * @param index Test's Index in DTO list
     * @return Test's index in store
     */
    public int getUnreportedTestDtoIndex(int index){
        List<unreportedTestDTO> unreportedTests=unreportedTestList();
        int indexInOriginalList;
        indexInOriginalList=unreportedTests.get(index).getIndex();
        return indexInOriginalList;
    }

    /**
     * Takes the parameters results of a test identified by its nhs Code
     * @param nhsCode Test's nhs Code
     * @return string formatted of the Test results
     */
    public String getTestParameterList(String nhsCode){
        test=getTestByNHSCode(nhsCode);
        List<TestParameter> listTestParameter=test.getTestParameterList();
        String parametersToString="";


        for (TestParameter a : listTestParameter){
            parametersToString=String.format("%s%n%n",parametersToString+a.toString());

        }

        return parametersToString;
    }

}


