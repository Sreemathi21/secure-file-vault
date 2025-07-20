package crypto;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {
    public static void main(String[] args) {
        try {
            // Step 1: Generate AES Key and save it to a file
            SecretKey key = AESUtil.generateKey();
            AESUtil.saveKeyToFile(key, "secret.key");

            // Step 2: Load the key from file
            SecretKey loadedKey = AESUtil.loadKeyFromFile("secret.key");

            // Step 3: Encrypt the input file
            FileEncryptor.encryptFile("C:\\Users\\sange\\secure-file-vault\\sample.txt", "encrypted.vault", loadedKey);
            System.out.println("✅ File encrypted successfully to 'encrypted.vault'");

            // Step 4: Decrypt the file
            FileDecryptor.decryptFile("encrypted.vault", "decrypted.txt", loadedKey);
            System.out.println("✅ File decrypted successfully to 'decrypted.txt'");

        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}
