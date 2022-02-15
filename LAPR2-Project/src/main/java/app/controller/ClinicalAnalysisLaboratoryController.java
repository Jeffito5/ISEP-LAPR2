package app.controller;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.Company;
import app.domain.model.TestType;
import app.domain.store.ClinicalAnalysisLaboratoryStore;
import app.domain.store.TestTypeStore;

import java.util.List;


/**
 * @author Luís Araújo
 */
public class ClinicalAnalysisLaboratoryController {
    private final Company company;
    private final ClinicalAnalysisLaboratoryStore store;
    private final TestTypeStore testTypeStore;
    private final App app;
    private ClinicalAnalysisLaboratory cal;

    public ClinicalAnalysisLaboratoryController() {
        // Singleton
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.store = company.getClinicalAnalysisLaboratoryStore();
        this.testTypeStore = company.getTestTypeStore();
    }

    public boolean createClinicalAnalysisLaboratory(String name, String laboratoryId, String phoneNumber, String tinNumber, String address, List<String> testTypes) {
        cal = store.createClinicalAnalysisLaboratory(name, laboratoryId, phoneNumber, tinNumber, address, testTypes);
        if (store.validateClinicalAnalysisLaboratory(cal)){
            return true;
        } else{
            System.out.println("Clinical Analysis Laboratory already exists.");
            return false;
        }
    }

    public void showParameterCategory() {
        store.showClinicalAnalysisLaboratory(cal);
    }

    public boolean addParameterCategory() {
        return store.addClinicalAnalysisLaboratory(cal);
    }

    public void showTestTypeList (){
        testTypeStore.showTestTypeList();
    }

    public TestType getTestType(int i){
        return testTypeStore.getTestType(i);
    }

    public List<String> getTestTypeList(){
        return testTypeStore.getTestTypeList();
    }

    public boolean saveCal()
    {
        return company.saveLabs(store);
    }
}
