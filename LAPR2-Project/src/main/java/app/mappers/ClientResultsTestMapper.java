package app.mappers;

import app.mappers.dto.ClientResultsTestDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author João Violante
 */
public class ClientResultsTestMapper {

    /**
     * Given a certain NHS Code, this method will encapsulate said NHS Code in a DTO
     * @param nhsCode Test's NHS Code
     * @return Test's NHS Code DTO
     */
    public ClientResultsTestDTO toDTO(String nhsCode){
        return new ClientResultsTestDTO(nhsCode);
    }

    /**
     * Receives a List with strings of NHS Codes and then transforms said List in an DTO list containing
     * the previous NHS Codes encapsulated
     * @param nhsCodeList List of NHS Codes
     * @return List of NHS Codes DTO
     */
    public List<ClientResultsTestDTO> nhsCodeListToDTO(List<String> nhsCodeList){
        List<ClientResultsTestDTO> ClientResultsTestDTOList=new ArrayList<>();


        for (String nhsCode: nhsCodeList) {
            ClientResultsTestDTOList.add(this.toDTO(nhsCode));
        }
        return ClientResultsTestDTOList;
    }
}
