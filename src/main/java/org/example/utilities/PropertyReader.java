package org.example.utilities;

import java.io.*;
import java.util.Properties;

public abstract class PropertyReader {
    private static Properties properties;

    private static void readProperties() throws FileNotFoundException {
        properties = new Properties();
        File initialFile = new File("src/resources/config.properties");
        InputStream inputStream = new FileInputStream(initialFile);
        try {
            properties.load(inputStream);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) throws FileNotFoundException {
        if (properties == null) {
            readProperties();
        }
        return properties.getProperty(key);
    }

    public static String getBrowserProperty( ) throws FileNotFoundException {
        return getProperty("Browser");
    }

    public static int getTimeoutProperty( ) throws FileNotFoundException {
        return Integer.parseInt(getProperty("Timeout"));
    }

    public static String getAdminFirstNameProperty( ) throws FileNotFoundException {
        return getProperty("AdminFirstName");
    }

    public static String getAdminLastNameProperty( ) throws FileNotFoundException {
        return getProperty("AdminLastName");
    }
    public static String getAdminEmailProperty( ) throws FileNotFoundException {
        return getProperty("AdminEmail");
    }

    public static String getAdminZipcodeProperty( ) throws FileNotFoundException {
        return getProperty("AdminUserZipcode");
    }

    public static String getAdminPasswordProperty( ) throws FileNotFoundException {
        return getProperty("AdminUserPassword");
    }
}
