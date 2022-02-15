package app.mappers;

import app.domain.model.Parameter;

import app.mappers.dto.ParameterDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rui Rocha
 */
public class ParameterMapper {

    /**
     * Converts a given Parameter object to a Parameter DTO.
     *
     * @param parameter Parameter object.
     * @return Parameter DTO.
     */
    public ParameterDto toDTO(Parameter parameter)
    {
        return new ParameterDto(parameter.getName(), parameter.getCode());
    }

    /**
     * Converts a given list of Parameter objects to a
     * list of Parameter DTOs.
     *
     * @param parameterList List of Parameter objects.
     * @return List of Parameter DTOs.
     */
    public List<ParameterDto> toDTO (List<Parameter> parameterList)
    {
        List<ParameterDto> parameterDTO = new ArrayList<>();

        for(Parameter parameter : parameterList)
        {
            parameterDTO.add(this.toDTO(parameter));
        }
        return parameterDTO;
    }
}
