import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.util.Base64;
import java.util.List;

public class CaConnectionPolicy extends ConnectionPolicy {

    private String privateKey;
    private String publicKey;

    @Override
    public void init() {
        Logger.log("Initializing asymmetric connection...");
        try {
            List<String> lines  = Files.readAllLines(Path.of("keys/keys.txt"));
            this.setPublicKey(lines.get(0));
            this.setPrivateKey(lines.get(1));
            this.cryptographyMethod = new AsymmetricCryptographyMethod();
            this.cryptographyMethod.init();
            ((AsymmetricCryptographyMethod)this.cryptographyMethod).setEncryptionKey(this.getPrivateKey());
            ((AsymmetricCryptographyMethod)this.cryptographyMethod).setDecryptionKey(this.getPublicKey());

        } catch (IOException e) {
            Logger.log(e.getMessage());
        }

    }

    @Override
    public boolean checkCSRContent(String csrPayload) {
        // index 0 ==> IP
        // Index 1 ==> PhoneNumber
        // Index 2 ==> PublicKey
        if (csrPayload != null) {
            String[] tempPayload = csrPayload.split("\0");
            return tempPayload[0] != null && tempPayload[1] != null  && tempPayload[2] != null;
        }
        return false;
    }

    @Override
    public CSR unpack(String csrPayload) {
        String[] tempPayload = csrPayload.split("\0");
        return new CSR(tempPayload[0], tempPayload[1], tempPayload[2]);
    }

    @Override
    public boolean validate(CSR csr) {
        // Check If the certificate already signed before (Build a small DICT for this later
        return false;
    }

    @Override
    public boolean sign(Certificate certificate) {
        Logger.log("Signing certificate...");
        try {
            CSR csr = certificate.getCsr();
            MessageDigest digest  =  MessageDigest.getInstance("SHA-256");
            byte[] contentDigestBytes = digest.digest(csr.toString().getBytes(StandardCharsets.UTF_8));
            String contentDigest = bytesToHex(contentDigestBytes);
            String signature = cryptographyMethod.encrypt(
                    contentDigest, AsymmetricCryptographyMethod.loadPrivateKey(privateKey)
            );
            certificate.setSignature(signature);

        } catch (NoSuchAlgorithmException e) {
            Logger.log(e.getMessage());
        }
        return true;
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @Override
    public String getClientPublicKey() {
        // TODO : Clean all unnecessary methods like this
        return null;
    }

    public Pair<String, String> generateKeyPair(){
        Logger.log("Generating key pair...");
        KeyPairGenerator kpg;
        String publicKey = null;
        String privateKey = null;
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            KeyPair kp = kpg.generateKeyPair();
            publicKey = Base64.getEncoder().encodeToString(kp.getPublic().getEncoded());
            privateKey = Base64.getEncoder().encodeToString(kp.getPrivate().getEncoded());
            return new Pair<String, String>(publicKey, privateKey);

        } catch (Exception e) {
            Logger.log(e.getMessage());
        }
        return null;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
