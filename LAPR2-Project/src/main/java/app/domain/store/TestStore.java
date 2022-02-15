package app.domain.store;
import app.domain.model.*;

import app.mappers.ClientResultsTestMapper;
import app.mappers.dto.ClientResultsTestDTO;
import app.mappers.dto.unreportedTestDTO;
import app.mappers.unreportedTestMapper;

import app.domain.shared.Constants;
import app.ui.console.utils.Utils;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 * @author João Violante
 */
public class TestStore implements Serializable {

    /**
     * List of Tests.
     */
    private final List<Test> store;

    /**
     * The last attributed test code.
     */
    private int lastCode;

    /**
     * List of Test's NHS Codes
     */
    List<String> listNHSCode = new ArrayList<>();

    private Date intervalDateBeginning;

    private Date intervalDateEnd;

    private int numberOfDays;

    public TestStore() {
        store = new ArrayList<>();
    }

    /**
     * Creates an instance of a test and, if not duplicated, sets the generated test code.
     *
     * @param clinicalAnalysisLaboratory  Clinical Analysis Laboratory object.
     * @param client Client object.
     * @param nhsCode Test's NHS code.
     * @param description Test's description (test type's collection method).
     * @param testType Test type object.
     * @param parametersToBeTested List of parameters to be tested.
     *
     * @return Test instance.
     */
    public Test createTest(ClinicalAnalysisLaboratory clinicalAnalysisLaboratory, Client client, String nhsCode,
                           String description, TestType testType, List<Parameter> parametersToBeTested)
    {
        Test test = new Test(clinicalAnalysisLaboratory, client, nhsCode, description, testType, parametersToBeTested);
        if (validateTest(test))
        {
            test.setCode(generateTestCode());
        }
        return test;
    }

    /**
     * Validates the test, by checking that it's not duplicated.
     *
     * @param test Test object.
     *
     * @return True if the store does not contain the given test.
     */
    public boolean validateTest(Test test) {
        if (store.contains(test))
        {
            throw new IllegalArgumentException("Test already exists.");
        }
        return true;
    }

    /**
     * Adds the test to the store, not without validating it first.
     * Sets the test's state to registered.
     * Increments the last code variable.
     *
     * @param test Test object.
     */
    public void addTest (Test test){
        validateTest(test);
        store.add(test);
        test.setCurrentState(Constants.STATE_REGISTERED);
        lastCode++;
    }

    /**
     * Creates a 12 digits code containing the last code variable.
     *
     */
    private String generateTestCode () {
        return String.format("%012d", lastCode + 1);
    }

    /**
     * Returns the Test Store's list of tests.
     *
     * @return Test Store's list of tests.
     */
    public List<Test> getTests () {
        return store;
    }

    /**
     * Returns the test that contains the given NHS code.
     *
     * @param testNhsCode Test's NHS Code.
     *
     * @return Test object.
     */
    public Test getTest(String testNhsCode){
        for (Test test : store)
        {
            if (test.getNhsCode().equals(testNhsCode))
            {
                return test;
            }
        }
        throw new IllegalArgumentException ("Test not found.");
    }

    /**
     * Returns the test that contains a sample with the given code.
     *
     * @param sampleCode Sample's barcode.
     *
     * @return Test object.
     */
    public Test getTestWithSample(String sampleCode) {
        for (Test test : store)
        {
            if(test.hasSample(sampleCode))
            {
                return test;
            }
        }
        throw new IllegalArgumentException ("Test not found.");
    }

    /**
     * Prints the given test's information, using the Test's toString method to obtain it.
     *
     * @param test Test.
     *
     */
    public void showTest(Test test) {
        System.out.println(test.toString());
    }

    /**
     * This method will checks if in the store is any Test ready to have report made, if it is,
     * the nhs Code and index on the original list will be saved on 2 different lists,
     * then returns a List created by Mapper class with the previous 2 lists inside
     * @return DTO List
     */
    public List<unreportedTestDTO> showUnreportedTestList() {
        unreportedTestMapper mapper = new unreportedTestMapper();
        List<Integer> listIndex = new ArrayList<>();
        for (Test a : store)
        {
            if (a.getCurrentState().equals("Samples Analyzed"))
            {
                listIndex.add(store.indexOf(a));
                listNHSCode.add(a.getNhsCode());
            }
        }

        return mapper.ListDTO(listIndex, listNHSCode);
    }

    public List<Test> getClientTestList(Client c){
        List<Test> clientTests=new ArrayList<>();

        for (Test a: store) {
            if(a.getClient().equals(c)){
                clientTests.add(a);
            }
        }
        return clientTests;
    }

    public List<Test> getClientTestListByState(Client client, String state){
        List<Test> clientTests=new ArrayList<>();

        for (Test test: store) {
            if(test.getClient().equals(client) && test.getCurrentState().equals("Validated")){
                clientTests.add(test);
            }
        }
        return clientTests;
    }

    public List<Test> getTestListByState(String state){
        List<Test> tests=new ArrayList<>();

        for (Test test: store) {
            if(test.getCurrentState().equals(state)){
                tests.add(test);
            }
        }
        return tests;
    }

    /**
     * Create a List of Test's NHS Codes of a certain client that has passed through certain steps.
     * This list is then sent to a mapper to be transformed to a DTO
     * @return Test's List DTO
     */
    public List<ClientResultsTestDTO> showClientTests(){
        List<String> nhsCodeList=new ArrayList<>();
        ClientResultsTestMapper mapper=new ClientResultsTestMapper();
        for(Test a : store){
            if(a.getCurrentState().equals("Validated")){
                nhsCodeList.add(a.getNhsCode());
            }
        }
        return mapper.nhsCodeListToDTO(nhsCodeList);
    }

    /**
     * Finds the NHS Code of a Test by the Test's index on the store
     * @param index Test's index store
     * @return Test's NHS Code
     */
    public String findNHSCodeByIndex ( int index){

            return store.get(index).getNhsCode();
    }

    /**
     * Returns number of tests with a certain state
     * @param testState test's state
     * @return number of tests
     */
    public int getNumberOfTests(String testState){
        int cont=0;
        for (Test a: store) {
            if(a.getCurrentState().equals(testState)){
                cont++;
            }
        }

        return cont;
    }


    /**
     * Returns an array with the difference between registered tests and available tests
     * between a certain interval of time
     * @param dateBeginning date of beginning of the interval of time
     * @param dateEnd date of end of the interval of time
     * @return returns an array with the difference of test between 2 dates
     * @throws Exception
     */
    public int[] getSequenceList(String dateBeginning, String dateEnd) throws Exception{
        List<Integer> sequenceList=new ArrayList<>();

        numberOfDays=getNumberOfDays(dateBeginning,dateEnd);




        intervalDateBeginning=StringToDate(dateBeginning);

        intervalDateEnd=StringToDate(dateEnd);
        intervalDateEnd.setHours(23);
        intervalDateEnd.setMinutes(59);
        intervalDateEnd.setSeconds(59);



        Date intervalDateDay=intervalDateBeginning;
        intervalDateDay.setHours(8);
            for (int i = 0; i < numberOfDays; i++) {
                Date intervalDateLower=intervalDateDay;
                Date intervalDateUpper=addMinutesToDate(intervalDateLower);
                for (int e = 0; e < 24; e++) {
                    sequenceList.add(getNumberOfRegisteredTestsDate(intervalDateLower, intervalDateUpper)
                            -getNumberOfAvailableTestsDate(intervalDateLower,intervalDateUpper));
                    intervalDateLower=addMinutesToDate(intervalDateLower);
                    intervalDateUpper=addMinutesToDate(intervalDateUpper);

                }
                intervalDateDay=addDayToDate(intervalDateDay);
            }

        int[] sequenceArray=IntegerToInt(sequenceList);




            return sequenceArray;
    }

    /**
     * Converts an Integer list to an array of ints
     * @param list list
     * @return array of ints
     */
    private int[] IntegerToInt(List<Integer> list){
        int[] array=new int[list.size()];
        int i=0;
        for (Integer a : list){
            array[i]=a;
            i++;
        }
        return array;
    }

    /**
     * Adds a day to a certain date
     * @param date Date
     * @return returns date with the day added
     */
    private Date addDayToDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, 24);

        Date addDay =  calendar.getTime();
        addDay.setHours(8);
        return addDay;
    }

    /**
     * Adds 30 minutes to a certain date
     * @param date date
     * @return returns date with the 30 minutes added
     */
    private Date addMinutesToDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, 30);
        Date addMinutes =  calendar.getTime();
        return addMinutes;
    }

    /**
     * Gets the number of Tests registered between a certain interval of time
     * @param intervalBegin beginning of the interval
     * @param intervalEnd end of the interval
     * @return numbers of tests registered
     */
    public int getNumberOfRegisteredTestsDate(Date intervalBegin,Date intervalEnd){
        int i=0;
        for (Test a: store) {
            if(a.getCurrentState().equalsIgnoreCase("Registered") && a.getRegistrationDate().after(intervalBegin) && a.getRegistrationDate().before(intervalEnd)){
                i++;
            }

        }
        return i;
    }

    /**
     * Gets the number of Tests available between a certain interval of time
     * @param intervalBegin beginning of the interval
     * @param intervalEnd end of the interval
     * @return numbers of tests available
     */
    public int getNumberOfAvailableTestsDate(Date intervalBegin,Date intervalEnd){
        int i=0;
        for (Test a: store) {
            if(a.getCurrentState().equalsIgnoreCase("Validated") && a.getRegistrationDate().after(intervalBegin) && a.getRegistrationDate().before(intervalEnd)){
                i++;
            }

        }
        return i;
    }

    /**
     * Converts a string to a date
     * @param stringDate date in string
     * @return Date
     */
        private Date StringToDate(String stringDate){
            Date date;
            String [] arrayDate;
            int day;
            int month;
            int year;

            arrayDate=stringDate.split("/");
            day=Integer.parseInt(arrayDate[0]);
            month=Integer.parseInt(arrayDate[1])-1;
            year=Integer.parseInt(arrayDate[2])-1900;
            date=new Date(year,month,day);

            return date;
        }

    /**
     * Gets number of days between 2 dates
     * @param dateB date beginning
     * @param dateE date end
     * @return number of days
     * @throws Exception exception
     */
        private int getNumberOfDays(String dateB, String dateE) throws Exception{



        Date dateBeginning=new SimpleDateFormat("dd/MM/yyyy").parse(dateB);
        Date dateEnd=new SimpleDateFormat("dd/MM/yyyy").parse(dateE);



            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(dateBeginning);
            cal2.setTime(dateEnd);

            int numberOfDays = 0;
            while (cal1.before(cal2)) {
                if (Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK)) {
                    numberOfDays++;
                }
                cal1.add(Calendar.DATE,1);
            }
            return numberOfDays+1;


        }

    /**
     * Returns a list of the Covid Tests performed (and validated) in an date interval.
     *
     * @param startDate Start date.
     * @param endDate  End date.
     * @return List of performed (and validated) Covid tests in interval.
     */
    public List<Test> getCovidTestsInInterval (Date startDate, Date endDate)
    {
        List<Test> testList = getTestListByState("Validated");

        List<Test> covidTestsInInterval = new ArrayList<>();
        for (Test test: testList)
        {
            Date validationDate = Utils.getDateWithoutTime(test.getValidationDate());

            if (test.getTestType().getDescription().equals("Covid")
                    && !validationDate.before(startDate)
                    && !validationDate.after(endDate))
            {
                covidTestsInInterval.add(test);
            }
        }
        return covidTestsInInterval;
    }

    // Daily Number of Covid Tests
    /**
     * Returns an array of the daily number of positive Covid Tests in an interval.
     *
     * @param workingDays company's working days.
     * @return Daily number of positive covid tests.
     */
    public double[] getDailyNumberOfCovidTestsInInterval (List<Date> workingDays, List<Test> covidTestsInInterval)
    {
        if (covidTestsInInterval.isEmpty())
        {
            throw new IllegalArgumentException("No Covid tests in this interval" + new SimpleDateFormat(("dd-MM-yyyy")).
                    format(workingDays.get(0)) + " - " +  new SimpleDateFormat(("dd-MM-yyyy")).
                    format(workingDays.get(workingDays.size() - 1)));
        }

        double[] performedTests = new double[workingDays.size()];
        double dailyPositiveResults;

        int i = 0;
        for (Date date : workingDays)
        {
            dailyPositiveResults = getDailyNumberOfCovidTestsInDate(date, covidTestsInInterval);
            performedTests[i] = dailyPositiveResults;
            i++;
        }
        return performedTests;

    }

    /**
     * Returns the number of performed Covid Tests in a specific day.
     *
     * @param date date
     * @param covidTests List of covid tests.
     * @return Number of performed Covid tests in a date.
     */
    private double getDailyNumberOfCovidTestsInDate(Date date, List<Test> covidTests)
    {
        double covidTestCounter = 0;
        LocalDate currentDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        for(Test test : covidTests)
        {
            LocalDate testDate = test.getValidationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (testDate.compareTo(currentDate) == 0)
            {
                covidTestCounter++;
            }
        }
        return covidTestCounter;
    }

    // Daily Number of Positive Covid Tests
    /**
     * Returns an array of the daily number of positive Covid Tests in an interval.
     *
     * @param workDays company's working days.
     * @param covidTests List of covid tests.
     * @return Daily number of positive covid tests.
     */
    public double[] getDailyNumberOfPositiveCovidTestsForInterval(List<Date> workDays, List<Test> covidTests)
    {
        double[] positiveResultsHelper = new double[workDays.size()];
        double dailyPositiveResults;

        for (int i = 0; i < workDays.size(); i++)
        {
            dailyPositiveResults = getNumberOfPositiveCovidTestsInDate(workDays.get(i), covidTests);
            positiveResultsHelper[i] = dailyPositiveResults;
        }
        return positiveResultsHelper;
    }

    /**
     * Returns the number of positive Covid Tests in a specific day.
     *
     * @param date date
     * @param covidTests List of covid tests.
     * @return Number of positive Covid tests in a date.
     */
    private double getNumberOfPositiveCovidTestsInDate(Date date, List<Test> covidTests)
    {
        double positiveResults = 0;
        double testResult;
        LocalDate currentDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        for(Test test : covidTests)
        {
            LocalDate testDate = test.getValidationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            testResult = test.getTestParameterFor(Constants.COVID_PARAMETER).getTestParameterResult().getValue();

            if (testDate.compareTo(currentDate) == 0 && testResult >= Constants.COVID_POSITIVE_UPPER_LIMIT)
            {
                positiveResults++;
            }
        }
        return positiveResults;
    }

    // Mean Ages
    /**
     * Returns an array of the daily mean ages of Covid Tests' clients in an interval.
     *
     * @param workingDays company's working days.
     * @param covidTestsInInterval List of covid tests,
     * filtered previously for an interval.
     *
     * @return Mean age of Covid Tests' clients
     */
    public double[] getDailyMeanAgeOfCovidTestsForInterval (List<Date> workingDays, List<Test> covidTestsInInterval)
    {
        double[] dailyMeanAges = new double[workingDays.size()];
        double dailyMeanAgeCounter;

        for (int i = 0; i < workingDays.size(); i++)
        {
            dailyMeanAgeCounter = getMeanAgeOfCovidTestsInDate(workingDays.get(i), covidTestsInInterval);
            dailyMeanAges[i] = dailyMeanAgeCounter;
        }

        return dailyMeanAges;
    }

    /**
     * Returns the mean age of Covid Tests' Client in a specific day.
     *
     * @param date date
     * @param covidTests List of covid tests.
     * @return Mean age of Covid Tests' clients
     */
    private double getMeanAgeOfCovidTestsInDate (Date date, List<Test> covidTests)
    {
        int sum = 0;
        int testCount = 0;
        double meanAge;
        LocalDate currentDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        for (Test test : covidTests){
            LocalDate testDate = test.getValidationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (testDate.compareTo(currentDate) == 0)
            {
                sum += test.getClient().getAge();
                testCount++;
            }
        }

        if (testCount == 0)
        {
            meanAge = 0;
        }
        else
        {
            meanAge = (double) sum / testCount;
        }
        return meanAge;
    }
}
