package app.ui.console.utils.serializationUtils;

import app.domain.store.ParameterStore;

import java.io.*;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class ParameterFile {
    /**
     * File name and path.
     */
    public static final String FILE_NAME = "./assets/database/ParameterList.mlf";

    /**
     * Method to read serialization binary file.
     *
     * @return Serialized Parameter store.
     */
    public ParameterStore read() {
        return read(FILE_NAME);
    }

    /**
     * Method to read serialization binary file.
     *
     * @param fileName file name.
     * @return Serialized Parameter store.
     */
    private ParameterStore read(String fileName) {
        return read(new File(fileName));
    }

    /**
     * Method to read serialization binary file.
     *
     * @param file file.
     * @return Serialized Parameter store.
     */
    private ParameterStore read(File file) {
        ParameterStore parameterStore;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file));
            try {
                parameterStore = (ParameterStore) in.readObject();
            } finally {
                in.close();
            }
            return parameterStore;
        } catch (IOException | ClassNotFoundException ex) {
            return new ParameterStore();
        }
    }

    /**
     * Method to save serialization binary file.
     *
     * @param parameterStore Parameter store.
     * @return True if the Parameter store was successfully saved.
     */
    public boolean save(ParameterStore parameterStore) {
        return save(FILE_NAME, parameterStore);
    }

    /**
     * Method to save serialization binary file.
     *
     * @param parameterList Parameter store.
     * @param fileName File name.
     * @return True if the Parameter store was successfully saved.
     */
    private boolean save(String fileName, ParameterStore parameterList) {
        return save(new File(fileName), parameterList);
    }

    /**
     * Method to save serialization binary file.
     *
     * @param parameterList Parameter store.
     * @param file Binary File.
     * @return True if the Parameter store was successfully saved.
     */
    private boolean save(File file, ParameterStore parameterList) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            try {
                out.writeObject(parameterList);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
