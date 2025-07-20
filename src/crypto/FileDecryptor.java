package crypto;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.GeneralSecurityException;

public class FileDecryptor {

    public static void decryptFile(String inputFilePath, String outputFilePath, SecretKey key)
            throws IOException, GeneralSecurityException {

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        try (
            FileInputStream fis = new FileInputStream(inputFilePath);
            CipherInputStream cis = new CipherInputStream(fis, cipher);
            FileOutputStream fos = new FileOutputStream(outputFilePath)
        ) {
            byte[] buffer = new byte[1024];
            int read;
            while ((read = cis.read(buffer)) != -1) {
                fos.write(buffer, 0, read);
            }
        }
    }
}
