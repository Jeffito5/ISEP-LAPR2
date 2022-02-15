package app.mappers;

import app.mappers.dto.unreportedTestDTO;


import java.util.ArrayList;
import java.util.List;

public class unreportedTestMapper {
    private List<unreportedTestDTO> testListDTO;
    public unreportedTestMapper(){
        testListDTO=new ArrayList<>();
    }



    private unreportedTestDTO toDTO(int index, String nhsCode){
        return new unreportedTestDTO(index, nhsCode);
    }

    public List<unreportedTestDTO> ListDTO(List<Integer> indexList, List<String> nhsCodeList)
    {

        for(int index: indexList)
        {
            testListDTO.add(this.toDTO(index, nhsCodeList.get(indexList.indexOf(index))));
        }
        return testListDTO;
    }
}
