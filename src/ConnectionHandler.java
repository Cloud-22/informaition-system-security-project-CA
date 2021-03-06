import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

// nc localhost 11111
public class ConnectionHandler implements Runnable {
//    private static final String initVector = "encryptionIntVec";
//    private static final String key = "aesEncryptionKey";
    public static final String currentDirectory = "files";

    private final Socket socket;
    private final ConnectionPolicy connectionPolicy;
    private String data;

    ConnectionHandler(Socket socket, ConnectionPolicy connectionPolicy) {
        this.data = "";
        this.socket = socket;
        this.connectionPolicy = connectionPolicy;
        this.connectionPolicy.init();
    }

    @Override
    public void run() {
        try {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // Unpack CSR

            String data = in.nextLine();
            if (!this.connectionPolicy.checkCSRContent(data)) {

                Logger.log("Failed to validate CSR." + "\n");
                return;

            } else {
                CSR csr = this.connectionPolicy.unpack(data);
                Certificate certificate = new Certificate(csr);
                this.connectionPolicy.sign(certificate);
                // Add CA public key

                out.println(certificate.toString());
            }

        } catch (Exception e) {
            Logger.log("Connection error");
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                Logger.log("Connection terminated.\n");
                
            } catch (IOException e) {
                Logger.log("Connection termination failed.\n");
            }
        }
    }


    private boolean checkCSRContent(String csrPayload) {
            if (csrPayload != null) {
                String[] tempPayload = csrPayload.split("\0");


            }
            return false;
    }

//    private static String encrypt(String data) throws InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {
//        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes());
//        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
//
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv); // or Cipher.DECRYPT_MODE
//
//        byte[] encrypted = cipher.doFinal(data.getBytes());
//
//        String s = Base64.getEncoder().encodeToString(encrypted);
//        Logger.log(s + "\n");
//        return s;
//    }

}