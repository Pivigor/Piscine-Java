public class Program {
    public static void main(String[] args) {
        Menu.LaunchMode mode = Menu.LaunchMode.Production;
        if (args.length == 1 && args[0].length() == 13 && args[0].contains("=")) {
            String[] parse = args[0].split("=");
            if (parse.length == 2 && parse[0].equals("--profile") && parse[1].equals("dev")) {
                mode = Menu.LaunchMode.Dev;
            }
        }

        new Menu(mode);
    }
}
