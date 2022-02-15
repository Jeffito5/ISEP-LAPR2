package app.domain.model.externalModules.adapters;

import app.domain.model.Parameter;
import app.domain.model.ReferenceValue;
import app.domain.model.externalModules.ExternalModule;
import com.example1.ExternalModule3API;

/**
 * @author Rui Rocha
 */
public class ExternalModuleAdapter3 implements ExternalModule {
    private final ExternalModule3API externalModule3API = new ExternalModule3API();

    @Override
    public ReferenceValue getReferenceValue(Parameter parameter) {
        String parameterId = parameter.getCode();

        int accessKey = 12345;
        double minimumValue = externalModule3API.getMinReferenceValue(parameterId, accessKey);
        double maximumValue = externalModule3API.getMaxReferenceValue(parameterId, accessKey);
        String metric = externalModule3API.usedMetric(parameterId, accessKey);

        return new ReferenceValue(minimumValue, maximumValue, metric);
    }
}
