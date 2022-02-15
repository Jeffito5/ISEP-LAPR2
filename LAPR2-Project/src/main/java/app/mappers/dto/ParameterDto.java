package app.mappers.dto;

/**
 * @author Rui Rocha
 */
public class ParameterDto {

    /**
     * Parameter's code.
     */
    private String code;

    /**
     * Parameter's name.
     */
    private String name;

    /**
     * Constructs an instance of Parameter DTO, receiving a code and name.
     *
     * @param code internal code.
     * @param name name.
     */
    public ParameterDto(String name, String code)
    {
        this.name = name;
        this.code = code;
    }

    /**
     * Returns the Parameter's code.
     *
     * @return Parameter's code.
     */
    public String getCode()
    {
        return code;
    }

    /**
     * Returns the Parameter's name.
     *
     * @return Parameter's name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns a string containing the Parameter's information.
     *
     * @return Parameter's information.
     */
    @Override
    public String toString()
    {
        return String.format("%s [%s]", name, code);
    }
}
