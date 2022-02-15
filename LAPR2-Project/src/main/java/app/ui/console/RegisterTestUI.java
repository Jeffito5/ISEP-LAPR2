package app.ui.console;

import app.controller.RegisterTestController;
import app.mappers.dto.ParameterCategoryDto;
import app.mappers.dto.ParameterDto;
import app.mappers.dto.TestTypeDto;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Rui Rocha
 */
public class RegisterTestUI implements Runnable{

    /**
     * Instance of RegisterTestController.
     */
    private final RegisterTestController registerTestController;

    /**
     * Counter of test parameters added to the test.
     */
    private static int testParameters;

    /**
     * Clinical Analysis Laboratory's Identification Number.
     */
    private final String clinicalAnalysisLaboratoryId;

    private final String INVALID_ANSWER = "Invalid answer.";

    public RegisterTestUI(String clinicalAnalysisLaboratoryId){
        registerTestController = new RegisterTestController();
        this.clinicalAnalysisLaboratoryId = clinicalAnalysisLaboratoryId;
    }

    @Override
    public void run() {
        //UTILS
        Scanner sc = new Scanner(System.in);
        boolean success;
        boolean validAnswer;
        String answer;
        int maxAttempts;

        //ATTRIBUTES
        String nhsCode;
        String clientTin;
        int testTypeIndex;
        String testTypeCode;
        testParameters = 0;

        //MENU TITLE
        System.out.printf("%nNEW TEST:%n");

        //CLIENT SELECTION
        do {

            System.out.printf("%nClient Tax Identification Number (10 digits): %n");
            clientTin = sc.nextLine();

            success = registerTestController.hasClient(clientTin);

            if (!success)
            {
                List<String> options = new ArrayList<>();

                options.add("Insert new Tax Identification Number");
                options.add("Register Client");

                do {
                    validAnswer = true;

                    int option = Utils.showAndSelectIndex(options, "");

                    switch (option)
                    {
                        case -1:
                            System.out.println("Cancelled.");
                            return;
                        case 0:
                            break;
                        case 1:
                            //Registering the new client
                            RegisterClientUI registerClientUI = new RegisterClientUI();
                            registerClientUI.run();
                            break;
                        default:
                            System.out.println(INVALID_ANSWER);
                            validAnswer = false;
                            break;
                    }
                } while (!validAnswer);
            }

        } while (!success);

        //TEST TYPE SELECTION
        List<TestTypeDto> testTypesDto = registerTestController.getTestTypes();

        testTypeIndex = Utils.showAndSelectIndex(testTypesDto, "\nSelect Test Types: ");

        if (testTypeIndex < 0)
        {
            return;
        }

        // Retrieving the test type code from the DTO.
        testTypeCode = testTypesDto.get(testTypeIndex).getCode();

        //PARAMETER CATEGORY SELECTION
        int parameterCategoryIndex;
        String parameterCategoryCode;
        List<ParameterCategoryDto> parameterCategoryDto;

        try {
            parameterCategoryDto = registerTestController.getParameterCategories(testTypeCode);
        } catch (IllegalArgumentException e){
            System.out.println("\nERROR: " + e.getMessage());
            return;
        }

        if (parameterCategoryDto.isEmpty())
        {
            System.out.println("\nNo existing parameter categories. Try again later.");
            return;
        }

        do{

            //Parameter Category selection
            parameterCategoryIndex = Utils.showAndSelectIndex(parameterCategoryDto, "\nSelect Parameter Category: ");

            if (parameterCategoryIndex < 0)
            {
                return;
            }

            parameterCategoryCode = parameterCategoryDto.get(parameterCategoryIndex).getCode();

            //Parameter selection
            try{
                success = selectParameterMenu(parameterCategoryCode);

            } catch (IllegalArgumentException | NullPointerException e){
                System.out.println(e.getMessage());
                success = false;
            }

            do {
                validAnswer = true;

                System.out.println("\nSelect other category? [y/n]");

                answer = sc.nextLine();

                switch (answer)
                {
                    case "y":
                    case "Y":
                        success = false;
                        break;
                    case "n":
                    case "N":
                        if (!success)
                            return;
                        success = true;
                        break;
                    default:
                        System.out.println(INVALID_ANSWER);
                        validAnswer = false;
                        break;
                }
            } while (!validAnswer);

        } while (!success);


        //TEST CREATION
        do {
            success = true;

            //DATA INPUT
            System.out.printf("%nNHS Code (12 digits): %n");
            nhsCode = sc.nextLine();

            boolean created = registerTestController.createTest(clinicalAnalysisLaboratoryId, nhsCode);

            if (!created)
            {
                success = false;

                System.out.println("Try again? [y/n]");

                do {
                    validAnswer = true;
                    answer = sc.nextLine();

                    switch (answer)
                    {
                        case "y":
                        case "Y":
                            break;
                        case "n":
                        case "N":
                            return;
                        default:
                            System.out.println(INVALID_ANSWER);
                            validAnswer = false;
                            break;
                    }
                } while (!validAnswer);
            }
        } while(!success);


        //TEST CONFIRMATION
        System.out.println();
        registerTestController.showTest();

        maxAttempts = 3;

        do{
            maxAttempts--;
            success = false;

            System.out.println("Confirm new test? [y/n]");
            answer = sc.nextLine();

            switch (answer){
                case "y":
                case "Y":
                    if(registerTestController.addTest())
                    {
                        System.out.println("Successfully added.");
                    }
                    if(!registerTestController.saveTest())
                    {
                        System.out.println("Error in saving the test. All data will be lost.");
                    }
                    success = true;
                    break;
                case "n":
                case "N":
                    System.out.println("Test not added.");
                    success = true;
                    break;
                default:
                    System.out.printf("%nInvalid Answer. %d tries remaining%n", maxAttempts);
            }
        } while (!success && maxAttempts > 0);

    }

    /**
     * Menu to select parameters, receiving the parameter category Index number.
     *
     * @return true if there are selected parameters.
     */
    public boolean selectParameterMenu(String parameterCategoryCode){
        Scanner sc = new Scanner(System.in);
        String answer;
        int parameterIndex;
        String parameterCode;
        int categoryParameters = 0;
        boolean success = false;
        boolean validAnswer;

        validAnswer = true;
        List<ParameterDto> parameterDto = registerTestController.getParameters(parameterCategoryCode);

        do {

            //Verifies that DTO is not empty, i.e., there's no parameters
            if (parameterDto.isEmpty())
                throw new NullPointerException("\nNo existing parameters. Try again later.");

            //Parameter Selection
            parameterIndex = Utils.showAndSelectIndex(parameterDto, "\nSelect Parameters: ");

            //Verifies that at least one parameter was added.
            if (parameterIndex < 0 && testParameters == 0)
            {
                throw new IllegalArgumentException("\nParameters required.");
            }
            else if (parameterIndex < 0)
            {
                return true;
            }

            parameterCode = parameterDto.get(parameterIndex).getCode();

            //Adds parameters to be tested
            if (registerTestController.addParametersForTest(parameterCode))
            {
                System.out.println("\nParameter successfully added.");
                testParameters++;
                categoryParameters++;
            }
            else {
                System.out.println("\nParameter already on the test.");
            }

            //If there's more parameters on that category, asks if user wants to add more
            if (categoryParameters < parameterDto.size())
            {
                System.out.println("\nAdd more parameters? [y/n]");
                answer = sc.nextLine();

                switch (answer)
                {
                    case "y":
                    case "Y":
                        success = false;
                        break;
                    case "n":
                    case "N":
                        success = true;
                    break;
                    default:
                        System.out.println(INVALID_ANSWER);
                        validAnswer = false;
                        break;
                }
            } else{
                success = true;
            }
        } while (!success || !validAnswer);

        return true;
    }
}
