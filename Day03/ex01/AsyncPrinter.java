public class AsyncPrinter implements Runnable {
    public AsyncPrinter(String message, int count) {
        this.message = message;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count;) {
            synchronized (AsyncPrinter.class) {
                if (active != this) {
                    active = this;
                    System.out.println(message);
                    i++;
                }
            }
        }
    }

    private final String message;
    private final int count;
    private static AsyncPrinter active = null;
}
