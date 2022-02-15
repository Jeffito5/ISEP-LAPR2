package app.ui.console;

import app.controller.RecordTestResultController;
import app.mappers.dto.ParameterDto;
import app.ui.console.utils.Utils;

import java.util.List;
import java.util.Scanner;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class RecordTestResultUI implements Runnable {

    private final RecordTestResultController recordTestResultController;

    public RecordTestResultUI(){
        recordTestResultController = new RecordTestResultController();
    }

    @Override
    public void run() {
        //UTILS
        Scanner sc = new Scanner(System.in);
        boolean success;
        boolean validAnswer;
        String answer;

        //ATTRIBUTES
        double result;
        String metric;
        String sampleCode;
        String parameterCode;

        // MENU TITLE
        System.out.printf("%nRECORD RESULTS:%n%n");

        // TEST SELECTION
        do{
            System.out.printf("%nSample Code (First 11 digits): %n");
            sampleCode = sc.nextLine();

            success = recordTestResultController.hasTestWithSample(sampleCode);

            if (!success){

                System.out.println("Try again? [y/n]");

                do {
                    validAnswer = true;
                    answer = sc.nextLine();

                    switch (answer) {
                        case "y":
                        case "Y":
                            success = false;
                            break;
                        case "n":
                        case "N":
                            return;
                        default:
                            System.out.println("Invalid answer.");
                            validAnswer = false;
                            break;
                    }
                } while (!validAnswer);
            }
        } while (!success);

        //TEST PARAMETERS SELECTION
        List<ParameterDto> parametersDto;

        try{
            parametersDto = recordTestResultController.getTestParameters();
        } catch (IllegalArgumentException e){
            System.out.println("\n" + e.getMessage());
            return;
        }


        if (parametersDto.isEmpty()){
            System.out.println("\nTest has no parameters to be tested.");
            return;
        }

        for (ParameterDto parameterDto : parametersDto) {

            parameterCode = parameterDto.getCode();

            do{
                System.out.println("\n" + parameterDto.toString());
                result = Utils.readDoubleFromConsole("Result: ");

                System.out.printf("%nMetric: %n");
                metric = sc.nextLine();

                success = false;

                System.out.printf("%nResult: %.2f %s%n", result, metric);

                do {
                    validAnswer = true;

                    System.out.println("\nConfirm? [y/n]");

                    answer = sc.nextLine();

                    switch (answer) {
                        case "y":
                        case "Y":
                            recordTestResultController.addTestResult(parameterCode, result, metric);
                            success = true;
                            break;
                        case "n":
                        case "N":
                            break;
                        default:
                            System.out.println("\nInvalid answer.");
                            validAnswer = false;
                            break;
                    }
                } while (!validAnswer);

            } while (!success);
        }

        recordTestResultController.setTestResultStateToAnalyzed();
        recordTestResultController.setTestResultDate();
        System.out.println("\nResult successfully added.");

        if(!recordTestResultController.saveStore())
        {
            System.out.println("Error saving to system. All data will be lost.");
        }
    }
}
