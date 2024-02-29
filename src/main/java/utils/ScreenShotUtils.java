package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;

public class ScreenShotUtils {

    private static final String sScreenShotFolder = System.getProperty("user.dir") + PropertiesUtils.getScreenShotsFolder();

    private static String createScreenShotPath(String sFileName) {
        return sScreenShotFolder + sFileName + System.currentTimeMillis() + ".png";
    }

    public static String takeScreenShot(WebDriver driver, String sTestName) {
        String sPathToFile = createScreenShotPath(sTestName);
        TakesScreenshot screenShot = (TakesScreenshot) driver;
        File srcFile = screenShot.getScreenshotAs(OutputType.FILE);
        File dstFile = new File(sPathToFile);
        try {
            FileUtils.copyFile(srcFile, dstFile);
            LoggerUtils.log.info("Screenshot for test '" + sTestName + "' is saved: " + sPathToFile);
        } catch (IOException e) {
            LoggerUtils.log.warn("Screenshot for test '" + sTestName + "' could not be saved in file '" + sPathToFile + "'. Message: " + e.getMessage());
            return null;
        }
        return sPathToFile;
    }
}
