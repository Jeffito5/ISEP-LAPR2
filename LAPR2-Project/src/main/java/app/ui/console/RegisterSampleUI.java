package app.ui.console;

import app.controller.ReceptionistController;
import app.controller.RegisterSampleController;
import app.controller.RegisterTestController;
import app.mappers.dto.ClinicalAnalysisLaboratoryDto;
import app.mappers.dto.TestDto;
import app.ui.console.utils.Utils;
import net.sourceforge.barbecue.BarcodeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Luís Araújo
 */
public class RegisterSampleUI implements Runnable {
    RegisterSampleController registerSampleController;
    ReceptionistController receptionistController;
    RegisterTestController registerTestController;
    boolean sampleUPCA;
    MedicalLabTechnicianUI medicalLabTechnicianUI;

    public RegisterSampleUI() {
        registerSampleController = new RegisterSampleController();
        receptionistController = new ReceptionistController();
        registerTestController = new RegisterTestController();
        medicalLabTechnicianUI = new MedicalLabTechnicianUI();
    }

    /**
     * Method that returns only the clinical analysis laboratory code using the mapper and dto
     *
     * @return the clinical analysis laboratory code
     */
    public String selectClinicalAnalysisLaboratory() {
        //ATTRIBUTES
        int clinicalAnalysisLaboratoryIndex;
        String clinicalAnalysisLaboratoryCode;

        List<ClinicalAnalysisLaboratoryDto> clinicalAnalysisLaboratoryDtoList = receptionistController.getClinicalAnalysisLaboratories();

        clinicalAnalysisLaboratoryIndex = Utils.showAndSelectIndex(clinicalAnalysisLaboratoryDtoList, "\nSelect Clinical Analysis Laboratory:");

        if (clinicalAnalysisLaboratoryIndex < 0)
            throw new IllegalArgumentException("Clinical Analysis Laboratory not selected.");

        clinicalAnalysisLaboratoryCode = clinicalAnalysisLaboratoryDtoList.get(clinicalAnalysisLaboratoryIndex).getLaboratoryId();

        return clinicalAnalysisLaboratoryCode;
    }

    @Override
    public void run() {
        List<String> options = new ArrayList<String>();
        List<TestDto> tests;
        Scanner in = new Scanner(System.in);
        int num;

        //ATRIBUTES
        int option = 0;
        int option2 = 0;
        String answer;

        // FILL THE ARRAY
        options.add("UPCA Barcode");

        //ESCOLHE UM DOS TIPOS DE TESTE
        // leva o id do lab para o controller filtrar os testes desse lab
        tests = registerSampleController.getTestsRegisted(selectClinicalAnalysisLaboratory());
        option2 = Utils.showAndSelectIndex(tests, "\n\nSelection of the test");

        TestDto testDto = tests.get(option2);
        String nhsCode = testDto.getNhsCode();

        System.out.println("How many samples do you want to regist?");
        num = in.nextInt();
        // no caso de não querer adicionar samples
        if (num == 0) {
            medicalLabTechnicianUI.run();
        }

        for (int i = 0; i < num; i++) {
            System.out.println("Select the type of barcode.\n");

            int maxAttempts = 3;
            boolean success;

            do {
                // PRINTING THE ARRAY AND SELECT THE BARCODE
                option = Utils.showAndSelectIndex(options, "\n\nMedical Lab Technician Menu");

                maxAttempts--;
                success = false;

                answer = Utils.readLineFromConsole("Confirm type of barcode? [y/n]");

                switch (answer) {
                    case "y":
                    case "Y":
                        try {
                            sampleUPCA = registerSampleController.createSample(nhsCode);
                        } catch (BarcodeException e) {
                            e.printStackTrace();
                        }
                        if (option == 0) {
                            if (sampleUPCA) {
                                try {
                                    registerSampleController.addTestSample(nhsCode);

                                    if(!registerSampleController.saveTests())
                                    {
                                        System.out.println("Error saving to system. All data will be lost.");
                                    }
                                } catch (BarcodeException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("Sucessfully added.");
                                registerSampleController.setTestStateToCollected();
                            }
                        } else {
                            System.out.println("Sample already exists.");
                        }
                        success = true;
                        medicalLabTechnicianUI.run();
                        break;
                    case "n":
                    case "N":
                        System.out.println("Sample not added.");
                        success = true;
                        break;
                    default:
                        System.out.printf("%nInvalid Answer. %d tries remaining%n", maxAttempts);
                }
            }
            while (!success && maxAttempts > 0);
        }
        if (!sampleUPCA) {
            boolean suc = false;
            do {
                answer = Utils.readLineFromConsole("Try again? [y/n]");

                switch (answer) {
                    case "y":
                    case "Y":
                        //To try again, the program reruns the UI.
                        this.run();
                        break;
                    case "n":
                    case "N":
                        System.out.println("Sample not created.");
                        suc = true;
                        medicalLabTechnicianUI.run();
                        break;
                    default:
                        System.out.println("Invalid answer.");
                        break;

                }
            } while (!suc);
        }
    }
}
