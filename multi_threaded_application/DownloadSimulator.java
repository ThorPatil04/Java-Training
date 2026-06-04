package multi_threaded_application;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DownloadSimulator {

    private ExecutorService executor;

    // Constructor
    public DownloadSimulator(int threadPoolSize) {

        executor = Executors.newFixedThreadPool(threadPoolSize);
    }

    // Simulate File Download   
    public Future<String> downloadFile(String fileName) {

        return executor.submit(() -> {

            System.out.println(
                    "Downloading " + fileName +
                            " using " +
                            Thread.currentThread().getName());

            Thread.sleep(2000);

            return fileName + " downloaded successfully";
        });
    }

    // Shutdown Executor
    public void shutdown() {

        executor.shutdown();

        System.out.println("Executor Service Shutdown");
    }

    // Main Method
    public static void main(String[] args)
            throws InterruptedException, ExecutionException {

        DownloadSimulator simulator =
                new DownloadSimulator(3);

        // Start Downloads
        Future<String> download1 =
                simulator.downloadFile("file1.txt");

        Future<String> download2 =
                simulator.downloadFile("file2.txt");

        Future<String> download3 =
                simulator.downloadFile("file3.txt");

        // Get Results
        System.out.println(download1.get());

        System.out.println(download2.get());

        System.out.println(download3.get());

        // Shutdown
        simulator.shutdown();
    }
}

