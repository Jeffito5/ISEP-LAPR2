package app.mappers;

import app.mappers.dto.ClientResultsTestDTO;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class ClientResultsTestMapperTest extends TestCase {
    ClientResultsTestMapper mapper=new ClientResultsTestMapper();

    String nhsCode1="1234567890";
    String nhsCode2="1234567891";
    String nhsCode3="1234567892";
    ClientResultsTestDTO clientResultsTestDTO1=new ClientResultsTestDTO(nhsCode1);
    ClientResultsTestDTO clientResultsTestDTO2=new ClientResultsTestDTO(nhsCode2);
    ClientResultsTestDTO clientResultsTestDTO3=new ClientResultsTestDTO(nhsCode3);

    List<ClientResultsTestDTO> list=new ArrayList<>();

    List<String> listNHSCode=new ArrayList<>();


    /**
     * Checks if a certain DTO is equal to another DTO with the same content
     *
     * checks if a certain DTO is different to another DTO with different content
     */
    public void testToDTO() {

        assertEquals(mapper.toDTO(nhsCode1).getNhsCode().equals(clientResultsTestDTO1.getNhsCode()),true);
        assertEquals(mapper.toDTO(nhsCode1).getNhsCode().equals(clientResultsTestDTO2.getNhsCode()),false);
    }

    /**
     * Given a List of Strings to convert to DTO, checks if after the conversion the content of the two lists
     * are still comparable
     */
    public void testNhsCodeListToDTO() {
        list.add(clientResultsTestDTO1);
        list.add(clientResultsTestDTO2);
        list.add(clientResultsTestDTO3);

        listNHSCode.add(nhsCode1);
        listNHSCode.add(nhsCode2);
        listNHSCode.add(nhsCode3);

        assertEquals(mapper.nhsCodeListToDTO(listNHSCode).get(0).getNhsCode().equals(list.get(0).getNhsCode()),true);
        assertEquals(mapper.nhsCodeListToDTO(listNHSCode).get(1).getNhsCode().equals(list.get(1).getNhsCode()),true);
        assertEquals(mapper.nhsCodeListToDTO(listNHSCode).get(2).getNhsCode().equals(list.get(2).getNhsCode()),true);




    }
}