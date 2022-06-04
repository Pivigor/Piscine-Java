import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Program {
    private static final String pathToFile = "F:\\Projects\\CLion Projects\\Applications\\School21\\Speedrun\\Piscine_Java\\Day03\\ex03\\files_urls.txt";

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Bad args");
            return;
        }

        try {
            int threadsCount = Integer.parseInt(parseArgs(args[0], "--threadsCount"));
            if (threadsCount <= 0) {
                throw new RuntimeException("Bad args");
            }

            BlockingQueue<MyFileReader.MyFile> queue = new LinkedBlockingQueue<>();

            MyFileReader fileReader = new MyFileReader(pathToFile, queue, threadsCount);
            fileReader.start();

            AsyncDownload[] threads = new AsyncDownload[threadsCount];
            for (int i = 0; i < threadsCount; i++) {
                threads[i] = new AsyncDownload(i + 1, queue);
            }
            for (int i = 0; i < threadsCount; i++) {
                threads[i].start();
            }
            for (int i = 0; i < threadsCount; i++) {
                threads[i].join();
            }

            System.out.println("MAIN THREAD FINISH!");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static String parseArgs(String arg, String prefix) {
        if (arg.contains("=")) {
            String[] parse = arg.split("=");
            if (parse.length == 2 && parse[0].equals(prefix)) {
                return parse[1];
            }
        }
        throw new RuntimeException("Bad args");
    }
}
