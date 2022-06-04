import java.util.Arrays;
import java.util.Random;

public class Program {
    private static final int randomLeftValue = -1000;
    private static final int randomRightValue = 1000;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Bad args");
            return;
        }

        try {
            int arraySize = Integer.parseInt(parseArgs(args[0], "--arraySize"));
            int threadsCount = Integer.parseInt(parseArgs(args[1], "--threadsCount"));
            if (arraySize <= 0 || threadsCount <= 0) {
                throw new RuntimeException("Bad args");
            }

            Random random = new Random();
            int[] array = random.ints(arraySize, randomLeftValue, randomRightValue + 1).toArray();
            System.out.println("Sum: " + Arrays.stream(array).sum());

            int oneSize = (int) Math.ceil((double) arraySize / threadsCount);
            AsyncSum[] threads = new AsyncSum[threadsCount];
            for (int i = 0; i < threadsCount; i++) {
                threads[i] = new AsyncSum(i + 1, array, i * oneSize,
                        Math.min((i + 1) * oneSize, arraySize));
            }
            for (int i = 0; i < threadsCount; i++) {
                threads[i].start();
            }
            for (int i = 0; i < threadsCount; i++) {
                threads[i].join();
            }

            int sum = 0;
            for (int i = 0; i < threadsCount; i++) {
                sum += threads[i].getSum();
            }
            System.out.println("Sum by threads: " + sum);

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
