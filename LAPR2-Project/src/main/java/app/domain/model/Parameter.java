package app.domain.model;

import java.io.Serializable;

/**
 * @author Rui Rocha
 */
public class Parameter implements Serializable {

    //region Attributes
    /**
     * Parameter's code.
     */
    private String code;

    /**
     * Parameter's name.
     */
    private String name;

    /**
     * Parameter's description.
     */
    private String description;

    /**
     * Parameter's default code.
     */
    private static final String DEFAULT_CODE = "N/A";

    /**
     * Parameter's default name.
     */
    private static final String DEFAULT_NAME = "N/A";

    /**
     * Parameter's default description.
     */
    private static final String DEFAULT_DESCRIPTION = "N/A";
    //endregion

    //region Constructors
    /**
     * Constructs an instance of Parameter, receiving the code, name and description.
     *
     * @param code internal code.
     * @param name name.
     * @param description description.
     */
    public Parameter(String name, String code, String description){
        setName(name);
        setCode(code);
        setDescription(description);
    }
    //endregion

    //region Getters and Setters
    /**
     * Method to return the parameter's name.
     *
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Method to modify the code.
     *
     * @param code internal code.
     */
    public void setCode(String code) {
        if (isCodeValid(code)){
            this.code = code;
        } else {
            this.code = DEFAULT_CODE;
        }
    }

    /**
     * Method to return the parameter's name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to modify the parameter's name.
     *
     * @param name parameter's name.
     */
    public void setName(String name) {
        if (isNameValid(name)){
            this.name = name;
        } else {
            this.name = DEFAULT_NAME;
        }
    }

    /**
     * Method to return the parameter's description.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method to modify the description.
     *
     * @param description parameter's description.
     */
    public void setDescription(String description) {
        if (isDescriptionValid(description)){
            this.description = description;
        } else {
            this.description = DEFAULT_DESCRIPTION;
        }
    }
    //endregion

    //region Validations
    /**
     * Checks the validity of the Code, by checking that it consists
     * of 5 alphanumerical characters and it's not null.
     *
     */
    private boolean isCodeValid (String code){
        try {
            if (code == null || code.length() != 5){
                throw new IllegalArgumentException("Invalid Parameter's code (must have 5 alphanumerical characters)");
            }
            if (!code.matches("^[a-zA-Z0-9]*$"))
                throw new IllegalArgumentException("Parameter code has invalid characters.");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks the validity of the name, by checking that it's
     * not empty nor bigger than 8 characters.
     *
     */
    private boolean isNameValid (String name){
        try {
            if (name == null || name.trim().isEmpty())
                throw new IllegalArgumentException("Parameter name is required.");
            if (name.length() > 8)
                throw new IllegalArgumentException("Parameter's name maximum length: 8 characters.");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks the validity of the description, by checking that it's
     * not empty nor bigger than 20 characters.
     *
     */
    private boolean isDescriptionValid (String description){
        try {
            if (description == null || description.trim().isEmpty())
                throw new IllegalArgumentException("Description is required.");
            if (description.length() > 20)
                throw new IllegalArgumentException("Description may only have 20 characters.");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage());
            return false;
        }
    }
    //endregion
}
