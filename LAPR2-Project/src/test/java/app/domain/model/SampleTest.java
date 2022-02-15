package app.domain.model;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;
import org.junit.Before;
import org.junit.Test;

import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author
 */
public class SampleTest {
    private Sample s1, s2;

    @Before
    public void setUp() throws Exception {
        String num1 = "123456789098";
        String num2 = "123456789432";
        s1 = new Sample(Long.parseLong(num1));
        s2 = new Sample(Long.parseLong(num2));
    }

    // testes ao comprimento
    @Test
    public void generateBarcode() {
        //Arrange
        String num1 = "12345678091";
        String num2 = "12345678909";

        int lenght1 = num1.length();
        int lenght2 = num2.length();

        //Act
        long num3 = s1.generateBarcode();
        long num4 = s2.generateBarcode();

        int result1 = String.valueOf(num3).length();
        int result2 = String.valueOf(num4).length();

        //Assert
        assertEquals(lenght1, result1);
        assertEquals(lenght2, result2);
    }

    @Test
    public void getBarcode_number() {
        //Arrange
        String expectedResult1 = "123456789098";
        String expectedResult2 = "123456789432";

        //Act
        long result1 = s1.getBarcode_number();
        long result2 = s2.getBarcode_number();

        //Assert
        assertEquals(Long.parseLong(expectedResult1), result1);
        assertEquals(Long.parseLong(expectedResult2), result2);
    }

    @Test
    public void setBarcode_number() {
        //Arrange
        String expectedResult1 = "123456787654";
        String expectedResult2 = "786543217890";

        //Act
        s1.setBarcode_number(Long.parseLong(expectedResult1));
        s2.setBarcode_number(Long.parseLong(expectedResult2));

        //Assert
        assertEquals(Long.parseLong(expectedResult1), s1.getBarcode_number());
        assertEquals(Long.parseLong(expectedResult2), s2.getBarcode_number());

    }

    @Test
    public void createUPCA() throws BarcodeException, OutputException, IOException {
        //Arrange
        String barcode = "12345678765";
        Barcode barcodeUPCA = BarcodeFactory.createUPCA(barcode);
        barcodeUPCA.setPreferredBarHeight(100);
        File f = new File("Barcodes\\barcode.jpeg");
        BarcodeImageHandler.savePNG(barcodeUPCA, f);

        //Act
        boolean result = f.exists();

        //Assert
        assertEquals(true, result);
    }

    @Test
    public void setPreferredBarHeight() throws BarcodeException {
        //Arrange
        int expectedResult1 = 101;
        String barcode = "12345678765";
        Barcode barcodeUPCA=BarcodeFactory.createUPCA(barcode);

        //Act
        barcodeUPCA.setPreferredBarHeight(expectedResult1);

        //Assert
        assertEquals(expectedResult1, barcodeUPCA.getPreferredBarHeight());
    }

}