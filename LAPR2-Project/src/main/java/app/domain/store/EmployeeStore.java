package app.domain.store;

import app.domain.model.Employee;
import app.domain.model.SpecialistDoctor;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmployeeStore implements Serializable {
    private final List<Employee> empStore=new ArrayList<Employee>();

    /**
     * Creates an instance of an employee
     * @param email employee email
     * @param name employee name
     * @param address employee address
     * @param phoneNumber employee Phone Number
     * @param soc employee SOC
     * @param organizationRole employee role
     * @return instance of employee
     */
    public Employee createEmployee(String email, String name, String address,
                                   String phoneNumber, String soc, String organizationRole){

        Employee employee=new Employee(email, name, address, phoneNumber, soc, organizationRole, empStore.size());
        return employee;
    }

    /**
     * Creates an instance of Specialist Doctor
     * @param email specialist doctor email
     * @param name specialist doctor name
     * @param address specialist doctor Address
     * @param phoneNumber specialist doctor Phone Number
     * @param soc specialist doctor SOC
     * @param organizationRole specialist doctor role
     * @param din specialist doctor DIN
     * @return instance of specialist doctor
     */
    public SpecialistDoctor createEmployee(String email, String name, String address,
                                   String phoneNumber, String soc, String organizationRole, String din){

        SpecialistDoctor employee=new SpecialistDoctor(email, name, address, phoneNumber, soc, organizationRole, din,empStore.size());

        return employee;
    }

    /**
     * Shows the data of an employee
     * @param emp
     * @return data of employee
     */
    public String showEmployee(Employee emp){
        String ret;

          return  ret=emp.toString();


    }

    /**
     * Adds an employee to the store
     * @param emp employee
     */
    public boolean save(Employee emp){
        return empStore.add(emp);
    }

    /**
     * Shows list of employee list (by ID)
     */


    /**
     * checks if employee is valid
     * @param emp employee
     * @return true if employee is valid
     */

    public boolean isEmployeeValid(Employee emp){
        return emp.isEmployeeValid();

    }









}
