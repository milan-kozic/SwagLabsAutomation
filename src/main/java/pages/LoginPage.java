package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.LoggerUtils;
import utils.PropertiesUtils;

public class LoginPage extends BasePageClass {

    private final String LOGIN_PAGE_URL = PropertiesUtils.getBaseUrl() + PageUrlPaths.LOGIN_PAGE;

    private final By usernameTextFieldLocator = By.id("user-name");
    private final By passwordTextFieldLocator = By.id("password");
    private final By loginButtonLocator = By.id("login-button");
    private final By loginErrorMessageLocator = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
        LoggerUtils.log.debug("LoginPage()");
        this.driver = driver;
    }

    public LoginPage open(boolean bVerify) {
        LoggerUtils.log.debug("open(" + LOGIN_PAGE_URL + ")");
        openUrl(LOGIN_PAGE_URL);
        if(bVerify) {
            verifyLoginPage();
        }
        return this;
    }

    public LoginPage open() {
        return open(true);
    }

    public boolean isUsernameTextFieldDisplayed() {
        LoggerUtils.log.debug("isUsernameTextFieldDisplayed()");
        return isWebElementDisplayed(usernameTextFieldLocator);
    }

    public boolean isUsernameTextFieldEnabled() {
        LoggerUtils.log.debug("isUsernameTextFieldEnabled()");
        Assert.assertTrue(isUsernameTextFieldDisplayed(), "Username TextField is NOT displayed on Login Page!");
        WebElement usernameTextField = getWebElement(usernameTextFieldLocator);
        return isWebElementEnabled(usernameTextField);
    }

    public String getUsername() {
        LoggerUtils.log.debug("getUsername()");
        Assert.assertTrue(isUsernameTextFieldDisplayed(), "Username TextField is NOT displayed on Login Page!");
        WebElement usernameTextField = getWebElement(usernameTextFieldLocator);
        return getValueFromWebElement(usernameTextField);
    }

    public String getUsernamePlaceholder() {
        LoggerUtils.log.debug("getUsernamePlaceholder()");
        Assert.assertTrue(isUsernameTextFieldDisplayed(), "Username TextField is NOT displayed on Login Page!");
        WebElement usernameTextField = getWebElement(usernameTextFieldLocator);
        return getPlaceholderFromWebElement(usernameTextField);
    }

    public LoginPage typeUsername(String sUsername) {
        LoggerUtils.log.debug("typeUsername(" + sUsername + ")");
        Assert.assertTrue(isUsernameTextFieldEnabled(), "Username TextField is NOT enabled on Login Page!");
        WebElement usernameTextField = getWebElement(usernameTextFieldLocator);
        clearAndTypeTextToWebElement(usernameTextField, sUsername);
        return this;
    }

    public boolean isPasswordTextFieldDisplayed() {
        LoggerUtils.log.debug("isPasswordTextFieldDisplayed()");
        return isWebElementDisplayed(passwordTextFieldLocator);
    }

    public boolean isPasswordTextFieldEnabled() {
        LoggerUtils.log.debug("isPasswordTextFieldEnabled()");
        Assert.assertTrue(isPasswordTextFieldDisplayed(), "Password TextField is NOT displayed on Login Page!");
        WebElement passwordTextField = getWebElement(passwordTextFieldLocator);
        return isWebElementEnabled(passwordTextField);
    }

    public String getPassword() {
        LoggerUtils.log.debug("getPassword()");
        Assert.assertTrue(isPasswordTextFieldDisplayed(), "Password TextField is NOT displayed on Login Page!");
        WebElement passwordTextField = getWebElement(passwordTextFieldLocator);
        return getValueFromWebElement(passwordTextField);
    }

    public String getPasswordPlaceholder() {
        LoggerUtils.log.debug("getPasswordPlaceholder()");
        Assert.assertTrue(isPasswordTextFieldDisplayed(), "Password TextField is NOT displayed on Login Page!");
        WebElement passwordTextField = getWebElement(passwordTextFieldLocator);
        return getPlaceholderFromWebElement(passwordTextField);
    }

    public LoginPage typePassword(String sPassword) {
        LoggerUtils.log.debug("typePassword(" + sPassword + ")");
        Assert.assertTrue(isPasswordTextFieldEnabled(), "Password TextField is NOT enabled on Login Page!");
        WebElement passwordTextField = getWebElement(passwordTextFieldLocator);
        clearAndTypeTextToWebElement(passwordTextField, sPassword);
        return this;
    }

    public boolean isLoginButtonDisplayed() {
        LoggerUtils.log.debug("isLoginButtonDisplayed()");
        return isWebElementDisplayed(loginButtonLocator);
    }

    public boolean isLoginButtonEnabled() {
        LoggerUtils.log.debug("isLoginButtonEnabled()");
        Assert.assertTrue(isLoginButtonDisplayed(), "Login Button is NOT displayed on Login Page!");
        WebElement loginButton = getWebElement(loginButtonLocator);
        return isWebElementEnabled(loginButton, Time.TIME_SHORTEST);
    }

    public String getLoginButtonTitle() {
        LoggerUtils.log.debug("getLoginButtonTitle()");
        Assert.assertTrue(isLoginButtonDisplayed(), "Login Button is NOT displayed on Login Page!");
        WebElement loginButton = getWebElement(loginButtonLocator);
        return getValueFromWebElement(loginButton);
    }

    private void clickLoginButtonNoVerification() {
        Assert.assertTrue(isLoginButtonEnabled(), "Login Button is NOT enabled on Login Page!");
        WebElement loginButton = getWebElement(loginButtonLocator);
        clickOnWebElement(loginButton);
    }

    public InventoryPage clickLoginButton() {
        LoggerUtils.log.debug("clickLoginButton()");
        clickLoginButtonNoVerification();
        InventoryPage inventoryPage = new InventoryPage(driver);
        return inventoryPage.verifyInventoryPage();
    }

    public LoginPage clickLoginButtonNoProgress() {
        LoggerUtils.log.debug("clickLoginButtonNoProgress()");
        Assert.assertTrue(isLoginButtonEnabled(), "Login Button is NOT enabled on Login Page!");
        clickLoginButtonNoVerification();
        LoginPage loginPage = new LoginPage(driver);
        return loginPage.verifyLoginPage();
    }

    public boolean isLoginErrorMessageDisplayed() {
        LoggerUtils.log.debug("isLoginErrorMessageDisplayed()");
        return isWebElementDisplayed(loginErrorMessageLocator);
    }

    public String getLoginErrorMessage() {
        LoggerUtils.log.debug("getLoginErrorMessage()");
        Assert.assertTrue(isLoginErrorMessageDisplayed(), "Login Error Message is NOT displayed on Login Page!");
        WebElement loginErrorMessage = getWebElement(loginErrorMessageLocator);
        return getTextFromWebElement(loginErrorMessage);
    }

    public LoginPage verifyLoginPage() {
        LoggerUtils.log.debug("verifyLoginPage()");
        waitForUrlChangeToExactUrl(LOGIN_PAGE_URL, Time.TIME_SHORTER);
        return this;
    }
}
