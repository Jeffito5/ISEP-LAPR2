package app.controller;

import app.domain.model.Company;
import app.domain.model.Parameter;
import app.domain.model.Test;
import app.domain.model.TestParameter;
import app.domain.shared.Constants;
import app.domain.store.TestStore;
import app.mappers.ParameterMapper;
import app.mappers.dto.ParameterDto;

import java.util.ArrayList;
import java.util.List;

public class RecordTestResultController {
    private final TestStore testStore;
    private Test test;
    private final ParameterMapper parameterMapper;
    private final Company company;

    public RecordTestResultController(){
        // Singleton
        App app = App.getInstance();
        company = app.getCompany();

        //Stores
        this.testStore = company.getTestStore();

        // Mappers
        parameterMapper = new ParameterMapper();
    }

    public boolean hasTest(String testNhsCode){

        try{
            test = testStore.getTest(testNhsCode);
            return true;
        } catch (IllegalArgumentException e){
            System.out.println("\nERROR: " + e.getMessage());
            return false;
        }

    }

    public boolean hasTestWithSample(String sampleCode){
        try{
            test = testStore.getTestWithSample(sampleCode);
            return true;
        } catch (IllegalArgumentException e){
            System.out.println("\nERROR: " + e.getMessage());
            return false;
        }
    }

    public List<ParameterDto> getTestParameters(){
        if (!test.getCurrentState().equals("Samples Collected"))
            throw new IllegalArgumentException("Results have been already recorded.");

        List<TestParameter> testParameters = test.getTestParameterList();
        List<Parameter> parameterList = new ArrayList<>();
        for (TestParameter testParameter : testParameters){
            Parameter parameter = testParameter.getParameter();
            parameterList.add(parameter);
        }
        return parameterMapper.toDTO(parameterList);
    }

    public void addTestResult(String parameterCode, double result, String metric){

        test.addTestResult(parameterCode, result,metric);
    }

    public void setTestResultStateToAnalyzed(){
        test.setCurrentState(Constants.STATE_SAMPLES_ANALYZED);
    }

    public void setTestResultDate(){
        test.setAnalysisDate();
    }

    public boolean saveStore()
    {
        return company.saveTests(testStore);
    }
}
