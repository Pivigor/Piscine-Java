import com.diogonunes.jcdp.color.api.Ansi;

import java.util.Properties;

public class Program {
    public static void main(String[] args) {
        Settings settings = new Settings("F:\\Projects\\CLion Projects\\Applications\\School21\\Speedrun\\Piscine_Java\\Rush00\\resources\\application-production.properties");
        Properties properties = settings.getSettings();

        try {
            MyArgsParser argsParser = new MyArgsParser(args);

            Game.GamingMapSettings mapSettings = new Game.GamingMapSettings(
                    argsParser.size,
                    argsParser.size,
                    argsParser.enemiesCount,
                    argsParser.wallsCount,
                    argsParser.profile,
                    properties.getProperty("empty.char").charAt(0),
                    Ansi.BColor.valueOf(properties.getProperty("empty.color")),
                    properties.getProperty("player.char").charAt(0),
                    Ansi.BColor.valueOf(properties.getProperty("player.color")),
                    properties.getProperty("enemy.char").charAt(0),
                    Ansi.BColor.valueOf(properties.getProperty("enemy.color")),
                    properties.getProperty("wall.char").charAt(0),
                    Ansi.BColor.valueOf(properties.getProperty("wall.color")),
                    properties.getProperty("goal.char").charAt(0),
                    Ansi.BColor.valueOf(properties.getProperty("goal.color")));

            Game gamingMap = new Game(mapSettings);
            gamingMap.play();

        } catch (Game.IllegalParametersException e) {
            throw e;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
