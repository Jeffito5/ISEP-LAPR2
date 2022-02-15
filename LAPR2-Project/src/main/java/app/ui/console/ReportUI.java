package app.ui.console;

import app.controller.ReportController;
import app.ui.console.utils.Utils;
import app.mappers.dto.unreportedTestDTO;


import java.util.List;
import java.util.Scanner;

/**
 * @author João Violante
 */
public class ReportUI implements Runnable{
    private final ReportController reportController;


    public ReportUI(){
        reportController=new ReportController();
    }
    

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);


        String nhsCode;
        String diagnosis;
        int listIndex;
        String makeAnotherReport;
        boolean makeAnotherReportBoolean=false;
        do {


            List<unreportedTestDTO> unreportedTests = reportController.unreportedTestList();
            if (unreportedTests.isEmpty()) {
                System.out.println("For the time being, there are no Tests available to make report.");
                return;
            }
            listIndex = Utils.showAndSelectIndex(unreportedTests, "\nSelect one of the tests to write a report");

            if (listIndex == -1) {
                return;
            }


            nhsCode = reportController.getNhsCodeByTestIndex(listIndex);

            System.out.format("TEST PARAMETERS%n%n%s%n",reportController.getTestParameterList(nhsCode));

            System.out.println("Write the report[400 words]:");
            diagnosis = sc.nextLine();

            boolean reportCreated = reportController.createReport(diagnosis);


            if (reportCreated) {
                System.out.println(reportController.showReport());
                System.out.println("Do you wish to save the report?[y/n]");
                String res;

                res = sc.nextLine();
                if (res.equalsIgnoreCase("y")) {
                    reportController.saveReport();
                    System.out.println("Report has been saved");

                } else if (res.equalsIgnoreCase("n")) {
                    System.out.println("Report not added");
                } else {
                    System.out.println("Invalid answer");
                }

            }
            System.out.println("Do you wish to make another report? [y/n]");
            makeAnotherReport=sc.nextLine();

            if(makeAnotherReport.equalsIgnoreCase("y")){
                makeAnotherReportBoolean=true;
            }else if(makeAnotherReport.equalsIgnoreCase("n")){
                makeAnotherReportBoolean=false;
            }

        }while(makeAnotherReportBoolean);
    }

}
