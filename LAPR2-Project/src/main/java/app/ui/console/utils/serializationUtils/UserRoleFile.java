package app.ui.console.utils.serializationUtils;

import auth.domain.store.UserRoleStore;

import java.io.*;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class UserRoleFile {

    /**
     * File name and path.
     */
    public static final String FILE_NAME = "./assets/database/UserRoleList.mlf";

    /**
     * Method to read serialization binary file.
     *
     * @return Serialized User Role store.
     */
    public UserRoleStore read() {
        return read(FILE_NAME);
    }

    /**
     * Method to read serialization binary file.
     *
     * @param fileName file name.
     * @return Serialized User Role store.
     */
    private UserRoleStore read(String fileName) {
        return read(new File(fileName));
    }

    /**
     * Method to read serialization binary file.
     *
     * @param file file.
     * @return Serialized User Role store.
     */
    private UserRoleStore read(File file)
    {
        UserRoleStore userRoleStore;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file));
            try {
                userRoleStore = (UserRoleStore) in.readObject();
            } finally {
                in.close();
            }
            return userRoleStore;
        } catch (IOException | ClassNotFoundException ex) {
            return new UserRoleStore();
        }
    }

    /**
     * Method to save serialization binary file.
     *
     * @param userRoleStore User Role store.
     * @return True if the User Role store was successfully saved.
     */
    public boolean save(UserRoleStore userRoleStore) {
        return save(FILE_NAME, userRoleStore);
    }

    /**
     * Method to save serialization binary file.
     *
     * @param userRoleList User Role store.
     * @param fileName File name.
     * @return True if the User Role store was successfully saved.
     */
    private boolean save(String fileName, UserRoleStore userRoleList) {
        return save(new File(fileName), userRoleList);
    }

    /**
     * Method to save serialization binary file.
     *
     * @param userRoleList User Role store.
     * @param file Binary File.
     * @return True if the User Role store was successfully saved.
     */
    private boolean save(File file, UserRoleStore userRoleList)
    {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            try {
                out.writeObject(userRoleList);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
