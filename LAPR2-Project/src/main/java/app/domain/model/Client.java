package app.domain.model;

import app.mappers.dto.ClientDto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Rui Rocha
 */
public class Client implements Comparable<Client>, Serializable {

    //region Attributes
    /**
     * Name.
     */
    private String name;

    /**
     * Date of birth.
     */
    private Date birthDate;

    /**
     * Sexes.
     */
    public enum Sex{
        MALE(1) { @Override public String toString() { return "Male"; }},
        FEMALE(2) { @Override public String toString() { return "Female"; }},
        OTHER(3) { @Override public String toString() { return "Other"; }},
        NONE(4) { @Override public String toString() { return "None"; }};

        int op;
        private Sex(int op){
            this.op = op;
        }

        public int showOp(){
            return op;
        }

        public static Stream<Sex> stream(){
            return Stream.of(Sex.values());
        }
    }

    /**
     * Sex.
     */
    private Sex sex;

    /**
     * Citizen Card Number.
     */
    private String ccn;

    /**
     * National Health Service Number.
     */
    private String nhsNumber;

    /**
     * Tax Identification Number.
     */
    private String tin;

    /**
     * Phone Number.
     */
    private String phoneNumber;

    /**
     * E-mail.
     */
    private String email;

    /**
     * Regular Expression for numerical values.
     */
    private static final String NUMERICAL_REGEX = "[0-9]+";

    /**
     * List of Client's tests.
     */
    private final List<Test> tests = new ArrayList<>();
    //endregion

    //region Constructors
    /**
     * Constructs an instance of Client, receiving the Name, Citizen Card Number,
     * NHS Number, Birth Date, Sex, Tax Identification Number, Phone Number and Email.
     *
     * @param name client's name.
     * @param birthDate client's birth date.
     * @param sex client's sex..
     * @param ccn client's citizen card number.
     * @param nhsNumber client's NHS number.
     * @param tin client's Tax Identification Number.
     * @param phoneNumber client's phone number.
     * @param email client's email.
     */
    public Client(String name, Date birthDate, Sex sex, String ccn,
                  String nhsNumber, String tin, String phoneNumber, String email){
        setName(name);
        setBirthDate(birthDate);
        setSex(sex);
        setCcn(ccn);
        setNhsNumber(nhsNumber);
        setTin(tin);
        setPhoneNumber(phoneNumber);
        setEmail(email);
    }

    /**
     * Constructs an instance of Client, receiving a Client Data Transfer Object.
     *
     * @param clientDto client's Data Transfer Object.
     */
    public Client(ClientDto clientDto){
        setName(clientDto.getName());
        setBirthDate(clientDto.getBirthDate());
        setSex(clientDto.getSex());
        setCcn(clientDto.getCcn());
        setNhsNumber(clientDto.getNhsNumber());
        setTin(clientDto.getTin());
        setPhoneNumber(clientDto.getPhoneNumber());
        setEmail(clientDto.getEmail());
    }
    //endregion

    //region Getters and Setters
    /**
     * Returns the client's name.
     *
     * @return client's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Modifies client's name.
     *
     * @param name new client's name.
     */
    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    /**
     * Returns the client's birth date.
     *
     * @return client's birth date.
     */
    public Date getBirthDate() {
        return (Date) birthDate.clone();
    }

    /**
     * Modifies client's birth date.
     *
     * @param birthDate new client's birth date.
     */
    public void setBirthDate(Date birthDate) {
        validateBirthDate(birthDate);
        this.birthDate = (Date) birthDate.clone();
    }

    /**
     * Returns the client's sex.
     *
     * @return client's sex.
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * Modifies client's sex.
     *
     * @param sex new client's sex.
     */
    public void setSex(Sex sex) {
        this.sex = sex;
    }

    /**
     * Returns the client's citizen card number.
     *
     * @return client's citizen card number.
     */
    public String getCcn() {
        return ccn;
    }

    /**
     * Modifies client's citizen card number.
     *
     * @param ccn new client's citizen card number.
     */
    public void setCcn(String ccn) {
        validateCcn(ccn);
        this.ccn = ccn;
    }

    /**
     * Returns the client's NHS number.
     *
     * @return client's NHS number.
     */
    public String getNhsNumber() {
        return nhsNumber;
    }

    /**
     * Modifies client's NHS number.
     *
     * @param nhsNumber new client's NHS number.
     */
    public void setNhsNumber(String nhsNumber) {
        validateNhsNumber(nhsNumber);
        this.nhsNumber = nhsNumber;
    }

    /**
     * Returns the client's Tax Identification Number.
     *
     * @return client's Tax Identification Number.
     */
    public String getTin() {
        return tin;
    }

    /**
     * Modifies client's Tax Identification Number.
     *
     * @param tin new client's Tax Identification Number.
     */
    public void setTin(String tin) {
        validateTin(tin);
        this.tin = tin;
    }

    /**
     * Returns the client's phone number.
     *
     * @return client's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Modifies client's phone number.
     *
     * @param phoneNumber new client's phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        validatePhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the client's email.
     *
     * @return client's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Modifies client's email.
     *
     * @param email new client's email.
     */
    public void setEmail(String email) {
        validateEmail(email);
        this.email = email;
    }

    /**
     * Returns the client's age.
     *
     * @return client's age.
     */
    public int getAge() {
        Calendar today = Calendar.getInstance();
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthDate);

        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        if (age > 0 && (birth.get(Calendar.MONTH) > today.get(Calendar.MONTH))
                || (birth.get(Calendar.MONTH) == today.get(Calendar.MONTH)) &&
                (birth.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }
        return age;
    }

    /**
     * Returns the client's list of tests.
     *
     * @return client's list of tests.
     */
    public List<Test> getTests() {
        return tests;
    }

    //endregion

    @Override
    public String toString() {
        return String.format("CLIENT %nName: %s %nBirth Date: %s (%d years old) %n"
                        + "Sex: %s %nCitizen Card Number: %s %n" +
                        "NHS Number: %s %nTax Identification Number: %s %n" +
                        "Phone Number: %s %nEmail: %s%n",
                name, new SimpleDateFormat("dd-MM-yyyy").format(birthDate),
                getAge(), sex.toString(), ccn, nhsNumber, tin, phoneNumber, email);
    }

    /**
     * Compares the client with other object
     *
     * @param otherObject the object used to compare the client.
     * @return true if input object represents an equivalent client
     *         to the client. Otherwise returns false.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        Client otherClient = (Client) otherObject;
        return ccn.equals(otherClient.ccn) ||
                nhsNumber.equals(otherClient.nhsNumber) ||
                tin.equals(otherClient.tin) ||
                email.equals(otherClient.email) ||
                phoneNumber.equals(otherClient.phoneNumber);
    }

    /**
     * Compares the client's name alphabetically with the name of other client's name, received
     * as a parameter.
     *
     * @param otherClient client to be compared.
     * @return value 0 if the otherClient has the same name as the Client;
     *         value -1 if, alphabetically, the name of the client is before
     *         the otherClient's name; value 1 if, alphabetically, the
     *         client's name is after the otherClient's name.
     */
    @Override
    public int compareTo(Client otherClient) {
        return name.compareToIgnoreCase(otherClient.name);
    }

    //region Validations
    /**
     * Checks the validity of the client's name, by checking that it's not empty.
     *
     */
    private void validateName (String name){
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name is required.");
        if (name.length() > 35)
            throw new IllegalArgumentException("Name maximum length: 35 characters.");
    }

    /**
     * Checks the validity of the client's birthday date, by checking that it's not empty
     * nor his age is above 150 years.
     *
     */
    private void validateBirthDate (Date date){
        if (date == null)
            throw new IllegalArgumentException("Birth Date is required.");

        Calendar today = Calendar.getInstance();
        Calendar birth = Calendar.getInstance();
        birth.setTime(date);
        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

        if (age > 150 || age < 0)
            throw new IllegalArgumentException("Birth Date invalid");
    }

    /**
     * Checks the validity of the Citizen Card Number, by checking that it's
     * purely numerical, has 16 digits, and doesn't start with a "0".
     *
     */
    private void validateCcn (String ccn){
        if (ccn == null || ccn.length() != 16){
            throw new IllegalArgumentException("Invalid Citizen Card Number (must have 16 digits)");
        }
        if (!ccn.matches(NUMERICAL_REGEX))
            throw new IllegalArgumentException("Citizen Card Number has invalid characters.");
    }

    /**
     * Checks the validity of the NHS number, by checking that it's
     * purely numerical, has 10 digits, and doesn't start with a "0".
     *
     */
    private void validateNhsNumber (String nhsNumber){
        if (nhsNumber == null || nhsNumber.length() != 10 || nhsNumber.charAt(0) == '0')
            throw new IllegalArgumentException("NHS Number must have 10 digits.");
        if (!nhsNumber.matches(NUMERICAL_REGEX))
            throw new IllegalArgumentException("NHS number has invalid characters.");
    }

    /**
     * Checks the validity of the Tax Identification Number, by checking that it's
     * purely numerical, has 10 digits, and doesn't start with a "0".
     *
     */
    private void validateTin (String tin){
        if (tin == null || tin.length() != 10 || tin.charAt(0) == '0')
            throw new IllegalArgumentException("Tax Identification Number must have 10 digits.");
        if (!tin.matches(NUMERICAL_REGEX))
            throw new IllegalArgumentException("Tax Identification Number has invalid characters.");
    }

    /**
     * Checks the validity of the phone number, by checking that it's
     * purely numerical, has 11 digits, and doesn't start with a "0".
     *
     */
    private void validatePhoneNumber (String phoneNumber){
        //As the phone number is optional, this method only checks validity if there was some input
        if (phoneNumber == null || phoneNumber.trim().isEmpty())
            throw new IllegalArgumentException("Phone number is required");
        else if (phoneNumber.length() != 11 || phoneNumber.charAt(0) == '0')
            throw new IllegalArgumentException("Phone Number must have 11 digits.");
        else if (!phoneNumber.matches(NUMERICAL_REGEX))
            throw new IllegalArgumentException("Phone Number has invalid characters.");
    }

    /**
     * Checks the validity of the client's email, by checking that it's not empty.
     *
     */
    private void validateEmail (String email){
        if (email == null || email.trim().isEmpty())
            throw new IllegalArgumentException("Email is required.");
        // Regular Expression for email validation from: https://stackoverflow.com/questions/201323/how-to-validate-an-email-address-using-a-regular-expression
        if (!email.matches("(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+" +
                ")*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\" +
                "x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*" +
                "\")@(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]" +
                "*[a-zA-Z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5" +
                "[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-zA-Z0-9-]*[a-zA-Z0-9]:(?:[\\x01-\\x0" +
                "8\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"))
            throw new IllegalArgumentException("Email is invalid.");
    }
    //endregion

    /**
     * Adds a new test to the Client's test list.
     */
    public void addTest(Test test){
        tests.add(test);
    }

    /**
     * Method to edit the client's data
     * @param updatedClient updated client
     * @return true if the data was updated
     */
    public boolean editData(Client updatedClient){
        setName(updatedClient.getName());
        setBirthDate(updatedClient.getBirthDate());
        setSex(updatedClient.getSex());
        setCcn(updatedClient.getCcn());
        setNhsNumber(updatedClient.getNhsNumber());
        setTin(updatedClient.getTin());
        setPhoneNumber(updatedClient.getPhoneNumber());
        setEmail(updatedClient.getEmail());
        return true;
    }
}