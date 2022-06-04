import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        int count = 0;

        while ((number = scanner.nextInt()) != 42) {
            if (isPrimeNumber(digitsSum(number))) {
                count++;
            }
        }
        System.out.println("Count of coffee-request - " + count);
    }

    public static int digitsSum(int number) {
        int sum = 0;

        while (number != 0) {
            sum += number % 10;
            number = number / 10;
        }
        return sum;
    }

    public static boolean isPrimeNumber(int number) {
        boolean isPrime = true;

        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }
}
