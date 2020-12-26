import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class CryptStream {
    public final static String CIPHER_TYPE = "DESede/ECB/PKCS5Padding";
    public final static String CIPHER_NAME = "DESede";

    private final static byte[] SECRET_KEY = new byte[] {
    }d

    public static byte[] generateNewSecretKey() {
        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance(CIPHER_NAME);
            return keyGenerator.generateKey().getEncoded();
        } catch (NoSuchAlgorithmException e) {
            ErrorHandling.handleException(String.format("Generating Cipher Key: %s", CIPHER_NAME), e);
        }

        return null;
    }

    public static InputStream decryptSteam(InputStream inputStream) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_TYPE);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(SECRET_KEY, CIPHER_NAME));

            return new CipherInputStream(inputStream, cipher);
        } catch (NoSuchAlgorithmException e) {
            ErrorHandling.handleException(String.format("Initializing the Cipher: %s", CIPHER_NAME), e);
        } catch (NoSuchPaddingException e) {
            ErrorHandling.handleException(String.format("Initializing the Cipher: %s", CIPHER_NAME), e);
        } catch (InvalidKeyException e) {
            ErrorHandling.handleException(String.format("Initializing the Cipher Key: %s", CIPHER_NAME), e);
        }

        return null;
    }

    public static OutputStream encryptStream(OutputStream outputStream) {

        try {
            Cipher cipher = Cipher.getInstance(CIPHER_TYPE);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(SECRET_KEY, CIPHER_NAME));

            return new CipherOutputStream(outputStream, cipher);
        } catch (NoSuchAlgorithmException e) {
            ErrorHandling.handleException(String.format("Initializing the Cipher: %s", CIPHER_NAME), e);
        } catch (NoSuchPaddingException e) {
            ErrorHandling.handleException(String.format("Initializing the Cipher: %s", CIPHER_NAME), e);
        } catch (InvalidKeyException e) {
            ErrorHandling.handleException(String.format("Initializing the Cipher Key: %s", CIPHER_NAME), e);
        }

        return null;
    }

}