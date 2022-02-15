package app.domain.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author
 */
public class MedicalLabTechnicianTest {
    String din1 = "-555555";
    String din2 = "555555";
    MedicalLabTechnician mlt1 = new MedicalLabTechnician("luis@gmail.com", "Luís", "Rua 1", "67381947161",
            "5555", "MEDICAL LAB TECHNICIAN", din2, 0);
    MedicalLabTechnician mlt2 = new MedicalLabTechnician("luis@gmail.com", "Luís", "Rua 1", "67381947161",
            "5555", "MEDICAL LAB TECHNICIAN", din1, 0);

    @Test
    public void testGetMedicalLabTechnicianIndexNumber() {
        assertEquals(mlt1.getMedicalLabTechnicianIndexNumber(), din2);
    }

    @Test
    public void testIsDINValid() {
        assertEquals(mlt1.isDINValid(), true);
        assertEquals(mlt2.isDINValid(), false);

    }
    @Test
    public void testIsDINValid2() {
        mlt1.setMedicalLabTechnicianIndexNumber("999999");
        mlt2.setMedicalLabTechnicianIndexNumber("100000");
        assertEquals(mlt1.isDINValid(), true);
        assertEquals(mlt2.isDINValid(), true);

    }

    @Test
    public void testIsEmployeeValid() {
        assertEquals(mlt1.isEmployeeValid(), true);
        assertEquals(mlt2.isEmployeeValid(), false);

    }

    @Test
    public void testTestToString() {
        MedicalLabTechnician mlt3 = new MedicalLabTechnician("luis@gmail.com", "Luís", "Rua 1", "67381947161",
                "5555", "MEDICAL LAB TECHNICIAN", din2, 0);
        assertEquals(mlt3.toString().equalsIgnoreCase(mlt1.toString()), true);
        assertEquals(mlt3.toString().equalsIgnoreCase(mlt2.toString()), false);
    }

    @Test
    public void testSetDoctorIndexNumber() {
        String din3 = "111111";
        mlt1.setMedicalLabTechnicianIndexNumber(din3);
        assertEquals(mlt1.getMedicalLabTechnicianIndexNumber().equalsIgnoreCase(din3), true);
    }
}