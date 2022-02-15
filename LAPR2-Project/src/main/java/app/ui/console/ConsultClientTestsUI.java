package app.ui.console;

import app.controller.ConsultClientTestsController;
import app.mappers.dto.ClientDto;
import app.mappers.dto.TestDto;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsultClientTestsUI implements Runnable{

    /**
     * Instance of ConsultClientTestsController.
     */
    private final ConsultClientTestsController consultClientTestsController;

    public ConsultClientTestsUI(){
        this.consultClientTestsController = new ConsultClientTestsController();
    }
    @Override
    public void run() {
        List<ClientDto> clientList = getClientListBySortingParameter();
        List<String> clientNamesList = new ArrayList<>();
        ClientDto clientDto;

        for(ClientDto client : clientList)
        {
            clientNamesList.add(client.getName());
        }

        do {
            int option = Utils.showAndSelectIndex(clientNamesList, "\nSelect Client: ");

            if (option < 0)
                return;

            clientDto = clientList.get(option);
            System.out.println(clientDto.toString());

        } while (!Utils.confirm("Confirm Client? [y/n]"));


        try
        {
            showClientTests(clientDto);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public List<ClientDto> getClientListBySortingParameter(){
        boolean success;
        List<String> options = new ArrayList<>();
        List<ClientDto> clientDtos = new ArrayList<>();

        // Set of parameters to sort the clients.
        options.add("Sort Clients By Name.");
        options.add("Sort Clients By Tax Identification Number.");

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nSelect Sorting Parameter:");

            switch (option) {
                case -1:
                    success = true;
                    break;
                case 0:
                    try{
                        clientDtos = consultClientTestsController.getClientListSortedByName();
                    } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    success = true;
                    break;
                case 1:
                    try {
                        clientDtos = consultClientTestsController.getClientListSortedByTin();
                    } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e){
                        e.printStackTrace();
                    }
                    success = true;
                    break;
                default:
                    success = false;
                    break;
            }
        }
        while (!success);

        return clientDtos;
    }

    public void showClientTests(ClientDto clientDto)
    {
        boolean success;
        boolean validAnswer;
        Scanner sc = new Scanner(System.in);
        String answer;

        String clientTin = clientDto.getTin();
        List<TestDto> testList = consultClientTestsController.getClientTests(clientTin);

        if (testList.isEmpty())
        {
            throw new IllegalArgumentException("\nClient does not have validated Tests.");
        }

        do{
            success = true;
            int testIndex = Utils.showAndSelectIndex(testList, "\nSelect Test: ");

            if (testIndex < 0)
            {
                return;
            }

            testMenu(testList.get(testIndex).getNhsCode());

            do {
                validAnswer = true;

                System.out.println("\nSelect other test? [y/n]");

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
                        System.out.println("Invalid answer.");
                        validAnswer = false;
                        break;
                }
            } while (!validAnswer);

        } while (!success);
    }

    public void testMenu(String testNHSCode) {

        boolean success;
        List<String> options = new ArrayList<>();

        options.add("Test Info.");
        options.add("Test Results.");

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\nSelect Option: ");

            switch (option) {
                case -1:
                    return;
                case 0:
                    System.out.println(consultClientTestsController.getTestInfo(testNHSCode));
                    success = true;
                    break;
                case 1:
                    System.out.println(consultClientTestsController.getTestResults(testNHSCode));
                    success = true;
                    break;
                default:
                    success = false;
                    break;
            }
        } while (!success);
    }
}
