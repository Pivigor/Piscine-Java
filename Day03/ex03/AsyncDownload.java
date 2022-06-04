import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.BlockingQueue;

public class AsyncDownload extends Thread {
    public AsyncDownload(int id, BlockingQueue<MyFileReader.MyFile> queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                MyFileReader.MyFile take = queue.take();
                if (take == MyFileReader.poisonPill) {
                    System.out.printf("Thread-%d FINISH!\n", id);
                    return;
                }

                try {
                    System.out.printf("Thread-%d start download file number %s\n", id, take.name);
                    DownloadFileByUrl(take);
                    System.out.printf("Thread-%d finish download file number %s\n", id, take.name);
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }

            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public void DownloadFileByUrl(MyFileReader.MyFile file) {
        try {
            URL url = new URL(file.url);
            String fileName = file.name + ".html";
            try {
                fileName = Paths.get(new URI(file.url).getPath()).getFileName().toString();
            } catch (Exception e) {
                System.out.printf("Thread-%d file name not found! Using %s.html\n", id, file.name);
            }

            InputStream in = url.openStream();
            Files.copy(in, Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final int id;
    private final BlockingQueue<MyFileReader.MyFile> queue;
}
