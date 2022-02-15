package app.ui.console;

import app.controller.CreateParameterCategoryController;

import java.util.Scanner;

public class CreateParameterCategoryUI implements Runnable{
    private CreateParameterCategoryController createParameterCategoryController;

    public CreateParameterCategoryUI(){
        createParameterCategoryController = new CreateParameterCategoryController();
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        String answer;

        //ATRIBUTES
        String name;
        String description;
        String code;
        String nhsIdentifier;

        //DATA INPUT
        System.out.printf("%nNew Parameter Category: %n");
        System.out.printf("Name: ");
        name = sc.nextLine();
        System.out.printf("%nDescription (Max 40 chars): ");
        description = sc.nextLine();
        System.out.printf("%nCode (4-8 digits): ");
        code = sc.nextLine();

        //PARAMETER CATEGORY CREATION
        boolean parameterCategoryCreated = createParameterCategoryController.
                createParameterCategory(name, description, code);

        //CONFIRMATION OF CLIENT DATA
        if (parameterCategoryCreated) {

            int maxAttempts = 3;
            boolean success;

            System.out.println();
            createParameterCategoryController.showParameterCategory();

            do {
                maxAttempts--;
                success = false;

                System.out.println("Confirm new parameter category? [y/n]");
                answer = sc.nextLine();

                switch (answer) {
                    case "y":
                    case "Y":
                        if (createParameterCategoryController.addParameterCategory()) {
                            System.out.println("Sucessfully added.");
                        } else {
                            System.out.println("Category already exists.");
                        }
                        if (!createParameterCategoryController.saveParameterCategory())
                        {
                            System.out.println("Error saving to system. All data will be lost.");
                        }
                        success = true;
                        break;
                    case "n":
                    case "N":
                        System.out.println("Parameter Category not added.");
                        success = true;
                        break;
                    default:
                        System.out.printf("%nInvalid Answer. %d tries remaining%n", maxAttempts);
                }
            } while (!success && maxAttempts > 0);
        } else {
            System.out.println("Try again? [y/n]");
            answer = sc.nextLine();

            switch (answer) {
                case "y":
                case "Y":
                    //To try again, the program reruns the UI.
                    this.run();
                    break;
                default:
                    System.out.println("Parameter Category not created.");
                    break;
            }
        }
    }
}
