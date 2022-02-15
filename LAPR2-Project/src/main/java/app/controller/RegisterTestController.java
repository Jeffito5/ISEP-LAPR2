package app.controller;

import app.domain.model.*;
import app.domain.store.ClientStore;
import app.domain.store.ClinicalAnalysisLaboratoryStore;
import app.domain.store.TestStore;
import app.domain.store.TestTypeStore;
import app.mappers.ParameterMapper;
import app.mappers.TestTypeMapper;
import app.mappers.dto.ParameterCategoryDto;
import app.mappers.ParameterCategoryMapper;
import app.mappers.dto.ParameterDto;
import app.mappers.dto.TestTypeDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rui Rocha
 */
public class RegisterTestController {

    /**
     * Company.
     */
    private final Company company;

    /**
     * Stores.
     */
    private final TestStore testStore;
    private final TestTypeStore testTypeStore;
    private final ClientStore clientStore;
    private final ClinicalAnalysisLaboratoryStore clinicalAnalysisLaboratoryStore;

    /**
     * Mappers.
     */
    private final TestTypeMapper testTypeMapper;
    private final ParameterMapper parameterMapper;
    private final ParameterCategoryMapper parameterCategoryMapper;

    /**
     * Created test.
     */
    private Test test;

    /**
     * Chosen test type.
     */
    private TestType testType;

    /**
     * Chosen client.
     */
    private Client client;

    /**
     * Chosen Parameter Category.
     */
    private ParameterCategory parameterCategory;

    /**
     * List of chosen parameters to be included in the test.
     */
    private final List <Parameter> parametersToBeTested;

    /**
     * Constructs an instance of the Controller,
     * using singleton to access the app instance;
     */
    public RegisterTestController()
    {
        // Singleton
        App app = App.getInstance();
        company = app.getCompany();

        //Stores
        this.testStore = company.getTestStore();
        this.testTypeStore = company.getTestTypeStore();
        this.clientStore = company.getClientStore();
        this.clinicalAnalysisLaboratoryStore = company.getClinicalAnalysisLaboratoryStore();

        //Mappers
        testTypeMapper =  new TestTypeMapper();
        parameterMapper = new ParameterMapper();
        parameterCategoryMapper = new ParameterCategoryMapper();

        //Array of Parameters of the Test Type's Parameter Categories
        //testTypeParameters = new ArrayList<>();
        //LIST OF THE SELECTED PARAMETERS TO BE TESTED
        parametersToBeTested = new ArrayList<>();
    }

    /**
     * Retrieves a client from the client store.
     *
     * @param clientTin client's Tax Identification Number.
     * @return true if the client exists and was retrieved from the store.
     * Otherwise returns false;
     */
    public boolean hasClient(String clientTin){
        try{
            this.client = clientStore.getClient(clientTin);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("\nERROR: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves the list of test types from the test type store and
     * converts it to a list of DTOs.
     *
     * @return List of TestTypeDto.
     */
    public List<TestTypeDto> getTestTypes(){
        List<TestType> testTypeList = testTypeStore.getTestTypes();
        return testTypeMapper.toDTO(testTypeList);
    }

    /**
     * Retrieves the list of Parameter Categories from the test type and converts it to a list of DTOs.
     *
     * @param testTypeCode Test type code.
     * @return List of ParameterCategoryDto.
     */
    public List<ParameterCategoryDto> getParameterCategories(String testTypeCode){

        //CLEARS ARRAY OF THE TEST TYPE'S PARAMETERS
        parametersToBeTested.clear();

        testType = testTypeStore.getTestType(testTypeCode);
        List<ParameterCategory> parameterCategoryList = testType.getCategories();
        return parameterCategoryMapper.toDTO(parameterCategoryList);
    }

    /**
     * Retrieves the list of Parameter Categories from the test type and converts it to a list of DTOs.
     *
     * @param parameterCategoryCode Code of the parameter category on test type's category list.
     * @return List of ParameterDTO.
     */
    public List<ParameterDto> getParameters(String parameterCategoryCode){

        //PARAMETER CATEGORY
        parameterCategory = testType.getCategory(parameterCategoryCode);

        //ADDS PARAMETER CATEGORY'S PARAMETERS
        List<Parameter> testTypeParameters = new ArrayList<>(parameterCategory.getParameters());

        //CONVERSION TO DTO
        return parameterMapper.toDTO(testTypeParameters);
    }

    /**
     * Adds a chosen parameter to a list of parameters to be included in the test.
     *
     * @param parameterCode Index of the parameter on Parameter Category's parameter list.
     * @return true if the parameter does not already exist on the list and, therefore,
     * was added. Otherwise returns false.
     */
    public boolean addParametersForTest(String parameterCode){

        Parameter parameter = parameterCategory.getParameterBy(parameterCode);

        if (!parametersToBeTested.contains(parameter))
            return parametersToBeTested.add(parameter);
        else
            return false;
    }

    /**
     * Adds a chosen parameter to a list of parameters to be included in the test.
     *
     * @param clinicalAnalysisLaboratoryId  Clinical Analysis Laboratory's Identification Number.
     * @param nhsCode Test's NHS Code.
     *
     * @return true if the test was successfully created. Otherwise returns false.
     */
    public boolean createTest(String clinicalAnalysisLaboratoryId, String nhsCode){

        // Gets the Clinical Analysis Laboratory ID from the user session
        ClinicalAnalysisLaboratory clinicalAnalysisLaboratory = getClinicalAnalysisLaboratory(clinicalAnalysisLaboratoryId);


        // TEST CREATION
        try{
            test = testStore.createTest(clinicalAnalysisLaboratory, client, nhsCode, testType.getCollectingMethod(),
                    testType, parametersToBeTested);
            return true;

        } catch (IllegalArgumentException e){
            System.out.println("\nERROR: " + e.getMessage());
            return false;
        }
    }

    /**
     * Asks the test store to display the currently created test.
     */
    public void showTest(){
        testStore.showTest(test);
    }

    /**
     * Adds the test to both the test store and the client, through the client store.
     *
     * @return true if the test was successfully added. Otherwise, returns false.
     */
    public boolean addTest() {
        try{
            testStore.addTest(test);
            clientStore.addTest(client, test);
            return true;
        } catch (IllegalArgumentException e){
            System.out.println("\nERROR: " + e.getMessage());
            return false;
        }
    }

    /**
     * Serializes the test.
     *
     * @return true if the test was successfully serialized. Otherwise, returns false.
     */
    public boolean saveTest()
    {
        return company.saveTests(testStore);
    }

    /**
     * Retrieves a Clinical Analysis Laboratory from its store, through a given ID.
     *
     * @param clinicalAnalysisLaboratoryId Clinical Analysis Laboratory's Identification Number.
     * @return Clinical Analysis Laboratory object.
     */
    private ClinicalAnalysisLaboratory getClinicalAnalysisLaboratory (String clinicalAnalysisLaboratoryId){
        return clinicalAnalysisLaboratoryStore.getClinicalAnalysisLaboratory(clinicalAnalysisLaboratoryId);
    }
}
