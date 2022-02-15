package app.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rui Rocha & Luis Araujo
 */
public class ParameterCategory implements Serializable {

    /**
     * Parameter Category's name
     */
    private String name;

    /**
     * ParameterCategory's description
     */
    private String description;

    /**
     * ParameterCategory's code
     */
    private String code;

    /**
     * Category's parameters.
     */
    private final List<Parameter> parameters = new ArrayList<>();

    /**
     * Constructor with the attributes: description and code
     *
     * @param name Parameter Category name.
     * @param description Parameter Category description.
     * @param code Parameter Category internal code.
     */
    public ParameterCategory(String name, String description, String code) {
        setName(name);
        setDescription(description);
        setCode(code);
    }

    /**
     * Creates an instance of Parameter Category from another Parameter Category
     *
     * @param otherParameterCategory other parameter category
     */
    public ParameterCategory(ParameterCategory otherParameterCategory) {
        setName(otherParameterCategory.name);
        setDescription(otherParameterCategory.description);
        setCode(otherParameterCategory.code);
    }

    /**
     * Method to return the Parameter Category's name
     *
     * @return parameter category's name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to modify the Parameter Category's name
     *
     * @param name new Parameter Category description.
     */
    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    /**
     * Method to return the description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method to modify the description
     *
     * @param description new Parameter Category description.
     */
    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    /**
     * Method to return the code
     *
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Method to modify the code
     *
     * @param code new Parameter Category internal code.
     */
    public void setCode(String code) {
        validateCode(code);
        this.code = code;
    }

    /**
     * Method to return the list of parameters
     *
     * @return parameters
     */
    public List<Parameter> getParameters(){
        return parameters;
    }

    /**
     * Method to return the list of parameters
     *
     * @return parameters
     */
    public Parameter getParameterBy(String parameterCode){
        for (Parameter parameter : parameters){
            if (parameter.getCode().equals(parameterCode))
                return parameter;
        }
        throw new IllegalArgumentException ("Parameter not found.");
    }

    /**
     * Method to add Parameter.
     */
    public void addParameter(Parameter p){
        parameters.add(p);
    }

    @Override
    public String toString() {
        return String.format("PARAMETER CATEGORY %nName: %s %nDescription: %s %n"
                        + "Code: %s %n", name, description, code);
    }

    /**
     * Compares the Parameter Category with other objects
     *
     * @param otherObject the object used to compare the Parameter category.
     * @return true if input object represents an equivalent Parameter Category
     *         to this Parameter Category. Otherwise returns false.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        ParameterCategory otherParameterCategory = (ParameterCategory) otherObject;
        return name.equals(otherParameterCategory.name) ||
                code.equals(otherParameterCategory.code);
    }

    /**
     * Checks the validity of the Parameter Category name,
     * by checking that it's not empty.
     *
     */
    private void validateName (String name){
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Parameter Category name is required.");
        }
    }

    /**
     * Checks the validity of the Parameter Category description,
     * by checking that it's not empty and doesn't have more than 40 chars.
     *
     */
    private void validateDescription (String description){
        if (description == null || description.trim().isEmpty())
            throw new IllegalArgumentException("Description is required.");
        if (description.length() > 40)
            throw new IllegalArgumentException("Description may only have 40 characters.");
    }

    /**
     * Checks the validity of the Parameter Category code, by checking that
     * it has between 4 and 8 alphanumeric chars.
     *
     */
    private void validateCode (String code){
        if (code == null)
            throw new IllegalArgumentException("Code is required.");
        if (code.length() < 4 || code.length() > 8)
            throw new IllegalArgumentException("Code length must be between 4 and 8 characters.");
        if (!code.matches("^[a-zA-Z0-9]*$")) //only accepts alphanumeric characters
            throw new IllegalArgumentException("Code has invalid characters.");
    }

}