package app.ui.console;

import app.controller.ViewResultsController;
import app.mappers.dto.ClientResultsTestDTO;
import app.ui.console.utils.Utils;

import java.util.List;
import java.util.Scanner;

public class ViewResultsUI implements Runnable{

    ViewResultsController viewResultsController;
    public ViewResultsUI(){viewResultsController=new ViewResultsController();}



    @Override
    public void run() {
        int listIndex;
        int res;
        Scanner sc=new Scanner(System.in);
        String nhsCode;


        List<ClientResultsTestDTO> clientTestList= viewResultsController.showClientTests();

        if (clientTestList.isEmpty()) {
            System.out.println("For the time being, there are no Tests available to view results");
            return;
        }

        listIndex= Utils.showAndSelectIndex(viewResultsController.getTestListCode(),"Select one of the Tests to view the results:");

        if(listIndex==-1){
            return;
        }

        nhsCode=clientTestList.get(listIndex).getNhsCode();
        System.out.println(viewResultsController.showTestResults(nhsCode));

        //System.out.println("Press 1 to return");
        //res=sc.nextInt();

        //if(res==1){
            return;
        //}


    }
}
