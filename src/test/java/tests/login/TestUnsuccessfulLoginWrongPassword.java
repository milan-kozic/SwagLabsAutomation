package tests.login;

import data.CommonStrings;
import data.Groups;
import data.Time;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import tests.BaseTestClass;
import utils.DateTimeUtils;
import utils.LoggerUtils;
import utils.PropertiesUtils;

@Test(groups = {Groups.REGRESSION, Groups.LOGIN})
public class TestUnsuccessfulLoginWrongPassword extends BaseTestClass {

    private final String sTestName = this.getClass().getName();
    private WebDriver driver;
    private String sUsername;
    private String sPassword;

    private String sExpectedLoginErrorMessage;


    @BeforeMethod
    public void setUpTest(ITestContext testContext) {

        LoggerUtils.log.info("[SETUP TEST] " + sTestName);

        driver = setUp(testContext);

        sUsername = PropertiesUtils.getUsername();
        sPassword = "wrong_password";

        sExpectedLoginErrorMessage = CommonStrings.getLoginErrorMessageWrongCredentials();
    }

    @Test
    public void test() {

        LoggerUtils.log.info("[START TEST] " + sTestName);

        LoginPage loginPage = new LoginPage(driver).open();
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        loginPage.typeUsername(sUsername);
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        loginPage.typePassword(sPassword);
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        Assert.assertFalse(loginPage.isLoginErrorMessageDisplayed(), "Login ErrorMessage should NOT be displayed!");

        loginPage = loginPage.clickLoginButtonNoProgress();
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        String sActualErrorMessage = loginPage.getLoginErrorMessage();
        Assert.assertEquals(sActualErrorMessage, sExpectedLoginErrorMessage, "Wrong Error Message");
    }

    @AfterMethod
    public void tearDownTest(ITestResult testResult) {

        LoggerUtils.log.info("[END TEST] " + sTestName);

        tearDown(driver, testResult);
    }
}
