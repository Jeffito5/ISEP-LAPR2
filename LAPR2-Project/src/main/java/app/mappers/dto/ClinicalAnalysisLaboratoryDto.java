package app.mappers.dto;

/**
 * @author Rui Rocha
 */
public class ClinicalAnalysisLaboratoryDto {

    /**
     * Laboratory's id
     */
    private String laboratoryId;

    /**
     * Laboratory's name
     */
    private String name;

    /**
     * Constructs an instance of ClinicalAnalysisLaboratoryDTO, receiving its name and ID.
     *
     * @param name Laboratory's Name.
     * @param laboratoryId Laboratory's Identification Number.
     */
    public ClinicalAnalysisLaboratoryDto(String name, String laboratoryId)
    {
        this.name = name;
        this.laboratoryId = laboratoryId;
    }

    /**
     * Returns the Clinical Analysis Laboratory's Identification Number.
     *
     * @return Laboratory ID.
     */
    public String getLaboratoryId()
    {
        return laboratoryId;
    }

    /**
     * Returns a string with the Clinical Analysis Laboratory's info (name and ID).
     *
     * @return String with Clinical Analysis Laboratory's info.
     */
    @Override
    public String toString()
    {
        return String.format("%s [%s]", name, laboratoryId);
    }
}
