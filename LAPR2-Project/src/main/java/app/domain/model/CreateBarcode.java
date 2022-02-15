package app.domain.model;


import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;

/**
 * @author Luís Araújo
 */
public interface CreateBarcode {
    /**
     * Method to generate an UPCA barcode
     *
     * @throws BarcodeException
     * @return
     */
    public Barcode createUPCA(String path) throws BarcodeException;

}
