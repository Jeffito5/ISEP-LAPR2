package app.domain.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author João Violante
 */
public class Report implements Serializable {

    /**
     * Report's diagnostic
     */
    private String diagnostic;

    /**
     * Report's date and time of creation
     */
    private final String dateTime;

    /**
     * Max words that diagnosis is allowed to have
     */
    private static final int REPORT_MAX_WORDS=400;


    /**
     * Constructs an instance of Report, receiving diagnostic
     * @param diagnostic report's diagnostic
     */
    public Report(String diagnostic){

        setDiagnostic(diagnostic);
        this.dateTime=getSystemDateTime();

    }


    /**
     * Returns Report's diagnostic
     * @return report's diagnostic
     */
    public String getReport(){
        return this.diagnostic;
    }

    /**
     * Checks if diagnostic is valid by making sure that he is not null, blank or if doesn't exceed
     * the limit of words, if the diagnostic is valid the method returns true
     * @param diagnostic report's diagnostic
     * @return true if diagnostic is valid, returns false otherwise
     */
    private boolean isDiagnosticValid(String diagnostic) {
        int stringLenght=diagnostic.split(" |\\.|\\,|\\;|\\:").length;
        boolean flag;

            if(stringLenght<=REPORT_MAX_WORDS-1 && stringLenght>0 && !(diagnostic.equals(""))) {
                flag = true;
            }else {
                flag=false;
                throw new IllegalArgumentException("Diagnostic must be between 0 and 400 words");

            }

            return flag;
    }


    /**
     * Method that will get the system date and time, it will format said date and time and will
     * return it
     * @return date and time formatted
     */
    private String getSystemDateTime(){
        String dateTimeFormatted;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dateAndTime = new Date();
        dateTimeFormatted=formatter.format(dateAndTime);

        return dateTimeFormatted;

    }

    /**
     * Shows the information of Report
     * @return information of report
     */
    public String toString(){
        return String.format("Report created in: %s%nDiagnosis:%s",dateTime,diagnostic);
    }

    /**
     * Changes Report's diagnostic if the new diagnostic is valid
     * @param diagnostic new Report's diagnostic
     * @return if diagnostic is valid returns true and changes the old diagnostic to the new
     * diagnostic, return false otherwise
     */
    public boolean setDiagnostic(String diagnostic){
        boolean flag=false;
        if(isDiagnosticValid(diagnostic)){
         this.diagnostic=diagnostic;
         flag=true;
        }
        return flag;
    }
}

