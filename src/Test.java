//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import java.security.*;
//import java.security.spec.InvalidKeySpecException;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//import java.util.Base64;
//
//public class Test {
//
//    private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgFGVfrY4jQSoZQWWygZ83roKXWD4YeT2x2p41dGkPixe73rT2IW04glagN2vgoZoHuOPqa5and6kAmK2ujmCHu6D1auJhE2tXP+yLkpSiYMQucDKmCsWMnW9XlC5K7OSL77TXXcfvTvyZcjObEz6LIBRzs6+FqpFbUO9SJEfh6wIDAQAB";
//    private static String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKAUZV+tjiNBKhlBZbKBnzeugpdYPhh5PbHanjV0aQ+LF7vetPYhbTiCVqA3a+Chmge44+prlqd3qQCYra6OYIe7oPVq4mETa1c/7IuSlKJgxC5wMqYKxYydb1eULkrs5IvvtNddx+9O/JlyM5sTPosgFHOzr4WqkVtQ71IkR+HrAgMBAAECgYAkQLo8kteP0GAyXAcmCAkA2Tql/8wASuTX9ITD4lsws/VqDKO64hMUKyBnJGX/91kkypCDNF5oCsdxZSJgV8owViYWZPnbvEcNqLtqgs7nj1UHuX9S5yYIPGN/mHL6OJJ7sosOd6rqdpg6JRRkAKUV+tmN/7Gh0+GFXM+ug6mgwQJBAO9/+CWpCAVoGxCA+YsTMb82fTOmGYMkZOAfQsvIV2v6DC8eJrSa+c0yCOTa3tirlCkhBfB08f8U2iEPS+Gu3bECQQCrG7O0gYmFL2RX1O+37ovyyHTbst4s4xbLW4jLzbSoimL235lCdIC+fllEEP96wPAiqo6dzmdH8KsGmVozsVRbAkB0ME8AZjp/9Pt8TDXD5LHzo8mlruUdnCBcIo5TMoRG2+3hRe1dHPonNCjgbdZCoyqjsWOiPfnQ2Brigvs7J4xhAkBGRiZUKC92x7QKbqXVgN9xYuq7oIanIM0nz/wq190uq0dh5Qtow7hshC/dSK3kmIEHe8z++tpoLWvQVgM538apAkBoSNfaTkDZhFavuiVl6L8cWCoDcJBItip8wKQhXwHp0O3HLg10OEd14M58ooNfpgt+8D8/8/2OOFaR0HzA+2Dm";
//
//    public static PublicKey getPublicKey(String base64PublicKey){
//        PublicKey publicKey = null;
//        try{
//            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            publicKey = keyFactory.generatePublic(keySpec);
//            return publicKey;
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (InvalidKeySpecException e) {
//            e.printStackTrace();
//        }
//        return publicKey;
//    }
//
//    public static PrivateKey getPrivateKey(String base64PrivateKey){
//        PrivateKey privateKey = null;
//        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
//        KeyFactory keyFactory = null;
//        try {
//            keyFactory = KeyFactory.getInstance("RSA");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        try {
//            privateKey = keyFactory.generatePrivate(keySpec);
//        } catch (InvalidKeySpecException e) {
//            e.printStackTrace();
//        }
//        return privateKey;
//    }
//
//    public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
//        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
//        return cipher.doFinal(data.getBytes());
//    }
//
//    public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//        return new String(cipher.doFinal(data));
//    }
//
//    public static String decrypt(String data, String base64PrivateKey) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
//        return decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(base64PrivateKey));
//    }
//
//    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {
//        try {
//            String encryptedString = Base64.getEncoder().encodeToString(encrypt("Dhiraj is the author", publicKey));
//            System.out.println(encryptedString);
//            String decryptedString = Test.decrypt(encryptedString, privateKey);
//            System.out.println(decryptedString);
//        } catch (NoSuchAlgorithmException e) {
//            System.err.println(e.getMessage());
//        }
//
//    }
//}

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Random;
import java.util.Scanner;

public class Test {
    private static String encryptionKey;
    private static String decryptionKey;
    private static String IV;
    private static String key;
    private static final String sessionKey = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";
    private static Cipher cipher;

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException {


//        KeyGenerator gen;
//
//
//        byte[] iv = new byte[128/8];
//        SecureRandom srandom = new SecureRandom();
//        srandom.nextBytes(iv);
//        IV = Base64.getEncoder().encodeToString(iv);
//
//        Logger.log("Generating key...");
//        try {
//            gen = KeyGenerator.getInstance("AES");
//            gen.init(128); /* 128-bit AES */
//            SecretKey secretKey = gen.generateKey();
//            byte[] bytes = secretKey.getEncoded();
//            key = String.format("%032X", new BigInteger(+1, bytes));
//
//            Logger.log("Done" + "\n");
//
//        } catch (NoSuchAlgorithmException e) {
//            Logger.log("Failed" + "\n");
//        }



//        Logger.log("Generating key pair...");
//        KeyPairGenerator kpg;
//        try {
//            kpg = KeyPairGenerator.getInstance("RSA");
//            kpg.initialize(1024);
//            KeyPair kp = kpg.generateKeyPair();
////            privateKey = Base64.getEncoder().encodeToString(kp.getPrivate().getEncoded());
////            publicKey = Base64.getEncoder().encodeToString(kp.getPublic().getEncoded());
//            encryptionKey = Base64.getEncoder().encodeToString(kp.getPublic().getEncoded());
//            decryptionKey = Base64.getEncoder().encodeToString(kp.getPrivate().getEncoded());
//            Logger.log("Done" + "\n");
//
//        } catch (Exception e) {
//            Logger.log("Failed" + "\n");
//        }
//
//        cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//
//        String hello = "Hello";
//        System.out.println(hello);
//        System.out.println(encrypt(hello));
//        System.out.println(decrypt(encrypt(hello)));
    }

//    public static String encrypt(String data) {
//        Logger.log("Encrypting symmetrically...");
//        IvParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(IV));
//        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
//
//        try {
//            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv); // or Cipher.DECRYPT_MODE
//            byte[] encrypted = cipher.doFinal(data.getBytes());
//
//            Logger.log("Done" + "\n");
//            return Base64.getEncoder().encodeToString(encrypted);
//
//        } catch (Exception e) {
//            Logger.log("Failed" + "\n");
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static String decrypt(String data) {
//        Logger.log("Decrypting symmetrically...");
//        try {
//            IvParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(IV));
//            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
//
//            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//
//            byte[] original = cipher.doFinal(Base64.getDecoder().decode(data));
//
//            Logger.log("Done" + "\n");
//            return new String(original, StandardCharsets.UTF_8);
//
//        } catch (Exception ex) {
//            Logger.log("Failed" + "\n");
//            ex.printStackTrace();
//        }
//        return null;
//    }

//    public static String encrypt(String data) {
//        Logger.log("Encrypting asymmetrically...");
//        try {
//            cipher.init(Cipher.ENCRYPT_MODE, loadPublicKey(encryptionKey));
//            Logger.log("Done" + "\n");
//            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
//
//        } catch (Exception e) {
//            Logger.log("Failed" + "\n");
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static String decrypt(String data) {
//        Logger.log("Decrypting asymmetrically...");
//        try {
//            cipher.init(Cipher.DECRYPT_MODE, loadPrivateKey(decryptionKey));
//
//            Logger.log("Done" + "\n");
//            return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
//
//        } catch (Exception e) {
//            Logger.log("Failed" + "\n");
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static Key loadPublicKey(String storedPublic) {
//        Logger.log("Loading public key...");
//        try {
//            byte[] data = Base64.getDecoder().decode(storedPublic.getBytes());
//            X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
//            KeyFactory fact = KeyFactory.getInstance("RSA");
//            Logger.log("Done" + "\n");
//            return fact.generatePublic(spec);
//
//        } catch (Exception e) {
//            Logger.log("Failed" + "\n");
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static Key loadPrivateKey(String storedPrivate) {
//        Logger.log("Loading private key...");
//        try {
//            byte[] data = Base64.getDecoder().decode(storedPrivate.getBytes());
//            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(data);
//            KeyFactory fact = KeyFactory.getInstance("RSA");
//            Logger.log("Done" + "\n");
//            return fact.generatePrivate(spec);
//
//        } catch (Exception e) {
//            Logger.log("Failed" + "\n");
//            e.printStackTrace();
//        }
//        return null;
//    }
}
