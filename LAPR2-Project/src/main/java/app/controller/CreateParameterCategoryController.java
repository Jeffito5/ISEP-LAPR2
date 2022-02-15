package app.controller;


import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.store.ParameterCategoryStore;

public class CreateParameterCategoryController {
    private final Company company;
    private final ParameterCategoryStore store;
    private final App app;
    private ParameterCategory pc;

    /**
     * Constructs an instance of the Controller,
     * using singleton to access the app instance.
     */
    public CreateParameterCategoryController(){
        // Singleton
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.store = company.getParameterCategoryStore();
    }

    /**
     * Creates a Parameter Category, receiving the Name, Description and Code.
     *
     * @param name Parameter Category's name.
     * @param description Parameter Category's description.
     * @param code Parameter Category's code.
     *
     * @return true if the Parameter Category was created. Otherwise, returns false.
     */
    public boolean createParameterCategory(String name, String description, String code){
        try {
            pc = store.createParameterCategory(name, description, code);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("\nERROR: " + e.getMessage());
            return false;
        }
    }

    /**
     * Asks the Parameter Category Store to show the created Parameter Category's info.
     */
    public void showParameterCategory(){
        store.showParameterCategory(pc);
    }

    /**
     * Calls the addParameterCategory method from the store to add the Parameter Category.
     *
     * @return true if the Parameter Category was added.
     */
    public boolean addParameterCategory (){
        return store.addParameterCategory(pc);
    }

    /**
     * Serializes the parameter category.
     *
     * @return true if the Parameter Category was added.
     */
    public boolean saveParameterCategory()
    {
        return company.saveParameterCategories(store);
    }
}
