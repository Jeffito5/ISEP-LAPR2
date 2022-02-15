package app.domain.store;

import app.domain.model.ParameterCategory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class ParameterCategoryStoreTest {
    private ParameterCategoryStore parameterCategoryStore;
    private ParameterCategory pc1, pc2, pc3;

    @Before
    public void setUp() {
        parameterCategoryStore = new ParameterCategoryStore();

        pc1 = new ParameterCategory("Hemogram", "Blood tests", "12345");
        pc2 = new ParameterCategory("Hemogram", "Blood testing", "987654");
        pc3 = new ParameterCategory("Thyroid", "Thyroid tests", "12345");

        parameterCategoryStore.addParameterCategory(pc1);
    }

    @Test
    public void addParameterCategorySameName() {
        boolean result = parameterCategoryStore.addParameterCategory(pc2);
        assertFalse(result);
    }

    @Test
    public void addParameterCategorySameCode() {
        boolean result = parameterCategoryStore.addParameterCategory(pc3);
        assertFalse(result);
    }

    @Test
    public void getParameterCategory() {
        boolean result1 = pc1.equals(parameterCategoryStore.getParameterCategory(0));
        boolean result2 = (pc1 == parameterCategoryStore.getParameterCategory(0));

        assertTrue(result1);
        assertFalse(result2);
    }
}