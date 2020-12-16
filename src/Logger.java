import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Logger {
    public static boolean consoleLogs = true;
    public static boolean fileLogs = false;
    public static String FAILURE = "Failure";
    public static String SUCCESS = "Success";
    private static ArrayList<String> logs;
    
    public static void start() {
        Logger.log("");
        if (fileLogs) {
            ScheduledExecutorService executor =
                    Executors.newSingleThreadScheduledExecutor();

            Runnable writeToFile = new Runnable() {
                public synchronized void run() {
                    StringBuilder temp = new StringBuilder();
                    for (String log : logs) {
                        temp.append(log);
                    }
                    try {
                        Files.write(
                                Paths.get("logs.txt"),
                                temp.toString().getBytes(),
                                StandardOpenOption.CREATE,
                                StandardOpenOption.APPEND
                        );
                        logs.clear();
                    } catch (IOException e) {
                        consoleLogs = false;
                        Logger.log("An unexpected error occurred when trying to create or write to logs.txt");
                    }
                }
            };

            executor.scheduleAtFixedRate(writeToFile, 0, 2, TimeUnit.MINUTES);
        }
    }

    public static synchronized void log(String message) {
        if (consoleLogs) {
            System.out.println(message);
        }
        if (fileLogs) {
            if (logs == null) {
                logs = new ArrayList<>();
            }
            logs.add(message);
        }
    }
}
