package app.ui.console.utils.comparators;

import app.mappers.dto.ClientDto;

import java.math.BigInteger;
import java.util.Comparator;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class ClientTinComparator implements Comparator<ClientDto> {
    /**
     * Method that compares the Tax Identification Number of two clients.
     *
     * @param clientDto client 1.
     * @param clientDto2 client 2.
     */
    @Override
    public int compare(ClientDto clientDto, ClientDto clientDto2) {
        String clientTin1 = clientDto.getTin();
        String clientTin2 = clientDto2.getTin();
        return new BigInteger(clientTin1).compareTo(new BigInteger(clientTin2));
    }
}
