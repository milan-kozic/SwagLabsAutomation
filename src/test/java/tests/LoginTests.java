package tests;

import data.CommonStrings;
import data.Groups;
import data.Time;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.DateTimeUtils;
import utils.LoggerUtils;
import utils.PropertiesUtils;
import utils.WebDriverUtils;

@Test(groups = {Groups.REGRESSION, Groups.LOGIN})
public class LoginTests {

//    WebDriver driver = null;
//
//    @BeforeMethod
//    public void setUp() {
//        driver = new ChromeDriver();
//        DateTimeUtils.wait(1);
//        driver.manage().window().maximize();
//        DateTimeUtils.wait(3);
//    }

    @Test(groups = {Groups.SANITY})
    public void testSuccessfulLogin() {

        LoggerUtils.log.info("[SETUP TEST] testSuccessfulLogin()");
        WebDriver driver = WebDriverUtils.setUpDriver();

        try {

            LoggerUtils.log.info("[START TEST] testSuccessfulLogin()");

            String sUsername = PropertiesUtils.getUsername();
            String sPassword = PropertiesUtils.getPassword();

            String sExpectedInventoryPageTitle = CommonStrings.getInventoryPageTitle();

            LoginPage loginPage = new LoginPage(driver).open();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            loginPage.typeUsername(sUsername);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            loginPage.typePassword(sPassword);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            InventoryPage inventoryPage = loginPage.clickLoginButton();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            String sActualInventoryPageTitle = inventoryPage.getInventoryPageTitle();
            Assert.assertEquals(sActualInventoryPageTitle, sExpectedInventoryPageTitle, "Wrong Page Title!");

        } finally {
            LoggerUtils.log.info("[END TEST] testSuccessfulLogin()");
            WebDriverUtils.quitDriver(driver);
        }
    }

    @Test
    public void testUnsuccessfulLoginWrongPassword() {

        LoggerUtils.log.info("[SETUP TEST] testUnsuccessfulLoginWrongPassword()");
        WebDriver driver = WebDriverUtils.setUpDriver();

        try{

            LoggerUtils.log.info("[START TEST] testUnsuccessfulLoginWrongPassword()");

            String sUsername = PropertiesUtils.getUsername();
            String sPassword = "wrong_password";

            String sExpectedLoginErrorMessage = CommonStrings.getLoginErrorMessageWrongCredentials();

            LoginPage loginPage = new LoginPage(driver).open();

            loginPage.typeUsername(sUsername);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            loginPage.typePassword(sPassword);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            Assert.assertFalse(loginPage.isLoginErrorMessageDisplayed(), "Login ErrorMessage should NOT be displayed!");

            loginPage = loginPage.clickLoginButtonNoProgress();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            String sActualErrorMessage = loginPage.getLoginErrorMessage();
            Assert.assertEquals(sActualErrorMessage, sExpectedLoginErrorMessage, "Wrong Error Message");

        } finally {
            LoggerUtils.log.info("[END TEST] testUnsuccessfulLoginWrongPassword()");
            WebDriverUtils.quitDriver(driver);
        }
    }

//    @AfterMethod
//    public void tearDown() {
//        if(driver != null) {
//            driver.quit();
//        }
//    }
}
