package app.domain.store;

import app.domain.model.Parameter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author
 */
public class ParameterStoreTest {
    private ParameterStore parameterStore;
    private Parameter parameter6;

    @Before
    public void setUp() throws Exception {
        parameterStore = new ParameterStore();
        parameter6 = new Parameter("MCV", "MCV00", "Mean Cell Volume");
    }

    @Test
    public void addParameter() {
        boolean result = parameterStore.addParameter(parameter6);
        assertTrue(result);
    }

    @Test
    public void getParameters() {
        List<Parameter> list = new ArrayList<>();
        list = parameterStore.getParameters();
        assertNotNull(list);
    }

    @Test
    public void getParameterWithCode() {
        Parameter parameter = new Parameter("MCV", "MCV00", "Mean Cell Volume");
        parameterStore.addParameter(parameter);
        Parameter parameter2 = parameterStore.getParameterWithCode("MCV00");
        assertNotNull(parameter);
    }
}