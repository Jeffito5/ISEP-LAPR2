package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.store.ClientStore;
import app.mappers.ClientMapper;
import app.mappers.dto.ClientDto;

/**
 * @author Luís Araújo
 */
public class UpdatePersonalDataController {
    private Client client;
    private ClientStore clientStore;
    private App app;
    private Company company;
    private ClientMapper clientMapper;

    public UpdatePersonalDataController() {
        String email;
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.clientStore = company.getClientStore();
        email = app.getCurrentUserSession().getUserId().getEmail();
        client = clientStore.getClientByEmail(email);
        clientMapper = new ClientMapper();
    }

    public boolean editData(ClientDto clientDto, Client client) {
        return clientStore.editData(client, clientDto);
    }

    public ClientDto getClientData() {
        return clientMapper.toDTO(client);
    }
}
