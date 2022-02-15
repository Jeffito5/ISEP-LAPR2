package app.ui.console.utils;

import app.domain.tasks.SendNhsReportTask;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NHSReportLogEvent {
    /**
     * Logs the date and instance of the Covid-19 report event into a file
     *
     * @param instance instance of Covid-19 NHS report.
     * @param date Date and time of forwarding.
     */
    public static void logEvent(int instance, String date)
    {
        FileWriter fileWriter;
        Formatter printToFile;

        try {
            fileWriter = new FileWriter("./NHSReport/log.txt", true);
            printToFile = new Formatter(fileWriter);

            //EMAIL TEXT
            try {
                printToFile.format("Covid-Report #" + instance + " [" + date + "]\n");
            } finally {
                printToFile.close();
                fileWriter.close();
            }
        } catch (FileNotFoundException e) {
            Logger.getLogger(SendNhsReportTask.class.getName()).log(Level.SEVERE, "Unable to log.", e);

        } catch (IOException e) {
            Logger.getLogger(SendNhsReportTask.class.getName()).log(Level.SEVERE, "Could not create file.", e);
        }

    }
}
