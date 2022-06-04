import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class MyFileReader extends Thread {
    public MyFileReader(String pathStr, BlockingQueue<MyFile> queue, int threadsCount) {
        this.pathStr = pathStr;
        this.queue = queue;
        this.threadsCount = threadsCount;
    }

    @Override
    public void run() {
        parseUrls();
        System.out.println("FILE-READER THREAD FINISH!");
    }

    public void parseUrls() {
        Path path = Paths.get(pathStr);
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.contains(" ")) {
                    String[] parse = line.split(" ");

                    if (parse.length == 2) {
                        queue.put(new MyFile(parse[0], parse[1]));
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        for (int i = 0; i < threadsCount;) {
            try {
                queue.put(poisonPill);
                i++;
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public static class MyFile {
        public MyFile(String name, String url) {
            this.name = name;
            this.url = url;
        }

        public String name;
        public String url;
    }

    private final String pathStr;
    private final BlockingQueue<MyFile> queue;
    private final int threadsCount;

    public static final MyFile poisonPill = new MyFile("42", "42");
}
