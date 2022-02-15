package app.mappers;

import app.domain.model.TestType;
import app.mappers.dto.TestTypeDto;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Rui Rocha
 */
public class TestTypeMapper {

    /**
     * Converts a given Test Type object to a Test Type DTO.
     *
     * @param testType Test Type object.
     * @return Test Type DTO.
     */
    public TestTypeDto toDTO(TestType testType)
    {
        return new TestTypeDto(testType.getCode(), testType.getDescription());
    }

    /**
     * Converts a given list of Test Type objects to a
     * list of Test Type DTOs.
     *
     * @param testTypes List of Test Type objects.
     * @return List of Test Type DTOs.
     */
    public List<TestTypeDto> toDTO (List<TestType> testTypes)
    {
        List<TestTypeDto> testTypesDTO = new ArrayList<>();

        for(TestType testType : testTypes)
        {
            testTypesDTO.add(this.toDTO(testType));
        }
        return testTypesDTO;
    }
}
