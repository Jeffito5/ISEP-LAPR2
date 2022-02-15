package app.domain.store;

import app.domain.model.Parameter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParameterStore implements Serializable {
    private List<Parameter> store;

    public ParameterStore() {
        store = new ArrayList<>();
    }

    public boolean addParameter(Parameter p) {
        return store.contains(p) ? false : store.add(p);
    }

    public List<Parameter> getParameters() {
        return store;
    }

    public Parameter getParameterWithCode(String code) {
        for (Parameter parameter : store) {
            if (parameter.getCode().equals(code))
                return parameter;
        }
        throw new IllegalArgumentException("Parameter not found.");
    }
}
