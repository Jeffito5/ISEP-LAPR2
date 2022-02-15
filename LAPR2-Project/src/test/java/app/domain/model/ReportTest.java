package app.domain.model;

import app.controller.App;
import app.controller.ReportController;
import app.domain.store.TestStore;
import junit.framework.TestCase;
import org.junit.Test;

public class ReportTest extends TestCase {

    App app=App.getInstance();
    Company company = app.getCompany();
    TestStore store= company.getTestStore();
    ReportController controller=new ReportController();






    private String nhsCode1="123456789011";
    private String nhsCode2="123456789012";
    private String nhsCode3="123456789013";
    private String nhsCode4="123456789014";
    private String nhsCode5="123456789015";
    private String nhsCodeInv="1234";


    private String diagnosticV = "DKane";
    private String diagnosticV1 = "DKane";
    private String diagnosticV2 = "Sup yall";
    private String diagnosticF1 = "";
    private String diagnosticF2 = null;


    private Report rep1 = new Report(diagnosticV);
    private Report rep2 = new Report(diagnosticV1);
    private Report rep3 = new Report(diagnosticV2);


    public void testGetReport() {
        String expectedReturn = "DKane";
        assertEquals(rep1.getReport(), expectedReturn);
        assertEquals(rep1.getReport().equals(rep3.getReport()), false);

    }

    public void testTestToString() {
        assertEquals(rep1.toString().equals(rep2.toString()), true);
        assertEquals(rep1.toString().equals(rep3.toString()), false);
    }

    public void testSetDiagnostic() {
        assertEquals(rep1.setDiagnostic(diagnosticV), true);
        assertEquals(rep1.setDiagnostic(diagnosticV2), true);


    }




    @Test(expected = IllegalArgumentException.class)
    public void setDiagnosticReturnsException2(){
        Report rep12= new Report(diagnosticF2);

    }
}