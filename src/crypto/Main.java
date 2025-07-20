package crypto;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {
    public static void main(String[] args) {
        try {
            // Generate key and save
            SecretKey key = AESUtil.generateKey();
            AESUtil.saveKeyToFile(key, "secret.key");

            // Load key from file
            SecretKey loadedKey = AESUtil.loadKeyFromFile("secret.key");

            // Encrypt file
            FileEncryptor.encryptFile("sample.txt", "sample_encrypted.vault", loadedKey);

            // Decrypt file
            FileDecryptor.decryptFile("sample_encrypted.vault", "sample_decrypted.txt", loadedKey);

            System.out.println("Encryption and Decryption done!");
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}
