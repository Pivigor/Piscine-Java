public class Program {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Bad args");
            return;
        }

        try {
            int count = Integer.parseInt(parseArgs(args[0]));

            Thread thread1 = new Thread(new AsyncPrinter("Egg", count));
            Thread thread2 = new Thread(new AsyncPrinter("Hen", count));

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            AsyncPrinter printer = new AsyncPrinter("Human", count);
            printer.run();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static String parseArgs(String arg) {
        if (arg.contains("=")) {
            String[] parse = arg.split("=");
            if (parse.length == 2 && parse[0].equals("--count")) {
                return parse[1];
            }
        }
        throw new RuntimeException("Bad args");
    }
}
