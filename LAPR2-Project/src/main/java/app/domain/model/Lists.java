package app.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luís Araújo
 */
public class Lists implements Serializable {
    List<Test> tests;

    public Lists() {
        this.tests = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Lists{");
        for (Test t : tests) {
            result.append("\n").append(t).append("; ");
        }
        result.append("\n}");
        return result.toString();
    }

    public void addTests(ClinicalAnalysisLaboratory clinicalAnalysisLaboratory, Client client, String nhsCode,
                         String description, TestType testType, List<Parameter> parameterList) {
        tests.add(new Test(clinicalAnalysisLaboratory, client, nhsCode, description, testType, parameterList));
    }
}
