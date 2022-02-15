package app.ui.console.utils.serializationUtils;

import app.domain.store.EmployeeStore;

import java.io.*;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class EmployeeFile {
    /**
     * File name and path.
     */
    public static final String FILE_NAME = "./assets/database/EmployeeList.mlf";

    /**
     * Method to read serialization binary file.
     *
     * @return Serialized Employee store.
     */
    public EmployeeStore read() {
        return read(FILE_NAME);
    }

    /**
     * Method to read serialization binary file.
     *
     * @param fileName file name.
     * @return Serialized Employee store.
     */
    private EmployeeStore read(String fileName) {
        return read(new File(fileName));
    }

    /**
     * Method to read serialization binary file.
     *
     * @param file file.
     * @return Serialized Employee store.
     */
    private EmployeeStore read(File file)
    {
        EmployeeStore employeeStore;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file));
            try {
                employeeStore = (EmployeeStore) in.readObject();
            } finally {
                in.close();
            }
            return employeeStore;
        } catch (IOException | ClassNotFoundException ex)
        {
            return new EmployeeStore();
        }
    }

    /**
     * Method to save serialization binary file.
     *
     * @param employeeStore Employee store.
     * @return True if the Employee store was successfully saved.
     */
    public boolean save(EmployeeStore employeeStore) {
        return save(FILE_NAME, employeeStore);
    }

    /**
     * Method to save serialization binary file.
     *
     * @param employeeList Employee store.
     * @param fileName File name.
     * @return True if the Employee store was successfully saved.
     */
    private boolean save(String fileName, EmployeeStore employeeList) {
        return save(new File(fileName), employeeList);
    }

    /**
     * Method to save serialization binary file.
     *
     * @param employeeList Employee store.
     * @param file Binary File.
     * @return True if the Employee store was successfully saved.
     */
    private boolean save(File file, EmployeeStore employeeList)
    {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            try {
                out.writeObject(employeeList);
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
