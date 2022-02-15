package app.domain.store;

import net.sourceforge.barbecue.Barcode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luís Araújo
 */
public class SampleStore implements Serializable {
    private final List<Barcode> store;

    /**
     * Constructs an instance of the Sample Store.
     */
    public SampleStore() {
        store = new ArrayList<Barcode>();
    }

    /**
     * Adds a sample to the store.
     *
     * @param s
     * @return true if the sample was validated and added to the store.
     */
    public boolean addSample(Barcode s) {
        if (!validateSample(s)) return false;
        return store.add(s);
    }

    /**
     * Checks if there isn't a sample with the same data already stored.
     *
     * @param s
     * @return true if the store does not contain the store. Otherwise, returns false.
     */
    public boolean validateSample(Barcode s) {
        return !store.contains(s);
    }

}
