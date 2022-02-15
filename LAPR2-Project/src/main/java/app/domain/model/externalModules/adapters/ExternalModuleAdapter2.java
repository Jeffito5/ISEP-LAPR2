package app.domain.model.externalModules.adapters;

import app.domain.model.Parameter;
import app.domain.model.ReferenceValue;
import app.domain.model.externalModules.ExternalModule;
import com.example2.*;

/**
 * @author Rui Rocha
 */
public class ExternalModuleAdapter2 implements ExternalModule {
    private final ExternalModule2API externalModule2API = new ExternalModule2API();

    @Override
    public ReferenceValue getReferenceValue(Parameter parameter) {
        String parameterId = parameter.getCode();

        EMRefValue emRefValue = externalModule2API.getReferenceFor(parameterId);
        double minimumValue = emRefValue.getMinValue();
        double maximumValue = emRefValue.getMaxValue();
        String metric = emRefValue.getMetric();

        return new ReferenceValue(minimumValue, maximumValue, metric);
    }
}
