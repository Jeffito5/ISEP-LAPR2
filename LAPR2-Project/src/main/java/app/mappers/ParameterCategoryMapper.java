package app.mappers;

import app.domain.model.ParameterCategory;
import app.mappers.dto.ParameterCategoryDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rui Rocha
 */
public class ParameterCategoryMapper {

    /**
     * Converts a given Parameter Category object to a Parameter Category DTO.
     *
     * @param parameterCategory Parameter Category object.
     * @return Parameter Category DTO.
     */
    public ParameterCategoryDto toDTO(ParameterCategory parameterCategory)
    {
        return new ParameterCategoryDto(parameterCategory.getName(), parameterCategory.getCode());
    }

    /**
     * Converts a given list of Parameter Category objects to a
     * list of Parameter Category DTOs.
     *
     * @param parameterCategoryList List of Parameter Category objects.
     * @return List of Parameter Category DTOs.
     */
    public List<ParameterCategoryDto> toDTO (List<ParameterCategory> parameterCategoryList)
    {
        List<ParameterCategoryDto> parameterCategoryDTO = new ArrayList<>();

        for(ParameterCategory parameterCategory : parameterCategoryList)
        {
            parameterCategoryDTO.add(this.toDTO(parameterCategory));
        }
        return parameterCategoryDTO;
    }
}
