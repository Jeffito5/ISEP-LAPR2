package app.ui.console;

import app.controller.ClinicalAnalysisLaboratoryController;
import app.domain.model.TestType;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Luís Araújo
 */
public class ClinicalAnalysisLaboratoryUI implements Runnable {
    private final ClinicalAnalysisLaboratoryController clinicalAnalysisLaboratoryController;
    List<String> testTypes;
    private static Scanner in;

    public ClinicalAnalysisLaboratoryUI() {
        clinicalAnalysisLaboratoryController = new ClinicalAnalysisLaboratoryController();
    }

    @Override
    public void run() {
        in = new Scanner(System.in);
        String answer;
        String name;
        String laboratoryId;
        String phoneNumber;
        String tinNumber;
        String address;
        testTypes = new ArrayList<>();

        System.out.print("\nNew clinical analysis laboratory: \n");
        System.out.print("\nName of the clinic (Max 20 chars): ");
        name = in.nextLine();
        System.out.print("\nLaboratory's ID (5 digits): ");
        laboratoryId = in.nextLine();
        System.out.print("\nLaboratory's phone number (11 digits): ");
        phoneNumber = in.nextLine();
        System.out.print("\nLaboratory's TIN number (10 digits): ");
        tinNumber = in.nextLine();
        System.out.print("\nLaboratory's address (Max 30 chars): ");
        address = in.nextLine();

        System.out.println("\nAdd tests? [y/n]");
        answer = in.nextLine();

        switch (answer) {
            case "y":
            case "Y":
                addTestsMenu();
                break;
            case "n":
            case "N":
                System.out.println("No tests were added.");
                break;
            default:
                System.out.println("Invalid answer. No tests were added");
                break;
        }

        //PARAMETER CATEGORY CREATION
        boolean clinicalAnalysisLaboratoryCreated = clinicalAnalysisLaboratoryController.createClinicalAnalysisLaboratory(name,
                laboratoryId, phoneNumber, tinNumber, address, testTypes);

        if (clinicalAnalysisLaboratoryCreated) {
            confirmClinicalAnalysisLaboratory();
        } else {
            boolean success = false;

            do{
                System.out.println("Try again? [y/n]");
                answer = in.nextLine();

                switch (answer) {
                    case "y":
                    case "Y":
                        this.run();
                        success = true;
                        break;
                    case "n":
                    case "N":
                        System.out.println("Clinical Analysis Laboratory not created.");
                        success = true;
                        break;
                    default:
                        System.out.println("Invalid answer");
                        break;
                }
            } while (!success);
        }
    }

    public void addTestsMenu (){
        Scanner sc = new Scanner(System.in);
        int op;
        String answer;

        do{
            List<String> testTypeList = clinicalAnalysisLaboratoryController.getTestTypeList();
            if (!testTypeList.isEmpty()){
                op = Utils.showAndSelectIndex(testTypeList, "Select Test Types: ");
                TestType tt = clinicalAnalysisLaboratoryController.getTestType(op);
                testTypes.add(tt.getDescription());

                System.out.println("Add more tests? [y/n]");
                answer = sc.nextLine();

                switch (answer) {
                    case "y":
                    case "Y":
                        op = 0;
                        break;
                    case "n":
                    case "N":
                        op = -1;
                        break;
                    default:
                        System.out.println("Invalid answer. Exiting.");
                        op = -1;
                        break;
                }

            } else {
                System.out.println("\nNo existing test types.");
                op = -1;
            }

        } while (op != -1);
    }

    public void confirmClinicalAnalysisLaboratory(){
        int maxAttempts = 3;
        boolean success;
        String answer;

        System.out.println();
        clinicalAnalysisLaboratoryController.showParameterCategory();

        do {
            maxAttempts--;
            success = false;

            System.out.println("\nConfirm new clinical analysis laboratory? [y/n]");
            answer = in.nextLine();

            switch (answer) {
                case "y":
                case "Y":
                    if (clinicalAnalysisLaboratoryController.addParameterCategory()) {
                        System.out.println("Successfully added.");
                    } else {
                        System.out.println("Clinical Analysis Laboratory already exists.");
                    }
                    if (!clinicalAnalysisLaboratoryController.saveCal())
                    {
                        System.out.println("Error in saving the laboratory. All data will be lost.");
                    }
                    success = true;
                    break;
                case "n":
                case "N":
                    System.out.println("Clinical Analysis Laboratory not added.");
                    success = true;
                    break;
                default:
                    System.out.printf("%nInvalid Answer. %d tries remaining%n", maxAttempts);
            }
        } while (!success && maxAttempts > 0);
    }
}

