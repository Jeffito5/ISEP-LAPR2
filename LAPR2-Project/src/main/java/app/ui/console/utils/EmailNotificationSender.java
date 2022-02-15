package app.ui.console.utils;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Formatter;

public class EmailNotificationSender implements Serializable {

    /**
     * Writes the client's username and password on a text file, simulating an email.
     *
     * @return true if the file was created and the message written;
     */
    public boolean writePasswordToFile (String email, String password){
        FileWriter fileWriter;
        Formatter printToFile;
        boolean success;

        try {
            fileWriter = new FileWriter("emailAndSMSMessages.txt", true);
            printToFile = new Formatter(fileWriter);

            //EMAIL TEXT
            try {
                printToFile.format("----- ACCOUNT DETAILS FOR NEW USER -----%n%n" +
                        "Your new Many Labs account has been created. %n" +
                        "Email: %s %nPassword: %s%n%nThank you for choosing " +
                        "our services,%nMany Labs%n%n", email, password);
                //System.out.println("Email sent.");
                success = true;
            } finally {
                printToFile.close();
                fileWriter.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("\nUnable to send email.\n" + e.getMessage());
            success = false;
        } catch (IOException e) {
            System.out.println("\nCould not create file.\n" + e.getMessage());
            success = false;
        }
        return success;
    }
}
