package utils;

import org.testng.Assert;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

public class PropertiesUtils {

    private static final String sPropertiesPath = "test.properties";
    //private static final String sPropertiesPath = System.getProperty("user.dir") + "\\src\\main\\resources\\test.properties";
    private static final Properties properties = loadPropertiesFile();

    public static Properties loadPropertiesFile(String sFilePath) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(sFilePath);
            properties.load(inputStream);
        } catch (Exception e) {
            Assert.fail("Cannot load " + sFilePath + " file! Message: " + e.getMessage());
        }
        return properties;
    }

    public static Properties loadPropertiesFile() {
        return loadPropertiesFile(sPropertiesPath);
    }

    private static String getProperty(String sProperty) {
        String sValue = properties.getProperty(sProperty);
        Assert.assertNotNull(sValue, "Cannot find property '" + sProperty + "' in " + sPropertiesPath + " file!");
        return sValue;
    }

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static String getLocale() {
        return getProperty("locale");
    }

    public static String getDriversFolder() {
        return getProperty("driversFolder");
    }

    public static String getEnvironment() {
        return getProperty("environment");
    }

    private static String getTestBaseUrl() {
        return getProperty("testBaseUrl");
    }

    private static String getProdBaseUrl() {
        return getProperty("prodBaseUrl");
    }

    public static String getBaseUrl() {
        String sEnvironment = getEnvironment();
        String sBaseUrl = null;
        switch (sEnvironment) {
            case "test" : {
                sBaseUrl = getTestBaseUrl();
                break;
            }
            case "prod" : {
                sBaseUrl = getProdBaseUrl();
                break;
            }
            default : {
                Assert.fail("Cannot get BaseUrl! Environment '" + sEnvironment + "' is not recognized!");
            }
        }
        return sBaseUrl;
    }

    public static String getUsername() {
        return getProperty("username");
    }

    public static String getPassword() {
        return getProperty("password");
    }



}
