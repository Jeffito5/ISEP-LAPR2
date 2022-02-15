package app.ui.console;

import app.controller.CreateEmployeeController;
import app.domain.model.Employee;


import java.util.Scanner;

public class CreateEmployeeUI implements Runnable{
    private final CreateEmployeeController createEmpController;





    public CreateEmployeeUI(){

        /**
         * Creats an instance of CreateEmployeeController
         */
        createEmpController=new CreateEmployeeController();

    }

    public void run(){
        Scanner sc = new Scanner(System.in);


        String email;

        String name;
        String address;
        String phoneNumber;
        String soc;
        String organizationRole;
        String din="";


        createEmpController.showRolesList();
        System.out.println("Organization Role: ");
        organizationRole=sc.nextLine();



        System.out.println("E-mail: ");
        email=sc.nextLine();

        System.out.println("Name: ");
        name=sc.nextLine();

        System.out.println("Address: ");
        address=sc.nextLine();
        System.out.println("Phone Number: ");
        phoneNumber=sc.nextLine();
        System.out.println("SOC: ");
        soc=sc.nextLine();
        if(organizationRole.equalsIgnoreCase("SpecialistDoctor")){
            System.out.println("DIN");
            din=sc.nextLine();
        }
        Employee e1=createEmpController.createEmployee(email, name, address,
                phoneNumber,soc,organizationRole,din);

        







        if(createEmpController.isEmployeeValid() && createEmpController.RoleExist()){
            System.out.println(e1.toString());

            System.out.println("Do you wish to save the employee as a user? [y/n]");
            String res;
            res=sc.next();
            if(res.equalsIgnoreCase("y")){
                createEmpController.save();
                System.out.println("User saved");

                if(!createEmpController.saveEmployees())
                {
                    System.out.println("Error saving to system. All data will be lost.");
                }

            }else if(res.equalsIgnoreCase("n")){
                System.out.println("User not Saved");
            }else
                System.out.println("Invalid Answer");
        }else{
            System.out.println("You have at least 1 fiel that is invalid");
        }

















    }








}
