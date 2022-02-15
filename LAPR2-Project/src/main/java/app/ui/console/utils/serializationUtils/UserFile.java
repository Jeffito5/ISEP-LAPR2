package app.ui.console.utils.serializationUtils;

import auth.domain.store.UserStore;

import java.io.*;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class UserFile {

    /**
     * File name and path.
     */
    public static final String FILE_NAME = "./assets/database/UserList.mlf";

    /**
     * Method to read serialization binary file.
     *
     * @return Serialized User store.
     */
    public UserStore read() {
        return read(FILE_NAME);
    }

    /**
     * Method to read serialization binary file.
     *
     * @param fileName file name.
     * @return Serialized User store.
     */
    private UserStore read(String fileName) {
        return read(new File(fileName));
    }

    /**
     * Method to read serialization binary file.
     *
     * @param file file.
     * @return Serialized User store.
     */
    private UserStore read(File file) {
        UserStore userStore;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file));
            try {
                userStore = (UserStore) in.readObject();
            } finally {
                in.close();
            }
            return userStore;
        } catch (IOException | ClassNotFoundException ex) {
            return new UserStore();
        }
    }

    /**
     * Method to save serialization binary file.
     *
     * @param userStore User store.
     * @return True if the User store was successfully saved.
     */
    public boolean save(UserStore userStore) {
        return save(FILE_NAME, userStore);
    }

    /**
     * Method to save serialization binary file.
     *
     * @param userList User store.
     * @param fileName File name.
     * @return True if the User store was successfully saved.
     */
    private boolean save(String fileName, UserStore userList) {
        return save(new File(fileName), userList);
    }

    /**
     * Method to save serialization binary file.
     *
     * @param userList User store.
     * @param file Binary File.
     * @return True if the User store was successfully saved.
     */
    private boolean save(File file, UserStore userList) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            try {
                out.writeObject(userList);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
