package crypto;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.GeneralSecurityException;

public class FileEncryptor {

    public static void encryptFile(String inputFilePath, String outputFilePath, SecretKey key)
            throws IOException, GeneralSecurityException {

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        try (
            FileInputStream fis = new FileInputStream(inputFilePath);
            FileOutputStream fos = new FileOutputStream(outputFilePath);
            CipherOutputStream cos = new CipherOutputStream(fos, cipher)
        ) {
            byte[] buffer = new byte[1024];
            int read;
            while ((read = fis.read(buffer)) != -1) {
                cos.write(buffer, 0, read);
            }
        }
    }
}
