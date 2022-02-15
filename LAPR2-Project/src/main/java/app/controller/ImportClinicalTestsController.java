package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.store.ClientStore;
import app.domain.store.ClinicalAnalysisLaboratoryStore;
import app.domain.store.TestStore;
import app.domain.store.TestTypeStore;
import app.mappers.dto.ClientDto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Luís Araújo
 */
public class ImportClinicalTestsController {
    private static final String NA = "NA";
    private static final int LAB_NHS_CODE_INDEX = 1;
    private static final int LABID_INDEX = 2;
    private static final int CLIENT_CCN_INDEX = 3;
    private static final int CLIENT_NHS_NUMBER_INDEX = 4;
    private static final int CLIENT_TIN_INDEX = 5;
    private static final int CLIENT_BIRTH_DATE_INDEX = 6;
    private static final int CLIENT_PHONE_NUMBER_INDEX = 7;
    private static final int CLIENT_NAME_INDEX = 8;
    private static final int CLIENT_EMAIL_INDEX = 9;
    private static final int CLIENT_ADDRESS_INDEX = 10;
    private static final int TEST_TYPE_INDEX = 11;
    private static final int PAREMETER_CATEGORY1_INDEX = 12;
    private static final int PARAMETER_HB000_INDEX = 13;
    private static final int PARAMETER_WBC00_INDEX = 14;
    private static final int PARAMETER_PLT00_INDEX = 15;
    private static final int PARAMETER_RBC00_INDEX = 16;
    private static final int PARAMETER_CATEGORY2_INDEX = 17;
    private static final int PARAMETER_HDL00_INDEX = 18;
    private static final int PARAMETER_CATEGORY3_INDEX = 19;
    private static final int PARAMETER_IgGAN_INDEX = 20;
    private static final int TEST_REGISTER_DATE_INDEX = 21;
    private static final int TEST_RESULTS_DATE_INDEX = 22;
    private static final int TEST_DIAGNOSIS_DATE_INDEX = 23;
    private static final int TEST_VALIDATION_DATE_INDEX = 24;
    private RegisterTestController registerTestController;
    private ClientStore clientStore;
    private TestTypeStore testTypeStore;
    private ClinicalAnalysisLaboratoryStore clinicalAnalysisLaboratoryStore;
    private ClinicalAnalysisLaboratory cal;
    private TestType testType;
    private TestStore testStore;
    private String[] header;
    private List<String[]> clientsFailure = new ArrayList<>();
    private App app;
    private Company company;

    public ImportClinicalTestsController() {
        app = App.getInstance();
        company = app.getCompany();
        clientStore = company.getClientStore();
        testStore = company.getTestStore();
        clinicalAnalysisLaboratoryStore = company.getClinicalAnalysisLaboratoryStore();
        testTypeStore = company.getTestTypeStore();
        registerTestController = new RegisterTestController();
    }

    public void readTests(String path) throws IOException {
        BufferedReader bufferedReader = getCSVFile(path);
        String line;
        line = bufferedReader.readLine();
        header = line.split(";");
        while ((line = bufferedReader.readLine()) != null) {
            try {
                List<String> tests = new ArrayList<String>();
                tests = readDataFromCSV(line);
                Client client = getClientWithInfo(tests);
                createTestWithInfo(tests, client);
            } catch (Exception e) {
                String[] values = line.split(";");
                String[] error=new String[2];
                error[0]=values[8];
                error[1]=e.getMessage();
                clientsFailure.add(error);
            }
        }
    }

    public List<String[]> getClientsFailure() {
        return clientsFailure;
    }

    private Client getClientWithInfo(List<String> tests) throws ParseException {
        try {
            return clientStore.getClient(tests.get(CLIENT_TIN_INDEX));
        } catch (IllegalArgumentException e) {
            ClientDto clientDto;
            String date = tests.get(CLIENT_BIRTH_DATE_INDEX).replace("/", "-");
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Date date1 = new Date();
            date1 = df.parse(date);

            String name = tests.get(CLIENT_NAME_INDEX);
            String ccn = String.format("%16s", tests.get(CLIENT_CCN_INDEX)).replace(" ", "0");
            String nhs = tests.get(CLIENT_NHS_NUMBER_INDEX);
            String tin = tests.get(CLIENT_TIN_INDEX);
            String phone_number = tests.get(CLIENT_PHONE_NUMBER_INDEX);
            String email = tests.get(CLIENT_EMAIL_INDEX);
            clientDto = new ClientDto(name, date1, ClientDto.Sex.NONE, ccn, nhs, tin, phone_number, email);
            Client client = new Client(clientDto);
            clientStore.addClient(client);
            return client;
        }
    }

    private void addTestToStore(Test test) {
        testStore.addTest(test);
    }

    private void createTestWithInfo(List<String> tests, Client client) {
        String nhsCode = tests.get(LAB_NHS_CODE_INDEX);
        String labID = tests.get(LABID_INDEX);

        List<Parameter> parametersToBeTested = new ArrayList<>();
        List<Parameter> parametersToBeTestedCat1 = new ArrayList<>();
        List<Double> results;
        double[] parameterCat1Results = new double[4];
        double parameterCat2Results = 0;
        double parameterCat3Results = 0;

        cal = clinicalAnalysisLaboratoryStore.getClinicalAnalysisLaboratory(labID);

        TestType tt = testTypeStore.getTestTypeByName(tests.get(TEST_TYPE_INDEX));

        String description = tt.getCollectingMethod();

        String category1Name = tests.get(PAREMETER_CATEGORY1_INDEX);
        if (!category1Name.equals(NA)) {
            ParameterCategory parameterCategory = tt.getCategoryWithName(category1Name);
            getParametersFromCat1(parameterCategory, parametersToBeTestedCat1);
            getResults(PARAMETER_HB000_INDEX, PARAMETER_RBC00_INDEX, parameterCat1Results, tests);
            parametersToBeTested.addAll(parametersToBeTestedCat1);
        }

        Parameter parameterCat2 = null;
        String category2Name = tests.get(PARAMETER_CATEGORY2_INDEX);
        if (!category2Name.equals(NA)) {
            ParameterCategory parameterCategory = tt.getCategoryWithName(category2Name);
            parameterCat2 = parameterCategory.getParameterBy(header[PARAMETER_HDL00_INDEX]);
            parametersToBeTested.add(parameterCat2);
            String result = tests.get(PARAMETER_HDL00_INDEX).replace(",", ".");
            parameterCat2Results = Double.parseDouble(result);
        }

        Parameter parameterCat3 = null;
        String category3Name = tests.get(PARAMETER_CATEGORY3_INDEX);
        if (!category3Name.equals(NA)) {
            ParameterCategory parameterCategory = tt.getCategoryWithName(category3Name);
            parameterCat3 = parameterCategory.getParameterBy(header[PARAMETER_IgGAN_INDEX]);
            parametersToBeTested.add(parameterCat3);
            String result = tests.get(PARAMETER_IgGAN_INDEX).replace(",", ".");
            parameterCat3Results = Double.parseDouble(result);
        }

        Test test = testStore.createTest(cal, client, nhsCode, description, tt, parametersToBeTested);
        addTestToStore(test);
        test.setCurrentState(Constants.STATE_VALIDATED);

        if (!category1Name.equals(NA)) {
            addResultsCat1(parametersToBeTestedCat1, parameterCat1Results, test);
        }
        if (!category2Name.equals(NA)) {
            test.addTestResult(parameterCat2.getCode(), parameterCat2Results, "");
        }
        if (!category3Name.equals(NA)) {
            test.addTestResult(parameterCat3.getCode(), parameterCat3Results, "");
        }

        String dateValidation = tests.get(TEST_VALIDATION_DATE_INDEX).replace("/", "-");
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        Date date1 = null;

        String dateRegister = tests.get(TEST_REGISTER_DATE_INDEX).replace("/", "-");
        Date date2 = null;

        String dateDiagnosis = tests.get(TEST_DIAGNOSIS_DATE_INDEX).replace("/", "-");
        Date date3 = null;

        try {
            date1 = df.parse(dateValidation);
        } catch (ParseException e) {
            date1 = new Date();
            System.out.println(e.getMessage());
        }

        try {
            date2 = df.parse(dateRegister);
        } catch (ParseException e) {
            date2 = new Date();
            System.out.println(e.getMessage());
        }

        try {
            date3 = df.parse(dateDiagnosis);
        } catch (ParseException e) {
            date3 = new Date();
            System.out.println(e.getMessage());
        }

        test.setValidationDate(date1);
        test.setRegistrationDate(date2);
        test.setDiagnosisDate(date3);
    }

    public BufferedReader getCSVFile(String fileName) throws FileNotFoundException {
        return new BufferedReader(new FileReader(fileName));
    }

    public List<String> readDataFromCSV(String line) throws IOException {
        List<String> tests = new ArrayList<>();
        String[] values = line.split(";");
        for (int a = 0; a <= TEST_VALIDATION_DATE_INDEX; a++) {
            tests.add(values[a]);
            //tests.add(Arrays.toString(values) + "\n");
        }
        return tests;
    }

    public void getParametersFromCat1(ParameterCategory category, List<Parameter> parameters) {
        for (int i = PARAMETER_HB000_INDEX; i <= PARAMETER_RBC00_INDEX; i++) {
            Parameter parameter = category.getParameterBy(header[i]);
            parameters.add(parameter);
        }
    }

    public void getResults(int start, int end, double[] parameterResults, List<String> line) {
        for (int i = PARAMETER_HB000_INDEX; i <= PARAMETER_RBC00_INDEX; i++) {
            for (int j = 0; j < parameterResults.length; j++) {
                parameterResults[j] = Double.parseDouble(line.get(i).replace(",", "."));
            }
        }
    }

    public void addResultsCat1(List<Parameter> parameters, double[] parameterResults, Test test) {
        for (int i = 0; i < parameterResults.length; i++) {
            Parameter parameter = parameters.get(i);
            String code = parameter.getCode();
            test.addTestResult(code, parameterResults[i], "");
        }
    }
    public boolean saveData(){
        return company.saveClients(clientStore) && company.saveTests(testStore) && company.saveUsers();
    }
}

