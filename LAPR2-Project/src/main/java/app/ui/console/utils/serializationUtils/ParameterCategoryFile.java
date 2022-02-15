package app.ui.console.utils.serializationUtils;

import app.domain.store.ParameterCategoryStore;

import java.io.*;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class ParameterCategoryFile {
    /**
     * File name and path.
     */
    public static final String FILE_NAME = "./assets/database/ParameterCategoryList.mlf";

    /**
     * Method to read serialization binary file.
     *
     * @return Serialized Parameter Category store.
     */
    public ParameterCategoryStore read() {
        return read(FILE_NAME);
    }

    /**
     * Method to read serialization binary file.
     *
     * @param fileName file name.
     * @return Serialized Parameter Category store.
     */
    private ParameterCategoryStore read(String fileName) {
        return read(new File(fileName));
    }

    /**
     * Method to read serialization binary file.
     *
     * @param file file.
     * @return Serialized Parameter Category store.
     */
    private ParameterCategoryStore read(File file) {
        ParameterCategoryStore parameterCategoryStore;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file));
            try {
                parameterCategoryStore = (ParameterCategoryStore) in.readObject();
            } finally {
                in.close();
            }
            return parameterCategoryStore;
        } catch (IOException | ClassNotFoundException ex) {
            return new ParameterCategoryStore();
        }
    }

    /**
     * Method to save serialization binary file.
     *
     * @param parameterCategoryStore Parameter Category store.
     * @return True if the Parameter Category store was successfully saved.
     */
    public boolean save(ParameterCategoryStore parameterCategoryStore) {
        return save(FILE_NAME, parameterCategoryStore);
    }

    /**
     * Method to save serialization binary file.
     *
     * @param parameterCategoryList Parameter Category store.
     * @param fileName File name.
     * @return True if the Parameter Category store was successfully saved.
     */
    private boolean save(String fileName, ParameterCategoryStore parameterCategoryList) {
        return save(new File(fileName), parameterCategoryList);
    }

    /**
     * Method to save serialization binary file.
     *
     * @param parameterCategoryList Parameter Category store.
     * @param file Binary File.
     * @return True if the Parameter Category store was successfully saved.
     */
    private boolean save(File file, ParameterCategoryStore parameterCategoryList) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            try {
                out.writeObject(parameterCategoryList);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
