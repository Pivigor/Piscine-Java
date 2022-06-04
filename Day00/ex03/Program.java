import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long buffer = 0;
        long power = 1;

        for (int week = 1; week <= 18; week++) {
            if (scanner.next().equals("42")) {
                break;
            }

            if (scanner.nextInt() != week) {
                System.err.println("IllegalArgument");
                System.exit(1);
            }

            int grade_min = parseWeekMin(scanner);

            buffer += grade_min * power;
            power *= 10;
        }

        for (int week = 1; week <= 18; week++) {
            if (buffer == 0) {
                break;
            }
            int data = (int) (buffer % 10);
            buffer /= 10;
            System.out.print("Week ");
            System.out.print(week);
            System.out.print(" ");
            while (data > 0) {
                System.out.print("=");
                data--;
            }
            System.out.println(">");
        }
    }

    public static int parseWeekMin(Scanner scanner) {
        int grade_min = 9;

        for (int i = 0; i < 5; i++) {
            int grade = scanner.nextInt();
            if (grade < grade_min) {
                grade_min = grade;
            }
        }
        return grade_min;
    }
}
