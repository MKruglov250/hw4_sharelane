package org.example.utilities;

import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.Properties;

public abstract class PropertyReader {
    private static Properties properties;

    private static void readProperties() {
        properties = new Properties();
        try {
            properties.load(PropertyReader.class.
                    getResourceAsStream("config.properties"));
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

    public static int getTimeoutProperty( ) {
        return Integer.parseInt(getProperty("Timeout"));
    }

    public static String getAdminFirstNameProperty( ) {
        return getProperty("AdminFirstName");
    }

    public static String getAdminLastNameProperty( ) {
        return getProperty("AdminLastName");
    }
    public static String getAdminEmailProperty( ) throws IOException, ParseException {
        return getProperty("AdminEmail");
    }

    public static String getAdminZipcodeProperty( ) {
        return getProperty("AdminUserZipcode");
    }

    public static String getAdminPasswordProperty( ) {
        return getProperty("AdminUserPassword");
    }
}
