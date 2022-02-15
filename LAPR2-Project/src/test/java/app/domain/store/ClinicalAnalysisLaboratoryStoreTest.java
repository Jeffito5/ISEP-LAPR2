package app.domain.store;

import app.domain.model.ClinicalAnalysisLaboratory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Luís Araújo
 */
public class ClinicalAnalysisLaboratoryStoreTest {
    ClinicalAnalysisLaboratoryStore cals;

    @Before
    public void setUp() throws Exception {
        cals = new ClinicalAnalysisLaboratoryStore();
    }

    public List<String> test_types1() {
        List<String> tt1 = new ArrayList<>();
        tt1.add("9875");
        tt1.add("Teste 1");
        tt1.add("Colheita 1");
        return tt1;
    }

    @Test
    public void createClinicalAnalysisLaboratory() {
        ClinicalAnalysisLaboratory result = cals.createClinicalAnalysisLaboratory("Joaquim Chaves", "1234", "1562275619", "8461745288", "Rua de Viameiro", test_types1());
        ClinicalAnalysisLaboratory expectedResult = new ClinicalAnalysisLaboratory("Joaquim Chaves", "1234", "1562275619", "8461745288", "Rua de Viameiro", test_types1());
        assertEquals(expectedResult.getName(), result.getName());
        assertEquals(expectedResult.getLaboratoryId(), result.getLaboratoryId());
        assertEquals(expectedResult.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(expectedResult.getTinNumber(), result.getTinNumber());
        assertEquals(expectedResult.getAddress(), result.getAddress());
        assertEquals(expectedResult.getTestTypes(), result.getTestTypes());
    }

    @Test
    public void addClinicalAnalysisLaboratory() {
        ClinicalAnalysisLaboratory aux = cals.createClinicalAnalysisLaboratory("Joaquim Chaves", "1234", "1562275619", "8461745288", "Rua de Viameiro", test_types1());
        boolean result = cals.addClinicalAnalysisLaboratory(aux);
        assertTrue(result);
    }

    @Test
    public void validateClinicalAnalysisLaboratory() {
        ClinicalAnalysisLaboratory aux = cals.createClinicalAnalysisLaboratory("Joaquim Chaves", "1234", "1562275619", "8461745288", "Rua de Viameiro", test_types1());
        boolean result = cals.validateClinicalAnalysisLaboratory(aux);
        assertTrue(result);
    }
}



