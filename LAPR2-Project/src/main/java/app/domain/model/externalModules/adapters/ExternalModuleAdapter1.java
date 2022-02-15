package app.domain.model.externalModules.adapters;

import app.domain.model.Parameter;
import app.domain.model.ReferenceValue;
import app.domain.model.externalModules.ExternalModule;
import com.example3.CovidReferenceValues1API;

/**
 * @author Rui Rocha
 */
public class ExternalModuleAdapter1 implements ExternalModule {
    private final CovidReferenceValues1API covidReferenceValues1API = new CovidReferenceValues1API();

    @Override
    public ReferenceValue getReferenceValue(Parameter parameter) {
        String parameterId = parameter.getCode();

        int accessKey = 12345;
        double minimumValue = covidReferenceValues1API.getMinReferenceValue(parameterId, accessKey);
        double maximumValue = covidReferenceValues1API.getMaxReferenceValue(parameterId, accessKey);
        String metric = covidReferenceValues1API.usedMetric(parameterId, accessKey);

        return new ReferenceValue(minimumValue, maximumValue, metric);
    }
}
