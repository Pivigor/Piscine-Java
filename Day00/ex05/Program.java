import java.util.Scanner;

public class Program {
    static String[] weekdays = new String[] { "MO", "TU", "WE", "TR", "FR", "SA", "SU" };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nameCount = 10;
        String[] names = new String[nameCount];
        for (int i = 0; i < nameCount; i++) {
            String data = scanner.nextLine();

            if (data.equals(".")) {
                nameCount = i;
                break;
            }

            names[i] = data;
        }

        int dayCount = 10;
        String[] days = new String[dayCount];
        for (int i = 0; i < dayCount; i++) {
            String data = scanner.nextLine();

            if (data.equals(".")) {
                dayCount = i;
                break;
            }

            days[i] = data;
        }

        int timeCount = 10;
        String[] times = new String[dayCount];
        for (int i = 0; i < timeCount; i++) {
            String data = scanner.nextLine();

            if (data.equals(".")) {
                timeCount = i;
                break;
            }

            times[i] = data;
        }

        printTable(names, nameCount, days, dayCount, times, timeCount);
    }

    public static void printTable(String[] names, int nameCount, String[] days, int dayCount, String[] times, int timeCount) {
        printWithTab("");
        System.out.println();
        for (int i = 0; i < nameCount; i++) {
            printWithTab(names[i]);
            System.out.println();
        }
    }

    public static void printWithTab(String word) {
        int offset = 10 - word.length();
        while (offset > 0) {
            System.out.print(' ');
            offset--;
        }
        System.out.print(word);
    }

    public static int[] getCalendar(String dayOfWeek) {
        if (dayOfWeek.equals("MO")) {
            return new int[] { 7, 14, 21, 28 };
        }
        if (dayOfWeek.equals("TU")) {
            return new int[] { 1, 8, 15, 22, 29 };
        }
        if (dayOfWeek.equals("WE")) {
            return new int[] { 2, 9, 16, 23, 30 };
        }
        if (dayOfWeek.equals("TH")) {
            return new int[] { 3, 10, 17, 24 };
        }
        if (dayOfWeek.equals("FR")) {
            return new int[] { 4, 11, 18, 25 };
        }
        if (dayOfWeek.equals("SA")) {
            return new int[] { 5, 12, 19, 26 };
        }
        if (dayOfWeek.equals("SU")) {
            return new int[] { 6, 13, 20, 27 };
        }
        return null;
    }

    public static String[] parseDates(Scanner scanner) {
        int daySize = 10;
        int[][] days = new int[daySize][2];
        for (int i = 0; i < daySize; i++) {
            if (!scanner.hasNextInt())
            int time = scanner.nextInt();

            if (data.equals(".")) {
                daySize = i;
                break;
            }

            days[i][0] = data;
        }


    }
}
