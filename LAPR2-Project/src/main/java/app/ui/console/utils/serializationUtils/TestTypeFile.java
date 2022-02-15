package app.ui.console.utils.serializationUtils;

import app.domain.store.TestTypeStore;

import java.io.*;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class TestTypeFile {
    /**
     * File name and path.
     */
    public static final String FILE_NAME = "./assets/database/TestTypeList.mlf";

    /**
     * Method to read serialization binary file.
     *
     * @return Serialized Test Type store.
     */
    public TestTypeStore read() {
        return read(FILE_NAME);
    }

    /**
     * Method to read serialization binary file.
     *
     * @param fileName file name.
     * @return Serialized Test Type store.
     */
    private TestTypeStore read(String fileName) {
        return read(new File(fileName));
    }

    /**
     * Method to read serialization binary file.
     *
     * @param file file.
     * @return Serialized Test Type store.
     */
    private TestTypeStore read(File file) {
        TestTypeStore testTypeStore;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file));
            try {
                testTypeStore = (TestTypeStore) in.readObject();
            } finally {
                in.close();
            }
            return testTypeStore;
        } catch (IOException | ClassNotFoundException ex) {
            return new TestTypeStore();
        }
    }

    /**
     * Method to save serialization binary file.
     *
     * @param testTypeStore Test Type store.
     * @return True if the Test Type store was successfully saved.
     */
    public boolean save(TestTypeStore testTypeStore) {
        return save(FILE_NAME, testTypeStore);
    }

    /**
     * Method to save serialization binary file.
     *
     * @param testTypeList Test Type store.
     * @param fileName File name.
     * @return True if the Test Type store was successfully saved.
     */
    private boolean save(String fileName, TestTypeStore testTypeList) {
        return save(new File(fileName), testTypeList);
    }

    /**
     * Method to save serialization binary file.
     *
     * @param testTypeList Test Type store.
     * @param file Binary File.
     * @return True if the Test Type store was successfully saved.
     */
    private boolean save(File file, TestTypeStore testTypeList) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            try {
                out.writeObject(testTypeList);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
