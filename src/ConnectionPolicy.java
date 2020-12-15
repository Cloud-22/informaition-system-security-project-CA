import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.math.BigInteger;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public abstract class ConnectionPolicy {
    protected ICryptographyMethod cryptographyMethod;

    public abstract void init();
    public abstract boolean checkCSRContent(String csrPayload);
    public abstract CSR unpack(String csrPayload);
    public abstract boolean validate(CSR csr);
    public abstract boolean sign(CSR csr);
    public abstract String getClientPublicKey();

//    public String generateKey(int keySize){
//        Logger.log("Generating key...");
//        KeyGenerator gen;
//        String SK = "";
//        try {
//            gen = KeyGenerator.getInstance("AES");
//            gen.init(keySize); /* 128-bit AES */
//            SecretKey secretKey = gen.generateKey();
//            byte[] bytes = secretKey.getEncoded();
//            SK = String.format("%032X", new BigInteger(+1, bytes));
//
//
//        } catch (NoSuchAlgorithmException e) {
//            Logger.log(e.getMessage());
//        }
//        return SK;
//    }
//
//    public String generateIV() {
//        Logger.log("Generating initial vector...");
//        byte[] iv = new byte[128/8];
//        SecureRandom srandom = new SecureRandom();
//        srandom.nextBytes(iv);
//        return Base64.getEncoder().encodeToString(iv);
//    }
}
