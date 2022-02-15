package app.domain.model;

import junit.framework.TestCase;

public class EmployeeTest extends TestCase {
    String email="j@gmail.com";
    String name="ju";
    String Address="maia";
    String PhoneNumber="12345678901";
    String SOC="1234";
    String EMAIL_POR_OMISSAO="sem email";
    String NAME_POR_OMISSAO="sem nome";
    String ADRESS_POR_OMISSAO="sem address";
    private Employee e=new Employee(email,name,Address,PhoneNumber,SOC,"LABCORD",0);

    private  Employee e1=new Employee("123",name,Address,PhoneNumber,SOC,"LABCORD",0);

    private Employee e2= new Employee(email,name,Address,"1234",SOC,"LABCORD",0);

    private Employee empO=new Employee();


    public void testGetEmail() {
        String expectedEmail="j@gmail.com";
        assertEquals(e.getEmail(),expectedEmail);
        assertEquals(empO.getEmail(),EMAIL_POR_OMISSAO);
    }

    public void testTestGetName() {
        String expectedName="ju";
        assertEquals(e.getName(),expectedName);
        assertEquals(empO.getName(),NAME_POR_OMISSAO);
    }

    public void testGetAddress() {
        String expectedAddress="maia";
        assertEquals(e.getAddress(),expectedAddress);
        assertEquals(empO.getAddress(),ADRESS_POR_OMISSAO);
    }

    public void testGetPhoneNumber() {
        String expectedPhoneNumber="12345678901";
        assertEquals(e.getPhoneNumber(),expectedPhoneNumber);
    }

    public void testGetSOC() {
        String expectedSOC="1234";
        assertEquals(e.getSOC(),expectedSOC);
    }

    public void testIsEmployeeValid() {
        assertEquals(e.isEmployeeValid(),true);
        assertEquals(e1.isEmployeeValid(), false);
        assertEquals(e2.isEmployeeValid(), false);

    }

    public void testSetEmail() {
        String email="j111@gmail.com";
        e.setEmail(email);
        assertEquals(e.getEmail().equalsIgnoreCase(email),true);
    }

    public void testTestSetName() {
        String name="juaoooo";
        e.setName(name);
        assertEquals(e.getName().equalsIgnoreCase(name),true);
    }



    public void testSetOrganizationRole() {String organizationRole="ADMINISTRATOR";
    e.setOrganizationRole(organizationRole);
    assertEquals(e.getOrganizationRole().equalsIgnoreCase(organizationRole),true);
    }

    public void testSetAddress() {
        String address="maia";
        e.setAddress(address);
        assertEquals(e.getAddress().equalsIgnoreCase(address),true);
    }

    public void testSetPhoneNumber() {
        String phoneNumber="10987654321";
        e.setPhoneNumber(phoneNumber);
        assertEquals(e.getPhoneNumber().equalsIgnoreCase(phoneNumber),true);
    }

    public void testSetSOC() {
        String soc="4321";
        e.setSOC(soc);
        assertEquals(e.getSOC().equalsIgnoreCase(soc),true);
    }
}