package app.domain.model;


import java.io.Serializable;

public class Employee implements Serializable {
    /**
     * email of the employee
     */
    private String email;
    private static final String EMAIL_POR_OMISSAO="sem email";
    /**
     * name of the employee
     */
    private String name;
    private static final String NAME_POR_OMISSAO="sem nome";
    /**
     * ID of the employee
     */
    private String EmployeeID;
    private static final String EMPLOYEEID_POR_OMISSAO="sem id";
    /**
     * Role of the employee
     */
    private String organizationRole;
    private static final String ORGANIZATIONROLE_POR_OMISSAO="sem role";
    /**
     * Address of the employee
     */
    private String address;
    private static final String ADRESS_POR_OMISSAO="sem address";
    /**
     * Phone Number of the employee
     */
    private String phoneNumber;
    private static final String PHONENUMBER_POR_OMISSAO="sem phonenumber";
    /**
     * Standard Occupational Classification code of the employee
     */
    private String soc;
    private static final String SOC_POR_OMISSAO="sem soc";


    /**
     * Constructs an employee receiving the parameters below while validating Name, PhoneNumber, SOC
     * @param email employee's email
     * @param name employee's address
     * @param address employee's address
     * @param phoneNumber employee's Phone Number
     * @param soc employee's SOC
     * @param organizationRole employee's role
     * @param numberOfEMp number of employees in the company
     */
    public Employee(String email,  String name, String address,
                    String phoneNumber, String soc, String organizationRole, int numberOfEMp){
        String e="erro";
        if(isNameValid(name)){
                this.name=name;
            }else{
            System.out.println(e);
            this.name=" ";
            }

            if(isPhoneNumberValid(phoneNumber)){
                this.phoneNumber=phoneNumber;
            }else{
                System.out.println(e);
                this.phoneNumber="0";
            }

            if(isSOCValid(soc)){
                this.soc=soc;
            }else{
                System.out.println(e);
                this.soc="0";
            }




        this.EmployeeID=generateID(numberOfEMp);
        this.email=email;

        this.address=address;



        this.organizationRole=organizationRole;

    }

    /**
     * Constructs an employee with every field with omission value
     */
    public Employee(){
        this.EmployeeID=EMPLOYEEID_POR_OMISSAO;
        this.email=EMAIL_POR_OMISSAO;
        this.name=NAME_POR_OMISSAO;
        this.address=ADRESS_POR_OMISSAO;
        this.phoneNumber=PHONENUMBER_POR_OMISSAO;
        this.soc=SOC_POR_OMISSAO;
        this.organizationRole=ORGANIZATIONROLE_POR_OMISSAO;

    }


    /**
     * returns employee email
     * @return employee's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * returns employee's name
     * @return employee's name
     */
    public String getName() {
        return name;
    }

    /**
     * returns employee's ID
     * @return employee's ID
     */
    public String getEmployeeID() {
        return EmployeeID;
    }

    /**
     * returns employee's role
     * @return employee's role
     */
    public String getOrganizationRole() {
        return organizationRole;
    }

    /**
     * returns employee's Address
     * @return employee's Address
     */
    public String getAddress() {
        return address;
    }

    /**
     * returns employee's Phone Number
     * @return employee's Phone Number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * returns employee's SOC
     * @return employee's SOC
     */
    public String getSOC() {
        return soc;
    }

    /**
     * Changes employee email
     * @param email employee's new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Changes employee's name
     * @param name new employee's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * changes employee's name
     * @param employeeID new employee's ID
     */


    /**
     * changes employee's Role
     * @param organizationRole new Employee's Role
     */
    public void setOrganizationRole(String organizationRole) {
        this.organizationRole = organizationRole;
    }

    /**
     * changes employee's address
     * @param address new employee's address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * changes employee's Phone Number
     * @param phoneNumber new employee's Phone Number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * changes employee's SOC code
     * @param SOC new employee's SOC code
     */
    public void setSOC(String SOC) {
        this.soc = SOC;
    }

    /**
     * Checks if email is valid
     * @param email employee's email
     * @return returns true if email is valid
     */
    private boolean isEmailValid(String email){
        try {
            if (email == null || email.trim().isEmpty())
                throw new IllegalArgumentException("Email is required.");
            // Regular Expression for email validation from: https://stackoverflow.com/questions/201323/how-to-validate-an-email-address-using-a-regular-expression
            if (!email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+" +
                    ")*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\" +
                    "x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*" +
                    "\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]" +
                    "*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5" +
                    "[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x0" +
                    "8\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"))
                throw new IllegalArgumentException("Email is invalid.");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("\nERROR: " + e.getMessage() + " (" + e.getClass().getSimpleName() + ")");
            return false;
        }
    }

    /**
     * checks if phone number is valid
     * @param phoneNumber employee's Phone number
     * @return true if phone number is valid
     */
    private boolean isPhoneNumberValid (String phoneNumber){
        try {
            //As the phone number is optional, this method only checks validity if there was some input
            if (phoneNumber != null && !phoneNumber.trim().isEmpty()){
                if (phoneNumber.length() != 11 || phoneNumber.charAt(0) == '0')
                    throw new IllegalArgumentException("Phone Number must have 11 digits.");
                if (!phoneNumber.matches("[0-9]+"))
                    throw new IllegalArgumentException("Phone Number has invalid characters.");
            }
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("\nERROR: " + e.getMessage() + " (" + e.getClass().getSimpleName() + ")");
            return false;
        }
    }

    /**
     * checks if name is valid
     * @param name employee's name
     * @return true if name is valid
     */
    private boolean isNameValid(String name){
        boolean flag=false;


        if ((name.trim().length()<45) && !(name.trim().isEmpty())){
            flag=true;

        }

        return flag;


    }

    /**
     * checks if SOC is valid
     * @param soc employee SOC
     * @return true if SOC is valid
     */
    private boolean isSOCValid(String soc){
        boolean flag=true;
        int sOC;
        sOC=Integer.parseInt(soc);
        if(sOC>9999 || sOC<1000){
            flag=false;
        }
        return flag;
    }

    /**
     * Shows the employee data
     * @return the employee data formated
     */
    public String toString(){
        return String.format("%nNome: %s%nEmployee ID: %s%nOrganization Role: %s%nAddress: %s%nPhone Number: %s%n" +
                        "email: %s%nStandard Occupational Classification code: %s%n",name,EmployeeID,organizationRole,
                address, phoneNumber, email,soc);
    }

    /**
     * checks if employee is valid
     * @return true if employee is valid
     */
    public boolean isEmployeeValid(){
        boolean flag=false;
        if(isNameValid(name) && isPhoneNumberValid(phoneNumber) && isSOCValid(soc) && isEmailValid(email)){
            flag=true;
        }
        return flag;
    }

    /**
     * generates an employee ID
     * @param n number of employees in the company
     * @return employee ID
     */
    public String generateID(int n){
        int num=n+1;
        String pad=String.valueOf(num);

        pad=String.format("%05d",num);



        EmployeeID="";
        try {
            String[] name = getName().split(" ");
            for (int i = 0; i < name.length; i++) {
                String s = name[i];
                EmployeeID = EmployeeID + s.charAt(0);

            }
            EmployeeID = EmployeeID + pad;
        }catch(Exception e){
            System.out.println("erro");
        }

        return EmployeeID;
    }
}
