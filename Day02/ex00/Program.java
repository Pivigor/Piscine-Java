import java.util.*;

public class Program {
    public static void main(String[] args) {
        final String signaturePath = "F:\\Projects\\CLion Projects\\Applications\\School21\\Speedrun\\Piscine_Java\\Day02\\ex00\\signatures.txt";
        final String resultPath = "F:\\Projects\\CLion Projects\\Applications\\School21\\Speedrun\\Piscine_Java\\Day02\\ex00\\result.txt";

        try {
            Scanner scanner = new Scanner(System.in);
            List<SignatureParser.Signature> signatures = SignatureParser.parseSignatures(signaturePath);
            boolean firstTime = true;

            while (true) {
                String pathStr = scanner.nextLine();
                if (pathStr.equals("42")) {
                    break;
                }

                List<SignatureParser.Signature> types = SignatureParser.findType(signatures, pathStr);
                if (types.size() > 0) {
                    SignatureParser.saveTypes(types, pathStr, resultPath, !firstTime);
                    firstTime = false;
                    System.out.println("PROCESSED");
                } else {
                    System.out.println("UNDEFINED");
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // InputStream inputStream = Files.newInputStream(Paths.get("F:\\Projects\\CLion Projects\\Applications\\School21\\Speedrun\\Piscine_Java\\Day02\\ex00\\Program.java"
    }

}
