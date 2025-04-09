package utils;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    static String value;

    public static String PropertyReader(String filePath, String key) throws FileNotFoundException {
        try (InputStream inputStream = new FileInputStream(filePath)) {

            Properties properties = new Properties();
            properties.load(inputStream);

            value = properties.getProperty(key);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}
