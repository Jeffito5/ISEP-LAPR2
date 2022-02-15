package app.domain.model;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

import java.io.File;
import java.io.Serializable;
import java.util.Random;

/**
 * @author Luís Araújo
 */
public class Sample implements CreateBarcode, Serializable {
    /**
     * Sample's default barcode number
     */
    private final static long DEFAULT_BARCODE_NUMBER = 0;
    /**
     * Random variable to later generate a random number to put in the barcode
     */
    private final Random random = new Random();
    /**
     * Sample's barcode number
     */
    private long barcode;
    /**
     * Sample's constructor with the barcode number
     *
     * @param barcode
     */
    public Sample(long barcode) {
        this.barcode = barcode;
    }
    /**
     * Sample's default barcode
     */
    public Sample() {
        barcode = DEFAULT_BARCODE_NUMBER;
    }
    /**
     * Method to generate a random number to put in the barcode
     * @return a random number to put in the barcode
     */
    public long generateBarcode() {
        /**
         * Generate a random number with 12 digits to put in the barcode
         */
        StringBuilder sb = new StringBuilder();
        // first not 0 digit
        sb.append(random.nextInt(9) + 1);
        // rest of 10 digits
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        barcode = Long.parseLong(String.valueOf(sb));
        return barcode;
    }

    /**
     * Method to return the barcode number
     *
     * @return barcode number
     */
    public long getBarcode_number() {
        return barcode;
    }

    /**
     * Method to modify the barcode number
     *
     * @param barcode
     */
    public void setBarcode_number(long barcode) {
        this.barcode = barcode;
    }

    /**
     * Method of the interface CreateBarcode that generates an UPCA barcode
     *
     * @return UPCA barcode
     * @throws BarcodeException
     */
    @Override
    public Barcode createUPCA(String path) throws BarcodeException {
        // calls the method that generates the number
        generateBarcode();
        String name = String.valueOf(barcode);
        // get a Barcode from the BarcodeFactory
        Barcode barcodeUPCA = BarcodeFactory.createUPCA(name);

        barcodeUPCA.setPreferredBarHeight(100);
        try {
            File f = new File("Barcodes\\" + "Name-" + name + "NHS Code-" + path + ".jpeg");
            // Let the barcode image handler do the hard work
            BarcodeImageHandler.savePNG(barcodeUPCA, f);
        } catch (Exception e) {
            System.out.println("Invalid barcode. Try again.\n");
        }
        return barcodeUPCA;
    }
}
