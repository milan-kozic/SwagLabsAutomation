package tests.inventory;

import data.Groups;
import data.Time;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryItemPage;
import pages.InventoryPage;
import pages.LoginPage;
import tests.BaseTestClass;
import utils.DateTimeUtils;
import utils.LoggerUtils;
import utils.PropertiesUtils;

@Test(groups = {Groups.REGRESSION, Groups.SANITY, Groups.INVENTORY})
public class TestVerifyInventoryItem extends BaseTestClass {

    private final String sTestName = this.getClass().getName();

    private WebDriver driver;
    private String sUsername;
    private String sPassword;

    private String sInventoryItemName;
    private String sExpectedInventoryItemPrice;

    @BeforeMethod
    public void setUpTest(ITestContext testContext) {

        LoggerUtils.log.info("[SETUP TEST] " + sTestName);

        driver = setUp(testContext);

        sUsername = PropertiesUtils.getUsername();
        sPassword = PropertiesUtils.getPassword();

        sInventoryItemName = "Sauce Labs Onesie";
        sExpectedInventoryItemPrice = "$7.99";
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

        InventoryPage inventoryPage = loginPage.clickLoginButton();
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        String sInventoryItemPrice = inventoryPage.getInventoryItemPrice(sInventoryItemName);
        Assert.assertEquals(sInventoryItemPrice, sExpectedInventoryItemPrice, "Wrong Inventory Item Price");

        InventoryItemPage inventoryItemPage = inventoryPage.clickInventoryItemName(sInventoryItemName);
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);
    }

    @AfterMethod
    public void tearDownTest(ITestResult testResult) {

        LoggerUtils.log.info("[END TEST] " + sTestName);

        tearDown(driver, testResult);
    }
}
