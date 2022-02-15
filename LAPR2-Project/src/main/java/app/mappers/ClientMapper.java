package app.mappers;

import app.domain.model.Client;
import app.mappers.dto.ClientDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luís Araújo
 */
public class ClientMapper {
    public ClientDto toDTO(Client client) {
        return new ClientDto(client.getName(), client.getBirthDate(), ClientDto.Sex.valueOf(client.getSex().name()), client.getCcn(), client.getNhsNumber(), client.getTin(),
                client.getPhoneNumber(), client.getEmail());
    }

    public List<ClientDto> toDTO(List<Client> clientList) {

        List<ClientDto> clientDtoList = new ArrayList<>();

        for (Client client : clientList) {
            clientDtoList.add(this.toDTO(client));
        }

        return clientDtoList;
    }
}
