package app.ui.console;

import app.controller.RegisterClientController;
import app.mappers.dto.ClientDto;
import app.ui.console.utils.Utils;

import java.util.Date;
import java.util.Scanner;

public class RegisterClientUI implements Runnable{

    @Override
    public void run() {
        try{
            //Tries to instantiate a controller, that checks if the user is logged in as receptionist
            RegisterClientController registerClientController = new RegisterClientController();

            Scanner sc = new Scanner(System.in);
            String answer;

            //ATTRIBUTES
            String name;
            Date birthDate;
            ClientDto.Sex sex;
            String ccn;
            String nhsNumber;
            String tin;
            String phoneNumber;
            String email;

            //DATA INPUT
            System.out.printf("%nNEW CLIENT:%n");
            System.out.printf("%nName: %n");
            name = sc.nextLine();
            birthDate = Utils.readDateFromConsole("Birth Date (dd-MM-yyyy):");
            System.out.printf("%nCitizen Card Number (16 digits): %n");
            ccn = sc.nextLine();

            System.out.print("\nSex: \n");

            ClientDto.Sex.stream().filter(s -> s.showOp() < 4).
                    forEach(s -> System.out.printf("%d - %s%n", s.showOp(), s.toString()));


            boolean validSex;
            //Sex by default
            sex = ClientDto.Sex.NONE;

            do {
                System.out.print("\nSelect option [1-3] from the list [Press Enter to ignore]: ");
                answer = sc.nextLine();
                validSex = true;

                if (!answer.trim().isEmpty()){
                    try {
                        if (!answer.matches("^[0-9]+$") || Integer.parseInt(answer) < 1 || Integer.parseInt(answer) > 3)
                            throw new IllegalArgumentException("Invalid answer.");
                        sex = ClientDto.Sex.values()[Integer.parseInt(answer.trim()) - 1];
                    } catch (IllegalArgumentException e){
                        System.out.println("\nERROR: " + e.getMessage());
                        validSex = false;
                    }
                }
            } while (!validSex);

            System.out.printf("%nNHS Number (10 digits): %n");
            nhsNumber = sc.nextLine();
            System.out.printf("%nTax Identification Number (10 digits): %n");
            tin = sc.nextLine();
            System.out.printf("%nPhone Number (11 digits): %n");
            phoneNumber = sc.nextLine();
            System.out.printf("%nEmail: %n");
            email = sc.nextLine();

            ClientDto clientDto = new ClientDto(name, birthDate, sex, ccn,
                    nhsNumber, tin, phoneNumber, email);

            //CLIENT CREATION
            boolean clientCreated = registerClientController.createClient(clientDto);

            //CONFIRMATION OF CLIENT DATA
            if (clientCreated){

                System.out.println();
                registerClientController.showClient();

                int maxAttempts = 3;
                boolean success;

              do{
                    maxAttempts--;
                    success = false;

                    System.out.println("Confirm new client? [y/n]");
                    answer = sc.nextLine();

                    switch (answer){
                        case "y":
                        case "Y":
                            if(registerClientController.addClient()){
                                System.out.println("Successfully added.");
                            } else {
                                System.out.println("Client already exists.");
                            }
                            if (!registerClientController.saveClient())
                            {
                                System.out.println("Error saving to system. All data will be lost.");
                            }
                            success = true;
                            break;
                        case "n":
                        case "N":
                            System.out.println("Client not added.");
                            success = true;
                            break;
                        default:
                            System.out.printf("%nInvalid Answer. %d tries remaining%n", maxAttempts);
                    }
                } while (!success && maxAttempts > 0);
            } else {
                System.out.println("Client could not be created.");
            }

        } catch (IllegalStateException ise){
            System.out.println("\nERROR: " + ise.getMessage());
        }

    }
}
