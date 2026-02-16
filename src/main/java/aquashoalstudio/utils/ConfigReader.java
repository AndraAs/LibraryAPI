package aquashoalstudio.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is != null) {
                properties.load(is);
                System.out.println("--- Successfully loaded config.properties from resources ---");
            } else {
                System.err.println("CRITICAL: config.properties not found in resources! Using hardcoded fallbacks.");
            }
        } catch (IOException e) {
            System.err.println("CRITICAL: Error reading config.properties: " + e.getMessage());
        }
    }

    /**
     * Finds a value in the config file.
     * If the file is missing or the key is wrong, it returns a safe default.
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);

        if (value == null) {
            // Safety net: ensures the framework doesn't crash if a key is missing
            switch (key) {
                case "base.uri": return "http://216.10.245.166";
                case "endpoint.add": return "/Library/Addbook.php";
                case "endpoint.get": return "/Library/GetBook.php";
                case "endpoint.delete": return "/Library/DeleteBook.php";
                default:
                    System.err.println("Warning: Key [" + key + "] not found in config!");
                    return "";
            }
        }
        return value;
    }
}