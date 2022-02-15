package app.mappers;

import app.domain.model.Test;
import app.mappers.dto.TestDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luís Araújo
 */
public class TestMapper {
    public TestDto toDTO(Test test) {
        return new TestDto(test.getClinicalAnalysisLaboratory(), test.getClient(), test.getNhsCode(), test.getDescription(), test.getTestType(), test.getTestParameterList());
    }

    public List<TestDto> toDTO(List<Test> testList) {

        List<TestDto> testDtoList = new ArrayList<>();

        for (Test test : testList) {
            testDtoList.add(this.toDTO(test));
        }

        return testDtoList;
    }
}
