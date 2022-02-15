package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.store.ClientStore;
import app.mappers.dto.ClientDto;
import auth.UserSession;
import auth.domain.store.UserStore;

public class RegisterClientController {
    private final ClientStore store;
    private Client c;
    private final Company company;

    public RegisterClientController(){
        // Singleton
        App app = App.getInstance();

        // Authorization Mechanism
        UserSession session = app.getCurrentUserSession();

        if (session.isLoggedInWithRole("RECEPTIONIST") || session.isLoggedInWithRole("CLIENT")){
            company = app.getCompany();
            this.store = company.getClientStore();
        } else {
            throw new IllegalStateException("Not authorized.");
        }
    }

    /**
     * Creates an instance of Client, receiving a Client Data Transfer Object.
     *
     * @param clientDto client's DTO.
     *
     * @return true if the client is validated (is not on the Client Store)
     */
    public boolean createClient(ClientDto clientDto){

        try {
            c = store.createClient(clientDto);
            return store.validateClient(c);
        } catch (IllegalArgumentException e) {
            System.out.println("\nERROR: " + e.getMessage());
            return false;
        }
    }

    /**
     * Asks the Client Store to show the created client info.
     */
    public void showClient(){
        store.showClient(c);
    }

    /**
     * Adds the client on the store and registers them on the app
     * in case they are not yet registered.
     *
     * @return true if the client was added.
     */
    public boolean addClient(){
        if (store.validateClient(c)){
            return store.addClient(c);
        } else{
            return false;
        }
    }

    public boolean saveClient()
    {
        return company.saveClients(store) && company.saveUsers();
    }

}
