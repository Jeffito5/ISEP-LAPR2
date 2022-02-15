package app.domain.store;

import app.domain.model.Client;
import app.domain.model.Test;
import app.mappers.dto.ClientDto;
import app.ui.console.utils.EmailNotificationSender;
import app.ui.console.utils.PasswordGenerator;
import auth.AuthFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClientStore implements Serializable {
    private final List<Client> store;
    private final PasswordGenerator passwordGenerator = new PasswordGenerator();
    private final EmailNotificationSender emailNotificationSender = new EmailNotificationSender();
    private AuthFacade authFacade;


    /**
     * Constructs an instance of the Client Store.
     */
    public ClientStore() {
        this.store = new ArrayList<>();
    }

    /**
     * Constructs an instance of the Client Store.
     *
     * @param authFacade Company's authorization facade.
     */
    public ClientStore(AuthFacade authFacade) {
        this.store = new ArrayList<>();
        this.authFacade = authFacade;
    }

    /**
     * Constructs an instance of the Client Store.
     *
     * @param authFacade Company's authorization facade.
     */
    public ClientStore(ClientStore clientStore, AuthFacade authFacade) {
        this.store = clientStore.getClients();
        this.authFacade = authFacade;
    }

    /**
     * Creates an instance of Client, receiving a Client Data Transfer Object
     *
     * @param clientDto Client's DTO
     * @return instance of Client.
     */
    public Client createClient(ClientDto clientDto) {

        return new Client(clientDto);
    }

    /**
     * Prints the client's info.
     */
    public void showClient(Client c) {
        System.out.println(c.toString());
    }

    /**
     * Adds a client to the store.
     *
     * @return true if the client was validated and added to the store.
     */
    public boolean addClient(Client c) {
        if (!validateClient(c)) return false;
        store.add(c);
        return registerClient(c);
    }

    /**
     * Checks if there isn't a client with the same data already stored.
     *
     * @return true if the store does not contain the client. Otherwise, returns false.
     */
    public boolean validateClient(Client c) {
        boolean userExists = authFacade.existsUser(c.getEmail());
        if (!userExists) {
            return !store.contains(c);
        } else {
            return false;
        }
    }

    /**
     * Registers the client on the company's app and sends email to confirm data.
     *
     * @return true if the client was registered. Otherwise returns false;
     */
    private boolean registerClient(Client c) {
        boolean registered;

        String pwd = passwordGenerator.generateRandomPassword();

        registered = this.authFacade.addUserWithRole(c.getName(), c.getEmail(), pwd, "CLIENT");
        if (registered && !emailNotificationSender.writePasswordToFile(c.getEmail(), pwd))
            System.out.println("Password: " + pwd);
        return registered;
    }

    public Client getClient(String clientTin) {
        for (Client client : store) {
            if (client.getTin().equals(clientTin)) {
                return client;
            }
        }
        throw new IllegalArgumentException("Client not found.");
    }

    public List<Client> getClients(){
        return store;
    }

    /**
     * Tries to get an instance of client through the email, if it succeeds, returns the instance,
     * if it fails, returns an Exception
     *
     * @param email Client's email
     * @return Client's email or Exception
     */
    public Client getClientByEmail(String email) {
        for (Client client : store) {
            if (client.getEmail().equals(email)) {
                return client;
            }
        }
        throw new IllegalArgumentException("Client not found.");
    }

    public void addTest(Client client, Test test) {
        client.addTest(test);
    }

    /**
     * Method to verify if the data was updated
     *
     * @param client    client
     * @param clientDto client dto
     * @return true if the data was updated
     */
    public boolean editData(Client client, ClientDto clientDto) {
        Client changedClient = new Client(clientDto);
        validateUpdatedData(client, changedClient);
        return client.editData(changedClient);
    }

    /**
     * Method to confirm if the crucial and unique client's parameters are not duplicated
     *
     * @param originalClient original client
     * @param updatedClient  updated client
     * @throws IllegalArgumentException if a parameter is duplicated.
     */
    private void validateUpdatedData(Client originalClient, Client updatedClient) {
        for (Client client : store) {
            if (!client.equals(originalClient)) {
                if (client.getCcn().equals(updatedClient.getCcn())) {
                    throw new IllegalArgumentException("Client's citizen card number already registered.\n");
                }
                if (client.getNhsNumber().equals(updatedClient.getNhsNumber())) {
                    throw new IllegalArgumentException("Client's nhs number already registered.\n");
                }
                if (client.getTin().equals(updatedClient.getTin())) {
                    throw new IllegalArgumentException("Client's tin number already registered.\n");
                }
                if (client.getPhoneNumber().equals(updatedClient.getPhoneNumber())) {
                    throw new IllegalArgumentException("Client's phone number already registered.\n");
                }
                if (client.getEmail().equals(updatedClient.getEmail())) {
                    throw new IllegalArgumentException("Client's email already registered.\n");
                }
            }
        }
    }

    public int getNumberOfClients(){
        return store.size();
    }
}
