package app.ui.console.utils;

import app.mappers.dto.ClinicalAnalysisLaboratoryDto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Utils {

    public static String readLineFromConsole(String prompt)
    {
        try
        {
            System.out.println("\n" + prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static int readIntegerFromConsole(String prompt)
    {
        do
        {
            try
            {
                String input = readLineFromConsole(prompt);

                int value = Integer.parseInt(input);

                return value;
            } catch (NumberFormatException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    public static double readDoubleFromConsole(String prompt)
    {
        do
        {
            try
            {
                String input = readLineFromConsole(prompt);

                double value = Double.parseDouble(input);

                return value;
            } catch (NumberFormatException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    public static Date readDateFromConsole(String prompt)
    {
        do
        {
            try
            {
                String strDate = readLineFromConsole(prompt);

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                Date date = df.parse(strDate);

                return date;
            } catch (ParseException ex)
            {
                //Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
                // REESCREVER ESTE MÉTODO
                System.out.println("Erro");
            }
        } while (true);
    }

    public static boolean confirm(String message) {
        String input;
        do {
            input = Utils.readLineFromConsole("\n" + message + "\n");
        } while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));

        return input.equalsIgnoreCase("y");
    }

    public static Object showAndSelectOne(List list, String header)
    {
        showList(list,header);
        return selectsObject(list);
    }
    public static int showAndSelectIndex(List list, String header)
    {
        showList(list,header);
        return selectsIndex(list);
    }
    public static void showList(List list, String header)
    {
        System.out.println(header);

        int index = 0;
        for (Object o : list)
        {
            index++;

            System.out.println(index + ". " + o.toString());
        }
        System.out.println("");
        System.out.println("0 - Cancel");
    }

    public static Object selectsObject(List list)
    {
        String input;
        Integer value;
        do
        {
            input = Utils.readLineFromConsole("Type your option: ");
            value =  Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        if (value == 0)
        {
            return null;
        } else
        {
            return list.get(value - 1);
        }
    }

    public static int selectsIndex(List list)
    {
        String input;
        Integer value = -2;
        do
        {

            input = Utils.readLineFromConsole("Type your option: ");

            try {
                if (!input.matches("^[0-9]+$"))
                    throw new IllegalArgumentException("Wrong input. Please insert option number.");
                value =  Integer.valueOf(input);
            }
            catch (IllegalArgumentException e){
                System.out.println("\nERROR: " + e.getMessage());
            }

            //value =  Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        return value - 1;
    }

    public static Date getDateWithoutTime(Date date){
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date dateWithoutTime = new Date();
        try{
            dateWithoutTime = df.parse(df.format(date));
        } catch (ParseException e) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, e);
        }
        return dateWithoutTime;
    }

    public static double[] listToArray(List<Double> list){
        double[] array = new double[list.size()];

        for (int i = 0; i < list.size(); i++){
            array[i] = list.get(i);
        }

        return array;
    }

    public static void setTimeToZero(Calendar cal)
    {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
    }

    public static List<String>  calDtoListToStringList(List<ClinicalAnalysisLaboratoryDto> clinicalAnalysisLaboratories){
        List<String> calDto = new ArrayList<>();
        for (ClinicalAnalysisLaboratoryDto dto : clinicalAnalysisLaboratories){
            String name = dto.toString();
            calDto.add(name);
        }
        return calDto;
    }
}
