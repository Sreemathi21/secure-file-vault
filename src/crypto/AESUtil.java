package crypto;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESUtil {

    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 128;

    // Generate AES key
    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(KEY_SIZE);
        return keyGen.generateKey();
    }

    // Convert SecretKey to Base64 string
    public static String keyToString(SecretKey key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    // Convert Base64 string back to SecretKey
    public static SecretKey stringToKey(String keyStr) {
        byte[] decodedKey = Base64.getDecoder().decode(keyStr);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, ALGORITHM);
    }

    // Save key to file
    public static void saveKeyToFile(SecretKey key, String filePath) throws IOException {
        String keyStr = keyToString(key);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(keyStr);
        }
    }

    // Load key from file
    public static SecretKey loadKeyFromFile(String filePath) throws IOException {
        String keyStr;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            keyStr = reader.readLine();
        }
        return stringToKey(keyStr);
    }
}
