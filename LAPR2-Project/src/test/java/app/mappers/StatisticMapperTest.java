package app.mappers;

import app.mappers.dto.StatisticDTO;
import org.junit.Test;

import static org.junit.Assert.*;

public class StatisticMapperTest {
    StatisticMapper mapper=new StatisticMapper();
    int numberofClients=5;
    int numberTestsWaitingResults=5;
    int numberTestsWaitingDiagnosis=5;
    int numberTestsProcessed=5;
    int[] sumArray={1, 2, 3, -2, 1, -3, -2, 5};
    StatisticDTO dto=new StatisticDTO(numberofClients,numberTestsWaitingResults,numberTestsWaitingDiagnosis,numberTestsProcessed,sumArray);
    @Test
    public void toDTO() {
        StatisticDTO newDTO=mapper.toDTO(numberofClients,numberTestsWaitingResults,numberTestsWaitingDiagnosis,numberTestsProcessed,sumArray);
        StatisticDTO newDTO1=mapper.toDTO(2,numberTestsWaitingResults,numberTestsWaitingDiagnosis,numberTestsProcessed,sumArray);
        String dtoString=dto.toString();
        String newDTOString=newDTO.toString();
        assertEquals(newDTOString.equals(dtoString),true);
        assertEquals(newDTO1.toString().equals(dtoString),false);
    }
}