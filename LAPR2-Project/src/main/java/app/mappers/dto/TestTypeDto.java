package app.mappers.dto;

/**
 * @author Rui Rocha
 */
public class TestTypeDto {
    /**
     * TestType's code
     */
    private String code;

    /**
     * TestType's description
     */
    private String description;

    /**
     * Constructs an instance of Test Type DTO, receiving a code and description.
     *
     * @param code Test type's internal code.
     * @param description Test type's description.
     */
    public TestTypeDto(String code, String description)
    {
        this.code = code;
        this.description = description;
    }

    /**
     * Returns the Test type's code.
     *
     * @return Test type's code.
     */
    public String getCode()
    {
        return code;
    }

    /**
     * Returns the Test type's description.
     *
     * @return Test type's description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Returns a string containing the Test type's information.
     *
     * @return Test type's information.
     */
    @Override
    public String toString()
    {
        return String.format("%s [%s]",
                description, code);
    }
}
