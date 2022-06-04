import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int max_char = 66000;
        int[] data = new int[max_char];

        for (int i = 0; i < max_char; i++) {
            data[i] = 0;
        }

        char[] input = scanner.nextLine().toCharArray();
        for (char c : input) {
            if (data[c] < 999) {
                data[c]++;
            }
        }

        int tableSize = 10;
        char[] tableChars = new char[tableSize];
        int[] tableCounts = new int[tableSize];
        for (int i = 0; i < 10; i++) {
            int data_max = 0;
            int data_max_j = -1;
            for (int j = 0; j < max_char; j++) {
                if (data[j] > data_max) {
                    data_max = data[j];
                    data_max_j = j;
                }
            }

            if (data_max == 0) {
                tableSize = i;
                break;
            }

            data[data_max_j] = 0;
            tableChars[i] = (char) data_max_j;
            tableCounts[i] = data_max;
        }
        printTable(tableChars, tableCounts, tableSize);
    }

    public static void printTable(char[] tableChars, int[] tableCounts, int tableSize) {
        if (tableSize == 0) {
            return;
        }

        int tableMax = -1;
        int tableMin = 1000;
        for (int i = 0; i < tableSize; i++) {
            if (tableCounts[i] < tableMin) {
                tableMin = tableCounts[i];
            }
            if (tableCounts[i] > tableMax) {
                tableMax = tableCounts[i];
            }
        }

        int[] tableHist = new int[tableSize];
        for (int i = 0; i < tableSize; i++) {
            //double percent = (double) ((tableCounts[i] - tableMin) * 100) / (tableMax - tableMin);
            tableHist[i] = tableCounts[i] * 10 / (tableMax);
            //System.out.println(tableHist[i]);
        }

        char[][] table = new char[tableSize * 3][12];
        for (int i = 0; i < tableSize * 3; i++) {
            for (int j = 11; j >= 0; j--) {
                table[i][j] = ' ';
                if ((i + 1) % 3 == 0) {
                    if (j == 11) {
                        table[i][j] = tableChars[i / 3];

                    } else if (tableHist[i / 3] > 0) {
                        table[i][j] = '#';
                        tableHist[i / 3]--;

                    } else if (tableHist[i / 3] == 0) {
                        for (int k = 0; k < 3; k++) {
                            table[i - k][j] = (char) (tableCounts[i / 3] % 10 + '0');
                            tableCounts[i / 3] /= 10;
                            if (tableCounts[i / 3] == 0) {
                                break;
                            }
                        }

                        tableHist[i / 3]--;
                    }
                }
            }
        }

        for (int j = 0; j < 12; j++) {
            for (int i = 0; i < tableSize * 3; i++) {
                System.out.print(table[i][j]);
            }
            System.out.println();
        }
    }
}
