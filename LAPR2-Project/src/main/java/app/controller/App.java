package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;

import app.domain.tasks.TaskManager;
import auth.AuthFacade;
import auth.UserSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App {

    private final Company company;
    private final AuthFacade authFacade;


    public App()
    {
        Properties props = getProperties();
        this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION));
        this.authFacade = this.company.getAuthFacade();

/*        TaskManager taskManager = TaskManager.getInstance();
        taskManager.scheduleNhsReport(company);*/
        bootstrap();
    }



    public Company getCompany()
    {
        return this.company;
    }


    public UserSession getCurrentUserSession()
    {
        return this.authFacade.getCurrentUserSession();
    }

    public boolean doLogin(String email, String pwd)
    {
        return this.authFacade.doLogin(email,pwd).isLoggedIn();
    }

    public void doLogout()
    {
        this.authFacade.doLogout();
    }

    private Properties getProperties()
    {
        Properties props = new Properties();

        // Add default properties and values
        //props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "Many Labs");
        //props.setProperty(Constants.PARAMS_SORTING_ALGORITHM, "app.domain.model.algorithms.sorting.QuickSort");


        // Read configured values
        try
        {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        }
        catch(IOException ex)
        {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return props;
    }


    private void bootstrap()
    {
        //ADMIN
        this.authFacade.addUserRole(Constants.ROLE_ADMIN,Constants.ROLE_ADMIN);
        this.authFacade.addUserWithRole("Main Administrator", "admin@lei.sem2.pt", "123456",Constants.ROLE_ADMIN);

        //RECEPTIONIST
        this.authFacade.addUserRole(Constants.ROLE_RECEPTIONIST,Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserWithRole("Receptionist", "receptionist@lei.sem2.pt", "123456",Constants.ROLE_RECEPTIONIST);

        //SPECIALIST DOCTOR
        this.authFacade.addUserRole(Constants.ROLE_SPEC_DOC,Constants.ROLE_SPEC_DOC);
        this.authFacade.addUserWithRole("Specialist Doctor", "specdoc@lei.sem2.pt","123456", Constants.ROLE_SPEC_DOC);

        //CLINICAL CHEMISTRY TECHNOLOGIST
        this.authFacade.addUserRole(Constants.ROLE_CLI_CHEM_TECH, Constants.ROLE_CLI_CHEM_TECH);
        this.authFacade.addUserWithRole("Clinical Chemistry Technologist", "clichemtech@lei.sem2.pt", "123456", Constants.ROLE_CLI_CHEM_TECH);

        //LABORATORY COORDINATOR
        this.authFacade.addUserRole(Constants.ROLE_LAB_COORD, Constants.ROLE_LAB_COORD);
        this.authFacade.addUserWithRole("Laboratory Coordinator", "labcoord@lei.sem2.pt", "123456", Constants.ROLE_LAB_COORD);

        //MEDICAL LABORATORY TECHNICIAN
        this.authFacade.addUserRole(Constants.ROLE_MED_LAB_TECH,Constants.ROLE_MED_LAB_TECH);
        this.authFacade.addUserWithRole("Medical Lab Technician","medlabtech@lei.sem2.pt","123456",Constants.ROLE_MED_LAB_TECH);

        //CLIENT
        this.authFacade.addUserRole(Constants.ROLE_CLIENT,Constants.ROLE_CLIENT);

        //ROLES
        String q1;
        q1="SPECIALIST DOCTOR";
        this.authFacade.addUserRole(q1,q1);

        String q2;
        q2="RECEPTIONIST";
        this.authFacade.addUserRole(q2,q2);

        //MED_LAB_TECH
        this.authFacade.addUserRole(Constants.ROLE_MED_LAB_TECH,Constants.ROLE_MED_LAB_TECH);

        String q4;
        q4="CLINCHETECH";
        this.authFacade.addUserRole(q4,q4);

        String q5;
        q5="LABCORD";
        this.authFacade.addUserRole(q5,q5);

        String q6;
        q6="ADMINISTRATOR";
        this.authFacade.addUserRole(q6,q6);



        //PARAMETER CATEGORIES
        List<ParameterCategory> parameterCategoryList = new ArrayList<>();
        List<ParameterCategory> parameterCategoryList2 = new ArrayList<>();

        ParameterCategory parameterCategory1 = new ParameterCategory("Hemogram", "Blood", "12345");
        ParameterCategory parameterCategory2 = new ParameterCategory("Covid", "Covid Antibodies", "88888");
        ParameterCategory parameterCategory3 = new ParameterCategory("Cholesterol", "Cholesterol", "65462");

        parameterCategoryList.add(parameterCategory2);

        parameterCategoryList2.add(parameterCategory1);

        parameterCategoryList2.add(parameterCategory3);


        this.company.getParameterCategoryStore().addParameterCategory(parameterCategory1);
        this.company.getParameterCategoryStore().addParameterCategory(parameterCategory2);

        //TEST TYPES
        List<String> testTypeList = new ArrayList<>();
        TestType testType1 = new TestType("666", "Covid", "Swab", parameterCategoryList);
        TestType testType2 = new TestType("888", "Blood", "Blood specimen", parameterCategoryList2);
        testTypeList.add(testType1.getDescription());
        testTypeList.add(testType2.getDescription());
        this.company.getTestTypeStore().addTestType(testType1);
        this.company.getTestTypeStore().addTestType(testType2);


        //PARAMETERS
        Parameter parameter1, parameter2, parameter3, parameter4, parameter5, parameter6,
                parameter7, parameter8, parameter9, parameter10, parameter11;
        parameter1 = new Parameter("RBC", "RBC00", "RedBloodCells");
        parameterCategory1.addParameter(parameter1);

        parameter2 = new Parameter("WBC", "WBC00", "WhiteBloodCells");
        parameterCategory1.addParameter(parameter2);

        parameter4 = new Parameter("HB", "HB000", "Haemoglobin");
        parameterCategory1.addParameter(parameter4);

        parameter5 = new Parameter("PLT", "PLT00", "Platelet Count");
        parameterCategory1.addParameter(parameter5);

        parameter6 = new Parameter("MCV", "MCV00", "Mean Cell Volume");
        parameterCategory1.addParameter(parameter6);

        parameter7 = new Parameter("MCH", "MCH00", "Mean Cell HB");
        parameterCategory1.addParameter(parameter7);

        parameter8 = new Parameter("MCH", "MCHC0", "Mean Cell HB Conc");
        parameterCategory1.addParameter(parameter8);

        parameter9 = new Parameter("ESR", "ESR00", "Erythrocyte Sed Rate");
        parameterCategory1.addParameter(parameter9);

        parameter10 = new Parameter("IgC", "IgGAN", "IgC antibodies");
        parameterCategory2.addParameter(parameter10);

        parameter11 = new Parameter("HDL", "HDL00", "Cholesterol");
        parameterCategory3.addParameter(parameter11);



        //CLINICAL ANALYSIS LABORATORIES
        ClinicalAnalysisLaboratory cal1, cal2, cal3, cal4, cal5, cal6;

        cal1 = new ClinicalAnalysisLaboratory("Many Labs London", "001DO", "12345678909", "1234567890", "Oxford Street 41", testTypeList);
        cal2 = new ClinicalAnalysisLaboratory("Many Labs Manchester", "001MA", "12345654321", "9999999999", "Shirley Road 66", testTypeList);
        cal3 = new ClinicalAnalysisLaboratory("Many Labs Liverpool", "001SO", "12345673409", "1234787890", "Acton Road, 32", testTypeList);
        cal4 = new ClinicalAnalysisLaboratory("Many Labs Birmingham", "001LR", "15645654321", "9991299999", "Alma Street 19", testTypeList);
        cal5 = new ClinicalAnalysisLaboratory("Many Labs Sheffield", "001WA", "12378678909", "1234837890", "Aughton Avenue 26", testTypeList);
        cal6 = new ClinicalAnalysisLaboratory("Many Labs Coventry", "001LN", "12342954321", "9999901999", " Woodshires Road 6", testTypeList);


        this.company.getClinicalAnalysisLaboratoryStore().addClinicalAnalysisLaboratory(cal1);
        this.company.getClinicalAnalysisLaboratoryStore().addClinicalAnalysisLaboratory(cal2);
        this.company.getClinicalAnalysisLaboratoryStore().addClinicalAnalysisLaboratory(cal3);
        this.company.getClinicalAnalysisLaboratoryStore().addClinicalAnalysisLaboratory(cal4);
        this.company.getClinicalAnalysisLaboratoryStore().addClinicalAnalysisLaboratory(cal5);
        this.company.getClinicalAnalysisLaboratoryStore().addClinicalAnalysisLaboratory(cal6);

        // CLIENTS
        Date date = new Date(80,2,20);
        Client c1 = new Client("Joao Violante", date, Client.Sex.MALE, "1234567899999999",
                "1234554321", "1234554321", "12345678901", "joao@gmail.com");
        Client c2 = new Client("Pedro Carvalho", date, Client.Sex.MALE, "1234567890123456",
                "5364789287", "7487364523", "73648276354", "pedro0@hotmail.com");
        Client c3 = new Client("Luisa Moreira", date, Client.Sex.FEMALE, "8888888888888888",
                "1234567890", "1234567890", "12345678909", "luisa0@hotmail.com");
        Client c4 = new Client("Carlos Carvalho", date, Client.Sex.MALE, "7777777777777777",
                "1234567897", "1234567894", "12345678906", "carlos0@hotmail.com");
        Client c5 = new Client("Carlos Carvalho", date, Client.Sex.MALE, "2222222222222222",
                "1234567332", "1234567465", "12345678123", "carlos2@hotmail.com");
        this.company.getClientStore().addClient(c1);
        this.company.getClientStore().addClient(c2);
        this.company.getClientStore().addClient(c3);
        this.company.getClientStore().addClient(c4);
        this.company.getClientStore().addClient(c5);


        List<Parameter> p1= new ArrayList<>();
        p1.add(parameter1);
        p1.add(parameter2);

        List<Parameter> covidParameter = new ArrayList<>();
        covidParameter.add(parameter10);



        Test test1 = this.company.getTestStore().createTest(cal1, c1,"123456789011", testType2.getCollectingMethod(),
                testType2,p1);
        Test test2 = this.company.getTestStore().createTest(cal1, c1,"123456789012", testType2.getCollectingMethod(),
                testType2,p1);
        Test test3=this.company.getTestStore().createTest(cal2, c2,"123456789013",testType1.getCollectingMethod(),
                testType1,p1);
        Test test4=this.company.getTestStore().createTest(cal2, c3,"123456789014",testType2.getCollectingMethod(),
                testType2,p1);
        Test test5=this.company.getTestStore().createTest(cal1, c4,"123456789015",testType1.getCollectingMethod(),
                testType1,p1);

        Test test6=this.company.getTestStore().createTest(cal1, c4,"123456789088",testType1.getCollectingMethod(),
                testType1,covidParameter);
        Test test7=this.company.getTestStore().createTest(cal1, c3,"123456789077",testType1.getCollectingMethod(),
                testType1,covidParameter);
        Test test8=this.company.getTestStore().createTest(cal1, c2,"123456789022",testType1.getCollectingMethod(),
                testType1,covidParameter);
        Test test9=this.company.getTestStore().createTest(cal1, c1,"562543142567",testType1.getCollectingMethod(),
                testType1,covidParameter);
        Test test10=this.company.getTestStore().createTest(cal1, c5,"172635432568",testType1.getCollectingMethod(),
                testType1,covidParameter);
        Test test11=this.company.getTestStore().createTest(cal1, c1,"837652637849",testType1.getCollectingMethod(),
                testType1,covidParameter);
        Test test12=this.company.getTestStore().createTest(cal1, c5,"387254637610",testType1.getCollectingMethod(),
                testType1,covidParameter);



      this.company.getTestStore().addTest(test1); //INDEX: O
        this.company.getTestStore().addTest(test2); //INDEX: 1
        this.company.getTestStore().addTest(test3); //INDEX: 2
        this.company.getTestStore().addTest(test4); //INDEX: 3
        this.company.getTestStore().addTest(test5); //INDEX: 4
        this.company.getTestStore().addTest(test6);
        this.company.getTestStore().addTest(test7);
        this.company.getTestStore().addTest(test8);
        this.company.getTestStore().addTest(test9);
        this.company.getTestStore().addTest(test10);


        test1.addTestResult("RBC00", 4.8, "mg");
        test1.addTestResult("WBC00", 34.6, "fl");
        test1.setAnalysisDate();

        test1.createReport("Low white blood cells.");
        test1.setDiagnosisDate();
        test1.setValidationDate();
        test1.setCurrentState(Constants.STATE_VALIDATED);

        LocalDate dateAux = LocalDate.of(2021, 6, 11);
        Date testDate = java.util.Date.from(dateAux.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        LocalDate dateAux2 = LocalDate.of(2021, 6, 10);
        Date testDate2 = java.util.Date.from(dateAux2.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        test6.addTestResult("IgGAN", 1.6, "Value");
        test7.addTestResult("IgGAN", 1.7, "Value");
        test8.addTestResult("IgGAN", 0.3, "Value");
        test9.addTestResult("IgGAN", 1.8, "Value");
        test10.addTestResult("IgGAN", 1.1, "Value");
        test11.addTestResult("IgGAN", 1.1, "Value");
        test12.addTestResult("IgGAN", 1.8, "Value");

        test6.setCurrentState(Constants.STATE_VALIDATED);
        test6.setValidationDate();
        test7.setCurrentState(Constants.STATE_VALIDATED);
        test7.setValidationDate();
        test8.setCurrentState(Constants.STATE_VALIDATED);
        test8.setValidationDate();
        test9.setCurrentState(Constants.STATE_VALIDATED);
        test9.setValidationDate(testDate);
        test10.setCurrentState(Constants.STATE_VALIDATED);
        test10.setValidationDate(testDate);
        test11.setCurrentState(Constants.STATE_VALIDATED);
        test11.setValidationDate(testDate2);
        test12.setCurrentState(Constants.STATE_VALIDATED);
        test12.setValidationDate(testDate2);


        //test1.setActualStatetoSamplesAnalyzed();
        //test3.setActualStatetoSamplesAnalyzed();
        //test5.setActualStatetoSamplesAnalyzed();

    }

    // Extracted from https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static App singleton = null;
    public static App getInstance()
    {
        if(singleton == null)
        {
            synchronized(App.class)
            {
                singleton = new App();
            }
        }
        return singleton;
    }

    public void stopTasks()
    {
        this.company.stopSendNHSReportTask();
    }


}
