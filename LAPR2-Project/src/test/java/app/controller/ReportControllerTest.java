package app.controller;

import app.domain.model.Company;
import app.domain.model.Report;
import app.domain.store.TestStore;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;

public class ReportControllerTest extends TestCase {
    App app = App.getInstance();
    Company company = app.getCompany();

    ReportController controller=new ReportController();


    String nhsCode="123456789011";
    String diagnosis="oi";

    String nhsCodeInv="1233";
    String diagnosisInv1="";
    String diagnosisInv2=null;


    public void testShowReport() {
        String diagnosisV="oii";

        String diagnosisV1="oii";
        String diagnosisv2="oiiiiiii";

        Report rep= new Report(diagnosisV);
        Report rep1= new Report(diagnosisV1);
        Report rep2=new Report(diagnosisv2);
        assertEquals(rep1.toString().equals(rep.toString()),true);
        assertEquals(rep2.toString().equals(rep.toString()),false);
    }

    public void testUnreportedTestList() {

    }






}