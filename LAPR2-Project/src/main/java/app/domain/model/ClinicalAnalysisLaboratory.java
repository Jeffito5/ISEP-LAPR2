package app.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luís Araújo
 */
public class ClinicalAnalysisLaboratory implements Serializable {
    /**
     * Laboratory's default id
     */
    private static final String DEFAULT_ID = "Without id.";

    /**
     * Laboratory's default name
     */
    private static final String DEFAULT_NAME = "Without name.";

    /**
     * Laboratory's default phone number
     */
    private static final String DEFAULT_PHONE_NUMBER = "Without phone number.";

    /**
     * Laboratory's default tin number
     */
    private static final String DEFAULT_TIN_NUMBER = "Without tin number.";

    /**
     * Laboratory's default address
     */
    private static final String DEFAULT_ADDRESS = "No address";
    /**
     * Error message.
     */
    private static final String ERROR = "\nError: ";
    /**
     * Laboratory's id
     */
    private String laboratoryId;
    /**
     * Laboratory's name
     */
    private String name;
    /**
     * Laboratory's phone number
     */
    private String phoneNumber;
    /**
     * Laboratory's tin number
     */
    private String tinNumber;
    /**
     * Laboratory's address
     */
    private String address;
    /**
     * Laboratory's types of test
     */
    private List<String> testTypes;

    /**
     * ClinicalAnalysisLaboratory's constructor with the parameters: name, laboratory_id, phone_number, tin_number and address
     *
     * @param name         name
     * @param laboratoryId id
     * @param phoneNumber  phone number
     * @param tinNumber    tin number
     * @param address      address
     */
    public ClinicalAnalysisLaboratory(String name, String laboratoryId, String phoneNumber, String tinNumber, String address) {
        setName(name);
        setLaboratoryId(laboratoryId);
        setPhoneNumber(phoneNumber);
        setTinNumber(tinNumber);
        setAddress(address);
        testTypes = new ArrayList<>();
    }

    /**
     * ClinicalAnalysisLaboratory's constructor with the parameters: name, laboratory_id, phone_number, tin_number, address and test type
     *
     * @param name         name
     * @param laboratoryId laboratory id
     * @param phoneNumber  phone number
     * @param tinNumber    tin number
     * @param address      address
     * @param testTypes    test types
     */
    public ClinicalAnalysisLaboratory(String name, String laboratoryId, String phoneNumber, String tinNumber, String address, List<String> testTypes) {
        setName(name);
        setLaboratoryId(laboratoryId);
        setPhoneNumber(phoneNumber);
        setTinNumber(tinNumber);
        setAddress(address);
        setTestTypes(testTypes);
    }

    /**
     * Empty constructor
     */
    public ClinicalAnalysisLaboratory() {
        name = DEFAULT_NAME;
        laboratoryId = DEFAULT_ID;
        phoneNumber = DEFAULT_PHONE_NUMBER;
        tinNumber = DEFAULT_TIN_NUMBER;
        address = DEFAULT_ADDRESS;
        testTypes = new ArrayList<>();
    }

    /**
     * Method to return the ClinicalAnalysisLaboratory's name
     *
     * @return ClinicalAnalysisLaboratory's name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to modify the ClinicalAnalysisLaboratory's name
     *
     * @param name new name
     */
    public void setName(String name) {
        if (isNameValid(name)) {
            this.name = name;
        }
    }

    /**
     * Method to return the ClinicalAnalysisLaboratory's id
     *
     * @return ClinicalAnalysisLaboratory's id
     */
    public String getLaboratoryId() {
        return laboratoryId;
    }

    /**
     * Method to modify the ClinicalAnalysisLaboratory's id
     *
     * @param laboratoryId new laboratory id
     */
    public void setLaboratoryId(String laboratoryId) {
        if (isLaboratoryIdValid(laboratoryId))
            this.laboratoryId = laboratoryId;
    }

    /**
     * Method to return the ClinicalAnalysisLaboratory's phone number
     *
     * @return ClinicalAnalysisLaboratory's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Method to modify the ClinicalAnalysisLaboratory's phone number
     *
     * @param phoneNumber new phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        if (isPhoneNumberValid(phoneNumber))
            this.phoneNumber = phoneNumber;
    }

    /**
     * Method to return the ClinicalAnalysisLaboratory's tin number
     *
     * @return ClinicalAnalysisLaboratory's tin number
     */
    public String getTinNumber() {
        return tinNumber;
    }

    /**
     * Method to modify the ClinicalAnalysisLaboratory's tin number
     *
     * @param tinNumber new tin number
     */
    public void setTinNumber(String tinNumber) {
        if (isTINValid(tinNumber))
            this.tinNumber = tinNumber;
    }

    /**
     * Method to return the ClinicalAnalysisLaboratory's address
     *
     * @return ClinicalAnalysisLaboratory's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Method to modify the ClinicalAnalysisLaboratory's address
     *
     * @param address new address
     */
    public void setAddress(String address) {
        if (isAddressValid(address))
            this.address = address;
    }

    /**
     * Method to return the ClinicalAnalysisLaboratory's test type list
     *
     * @return ClinicalAnalysisLaboratory's test type list
     */
    public List<String> getTestTypes() {
        return testTypes;
    }

    /**
     * Method to modify the ClinicalAnalysisLaboratory's test type
     *
     * @param testTypes new test types
     */
    public void setTestTypes(List<String> testTypes) {
        this.testTypes = new ArrayList<>(testTypes);
    }

    /**
     * Method to add a test type
     *
     * @param testType new test type
     * @return true if the test type was added
     */
    public boolean addTypeTest(String testType) {
        return testTypes.add(testType);
    }

    /**
     * Compares the ClinicalAnalysisLaboratory with the other object
     *
     * @param o the object used to compare the ClinicalAnalysisLaboratory.
     * @return true if input o represents an equivalent ClinicalAnalysisLaboratory
     * to the ClinicalAnalysisLaboratory. Otherwise returns false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClinicalAnalysisLaboratory otherCal = (ClinicalAnalysisLaboratory) o;
        return name.equals(otherCal.name) ||
                laboratoryId.equals(otherCal.laboratoryId) ||
                phoneNumber.equals(otherCal.phoneNumber) ||
                address.equals(otherCal.address);
    }

    @Override
    public String toString() {
        return String.format("CLINICAL ANALYSIS LABORATORY %nName: %s %nID: %s %n"
                + "Phone Number: %s %nTIN Number: %s %nAddress: %s %n" +
                "%s%n", name, laboratoryId, phoneNumber, tinNumber, address, printTestTypes());
    }

    /**
     * Checks the validity of the Clinical Analysis Laboratory's name,
     * by checking that it's not empty and has no more than 20 characters.
     *
     * @return true if Clinical Analysis Laboratory's name is valid.
     */
    public boolean isNameValid(String name) {
        try {
            if (name == null || name.length() > 20 || name.trim().isEmpty()) {
                return false;
            }
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR + e.getMessage() + " (" + e.getClass().getSimpleName() + ")");
            return false;
        }
    }

    /**
     * Method to verify if the laboratory's id is valid. This id can't be null and has no more the five alphanumeric characters
     *
     * @param laboratoryId laboratory id
     * @return true if the laboratory's id is valid
     */
    public boolean isLaboratoryIdValid(String laboratoryId) {
        try {
            if (laboratoryId == null || !laboratoryId.matches("^[a-zA-Z0-9]*$") || laboratoryId.length() > 5 || laboratoryId.length() < 0) {
                return false;
            } else {
                return true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR + e.getMessage() + " (" + e.getClass().getSimpleName() + ")");
            return false;
        }
    }

    /**
     * Checks the validity of the phone number by checking that if it has 11 digits and doesn't start with a "0".
     *
     * @return true if phone number is valid.
     */
    public boolean isPhoneNumberValid(String phoneNumber) {
        try {
            if (phoneNumber == null || phoneNumber.trim().isEmpty() || phoneNumber.length() != 11 || phoneNumber.charAt(0) == '0' || !phoneNumber.matches("[0-9]+")) {
                return false;
            } else {
                return true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR + e.getMessage() + " (" + e.getClass().getSimpleName() + ")");
            return false;
        }
    }

    /**
     * Method to verify if the address is valid
     *
     * @param address address
     * @return true if the address is valid
     */
    public boolean isAddressValid(String address) {
        try {
            if (address == null || address.trim().isEmpty() || address.length() > 30) {
                return false;
            } else {
                return true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR + e.getMessage());
            return false;
        }
    }

    /**
     * Checks the validity of the Tax Identification Number, by checking that if it has 10 digits and doesn't start with a "0".
     *
     * @return true if Tax Identification Number is valid.
     */
    public boolean isTINValid(String tinNumber) {
        try {
            if (tinNumber == null || tinNumber.length() != 10 || tinNumber.charAt(0) == '0' || !tinNumber.matches("[0-9]+")) {
                return false;
            } else {
                return true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR + e.getMessage() + " (" + e.getClass().getSimpleName() + ")");
            return false;
        }
    }

    /**
     * Creates string with a list of the test types associated with the laboratory
     *
     * @return list of test types
     */
    private String printTestTypes() {
        StringBuilder tt = new StringBuilder();
        tt.append("Test Types: ").append("\n");
        if (!testTypes.isEmpty()){
            for (String testType : testTypes) {
                tt.append(" - ").append(testType).append("\n");
            }
        } else {
            tt.append("No tests.");
        }
        return tt.toString();
    }
}



