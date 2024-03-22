package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigManager {
    private static final Logger LOGGER = LogManager.getLogger(ConfigManager.class);

    private static final Properties PROPERTIES = new Properties();

    public static String getProperty(String key) {

        try (InputStream inputStream = ConfigManager.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
            return PROPERTIES.getProperty(key);
        } catch (IOException e) {
            LOGGER.error("Application properties can not be loaded!");
            return null;
        }

    }

    public static void setProperties(String key, String newValue) {

        try {
            Map<String, String> map = new HashMap<>();
            String filePath = System.getProperty("user.dir") + "/src/test/resources/application.properties";

            //First we read the application.properties file
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=", 2);

                if (parts.length >= 2) {
                    String currentKey = parts[0].trim();
                    String currentValue = parts[1].trim();
                    map.put(currentKey, currentValue);
                }
            }
            reader.close();

            //Changing value according to parameters
            map.put(key, newValue);

            //Lastly we write all file with new value
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            for (Map.Entry<String, String> entry : map.entrySet()) {
                writer.write(entry.getKey() + " = " + entry.getValue());
                writer.newLine();
            }
            writer.close();

            LOGGER.info("Application properties file has been updated successfully!");

        } catch (IOException e) {
            LOGGER.error("Error occurred while updating file!");

        }


    }
}
