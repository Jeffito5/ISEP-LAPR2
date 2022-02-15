package app.mappers.dto;

import junit.framework.TestCase;

public class ClientResultsTestDTOTest extends TestCase {
    String nhsCode="1234567890";
    ClientResultsTestDTO clientResultsTestDTO=new ClientResultsTestDTO(nhsCode);
    ClientResultsTestDTO clientResultsTestDTO1=new ClientResultsTestDTO("1234555");

    /**
     * Checks if the content of a certain DTO is equal to the string that originated it
     */
    public void testGetNhsCode() {
        assertEquals(nhsCode.equals(clientResultsTestDTO.getNhsCode()),true);
        assertEquals(nhsCode.equals(clientResultsTestDTO1.getNhsCode()),false);
    }
}