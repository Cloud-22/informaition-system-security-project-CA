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
    public abstract boolean sign(Certificate certificate);
    public abstract String getClientPublicKey();

}
