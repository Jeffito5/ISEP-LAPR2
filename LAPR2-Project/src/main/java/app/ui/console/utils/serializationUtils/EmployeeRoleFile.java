package app.ui.console.utils.serializationUtils;

import auth.domain.store.EmployeeRoleStore;

import java.io.*;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class EmployeeRoleFile {
    /**
     * File name and path.
     */
    public static final String FILE_NAME = "./assets/database/EmployeeRoleList.mlf";

    /**
     * Method to read serialization binary file.
     *
     * @return Serialized Employee Role store.
     */
    public EmployeeRoleStore read() {
        return read(FILE_NAME);
    }

    /**
     * Method to read serialization binary file.
     *
     * @param fileName file name.
     * @return Serialized Employee role store.
     */
    private EmployeeRoleStore read(String fileName) {
        return read(new File(fileName));
    }

    /**
     * Method to read serialization binary file.
     *
     * @param file file.
     * @return Serialized Employee store.
     */
    private EmployeeRoleStore read(File file)
    {
        EmployeeRoleStore employeeRoleStore;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file));
            try {
                employeeRoleStore = (EmployeeRoleStore) in.readObject();
            } finally {
                in.close();
            }
            return employeeRoleStore;
        } catch (IOException | ClassNotFoundException ex) {
            return new EmployeeRoleStore();
        }
    }

    /**
     * Method to save serialization binary file.
     *
     * @param employeeRoleStore Employee Role store.
     * @return True if the Employee Role store was successfully saved.
     */
    public boolean save(EmployeeRoleStore employeeRoleStore) {
        return save(FILE_NAME, employeeRoleStore);
    }

    /**
     * Method to save serialization binary file.
     *
     * @param employeeRoleList Employee Role store.
     * @param fileName File name.
     * @return True if the Employee role store was successfully saved.
     */
    private boolean save(String fileName, EmployeeRoleStore employeeRoleList) {
        return save(new File(fileName), employeeRoleList);
    }

    /**
     * Method to save serialization binary file.
     *
     * @param employeeRoleList Employee store.
     * @param file Binary File.
     * @return True if the Employee role store was successfully saved.
     */
    private boolean save(File file, EmployeeRoleStore employeeRoleList) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            try {
                out.writeObject(employeeRoleList);
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
