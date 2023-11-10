package org.example;

import java.io.IOException;
import java.util.Properties;

public abstract class PropertyReader {
    private static Properties properties;

    private static void readProperties() {
        properties = new Properties();
        try {
            properties.load(PropertyReader.class.getClassLoader().
                    getResourceAsStream("org/example/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        if (properties == null) {
            readProperties();
        }
        return properties.getProperty(key);
    }

    public static String getBrowserProperty( ) {
        return getProperty("Browser");
    }

    public static String getAdminNameProperty( ) {
        return getProperty("AdminUserName");
    }

    public static String getAdminPasswordProperty( ) {
        return getProperty("AdminUserPassword");
    }
}
