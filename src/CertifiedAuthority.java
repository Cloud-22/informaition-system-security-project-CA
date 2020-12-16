import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// nc localhost 22222
public class CertifiedAuthority {
//    public static final String registerPath = "register.txt";
    public static void main(String[] args) throws IOException {
        Logger.start();
        try (ServerSocket listener = new ServerSocket(22222)) {
            Logger.log("The CA is running...\n");
            // Generate Public, Private Keys
            ExecutorService pool = Executors.newFixedThreadPool(20);
            while (true) {
                Socket socket = listener.accept();
                Logger.log("Connection accepted.");
                CaConnectionPolicy caConnectionPolicy = new CaConnectionPolicy();
                pool.execute(new ConnectionHandler(socket, caConnectionPolicy));
            }
        }
    }
}
