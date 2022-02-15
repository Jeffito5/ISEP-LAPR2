package app.domain.model;

import app.domain.model.externalModules.ExternalModule;
import app.domain.shared.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Luís Araújo
 */
public class TestType implements Serializable {
    // code- 5 alfanum / description- 15 chars / collecting method 20 chars / set of categories
    /**
     * TestType's default code
     */
    private static final String DEFAULT_CODE = "Without code.";
    /**
     * TestType's default description
     */
    private static final String DEFAULT_DESCRIPTION = "Without description.";
    /**
     * TestType's default collecting method
     */
    private static final String DEFAULT_COLLECTING_METHOD = "Without collecting method";
    /**
     * TestType's set of categories
     */
    List<ParameterCategory> categories = new ArrayList<ParameterCategory>();
    /**
     * TestType's code
     */
    private String code;
    /**
     * TestType's description
     */
    private String description;
    /**
     * TestType's collecting method
     */
    private String collectingMethod;

    /**
     * TestType's constructor with the parameters: code, description, collecting method and categories
     *
     * @param code code
     * @param description description
     * @param collecting_method collecting method
     * @param categories parameter categories
     */
    public TestType(String code, String description, String collecting_method, List<ParameterCategory> categories) {
        this.code = code;
        this.description = description;
        this.collectingMethod = collecting_method;
        this.categories = categories;
    }

    /**
     * Empty constructor
     */
    public TestType() {
        code = DEFAULT_CODE;
        description = DEFAULT_DESCRIPTION;
        collectingMethod = DEFAULT_COLLECTING_METHOD;
    }

    /**
     * TestType's constructor with other test type
     *
     * @param otherTestType other test type
     */
    public TestType(TestType otherTestType) {
        code = otherTestType.getCode();
        description = otherTestType.getDescription();
        collectingMethod = otherTestType.getCollectingMethod();
        categories = otherTestType.categories;
    }

    /**
     * Method to return the TestType code
     *
     * @return TestType's code
     */
    public String getCode() {
        return code;
    }

    /**
     * Method to modify the code
     *
     * @param code New code.
     */
    public void setCode(String code) {
        if (isCodeValid(code))
            this.code = code;
    }

    /**
     * Method to return the TestType description
     *
     * @return TestType's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method to modify the description
     *
     * @param description New description.
     */
    public void setDescription(String description) {
        if (isDescriptionValid(description))
            this.description = description;
    }

    /**
     * Method to return the collecting method
     *
     * @return collecting method
     */
    public String getCollectingMethod() {
        return collectingMethod;
    }

    /**
     * Method to modify the collecting method
     *
     * @param collectingMethod New Collecting method.
     */
    public void setCollectingMethod(String collectingMethod) {
        if (isCollectingMethodValid(collectingMethod))
            this.collectingMethod = collectingMethod;
    }

    /**
     * Method to return the set of categories
     *
     * @return set of categories
     */
    public List<ParameterCategory> getCategories() {
        return categories;
    }

    /**
     * Method to return a category.
     *
     * @return parameter category
     */
    public ParameterCategory getCategory(String parameterCategoryCode) {
        for (ParameterCategory parameterCategory : categories){
            if (parameterCategory.getCode().equals(parameterCategoryCode))
                return parameterCategory;
        }
        throw new IllegalArgumentException ("Parameter Category not found.");
    }

    /**
     * Method to return a category.
     *
     * @return parameter category
     */
    public ParameterCategory getCategoryWithName(String parameterCategoryName) {
        for (ParameterCategory parameterCategory : categories){
            if (parameterCategory.getName().equals(parameterCategoryName.trim()))
                return parameterCategory;
        }
        throw new IllegalArgumentException ("Parameter Category not found.");
    }

    /**
     * Method to modify the set of categories
     *
     * @param categories Parameter categories.
     */
    public void setCategories(List<ParameterCategory> categories) {
        this.categories = categories;
    }

    /**
     * Compares the TestType with the other object
     *
     * @param o the object used to compare the TestType.
     * @return true if input o represents an equivalent TestType
     * to the TestType. Otherwise returns false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestType)) return false;
        TestType testType = (TestType) o;
        return code.equals(testType.code) && Objects.equals(description, testType.description) && Objects.equals(categories, testType.categories);
    }

    @Override
    public String toString() {
        return String.format("Test Type: \n" +
                "Code: %s\n" +
                "Description: %s\n" +
                "%s\n", code, description, printCategories());
    }

    /**
     * Method to verify if the code is valid. This code can't be null and has no more the five alphanumeric characters
     *
     * @param code code;
     * @return true if the code is valid
     */
    public boolean isCodeValid(String code) {
        try {
            if (code == null || !code.matches("[a-zA-Z0-9]*$") || code.length() > 6 || code.length() < 1 || code.trim().isEmpty()) {
                return false;
            } else {
                return true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage() + " (" + e.getClass().getSimpleName() + ")");
            return false;
        }
    }

    /**
     * Method to verify if the description is valid. This description cannot be null and has no more than 15 characters
     *
     * @param description description;
     * @return true if the description is valid
     */
    public boolean isDescriptionValid(String description) {
        try {
            if (description == null || description.length() > 15 || description.trim().isEmpty()) {
                return false;
            }
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage() + " (" + e.getClass().getSimpleName() + ")");
            return false;
        }
    }

    /**
     * Method to verify if the collecting method is valid. This collecting method cannot be null and has no more than 20 characters
     *
     * @param collectingMethod Collecting Method
     * @return true if the description is valid
     */
    public boolean isCollectingMethodValid(String collectingMethod) {
        try {
            if (collectingMethod == null || collectingMethod.length() > 20 || collectingMethod.trim().isEmpty()) {
                return false;
            }
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage() + " (" + e.getClass().getSimpleName() + ")");
            return false;
        }
    }

    /**
     * Checks the validity of a Test Type by checking the
     * validity of every attribute.
     *
     * @return true if Test Type is valid.
     */
    public boolean isTestTypeValid(TestType tt) {
        return isCodeValid(tt.code) &&
                isDescriptionValid(tt.description) &&
                isCollectingMethodValid(tt.collectingMethod);
    }

    /**
     * Creates string with a list of the Parameter Categories associated with the test type.
     *
     * @return list of categories
     */
    public String printCategories(){
        StringBuilder tt = new StringBuilder();
        tt.append("Categories: ").append("\n");
        if (!categories.isEmpty()){
            for (ParameterCategory pc : categories) {
                tt.append(" - ").append(pc.getName()).append("\n");
            }
        } else {
            tt.append("No categories.");
        }
        return tt.toString();
    }

    /**
     * Method to get the External Module to get reference values.
     *
     * @return External Module.
     */
    public ExternalModule getExternalModule() throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        if(this.description.equals("Blood")){
            Class<?> oClass = Class.forName(Constants.PARAMS_BLOOD_EXTERNAL_MODULE_API);
            return (ExternalModule) oClass.newInstance();
        } else if(this.description.equals("Covid")){
            Class<?> oClass = Class.forName(Constants.PARAMS_COVID_EXTERNAL_MODULE_API);
            return (ExternalModule) oClass.newInstance();
        }
        throw new ClassNotFoundException();
    }

}
