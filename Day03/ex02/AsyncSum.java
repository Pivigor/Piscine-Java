public class AsyncSum extends Thread {
    public AsyncSum(int id, int[] array, int start, int stop) {
        this.id = id;
        this.array = array;
        this.start = start;
        this.stop = stop;
        this.sum = 0;
    }

    @Override
    public void run() {
        for (int i = start; i < stop; i++) {
            sum += array[i];
        }
        System.out.printf("Thread %d: from %d to %d sum is %d\n", id, start, stop - 1, sum);
    }

    public int getSum() {
        return sum;
    }

    private final int id;
    private final int[] array;
    private final int start;
    private final int stop;
    private int sum;

}
