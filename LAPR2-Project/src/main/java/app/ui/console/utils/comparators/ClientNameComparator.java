package app.ui.console.utils.comparators;

import app.mappers.dto.ClientDto;

import java.util.Comparator;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class ClientNameComparator implements Comparator<ClientDto> {

    /**
     * Method that compares the name of two clients.
     *
     * @param clientDto client 1.
     * @param clientDto2 client 2.
     */
    @Override
    public int compare(ClientDto clientDto, ClientDto clientDto2) {
        String clientName1 = clientDto.getName();
        String clientName2 = clientDto2.getName();
        return clientName1.compareToIgnoreCase(clientName2);
    }
}
