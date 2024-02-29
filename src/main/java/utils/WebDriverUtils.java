package utils;

import data.Time;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.time.Duration;

public class WebDriverUtils extends LoggerUtils {

    public static WebDriver setUpDriver() {

        String sBrowser = PropertiesUtils.getBrowser();

        // String sDriverFolder = PropertiesUtils.getDriversFolder();
        // String sPathDriverChrome = sDriverFolder + "chromedriver.exe";
        // String sPathDriverFirefox = sDriverFolder + "geckodriver.exe";
        // String sPathDriverEdge = sDriverFolder + "msedgedriver.exe";

        WebDriver driver = null;

        log.info("setUpDriver(" + sBrowser + ")");

        switch(sBrowser) {
            case "chrome" : {
                // System.setProperty("webdriver.chrome.driver", sPathDriverChrome);
                driver = new ChromeDriver();
                break;
            }
            case "firefox" : {
                // System.setProperty("webdriver.gecko.driver", sPathDriverFirefox);
                driver = new FirefoxDriver();
                break;
            }
            case "edge" : {
                // System.setProperty("webdriver.edge.driver", sPathDriverEdge);
                driver = new EdgeDriver();
                break;
            }
            default : {
                Assert.fail("Cannot create driver! Browser '" + sBrowser + "' is not supported!");
            }
        }

        // Setup Implicit Waits
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Time.IMPLICIT_TIMEOUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Time.PAGE_LOAD_TIMEOUT));

        // Maximize Browser
        driver.manage().window().maximize();
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        return driver;
    }

    public static void quitDriver(WebDriver driver) {

        log.info("quitDriver()");
        if (driver != null) {
            driver.quit();
        }
    }

    public static void setImplicitWait(WebDriver driver, int timeout) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }

}
