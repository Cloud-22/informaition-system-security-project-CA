import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class AsymmetricCryptographyMethod implements ICryptographyMethod {
    private String encryptionKey;
    private String decryptionKey;
    private Cipher cipher;

    public void init() {
        Logger.log("Initializing asymmetric encryption...");
        try {
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        } catch (Exception e) {
            Logger.log(e.getMessage());
        }
    }

    @Override
    public String encrypt(String data) {
        Logger.log("Encrypting asymmetrically...");
        return this.encrypt(data, loadPublicKey(this.encryptionKey));
    }

    @Override
    public String decrypt(String data) {
        Logger.log("Decrypting asymmetrically...");
        return this.decrypt(data, loadPrivateKey(this.decryptionKey));
    }

    @Override
    public String encrypt(String data, Key key) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));

        } catch (Exception e) {
            Logger.log(e.getMessage());
        }
        return null;
    }

    @Override
    public String decrypt(String data, Key key) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);

            return new String(cipher.doFinal(Base64.getDecoder().decode(data)));

        } catch (Exception e) {
            Logger.log(e.getMessage());
        }
        return null;
    }

    public static Key loadPublicKey(String storedPublic) {
        try {
            byte[] data = Base64.getDecoder().decode(storedPublic.getBytes());
            X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            return fact.generatePublic(spec);

        } catch (Exception e) {
            Logger.log(e.getMessage());
        }
        return null;
    }

    public static Key loadPrivateKey(String storedPrivate) {
        try {
            byte[] data = Base64.getDecoder().decode(storedPrivate.getBytes());
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(data);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            return fact.generatePrivate(spec);

        } catch (Exception e) {
            Logger.log(e.getMessage());
        }
        return null;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }
    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }
    public String getDecryptionKey() {
        return decryptionKey;
    }
    public void setDecryptionKey(String decryptionKey) {
        this.decryptionKey = decryptionKey;
    }
    public Cipher getCipher() {
        return cipher;
    }
    public void setCipher(Cipher cipher) {
        this.cipher = cipher;
    }
}
