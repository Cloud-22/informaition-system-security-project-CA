import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// nc localhost 22222
public class CertifiedAuthority {
//    public static final String registerPath = "register.txt";
    public static void main(String[] args) throws IOException {
        Logger.start();
        try (ServerSocket listener = new ServerSocket(22222)) {
            Logger.log("The CA is running...");
            // Generate Public, Private Keys
            CaConnectionPolicy caConnectionPolicy = new CaConnectionPolicy();
            caConnectionPolicy.init();
            ExecutorService pool = Executors.newFixedThreadPool(20);
            while (true) {
//                ConnectionPolicy connectionPolicy = new HybridConnectionPolicy();
                pool.execute(new ConnectionHandler(listener.accept(), caConnectionPolicy));
            }
        }
    }
}
