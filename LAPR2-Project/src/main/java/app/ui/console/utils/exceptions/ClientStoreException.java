package app.ui.console.utils.exceptions;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class ClientStoreException extends ClassNotFoundException{
    /**
     * Method that sends exception message to the super class.
     * Used to throw in the serialization method.
     */
    public ClientStoreException() {
        super("Client Store could not be loaded.");
    }

    /**
     * Method that sends received exception message to the super class.
     * Used to throw in the serialization method.
     *
     * @param message Message.
     */
    public ClientStoreException(String message) {
        super(message);
    }
}
