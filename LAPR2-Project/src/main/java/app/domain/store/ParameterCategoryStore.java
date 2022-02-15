package app.domain.store;

import app.domain.model.ParameterCategory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParameterCategoryStore implements Serializable {
    private final List<ParameterCategory> store;

    /**
     * Constructs an instance of the Parameter Category Store.
     */
    public ParameterCategoryStore(){
        store = new ArrayList<>();
    }

    /**
     * Creates a Parameter Category, receiving the Name, Description and Code.
     *
     * @param name Parameter Category's name.
     * @param description Parameter Category's description.
     * @param code Parameter Category's code.
     *
     * @return parameter category
     */
    public ParameterCategory createParameterCategory(String name, String description, String code) {

        return new ParameterCategory(name, description, code);
    }

    /**
     * Prints the parameter category's info.
     */
    public void showParameterCategory(ParameterCategory pc){
        System.out.println(pc.toString());
    }

    /**
     * Adds a parameter category to the store.
     *
     * @return true if the parameter category was validated and added to the store.
     */
    public boolean addParameterCategory(ParameterCategory pc){
        if (!validateParameterCategory(pc)) return false;
        return store.add(pc);
    }

    /**
     * Checks if there isn't a parameter category with the same data already stored.
     *
     * @return true if the store does not contain the parameter category. Otherwise, returns false.
     */
    public boolean validateParameterCategory (ParameterCategory pc){
        return !store.contains(pc);
    }

    public ParameterCategory getParameterCategory (int i){
        return new ParameterCategory(store.get(i));
    }

    public void showParameterCategoryList (){
        int i = 1;
        for (ParameterCategory pc : store){
            System.out.println(i + " - " + pc.getName());
            i++;
        }
    }

    public List<String> getParameterCategoryList (){
        List <String> clone = new ArrayList<>(store.size());
        for (ParameterCategory pc : store){
            clone.add(pc.getName());
        }
        return clone;
    }
}
