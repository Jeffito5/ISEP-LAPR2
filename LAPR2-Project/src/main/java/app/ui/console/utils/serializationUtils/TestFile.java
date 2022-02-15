package app.ui.console.utils.serializationUtils;

import app.domain.store.TestStore;

import java.io.*;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class TestFile {
    /**
     * File name and path.
     */
    public static final String FILE_NAME = "./assets/database/TestList.mlf";

    /**
     * Method to read serialization binary file.
     *
     * @return Serialized Test store.
     */
    public TestStore read() {
        return read(FILE_NAME);
    }

    /**
     * Method to read serialization binary file.
     *
     * @param fileName file name.
     * @return Serialized Test store.
     */
    private TestStore read(String fileName) {
        return read(new File(fileName));
    }

    /**
     * Method to read serialization binary file.
     *
     * @param file file.
     * @return Serialized Test store.
     */
    private TestStore read(File file) {
        TestStore testStore;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file));
            try {
                testStore = (TestStore) in.readObject();
            } finally {
                in.close();
            }
            return testStore;
        } catch (IOException | ClassNotFoundException ex) {
            return new TestStore();
        }
    }

    /**
     * Method to save serialization binary file.
     *
     * @param testStore Test store.
     * @return True if the Test store was successfully saved.
     */
    public boolean save(TestStore testStore) {
        return save(FILE_NAME, testStore);
    }

    /**
     * Method to save serialization binary file.
     *
     * @param testList Test store.
     * @param fileName File name.
     * @return True if the Test store was successfully saved.
     */
    private boolean save(String fileName, TestStore testList) {
        return save(new File(fileName), testList);
    }

    /**
     * Method to save serialization binary file.
     *
     * @param testList Test store.
     * @param file Binary File.
     * @return True if the Test store was successfully saved.
     */
    private boolean save(File file, TestStore testList) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            try {
                out.writeObject(testList);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
