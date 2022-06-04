import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class MyFileSystem {
    public MyFileSystem(String pathStr) {
        this.current = Paths.get(pathStr);

        if (!Files.exists(this.current) || !Files.isDirectory(this.current)) {
            throw new RuntimeException("Directory from args not found");
        }

        System.out.println(current.toAbsolutePath());
    }

    public void cd(String dirPathStr) {
        try {
            Path dir = getPath(dirPathStr);

            if (Files.exists(dir) && Files.isDirectory(dir)) {
                current = dir;
                System.out.println(current.toAbsolutePath());
            } else {
                System.out.println("Directory not found");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void ls() {
        try (Stream<Path> stream = Files.walk(current)) {
            stream.forEach(path -> {
                try {
                    if (path.toAbsolutePath() != current.toAbsolutePath()) {
                        if (Files.isDirectory(path)) {
                            System.out.println(path.getFileName());
                        } else {
                            long bytes = Files.size(path);
                            long kilobytes = (long) Math.ceil((double) bytes / 1024);
                            System.out.printf("%s %d KB\n", path.getFileName(), kilobytes);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            });
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void mv(String fromPathStr, String toPathStr) {
        try {
            Path from = getPath(fromPathStr);
            Path to = getPath(toPathStr);

            if (!Files.isDirectory(from) && Files.isDirectory(to)) {
                to = Paths.get(to + "\\" + from.getFileName());
            }
            Files.move(from, to);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Path getPath(String pathStr) {
        Path path = Paths.get(pathStr);
        if (!path.isAbsolute()) {
            path = Paths.get(current.toString(), pathStr);
        }

        path = path.normalize();
        return path;
    }

    public Path current;
}
