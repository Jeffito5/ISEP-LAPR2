package app.mappers;

import app.mappers.dto.StatisticDTO;

public class StatisticMapper {
    /**
     * Creates a DTO
     * @param numberClients number of clients
     * @param numberTestsWaitingResults number of tests waiting for results
     * @param numberTestsWaitingDiagnosis number of tests waiting for diagnosis
     * @param numberTestsProcessed number of tests processed
     * @param sumArray array with max sum
     * @return returns an instance of a DTO with the previous info
     */
    public StatisticDTO toDTO(int numberClients, int numberTestsWaitingResults,
                              int numberTestsWaitingDiagnosis, int numberTestsProcessed,
                              int[] sumArray){
        return new StatisticDTO(numberClients,numberTestsWaitingResults,numberTestsWaitingDiagnosis,numberTestsProcessed,sumArray);
    }
}
