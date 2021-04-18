package ilia.nemankov;

import java.io.IOException;
import java.util.Properties;

public class ConfigurationProvider {

    private String filePath;
    private Properties properties = new Properties();
    private static ConfigurationProvider instance;

    private ConfigurationProvider(String filePath) {
        try {
            this.filePath = filePath;
            properties.load(ConfigurationProvider.class.getClassLoader().getResourceAsStream(filePath));
        } catch (IOException e) {
            System.out.println("Could not load configuration");
            e.printStackTrace();
        }
    }

    public static ConfigurationProvider getInstance() {
        if (instance == null) {
            instance = new ConfigurationProvider("configuration.properties");
        }
        return instance;
    }

    public String getFilePath() {
        return filePath;
    }

    public Properties getProperties() {
        return properties;
    }
}
