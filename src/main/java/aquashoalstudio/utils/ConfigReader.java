package aquashoalstudio.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();

    // This static block runs once when the class is first loaded
    static {
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            // This prints to your console if the file isn't in the project root
            System.err.println("CRITICAL: config.properties not found! Using hardcoded fallbacks.");
        }
    }

    /**
     * Finds a value in the config file.
     * If the file is missing or the key is wrong, it returns a safe default.
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);

        if (value == null) {
            // Safety net: prevents "Base URI cannot be null" error
            switch (key) {
                case "base.uri": return "http://216.10.245.166";
                case "endpoint.add": return "/Library/Addbook.php";
                case "endpoint.get": return "/Library/GetBook.php";
                case "endpoint.delete": return "/Library/DeleteBook.php";
                default:
                    System.err.println("Warning: Key [" + key + "] not found!");
                    return "";
            }
        }
        return value;
    }
}