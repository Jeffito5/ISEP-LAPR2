package app.domain.store;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.Module;
import net.sourceforge.barbecue.linear.upc.UPCABarcode;
import net.sourceforge.barbecue.output.Output;
import net.sourceforge.barbecue.output.OutputException;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * @author
 */
public class SampleStoreTest {
    private SampleStore sampleStore;

    @Before
    public void setUp() throws Exception {
        sampleStore=new SampleStore();
    }

    @Test
    public void addSample() throws BarcodeException {
        String barcode1="12345678765";
        Barcode barcode = new Barcode(barcode1) {
            @Override
            protected Module[] encodeData() {
                return new Module[0];
            }

            @Override
            protected Module calculateChecksum() {
                return null;
            }

            @Override
            protected Module getPreAmble() {
                return null;
            }

            @Override
            protected Module getPostAmble() {
                return null;
            }

            @Override
            protected Dimension draw(Output output, int i, int i1, int i2, int i3) throws OutputException {
                return null;
            }
        };
        sampleStore.addSample(barcode);
    }

    @Test
    public void validateSample() {
    }
}