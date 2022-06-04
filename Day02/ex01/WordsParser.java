import java.io.*;
import java.util.*;

public class WordsParser {

    public static Map<String, Integer> parseWords(String pathStr) {
        Map<String, Integer> map = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(pathStr))) {
            while (reader.ready()) {
                for (String s : reader.readLine().trim().split("\\s+")) {
                    if (!s.isEmpty()) {
                        if (map.containsKey(s)) {
                            map.put(s, map.get(s) + 1);
                        } else {
                            map.put(s, 1);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return map;
    }

    public static SortedSet<String> createDict(Map<String, Integer> map1, Map<String, Integer> map2) {
        SortedSet<String> dict = new TreeSet<>();
        dict.addAll(map1.keySet());
        dict.addAll(map2.keySet());
        return dict;
    }

    public static Vector<Integer> countWords(Map<String, Integer> map, SortedSet<String> dict) {
        Vector<Integer> vector = new Vector<>(dict.size());

        for (String word : dict) {
            vector.add(map.getOrDefault(word, 0));
        }

        return vector;
    }

    public static void saveDict(SortedSet<String> dict, String pathForSave) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathForSave))) {
            for (String word : dict) {
                writer.write(word);
                writer.write(" ");
            }
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
