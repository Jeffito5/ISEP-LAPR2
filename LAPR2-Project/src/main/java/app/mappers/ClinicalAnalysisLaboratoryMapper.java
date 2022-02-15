package app.mappers;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.mappers.dto.ClinicalAnalysisLaboratoryDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class ClinicalAnalysisLaboratoryMapper {

    /**
     * Converts a given Clinical Analysis Laboratory object to a Clinical Analysis Laboratory DTO.
     *
     * @param clinicalAnalysisLaboratory Clinical Analysis Laboratory object.
     * @return Clinical Analysis Laboratory DTO.
     */
    public ClinicalAnalysisLaboratoryDto toDTO(ClinicalAnalysisLaboratory clinicalAnalysisLaboratory)
    {
        return new ClinicalAnalysisLaboratoryDto(clinicalAnalysisLaboratory.getName(), clinicalAnalysisLaboratory.getLaboratoryId());
    }

    /**
     * Converts a given list of Clinical Analysis Laboratory objects to a
     * list of Clinical Analysis Laboratory DTOs.
     *
     * @param clinicalAnalysisLaboratoryList List of Clinical Analysis Laboratory objects.
     * @return List of Clinical Analysis Laboratory DTOs.
     */
    public List<ClinicalAnalysisLaboratoryDto> toDTO (List<ClinicalAnalysisLaboratory> clinicalAnalysisLaboratoryList)
    {

        List<ClinicalAnalysisLaboratoryDto> clinicalAnalysisLaboratoryDtoList = new ArrayList<>();

        for(ClinicalAnalysisLaboratory clinicalAnalysisLaboratory : clinicalAnalysisLaboratoryList)
        {
            clinicalAnalysisLaboratoryDtoList.add(this.toDTO(clinicalAnalysisLaboratory));
        }

        return clinicalAnalysisLaboratoryDtoList;
    }
}
