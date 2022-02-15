package app.domain.model.externalModules;

import app.domain.model.Parameter;
import app.domain.model.ReferenceValue;

/**
 * @author Rui Rocha
 */
public interface ExternalModule {
    ReferenceValue getReferenceValue(Parameter parameter);
}
