package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        String environment = System.getProperty("test", "default"); // Default can be dev, test, prod, etc.
        try (InputStream input = new FileInputStream("src/test/resources/" + environment + ".properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error loading properties file: " + environment + ".properties");
        }
    }


    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
