package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.store.EmployeeStore;
import auth.AuthFacade;
import auth.domain.model.*;
import auth.domain.store.EmployeeRoleStore;
import auth.domain.store.UserRoleStore;
import auth.domain.store.UserStore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;



public class CreateEmployeeController {


    private EmployeeRole OrganizationRole;
    private  App app;
    private final Company company;
    private Employee employee;
    private final EmployeeStore empStore;
    private final EmployeeRoleStore emprolestore;
    private UserRoleStore userRoleStore;
    private final UserStore userStore;
    private final AuthFacade authfacade;
    private final File empfile= new File("employeeEmailAndSMSMessages.txt");

    private final String passtemp=generateRandomPassword();
    private Password pwd;



    public CreateEmployeeController(){
        this.app=app.getInstance();
        this.company=app.getCompany();
        this.empStore=company.getEmployeeStore();
        this.emprolestore= company.getEmployeeRoleStore();
        this.userStore=company.getUserStore();
        this.authfacade=company.getAuthFacade();
        this.userRoleStore=company.getUserRoles();
    }


    /**
     * Creates an Instance of employee
     * @param emails email of the employee
     * @param name name of the employee
     * @param address address of the employee
     * @param phoneNumber Phone number of the employee
     * @param soc Standard Occputational Classification code of the employee
     * @param organizationRoles Role of the employee
     * @param din Doctor index number of the employee (if he has one)
     */
    public Employee createEmployee( String emails, String name, String address,
                                String phoneNumber, String soc, String organizationRoles, String din) {
        Email email;
        try{
            email=new Email(emails);

                OrganizationRole=new EmployeeRole(organizationRoles);



        }catch(Exception e){
            System.out.println("Erro 1");
        }




        if(din.equals("")){
            employee = empStore.createEmployee(emails, name, address, phoneNumber, soc, organizationRoles);

        }else{
            employee=empStore.createEmployee(emails, name, address, phoneNumber, soc, organizationRoles, din);
        }
        return employee;









    }

    /**
     * checks if the employee roles exists
     * @return true if role exists, if not return false
     */
    public boolean RoleExist(){



        return this.authfacade.rolesExists(OrganizationRole.getId());
    }

    /**
     * checks if a employee is valid through validation of certains fields
     * @return true if employee is valid, if not return false
     */
    public boolean isEmployeeValid(){
        return empStore.isEmployeeValid(employee);
    }


    /**
     * Shows the data of the employee
     */
    public void showEmployee(){
        empStore.showEmployee(employee);
    }


    /**
     * Shows the roles that are valid
     */
    public void showRolesList(){

        this.authfacade.showRoleslist();
    }

    /**
     * Saves the employee on the employee store
     * Creates an instance of a User with role
     * Writes the login credentials in a file
     */
    public void save(){
        String passtemp=generateRandomPassword();
        pwd=new Password(passtemp);

        empStore.save(employee);
        authfacade.addUserWithRole(employee.getName(), employee.getEmail(),passtemp,employee.getOrganizationRole());

        WritePassinFile(employee.getEmail(), passtemp);
    }


    /**
     * Generates a random password for the user
     * @return the password
     */
    public String generateRandomPassword(){
        //source: https://www.baeldung.com/java-random-string

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;

        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    /**
     * Writes in the file the login credentials of the user
     * @param email email of the user
     * @param pwd password of the user
     */
    public void WritePassinFile(String email, String pwd){
        try{
            FileWriter myWriter = new FileWriter("employeeEmailAndSMSMessages.txt", true);
            myWriter.append("\n ");
            myWriter.append("\n ");
            myWriter.append(email + " a sua palavra passe é " + pwd );
            myWriter.append("\n");
            myWriter.append("\n ");
            myWriter.close();
        } catch (IOException e){
            System.out.println("Error");
        }



    }

    public boolean saveEmployees()
    {
        return company.saveEmployees(empStore) && company.saveUsers();
    }








}

