import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Settings {

//    static private final String BASE_PATH = "src/main/resources/application-";
//    static private final String PRODUCTION_PATH = "src/main/resources/application-production.properties";
//    static private final String DEV_PATH = "src/main/resources/application-dev.properties";
//    static private final String DEV = "dev";
//    static private final String PRODUCTION = "production";
//    static private final String PROPERTIES = ".properties";
    static private final String FILE_NOT_FOUND = "File not found";

    private String _settings;
    private String _path;

    public Settings(String settings){
        _settings = settings;
//        if (settings.equals(PRODUCTION)){
//            _path = PRODUCTION_PATH;
//        } else if (settings.equals(DEV)){
//            _path = DEV_PATH;
//        } else {
//            _path = BASE_PATH + settings + PROPERTIES;
//        }
        _path = _settings;
    }

    public Properties getSettings(){
        Properties settings = new Properties();
        try {
            settings.load(new FileReader(this._path));
        } catch (IOException e) {
            System.err.println(FILE_NOT_FOUND);
            System.exit(-1);
        }

        return settings;
    }
}
