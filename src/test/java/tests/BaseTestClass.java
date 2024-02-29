package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import utils.LoggerUtils;
import utils.ScreenShotUtils;
import utils.WebDriverUtils;

public abstract class BaseTestClass {

    protected WebDriver setUp(ITestContext testContext) {
        return WebDriverUtils.setUpDriver();
    }

    protected void tearDown(WebDriver driver, ITestResult testResult) {
        String sTestName = testResult.getTestClass().getName();
        try {
            if (testResult.getStatus() == ITestResult.FAILURE) {
                ScreenShotUtils.takeScreenShot(driver, sTestName);
            }
        } catch (AssertionError | Exception e) {
            LoggerUtils.log.error("Exception occurred in tearDown(" + sTestName + "). Message: " + e.getMessage());
        } finally {
            WebDriverUtils.quitDriver(driver);
        }
    }
}
