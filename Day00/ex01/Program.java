import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = 0;

        if (!scanner.hasNextInt() || (number = scanner.nextInt()) <= 1) {
            System.err.println("IllegalArgument");
            System.exit(1);
        }

        int steps = 0;
        boolean isPrime = true;
        for (int i = 2; i * i <= number; i++) {
            steps++;
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }
        System.out.println(isPrime + " " + steps);
    }
}
