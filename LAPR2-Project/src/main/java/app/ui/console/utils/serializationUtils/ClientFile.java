package app.ui.console.utils.serializationUtils;

import app.ui.console.utils.exceptions.ClientStoreException;
import app.domain.store.ClientStore;

import java.io.*;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class ClientFile {

    /**
     * File name and path.
     */
    public static final String FILE_NAME = "./assets/database/ClientList.mlf";


    /**
     * Method to read serialization binary file.
     *
     * @return Serialized client store.
     */
    public ClientStore read() throws ClientStoreException {
        return read(FILE_NAME);
    }

    /**
     * Method to read serialization binary file.
     *
     * @param fileName file name.
     *
     * @return Serialized client store.
     * @throws ClientStoreException if Client Store was not found.
     */
    private ClientStore read(String fileName) throws ClientStoreException {
        return read(new File(fileName));
    }

    /**
     * Method to read serialization binary file.
     *
     * @param file binary file.
     * @return Serialized client store.
     * @throws ClientStoreException if Client Store was not found.
     */
    private ClientStore read(File file) throws ClientStoreException {
        ClientStore clientStore;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file));
            try {
                clientStore = (ClientStore) in.readObject();
            } finally {
                in.close();
            }
            return clientStore;
        } catch (IOException | ClassNotFoundException ex) {
            throw new ClientStoreException();
        }
    }

    /**
     * Method to save serialization binary file.
     *
     * @param clientStore client store.
     * @return True if the client store was successfully saved.
     */
    public boolean save(ClientStore clientStore) {
        return save(FILE_NAME, clientStore);
    }

    /**
     * Method to save serialization binary file.
     *
     * @param clientList client store.
     * @param fileName File name.
     * @return True if the client store was successfully saved.
     */
    private boolean save(String fileName, ClientStore clientList) {
        return save(new File(fileName), clientList);
    }

    /**
     * Method to save serialization binary file.
     *
     * @param clientList client store.
     * @param file Binary File.
     * @return True if the client store was successfully saved.
     */
    private boolean save(File file, ClientStore clientList) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            try {
                out.writeObject(clientList);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
