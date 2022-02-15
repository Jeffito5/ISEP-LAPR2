package app.ui.console;


import app.controller.PerformanceController;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PerformanceUI implements Runnable{
    private final PerformanceController performanceController;

    public PerformanceUI(){performanceController=new PerformanceController();}

    @Override
    public void run() {
        List<String> algorithms=new ArrayList<>();
        algorithms.add("Benchmark");
        algorithms.add("Brute Force");
        int index;
        Scanner sc=new Scanner(System.in);
        String dateBeginning;
        String dateEnd;



                System.out.println("Type the interval of time to analyse:");
        System.out.println("Beginning date[dd/mm/yyyy]:");
        dateBeginning=sc.nextLine();
        System.out.println("End date[dd/mm/yyyy]:");
        dateEnd=sc.nextLine();



        index=Utils.showAndSelectIndex(algorithms,"Select one of the algorithms to analyze the data:");

        performanceController.getData(dateBeginning,dateEnd,index);

        System.out.println(performanceController.showData());


    }
}
