package app.domain.store;

import app.domain.model.ParameterCategory;
import app.domain.model.TestType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luís Araújo
 */
public class TestTypeStore implements Serializable {
    private List<TestType> store;

    public TestTypeStore() {
        store = new ArrayList<TestType>();
    }

    public TestType createTestType(String code, String description, String collecting_method, List<ParameterCategory> categories) {
        TestType tt = new TestType(code, description, collecting_method, categories);
        return tt;
    }

    public String showTestType(TestType tt) {
        String ret;
        return ret = tt.toString();
    }

    public boolean addTestType(TestType tt) {
        return store.contains(tt) ? false : store.add(tt);
    }

    public boolean validateTestType(TestType tt) {
        return (tt.isTestTypeValid(tt));
    }

    public void showTestTypeList() {
        int i = 1;
        if (store.isEmpty()) {
            System.out.println("No existing tests.");
        } else {
            for (TestType tt : store) {
                System.out.println(i + " - " + tt.getDescription());
                i++;
            }
        }
    }

    public TestType getTestType(int i) {
        return new TestType(store.get(i));
    }

    /**
     * Returns the Test type that contains the given code.
     *
     * @param testTypeCode Test type's code.
     * @return Test type.
     */
    public TestType getTestType(String testTypeCode) {
        for (TestType testType : store) {
            if (testType.getCode().equals(testTypeCode))
                return testType;
        }
        throw new IllegalArgumentException("Test type not found.");
    }

    public List<String> getTestTypeList() {
        List<String> clone = new ArrayList<>(store.size());
        for (TestType tt : store) {
            clone.add(tt.getDescription());
        }
        return clone;
    }

    public TestType getTestTypeByName(String name) {
        for (TestType testType : store) {
            if (testType.getDescription().equals(name))
                return testType;
        }
        throw new IllegalArgumentException("Test type not found.");
    }

    public List<TestType> getTestTypes() {
        return store;
    }
}






