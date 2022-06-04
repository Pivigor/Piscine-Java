import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Bad args");
            return;
        }

        try {
            MyFileSystem fileSystem = new MyFileSystem(parseArgs(args[0]));
            Scanner scanner = new Scanner(System.in);
            String cmd;
            while (!(cmd = scanner.next()).equals("exit")) {
                switch (cmd) {
                    case "ls":
                        fileSystem.ls();
                        break;
                    case "cd":
                        String dir = parseQuotes(scanner);
                        fileSystem.cd(dir);
                        break;
                    case "mv":
                        String from = parseQuotes(scanner);
                        String to = parseQuotes(scanner);
                        fileSystem.mv(from, to);
                        break;
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static String parseQuotes(Scanner scanner) {
        String str = scanner.findInLine("\".*?\"");
        if (str == null) {
            str = scanner.next();
        } else {
            str = str.substring(1, str.length() - 1);
        }
        return str;
    }

    public static String parseArgs(String arg) {
        if (arg.contains("=")) {
            String[] parse = arg.split("=");
            if (parse.length == 2 && parse[0].equals("--current-folder")) {
                return parse[1];
            }
        }
        throw new RuntimeException("Bad args");
    }
}
