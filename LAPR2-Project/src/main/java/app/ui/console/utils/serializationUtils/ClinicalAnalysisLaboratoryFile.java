package app.ui.console.utils.serializationUtils;

import app.domain.store.ClinicalAnalysisLaboratoryStore;

import java.io.*;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class ClinicalAnalysisLaboratoryFile {
    /**
     * File name and path.
     */
    public static final String FILE_NAME = "./assets/database/CalList.mlf";

    /**
     * Method to read serialization binary file.
     *
     * @return Serialized Clinical Analysis Laboratory store.
     */
    public ClinicalAnalysisLaboratoryStore read() {
        return read(FILE_NAME);
    }

    /**
     * Method to read serialization binary file.
     *
     * @param fileName file name.
     * @return Serialized Clinical Analysis Laboratory store.
     */
    private ClinicalAnalysisLaboratoryStore read(String fileName) {
        return read(new File(fileName));
    }

    /**
     * Method to read serialization binary file.
     *
     * @param file file.
     * @return Serialized Clinical Analysis Laboratory store.
     */
    private ClinicalAnalysisLaboratoryStore read(File file)
    {
        ClinicalAnalysisLaboratoryStore calStore;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file));
            try {
                calStore = (ClinicalAnalysisLaboratoryStore) in.readObject();
            } finally {
                in.close();
            }
            return calStore;
        } catch (IOException | ClassNotFoundException ex)
        {
            return new ClinicalAnalysisLaboratoryStore();
        }
    }

    /**
     * Method to save serialization binary file.
     *
     * @param calStore Clinical Analysis Laboratory store.
     * @return True if the clinical analysis laboratory store
     * was successfully saved.
     */
    public boolean save(ClinicalAnalysisLaboratoryStore calStore) {
        return save(FILE_NAME, calStore);
    }

    /**
     * Method to save serialization binary file.
     *
     * @param calList Clinical Analysis Laboratory store.
     * @param fileName File name.
     * @return True if the clinical analysis laboratory store
     * was successfully saved.
     */
    private boolean save(String fileName, ClinicalAnalysisLaboratoryStore calList) {
        return save(new File(fileName), calList);
    }

    /**
     * Method to save serialization binary file.
     *
     * @param calList clinical analysis laboratory store.
     * @param file Binary File.
     * @return True if the clinical analysis laboratory store was successfully saved.
     */
    private boolean save(File file, ClinicalAnalysisLaboratoryStore calList)
    {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            try {
                out.writeObject(calList);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex)
        {
            return false;
        }
    }
}
