package app.ui.console.utils.serializationUtils;

import app.domain.store.SampleStore;

import java.io.*;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class SampleFile {
    /**
     * File name and path.
     */
    public static final String FILE_NAME = "./assets/database/SampleList.mlf";

    /**
     * Method to read serialization binary file.
     *
     * @return Serialized Sample store.
     */
    public SampleStore read() {
        return read(FILE_NAME);
    }

    /**
     * Method to read serialization binary file.
     *
     * @param fileName file name.
     * @return Serialized Sample store.
     */
    private SampleStore read(String fileName) {
        return read(new File(fileName));
    }

    /**
     * Method to read serialization binary file.
     *
     * @param file file.
     * @return Serialized Sample store.
     */
    private SampleStore read(File file) {
        SampleStore sampleStore;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file));
            try {
                sampleStore = (SampleStore) in.readObject();
            } finally {
                in.close();
            }
            return sampleStore;
        } catch (IOException | ClassNotFoundException ex) {
            return new SampleStore();
        }
    }

    /**
     * Method to save serialization binary file.
     *
     * @param sampleStore Sample store.
     * @return True if the Sample store was successfully saved.
     */
    public boolean save(SampleStore sampleStore) {
        return save(FILE_NAME, sampleStore);
    }

    /**
     * Method to save serialization binary file.
     *
     * @param sampleList Sample store.
     * @param fileName File name.
     * @return True if the Sample store was successfully saved.
     */
    private boolean save(String fileName, SampleStore sampleList) {
        return save(new File(fileName), sampleList);
    }

    /**
     * Method to save serialization binary file.
     *
     * @param sampleList Sample store.
     * @param file Binary File.
     * @return True if the Sample store was successfully saved.
     */
    private boolean save(File file, SampleStore sampleList) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            try {
                out.writeObject(sampleList);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
