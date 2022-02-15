package app.mappers.dto;

public class StatisticDTO {
    /**
     * number of Clients
     */
    private int numberClients;
    /**
     * number of tests waiting for results
     */
    private int numberTestsWaitingResults;
    /**
     * number of tests waiting for diagnosis
     */
    private int numberTestsWaitingDiagnosis;
    /**
     * number of tests processed
     */
    private int numberTestsProcessed;
    /**
     * array with max sum
     */
    private int[] sumArray;



    /**
     * Constructs an instance of a DTO
     * @param numberClients number of  clients
     * @param numberTestsWaitingResults number of tests waiting for results
     * @param numberTestsWaitingDiagnosis number of tests waiting for diagnosis
     * @param numberTestsProcessed number of tests processed
     * @param sumArray array with max sum
     */
    public StatisticDTO(int numberClients, int numberTestsWaitingResults,
                        int numberTestsWaitingDiagnosis, int numberTestsProcessed,
                        int[] sumArray){
        this.numberClients=numberClients;
        this.numberTestsWaitingResults=numberTestsWaitingResults;
        this.numberTestsWaitingDiagnosis=numberTestsWaitingDiagnosis;
        this.numberTestsProcessed=numberTestsProcessed;
        this.sumArray=sumArray;
    }

    /**
     * returns the number of clients
     * @return returns number of clients
     */
    public int getNumberClients(){
        return numberClients;
    }

    /**
     * returns the number of tests waiting for results
     * @return returns number of tests waiting for results
     */
    public int getNumberTestsWaitingResults(){
        return numberTestsWaitingResults;
    }

    /**
     * returns the number of tests waiting for diagnosis
     * @return returns number of tests waiting for diagnosis
     */
    public int getNumberTestsWaitingDiagnosis(){
        return numberTestsWaitingDiagnosis;
    }

    /**
     * returns the number of tests processed
     * @return number of tests processed
     */
    public int getNumberTestsProcessed(){
        return numberTestsProcessed;
    }

    /**
     * returns the array with max sum
     * @return array with max sum
     */
    public int[] getSumArray(){
        return sumArray;
    }

    public String toString(){
        return String.format("%nNUMBER OF CLIENTS: %s%n%nNUMBER OF TESTS WAITING RESULTS: %s%n%n\" +\n" +
                "        \"NUMBER OF TESTS WAITING DIAGNOSIS: %s%n%nNUMBER OF TESTS PROCESSED: %s%n%n\" +\n" +
                "                \"INTERVAL WHERE THE COMPANY WAS LESS EFFECTIVE: %s%n%n",numberClients,numberTestsWaitingResults,numberTestsWaitingDiagnosis,numberTestsProcessed,sumArray.toString());
    }
}
