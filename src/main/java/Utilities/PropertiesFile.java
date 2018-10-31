package Utilities;

import io.restassured.RestAssured;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesFile {


    public static String readPropertiesValue(String parameter)
    {
        String value;
        try {
            Properties prop = new Properties();
            FileInputStream input = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");

            // load a properties file
            prop.load(input);

            value = prop.getProperty(parameter);

            return value;

        } catch (Exception ex)
        {
            return null;
        }
    }
}
