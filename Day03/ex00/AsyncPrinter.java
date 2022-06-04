public class AsyncPrinter implements Runnable {
    public AsyncPrinter(String message, int count) {
        this.message = message;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(message);
        }
    }

    private final String message;
    private final int count;
}
