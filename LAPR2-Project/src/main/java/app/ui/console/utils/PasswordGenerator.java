package app.ui.console.utils;

import java.io.Serializable;
import java.util.Random;

public class PasswordGenerator implements Serializable {
    private final Random random = new Random();

    public String generateRandomPassword(){
        //inspired by: https://www.baeldung.com/java-random-string

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
