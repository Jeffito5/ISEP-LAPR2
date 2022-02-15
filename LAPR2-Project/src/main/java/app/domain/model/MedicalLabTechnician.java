package app.domain.model;

import java.io.Serializable;

/**
 * @author Luís Araújo
 */
public class MedicalLabTechnician extends Employee implements Serializable {
    private String medicalLabTechnicianIndexNumber;

    /**
     * Constructs an Specialist Doctor
     *
     * @param email                           specialist doctor email
     * @param name                            specialist doctor name
     * @param address                         specialist doctor address
     * @param phoneNumber                     specialist doctor phone number
     * @param soc                             specialist doctor SOC code
     * @param organizationRole                specialist doctor role
     * @param medicalLabTechnicianIndexNumber specialist doctor DIN
     * @param numberOfEMp                     number of employees in the company
     */
    public MedicalLabTechnician(String email, String name, String address, String phoneNumber, String soc, String organizationRole, String medicalLabTechnicianIndexNumber, int numberOfEMp) {
        super(email, name, address, phoneNumber, soc, organizationRole, numberOfEMp);
        this.medicalLabTechnicianIndexNumber = medicalLabTechnicianIndexNumber;
    }

    /**
     * returns specialist doctor's DIN
     *
     * @return specialist doctor DIN
     */
    public String getMedicalLabTechnicianIndexNumber() {
        return medicalLabTechnicianIndexNumber;
    }

    /**
     * changes specialist doctor's DIN
     *
     * @param medicalLabTechnicianIndexNumber new DIN code
     */
    public void setMedicalLabTechnicianIndexNumber(String medicalLabTechnicianIndexNumber) {
        this.medicalLabTechnicianIndexNumber = medicalLabTechnicianIndexNumber;
    }

    /**
     * checks if DIN code is valid
     *
     * @return true if DIN is valid
     */
    public boolean isDINValid() {
        boolean flag = true;
        int din;
        din = Integer.parseInt(medicalLabTechnicianIndexNumber);
        if (din > 999999 || din < 100000) {
            flag = false;
        }
        return flag;
    }

    /**
     * checks if specialist doctor is valid
     *
     * @return true if specialist doctor is valid
     */
    @Override
    public boolean isEmployeeValid() {
        boolean flag = false;

        if (super.isEmployeeValid() && isDINValid()) {
            flag = true;
        }

        return flag;
    }

    /**
     * shows the specialist doctor data
     *
     * @return specialist doctor data formated
     */
    @Override
    public String toString() {
        return String.format("%s%nDIN: %s%n", super.toString(), medicalLabTechnicianIndexNumber);
    }
}
