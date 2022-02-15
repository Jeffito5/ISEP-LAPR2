package app.controller;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.Company;
import app.domain.store.ClinicalAnalysisLaboratoryStore;
import app.mappers.ClinicalAnalysisLaboratoryMapper;
import app.mappers.dto.ClinicalAnalysisLaboratoryDto;

import java.util.List;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 *
 */
public class ReceptionistController {

    /**
     * Clinical Analysis Laboratory's store.
     */
    private final ClinicalAnalysisLaboratoryStore clinicalAnalysisLaboratoryStore;

    /**
     * Clinical Analysis Laboratory's Mapper.
     */
    private final ClinicalAnalysisLaboratoryMapper clinicalAnalysisLaboratoryMapper;

    /**
     * Constructs an instance of the Controller,
     * using singleton to access the app instance;
     */
    public ReceptionistController(){
        // Singleton
        App app = App.getInstance();
        Company company = app.getCompany();

        // Store
        this.clinicalAnalysisLaboratoryStore = company.getClinicalAnalysisLaboratoryStore();

        // Mapper
        this.clinicalAnalysisLaboratoryMapper = new ClinicalAnalysisLaboratoryMapper();
    }

    /**
     * Retrieves the list of Clinical Analysis Laboratories from the
     * Clinical Analysis Laboratory's store and converts it to a list of DTOs.
     *
     * @return List of Clinical Analysis Laboratory's DTO.
     */
    public List<ClinicalAnalysisLaboratoryDto> getClinicalAnalysisLaboratories(){
        List<ClinicalAnalysisLaboratory> clinicalAnalysisLaboratoryList = clinicalAnalysisLaboratoryStore.getClinicalAnalysisLaboratories();
        return clinicalAnalysisLaboratoryMapper.toDTO(clinicalAnalysisLaboratoryList);
    }
}
