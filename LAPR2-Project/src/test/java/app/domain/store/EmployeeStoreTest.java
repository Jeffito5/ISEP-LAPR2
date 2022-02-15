package app.domain.store;

import app.controller.CreateEmployeeController;
import app.domain.model.Employee;
import app.domain.model.SpecialistDoctor;
import junit.framework.TestCase;

public class EmployeeStoreTest extends TestCase {
    EmployeeStore empStore=new EmployeeStore();

    String email9="j@gmail.com";
    String name9="ju";
    String address9="maia";
    String phoneNumber9="12345678901";
    String soc9="1234";
    private Employee e=empStore.createEmployee(email9,name9,address9,phoneNumber9,soc9,"LABCORD");

    private  Employee e3=empStore.createEmployee("123",name9,address9,phoneNumber9,soc9,"LABCORD");

    private Employee e4= empStore.createEmployee(email9,name9,address9,"1234",soc9,"LABCORD");


    private String email="j@gmail.com";
    private String name="ju";
    private String name1="juuuuu";
    private String address="maia";
    private String phoneNumber="12345678901";
    private String soc="1234";
    private String organizationRole="LABCORD";
    private String din="123456";

    private Employee e1=new Employee(email,name,address,phoneNumber,soc,organizationRole,0);
    private SpecialistDoctor e2=new SpecialistDoctor(email,name,address,phoneNumber,soc,organizationRole,din,0);
    public void testCreateEmployee() {
        Employee ev2= empStore.createEmployee(email,name,address,phoneNumber,soc,organizationRole);
        Employee ev1=empStore.createEmployee(email,name1,address,phoneNumber,soc,organizationRole);
        assertEquals(ev2.toString().equalsIgnoreCase(e1.toString()),true);
        assertEquals(e1.toString().equalsIgnoreCase(ev1.toString()),false);

    }

    public void testTestCreateEmployee() {
        SpecialistDoctor sd1=empStore.createEmployee(email,name,address,phoneNumber,soc,organizationRole,din);
        SpecialistDoctor sd2=empStore.createEmployee(email,name1,address,phoneNumber,soc,organizationRole,"1114444441");

        assertEquals(e2.toString().equalsIgnoreCase(sd1.toString()),true);
        assertEquals(e2.toString().equalsIgnoreCase(sd2.toString()),false);
    }

    public void testIsEmployeeValid() {
        assertEquals(e.isEmployeeValid(),true);
        assertEquals(e3.isEmployeeValid(), false);
        assertEquals(e4.isEmployeeValid(), false);
    }


    public void testShowEmployee() {
        String ve1=empStore.showEmployee(e1);
        String ve2=empStore.showEmployee(e2);
        assertEquals(e1.toString().equalsIgnoreCase(ve1),true);
        assertEquals(e2.toString().equalsIgnoreCase(ve2),true);
        assertEquals(e1.toString().equalsIgnoreCase(ve2),false);

    }

    public void testSave() {
        assertEquals(empStore.save(e1),true);
    }

    public void testTestIsEmployeeValid() {
        assertEquals(empStore.isEmployeeValid(e1),true);
        assertEquals(empStore.isEmployeeValid(e3),false);
    }
}