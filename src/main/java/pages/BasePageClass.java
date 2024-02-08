package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LoggerUtils;

import java.time.Duration;

public abstract class BasePageClass {

    protected WebDriver driver;

    protected BasePageClass(WebDriver driver) {
        this.driver = driver;
    }

    protected void openUrl(String url) {
        LoggerUtils.log.trace("openUrl(" + url + ")");
        driver.get(url);
    }

    protected String getCurrentUrl() {
        LoggerUtils.log.trace("getCurrentUrl()");
        return driver.getCurrentUrl();
    }

    private WebDriverWait getWebDriverWait(int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    protected WebElement getWebElement(By locator) {
        LoggerUtils.log.trace("getWebElement(" + locator + ")");
        return driver.findElement(locator);
    }

    protected WebElement getWebElement(By locator, int timeout) {
        LoggerUtils.log.trace("getWebElement(" + locator + ", " + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement waitForWebElementToBeVisible(By locator, int timeout) {
        LoggerUtils.log.trace("waitForWebElementToBeVisible(" + locator + ", " + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForWebElementToBeVisible(WebElement element, int timeout) {
        LoggerUtils.log.trace("waitForWebElementToBeVisible(" + element + ", " + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForWebElementToBeClickable(By locator, int timeout) {
        LoggerUtils.log.trace("waitForWebElementToBeClickable(" + locator + ", " + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitForWebElementToBeClickable(WebElement element, int timeout) {
        LoggerUtils.log.trace("waitForWebElementToBeClickable(" + element + ", " + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected boolean waitForWebElementToBeSelected(By locator, int timeout) {
        LoggerUtils.log.trace("waitForWebElementToBeSelected(" + locator + ", " + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.elementToBeSelected(locator));
    }

    protected boolean waitForWebElementToBeSelected(WebElement element, int timeout) {
        LoggerUtils.log.trace("waitForWebElementToBeSelected(" + element + ", " + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.elementToBeSelected(element));
    }

    protected void waitForUrlChange(String url, int timeout) {
        LoggerUtils.log.trace("waitForUrlChange(" + url + ", " + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        wait.until(ExpectedConditions.urlContains(url));
    }

    protected void waitForUrlChangeToExactUrl(String url, int timeout) {
        LoggerUtils.log.trace("waitForUrlChange(" + url + ", " + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        wait.until(ExpectedConditions.urlToBe(url));
    }

    protected boolean isWebElementDisplayed(By locator) {
        try {
            WebElement element = getWebElement(locator);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isWebElementDisplayed(WebElement element) {
        LoggerUtils.log.trace("isWebElementDisplayed(" + element + ")");
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isWebElementEnabled(WebElement element) {
        LoggerUtils.log.trace("isWebElementEnabled(" + element + ")");
        return element.isEnabled();
    }

    protected boolean isWebElementEnabled(WebElement element, int timeout) {
        LoggerUtils.log.trace("isWebElementEnabled(" + element + ")");
        try {
            element = waitForWebElementToBeClickable(element, timeout);
            if (element == null) {
                return false;
            } else {
                return element.isEnabled();
            }
        } catch (Exception e) {
            return false;
        }
    }

    protected void clickOnWebElement(WebElement element) {
        LoggerUtils.log.trace("clickOnWebElement(" + element + ")");
        element.click();
    }

    protected void clickOnWebElement(WebElement element, int timeout) {
        LoggerUtils.log.trace("clickOnWebElement(" + element + ", " + timeout + ")");
        WebElement webElement = waitForWebElementToBeClickable(element, timeout);
        webElement.click();
    }

    protected void typeTextToWebElement(WebElement element, String text) {
        LoggerUtils.log.trace("typeTextToWebElement(" + element + ", " + text + ")");
        element.sendKeys(text);
    }

    protected void clearAndTypeTextToWebElement(WebElement element, String text) {
        LoggerUtils.log.trace("clearAndTypeTextToWebElement(" + element + ", " + text + ")");
        element.clear();
        element.sendKeys(text);
    }

    protected String getTextFromWebElement(WebElement element) {
        LoggerUtils.log.trace("getTextFromWebElement(" + element + ")");
        return element.getText();
    }

    private String getAttributeFromWebElement(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    protected String getValueFromWebElement(WebElement element) {
        LoggerUtils.log.trace("getValueFromWebElement(" + element + ")");
        return getAttributeFromWebElement(element, "value");
    }

    protected String getPlaceholderFromWebElement(WebElement element) {
        LoggerUtils.log.trace("getPlaceholderFromWebElement(" + element + ")");
        return getAttributeFromWebElement(element, "placeholder");
    }


}
