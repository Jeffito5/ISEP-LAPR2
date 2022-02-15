package app.domain.model;

import junit.framework.TestCase;

public class SpecialistDoctorTest extends TestCase {

    String DIN1="-111111";
    String DINV="123456";
    SpecialistDoctor sd=new SpecialistDoctor("j@gmail.com","ju","maia","12345678901",
            "1234","SPECIALISTDOC",DINV,0);
    SpecialistDoctor sd1=new SpecialistDoctor("j@gmail.com","ju","maia","12345678901",
            "1234","SPECIALISTDOC",DIN1,0);



    public void testGetDoctorIndexNumber() {
        assertEquals(sd.getDoctorIndexNumber(),DINV);
    }

    public void testIsDINValid() {
        assertEquals(sd.isDINValid(),true);
        assertEquals(sd1.isDINValid(),false);

    }

    public void testIsEmployeeValid() {
        assertEquals(sd.isEmployeeValid(),true);
        assertEquals(sd1.isEmployeeValid(),false);

    }

    public void testTestToString() {
        SpecialistDoctor sd2=new SpecialistDoctor("j@gmail.com","ju","maia","12345678901",
                "1234","SPECIALISTDOC",DINV,0);
        assertEquals(sd2.toString().equalsIgnoreCase(sd.toString()),true);
        assertEquals(sd2.toString().equalsIgnoreCase(sd1.toString()),false);
    }

    public void testSetDoctorIndexNumber() {
        String din10="555444";
        sd.setDoctorIndexNumber(din10);
        assertEquals(sd.getDoctorIndexNumber().equalsIgnoreCase(din10),true);
    }
}