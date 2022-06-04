import java.util.*;

public class Program {
    public static void main(String[] args) {
        final String resultPath = "F:\\Projects\\CLion Projects\\Applications\\School21\\Speedrun\\Piscine_Java\\Day02\\ex01\\dictionary.txt";

        if (args.length != 2) {
            System.err.println("Bad args");
            return;
        }

        try {
            Map<String, Integer> map1 = WordsParser.parseWords(args[0]);
            Map<String, Integer> map2 = WordsParser.parseWords(args[1]);

            SortedSet<String> dict = WordsParser.createDict(map1, map2);
            //System.out.println(dict);
            WordsParser.saveDict(dict, resultPath);

            Vector<Integer> vector1 = WordsParser.countWords(map1, dict);
            Vector<Integer> vector2 = WordsParser.countWords(map2, dict);
            //System.out.println(vector1);
            //System.out.println(vector2);

            double numerator = 0;
            double square1 = 0;
            double square2 = 0;
            for (int i = 0; i < dict.size(); i++) {
                numerator += vector1.get(i) * vector2.get(i);
                square1 += vector1.get(i) * vector1.get(i);
                square2 += vector2.get(i) * vector2.get(i);
            }

            double denominator = Math.sqrt(square1) * Math.sqrt(square2);
            //System.out.println(numerator + " " + denominator);

            double similarity = numerator / denominator;

            System.out.printf("Similarity = %.2f\n", similarity);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
