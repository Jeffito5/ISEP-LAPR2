package app.controller;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import app.domain.store.ParameterCategoryStore;
import app.domain.store.TestTypeStore;
import java.util.List;

/**
 * @author Luís Araújo
 */
public class TestTypeController {
    private Company company;
    private TestTypeStore store;
    private App app;
    private TestType tt;
    private ParameterCategoryStore pcs;

    public TestTypeController() {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.store = company.getTestTypeStore();
        this.pcs = company.getParameterCategoryStore();
    }

    public boolean createTestType(String code, String description, String collecting_method, List<ParameterCategory> categories) {
        tt = store.createTestType(code, description, collecting_method, categories);
        return store.validateTestType(tt);
    }

    public String showTestType() {
        return store.showTestType(tt);
    }

    public boolean addTestType() {
        return store.addTestType(tt);
    }

    public ParameterCategory getParameterCategory(int i){
        return pcs.getParameterCategory(i);
    }

    public List<String> getParameterCategoryList(){
        return pcs.getParameterCategoryList();
    }

    public boolean saveTestType()
    {
        return company.saveTestTypes(store);
    }
}


