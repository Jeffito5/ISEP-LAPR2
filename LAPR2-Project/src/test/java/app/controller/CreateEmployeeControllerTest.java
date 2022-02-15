package app.controller;

import app.domain.model.Employee;
import junit.framework.TestCase;

public class CreateEmployeeControllerTest extends TestCase {

    CreateEmployeeController cec=new CreateEmployeeController();

    public void testCreateEmployee() {


         Employee e1=cec.createEmployee("j@gmail.com", "J M V", "Maia", "12345678901", "1234", "LABCORD","");
        assertEquals(e1.isEmployeeValid(),true);
        Employee e2=cec.createEmployee("123", "J M V","Maia", "12345678901", "1234", "LABCORD","");
        assertEquals(e2.isEmployeeValid(), false);
        Employee e3=cec.createEmployee("j@gmail.com", "J M V", "Maia", "12345", "1234", "LABCORD","");
        assertEquals(e3.isEmployeeValid(), false);
        Employee e4=cec.createEmployee("j@gmail.com", "J M Vdgjdtbdtljedjlierjlgdjbjdjbjrgjjfjgjjfjdjfgjgjfjfjjgjdjdjfhfhhdhdfhfhfh","Maia", "12345678901", "1234", "LABCORD","");
        assertEquals(e2.isEmployeeValid(), false);


    }




    public void testRoleExist() {
        cec.createEmployee("j@gmail.com", "J M V", "Maia", "12345678901", "1234", "LABCORD","");

        assertEquals(cec.RoleExist(),true);

        cec.createEmployee("j@gmail.com", "J M V", "Maia", "12345678901", "1234", "DKANEPUTITDOWN","");
        assertEquals(cec.RoleExist(),false);
    }

    public void testIsEmployeeValid() {
        Employee e1=cec.createEmployee("j@gmail.com", "J M V", "Maia", "12345678901", "1234", "LABCORD","");
        Employee e2=cec.createEmployee("123", "J M V", "Maia", "12345678901", "1234", "LABCORD","");
        assertEquals(e1.isEmployeeValid(),true);
        assertEquals(e2.isEmployeeValid(),false);
    }

}