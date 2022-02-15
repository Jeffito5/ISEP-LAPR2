package app.mappers.dto;

/**
 * @author Rui Rocha
 */
public class ParameterCategoryDto {

    /**
     * Parameter Category's code.
     */
    private String code;

    /**
     * Parameter Category's name.
     */
    private String name;

    /**
     * Constructs an instance of ParameterCategoryDTO, receiving a name and code.
     *
     * @param name Parameter Category's Name.
     * @param code Parameter Category's Code.
     */
    public ParameterCategoryDto(String name, String code)
    {
        this.name = name;
        this.code = code;
    }

    /**
     * Returns the Parameter Category's code.
     *
     * @return Parameter Category's code.
     */
    public String getCode()
    {
        return code;
    }

    /**
     * Returns the Parameter Category's name.
     *
     * @return Parameter Category's name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns a string containing the Parameter Category's information.
     *
     * @return Parameter Category's information.
     */
    @Override
    public String toString()
    {
        return String.format("%s [%s]", name, code);
    }
}
