package app.controller;

import app.domain.model.Company;
import app.domain.model.Configurations;
import app.domain.model.algorithms.BruteForceAlgorithm.BruteForceAlgorithm;
import app.domain.store.ClientStore;
import app.domain.store.TestStore;
import app.mappers.StatisticMapper;
import app.mappers.dto.StatisticDTO;
import com.isep.mdis.Sum;


import java.util.Arrays;


public class PerformanceController {
    /**
     * Application
     */
    private final App app;
    /**
     * Company
     */
    private final Company company;
    /**
     * Client's store
     */
    private final ClientStore clientStore;
    /**
     * Test's store
     */
    private final TestStore testStore;

    /**
     * number of clients
     */
    private int numberClients;
    /**
     * number of tests waiting results
     */
    private int numberTestsWaitingResults;
    /**
     * number of tests waiting diagnosis
     */
    private int numberTestsWaitingDiagnosis;
    /**
     * number of tests processed
     */
    private int numberTestsProcessed;
    /**
     * State of tests waiting for results
     */
    private final String TEST_STATE_SAMPLES_COLLECTED="Samples Collected";
    /**
     * State of tests waiting for diagnosis
     */
    private final String TEST_STATE_SAMPLES_ANALYZED="Samples Analyzed";
    /**
     * state of tests that are processed
     */
    private final String TEST_STATE_PROCESSED="Validated";
    /**
     * array with contiguous subsequence with maximum sum
     */
    private int[] sumArray;
    /**
     * array with sequence of difference between registered tests and available tests
     */
    private int[] arrayToAnalyze;
    /**
     * DTO mapper
     */
    private StatisticMapper mapper;
    /**
     * DTO
     */
    private StatisticDTO dto;

    /**
     * Constructs an instance of the controller
     */
    public PerformanceController(){
        app=App.getInstance();
        company=app.getCompany();
        clientStore=company.getClientStore();
        testStore= company.getTestStore();
        mapper=new StatisticMapper();
    }

    /**
     * Tries to get the array to analyse, then gets the data on number of clients, number of tests
     * waiting for results, number of tests waiting for diagnosis and number of tests processed,
     * then sends the array to analyze and returns the subsequence with max sum
     * @param dateBeginning date of beginning of the interval of time
     * @param dateEnd date of end of the interval of time
     * @param index index to choose the algorithm
     * @return DTO with all the previous info
     */
    public StatisticDTO getData(String dateBeginning, String dateEnd, int index){

        try{

            arrayToAnalyze=testStore.getSequenceList(dateBeginning,dateEnd);
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        numberClients=getNumberOfClients();
        numberTestsWaitingResults=getNumberTests(TEST_STATE_SAMPLES_COLLECTED);
        numberTestsWaitingDiagnosis=getNumberTests(TEST_STATE_SAMPLES_ANALYZED);
        numberTestsProcessed=getNumberTests(TEST_STATE_PROCESSED);


        sumArray=getAlgorithm(index, arrayToAnalyze);
        dto=mapper.toDTO(numberClients,numberTestsWaitingResults,numberTestsWaitingDiagnosis,numberTestsProcessed,sumArray);
        return dto;
    }

    /**
     * Tries to obtain the array with the array to analyse that is confined between 2 dates
     * @param dateBeginning date of beginning of the interval of time
     * @param dateEnd date of end of the interval of time
     * @return returns array to analyse that is confined between 2 dates
     * @throws Exception
     */
    public int[] getSequenceList(String dateBeginning, String dateEnd) throws Exception{


        return testStore.getSequenceList(dateBeginning,dateEnd);
    }

    /**
     * returns number of clients in store
     * @return number of clients
     */
    private int getNumberOfClients(){
        return clientStore.getNumberOfClients();
    }

    /**
     * number of Tests with a certain state in store
     * @param testState state of the test
     * @return number of tests
     */
    private int getNumberTests(String testState){
        return testStore.getNumberOfTests(testState);
    }

    /**
     * Chooses between an algorithm to analyse an array and returns a subsequence with max sum
     * @param index index of algorithm
     * @param array array to analyse
     * @return returns subsequence with max sum
     */
    private int[] getAlgorithm(int index, int[] array ){


        switch (index){
            case 0:

                sumArray= Sum.Max(array);
                break;

            case 1:

                sumArray= BruteForceAlgorithm.Max(array);
                break;
        }
        return sumArray;
    }






    /**
     * shows the data to the user
     * @return data formatted
     */
    public String showData(){
        String numberClientsString=String.valueOf(dto.getNumberClients());
        String numberTestsWaitingResultsString=String.valueOf(dto.getNumberTestsWaitingResults());
        String numberTestsWaitingDiagnosisString=String.valueOf(dto.getNumberTestsWaitingDiagnosis());
        String numberTestsProcessedString=String.valueOf(dto.getNumberTestsProcessed());

        return String.format("%nNUMBER OF CLIENTS: %s%n%nNUMBER OF TESTS WAITING RESULTS: %s%n%n" +
        "NUMBER OF TESTS WAITING DIAGNOSIS: %s%n%nNUMBER OF TESTS PROCESSED: %s%n%n" +
                "INTERVAL WHERE THE COMPANY WAS LESS EFFECTIVE: %s%n%n",numberClientsString,numberTestsWaitingResultsString,numberTestsWaitingDiagnosisString,numberTestsProcessedString, Arrays.toString(dto.getSumArray()));
    }






}
