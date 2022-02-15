package app.ui.console;

import app.controller.TestTypeController;
import app.domain.model.ParameterCategory;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Luís Araújo
 */
public class TestTypeUI implements Runnable {
    private final TestTypeController testTypeController;
    private List<ParameterCategory> categories;

    public TestTypeUI() {
        testTypeController = new TestTypeController();
    }

    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        String answer;

        String code;
        String description;
        String collecting_method;
        categories = new ArrayList<>();

        System.out.println("\nNew Test Type: ");
        System.out.print("\nCode (Max 5 alphanumeric characters): ");
        code = in.nextLine();
        System.out.print("\nDescription (Max 15 chars): ");
        description = in.nextLine();
        System.out.print("\nCollecting Method (Max 15 chars): ");
        collecting_method = in.nextLine();

        System.out.println("\nAdd parameter categories? [y/n]");
        answer = in.nextLine();

        switch (answer) {
            case "y":
            case "Y":
                addCategoriesMenu();
                break;
            case "n":
            case "N":
                System.out.println("No categories were added.");
                break;
            default:
                System.out.println("Invalid answer. No parameter categories were added");
                break;
        }


        boolean testTypeCreated = testTypeController.createTestType(code, description, collecting_method, categories);

        if (testTypeCreated) {

            int maxAttempts = 3;
            boolean success;

            System.out.println();
            System.out.println(testTypeController.showTestType());

            do {
                maxAttempts--;
                success = false;

                System.out.println("Confirm new test type? [y/n]");
                answer = in.nextLine();

                switch (answer) {
                    case "y":
                    case "Y":
                        if (testTypeController.addTestType()) {
                            System.out.println("Successfully added.");
                        } else {
                            System.out.println("Test Type already exists.");
                        }
                        if (!testTypeController.saveTestType())
                        {
                            System.out.println("Error saving to system. All data will be lost.");
                        }
                        success = true;
                        break;
                    case "n":
                    case "N":
                        System.out.println("Test Type not added.");
                        success = true;
                        break;
                    default:
                        System.out.printf("%nInvalid Answer. %d tries remaining%n", maxAttempts);
                }
            } while (!success && maxAttempts > 0);
        } else {
            System.out.println("Try again? [y/n]");
            answer = in.nextLine();

            switch (answer) {
                case "y":
                case "Y":
                    this.run();
                    break;
                default:
                    System.out.println("Test Type not created.");
                    break;
            }
        }
    }

    public void addCategoriesMenu (){
        Scanner sc = new Scanner(System.in);
        int op;
        String answer;

        do{
            List<String> parameterCategoryList = testTypeController.getParameterCategoryList();
            if (!parameterCategoryList.isEmpty()){

                int chosenCat = Utils.showAndSelectIndex(parameterCategoryList, "\nSelect Parameter Category: ");
                ParameterCategory pc = testTypeController.getParameterCategory(chosenCat);
                categories.add(pc);

                System.out.println("\nAdd more categories? [y/n]");
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
                System.out.println("No existing parameter categories");
                op = -1;
            }
        } while (op != -1);
    }

}
