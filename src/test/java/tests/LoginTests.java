package tests;

import data.CommonStrings;
import data.Groups;
import data.Time;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryItemPage;
import pages.InventoryPage;
import pages.LoginPage;
import utils.*;

// This test class is now deprecated since we are using "one test case -> one test class -> one test method" approach
@Deprecated
//@Test(groups = {Groups.REGRESSION, Groups.LOGIN})
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

    //@Test(groups = {Groups.SANITY})
    public void testSuccessfulLogin() {

        final String sTestName = "testSuccessfulLogin";
        LoggerUtils.log.info("[SETUP TEST] " + sTestName);

        WebDriver driver = WebDriverUtils.setUpDriver();
        boolean bSuccess = false;

        try {

            LoggerUtils.log.info("[START TEST] " + sTestName);

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
            bSuccess = true;

        } finally {
            LoggerUtils.log.info("[END TEST] " + sTestName);
            if(!bSuccess) {
                ScreenShotUtils.takeScreenShot(driver, sTestName);
            }
            WebDriverUtils.quitDriver(driver);
        }
    }

    //@Test
    public void testUnsuccessfulLoginWrongPassword() {

        final String sTestName = "testUnsuccessfulLoginWrongPassword";
        LoggerUtils.log.info("[SETUP TEST] " + sTestName);

        WebDriver driver = WebDriverUtils.setUpDriver();
        boolean bSuccess = false;

        try{

            LoggerUtils.log.info("[START TEST] " + sTestName);

            String sUsername = PropertiesUtils.getUsername();
            String sPassword = "wrong_password";

            String sExpectedLoginErrorMessage = CommonStrings.getLoginErrorMessageWrongCredentials();

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
            bSuccess = true;

        } finally {
            LoggerUtils.log.info("[END TEST] " + sTestName);
            if(!bSuccess) {
                ScreenShotUtils.takeScreenShot(driver, sTestName);
            }
            WebDriverUtils.quitDriver(driver);
        }
    }

    //@Test
    public void testVerifyInventoryItem() {

        final String sTestName = "testVerifyInventoryItem";
        LoggerUtils.log.info("[SETUP TEST] " + sTestName);

        WebDriver driver = WebDriverUtils.setUpDriver();
        boolean bSuccess = false;

        try{

            LoggerUtils.log.info("[START TEST] " + sTestName);

            String sUsername = PropertiesUtils.getUsername();
            String sPassword = PropertiesUtils.getPassword();

            String sInventoryItemName = "Sauce Labs Onesie";
            String sExpectedInventoryItemPrice = "$7.99";

            LoginPage loginPage = new LoginPage(driver).open();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            loginPage.typeUsername(sUsername);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            loginPage.typePassword(sPassword);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            InventoryPage inventoryPage = loginPage.clickLoginButton();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            String sInventoryItemPrice = inventoryPage.getInventoryItemPrice(sInventoryItemName);
            Assert.assertEquals(sInventoryItemPrice, sExpectedInventoryItemPrice, "Wrong Inventory Item Price");

            InventoryItemPage inventoryItemPage = inventoryPage.clickInventoryItemName(sInventoryItemName);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);
            bSuccess = true;

        } finally {
            LoggerUtils.log.info("[END TEST] " + sTestName);
            if(!bSuccess) {
                ScreenShotUtils.takeScreenShot(driver, sTestName);
            }
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
