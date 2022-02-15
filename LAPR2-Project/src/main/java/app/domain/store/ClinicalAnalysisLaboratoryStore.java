package app.domain.store;

import app.domain.model.ClinicalAnalysisLaboratory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luís Araújo
 */
public class ClinicalAnalysisLaboratoryStore implements Serializable {
    private final List<ClinicalAnalysisLaboratory> store;

    public ClinicalAnalysisLaboratoryStore() {
        store = new ArrayList<>();
    }

    public ClinicalAnalysisLaboratory createClinicalAnalysisLaboratory(String name, String laboratoryId, String phoneNumber,
                                                                       String tinNumber, String address, List<String> testTypes) {

        return new ClinicalAnalysisLaboratory(name, laboratoryId, phoneNumber, tinNumber, address, testTypes);
    }

    public void showClinicalAnalysisLaboratory(ClinicalAnalysisLaboratory cal) {
        System.out.println(cal.toString());
    }

    public boolean addClinicalAnalysisLaboratory(ClinicalAnalysisLaboratory cal) {
        if (!validateClinicalAnalysisLaboratory(cal)) return false;
        return store.add(cal);
    }

    public boolean validateClinicalAnalysisLaboratory(ClinicalAnalysisLaboratory cal) {
        return !store.contains(cal);
    }

    public List<ClinicalAnalysisLaboratory> getClinicalAnalysisLaboratories(){
        return store;
    }

    public ClinicalAnalysisLaboratory getClinicalAnalysisLaboratory(String clinicalAnalysisLaboratoryId){
        for (ClinicalAnalysisLaboratory clinicalAnalysisLaboratory : store){
            if (clinicalAnalysisLaboratory.getLaboratoryId().equals(clinicalAnalysisLaboratoryId))
                return clinicalAnalysisLaboratory;
        }
        throw new IllegalArgumentException ("Clinical Analysis Laboratory not found.");
    }
}



