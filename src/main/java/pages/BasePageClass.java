package pages;

import data.Time;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LoggerUtils;
import utils.WebDriverUtils;

import java.time.Duration;
import java.util.List;

public abstract class BasePageClass {

    protected WebDriver driver;

    protected BasePageClass(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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

    protected List<WebElement> getWebElements(By locator) {
        LoggerUtils.log.trace("getWebElements(" + locator + ")");
        return driver.findElements(locator);
    }

    protected WebElement getWebElement(By locator, int timeout) {
        LoggerUtils.log.trace("getWebElement(" + locator + ", " + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement getNestedWebElement(WebElement parentWebElement, By locator) {
        LoggerUtils.log.trace("getNestedWebElement(" + parentWebElement + ", " + locator + ")");
        return parentWebElement.findElement(locator);
    }

    protected List<WebElement> getNestedWebElements(WebElement parentWebElement, By locator) {
        LoggerUtils.log.trace("getNestedWebElements(" + parentWebElement + ", " + locator + ")");
        return parentWebElement.findElements(locator);
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

    protected boolean isWebElementDisplayed(By locator, int timeout) {
        try {
            WebElement element = getWebElement(locator, timeout);
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

    protected boolean isWebElementDisplayed(WebElement element, int timeout) {
        LoggerUtils.log.trace("isWebElementDisplayed(" + element + ", " + timeout + ")");
        WebDriverUtils.setImplicitWait(driver, timeout);
        boolean isDisplayed = isWebElementDisplayed(element);
        WebDriverUtils.setImplicitWait(driver, Time.IMPLICIT_TIMEOUT);
        return isDisplayed;
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

    protected void clickOnWebElementJS(WebElement element) {
        LoggerUtils.log.trace("clickOnWebElementJS(" + element + ")");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
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

    protected String getAttributeFromWebElement(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    protected void setAttributeToWebElement(WebElement element, String attribute, String value) {
        LoggerUtils.log.trace("setAttributeToWebElement(" + element + ", " + attribute + ", " + value + ")");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('" + attribute + "', '" + value + "')", element);
    }

    protected void removeAttributeFromWebElement(WebElement element, String attribute) {
        LoggerUtils.log.trace("removeAttributeFromWebElement(" + element + ", " + attribute + ")");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('" + attribute + "')", element);
    }

    protected String getValueFromWebElement(WebElement element) {
        LoggerUtils.log.trace("getValueFromWebElement(" + element + ")");
        return getAttributeFromWebElement(element, "value");
    }

    protected String getValueFromWebElementJS(WebElement element) {
        LoggerUtils.log.trace("getValueFromWebElementJS(" + element + ")");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].value", element);
    }

    protected String getPlaceholderFromWebElement(WebElement element) {
        LoggerUtils.log.trace("getPlaceholderFromWebElement(" + element + ")");
        return getAttributeFromWebElement(element, "placeholder");
    }

    protected void selectOptionOnWebElement(WebElement element, String option) {
        LoggerUtils.log.trace("selectOptionOnWebElement(" + element + ", " + option + ")");
        Select options = new Select(element);
        options.selectByVisibleText(option);
    }

    protected boolean isOptionPresentOnWebElement(WebElement element, String option) {
        LoggerUtils.log.trace("isOptionPresentOnWebElement(" + element + ", " + option + ")");
        Select options = new Select(element);
        List<WebElement> listOfOptions = options.getOptions();
        boolean bPresent = false;
        for (WebElement e : listOfOptions) {
            if (getValueFromWebElement(e).equals(option)) {
                bPresent = true;
                break;
            }
        }
        return bPresent;
    }

    protected String getFirstSelectedOptionOnWebElement(WebElement element) {
        LoggerUtils.log.trace("getFirstSelectedOptionOnWebElement(" + element + ")");
        Select options = new Select(element);
        WebElement selectedOption = options.getFirstSelectedOption();
        return selectedOption.getText();
    }
}
