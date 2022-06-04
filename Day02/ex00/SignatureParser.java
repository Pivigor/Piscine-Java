import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class SignatureParser {

    public static class Signature {
        public Signature(String type, int[] hexes) {
            this.type = type;
            this.hexes = hexes;
        }

        public String type;
        public int[] hexes;
    }

    public static List<Signature> parseSignatures(String pathStr) {
        List<Signature> signatures = new LinkedList<>();

        Path path = Paths.get(pathStr);
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.contains(", ")) {
                    String[] parse = line.split(", ");

                    if (parse.length == 2 && parse[1].contains(" ")) {
                        String[] strHexes = parse[1].split(" ");

                        if (strHexes.length > 0) {
                            int[] hexes = new int[strHexes.length];
                            for (int i = 0; i < strHexes.length; i++) {
                                hexes[i] = Integer.parseInt(strHexes[i], 16);
                            }

                            signatures.add(new Signature(parse[0], hexes));
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return signatures;
    }

    public static List<Signature> findType(List<Signature> signatures, String pathStr) {
        List<Signature> output = new LinkedList<>(signatures);
        Signature maxSignature = output.stream().max(Comparator.comparing(signature -> signature.hexes.length)).orElse(null);
        int maxSize = 0;
        if (maxSignature != null) {
            maxSize = maxSignature.hexes.length;
        }

        try (InputStream inputStream = new FileInputStream(pathStr)) {
            for (int i = 0; i < maxSize && inputStream.available() > 0; i++) {
                int input = inputStream.read();
                //System.out.printf("%02X \n", input);

                int finalI = i;
                output.removeIf(signature -> signature.hexes.length >= finalI + 1 && signature.hexes[finalI] != input);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return output;
    }

    public static void saveTypes(List<Signature> signatures, String pathForTitle, String pathForSave, boolean append) {
        try (OutputStream outputStream = new FileOutputStream(pathForSave, append)) {
            outputStream.write(pathForTitle.getBytes());
            outputStream.write('\n');

            for (Signature signature : signatures) {
                outputStream.write(signature.type.getBytes());
                outputStream.write('\n');
            }
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
