package pages;

import data.PageUrlPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utils.LoggerUtils;
import utils.PropertiesUtils;

import java.util.List;

public class InventoryPage extends BasePageClass {

    private final String INVENTORY_PAGE_URL = PropertiesUtils.getBaseUrl() + PageUrlPaths.INVENTORY_PAGE;

    //private final By inventoryPageTitleLocator = By.xpath("//div[@id='header_container']//span[@class='title']");

    @FindBy(xpath = "//div[@id='header_container']//span[@class='title']")
    private WebElement inventoryPageTitle;

    @FindBy(xpath = "//div[@id='inventory_container']/div[@class='inventory_list']")
    private WebElement inventoryList;

    @FindBy(xpath = "//div[@id='inventory_container']/div[@class='inventory_list']/div[@class='inventory_item']")
    private List<WebElement> inventoryItems;

    private String createXpathForInventoryItem(String sItemName) {
        return ".//div[@class='inventory_item_name ' and text()='" + sItemName + "']/ancestor::div[@class='inventory_item']";
    }

    private String createXpathForInventoryItemPrice(String sItemName) {
        return createXpathForInventoryItem(sItemName) + "//div[@class='inventory_item_price']";
    }

    private String createXpathForInventoryItemName(String sItemName) {
        return createXpathForInventoryItem(sItemName) + "//div[@class='inventory_item_name ']";
    }

    public InventoryPage(WebDriver driver) {
        super(driver);
        LoggerUtils.log.debug("InventoryPage()");
        this.driver = driver;
    }

    public InventoryPage open() {
        return open(true);
    }

    public InventoryPage open(boolean bVerify) {
        LoggerUtils.log.debug("open(" + INVENTORY_PAGE_URL + ")");
        openUrl(INVENTORY_PAGE_URL);
        if (bVerify) {
            verifyInventoryPage();
        }
        return this;
    }

    public InventoryPage verifyInventoryPage() {
        LoggerUtils.log.debug("verifyInventoryPage()");
        waitForUrlChange(INVENTORY_PAGE_URL, 1);
        return this;
    }

    public String getInventoryPageTitle() {
        LoggerUtils.log.debug("getInventoryPageTitle()");
        return getTextFromWebElement(inventoryPageTitle);
    }

    /*
     * This method iterates through all web elements and search for web element with specified text (time and resource consuming)
     * This is time and resource consuming. It is better to use complex, parametrized locators
     *
    public String getInventoryItemPrice(String sItemName) {
        for(int i = 0; i < inventoryItems.size(); i++) {
            WebElement inventoryItemName = getNestedWebElement(inventoryItems.get(i), By.xpath(".//div[@class='inventory_item_name ']"));
            if(inventoryItemName.getText().equals(sItemName)) {
                WebElement inventoryItemPrice = getNestedWebElement(inventoryItems.get(i), By.xpath(".//div[@class='inventory_item_price']"));
                return inventoryItemPrice.getText();
            }
        }
        Assert.fail("Inventory Item '" + sItemName + "' doesn't exist in Inventory Items List!");
        return null;
    }
    */

    /*
     * This method iterates through all web elements and search for web element with specified text
     * This is time and resource consuming. It is better to use complex, parametrized locators
     *
    public InventoryItemPage clickInventoryItemTitle(String sItemName) {
        for (WebElement inventoryItem : inventoryItems) {
            WebElement inventoryItemName = getNestedWebElement(inventoryItem, By.xpath(".//div[@class='inventory_item_name ']"));
            if (inventoryItemName.getText().equals(sItemName)) {
                clickOnWebElement(inventoryItemName);
                InventoryItemPage inventoryItemPage = new InventoryItemPage(driver);
                return inventoryItemPage.verifyInventoryItemPage();
            }
        }
        Assert.fail("Inventory Item '" + sItemName + "' doesn't exist in Inventory Items List!");
        return null;
    }
    */

    public boolean isInventoryItemPresentInInventoryList(String sItemName) {
        LoggerUtils.log.debug("isInventoryItemPresentInInventoryList(" + sItemName + ")");
        String xPath = createXpathForInventoryItem(sItemName);
        return isWebElementDisplayed(By.xpath(xPath));
    }

    public String getInventoryItemPrice(String sItemName) {
        LoggerUtils.log.debug("getInventoryItemPrice(" + sItemName + ")");
        Assert.assertTrue(isInventoryItemPresentInInventoryList(sItemName), "Inventory Item '" + sItemName + "'is NOT present in Inventory List!");
        String xPath = createXpathForInventoryItemPrice(sItemName);
        WebElement inventoryItemPrice = getWebElement(By.xpath(xPath));
        return getTextFromWebElement(inventoryItemPrice);
    }

    public InventoryItemPage clickInventoryItemName(String sItemName) {
        LoggerUtils.log.debug("clickInventoryItemName(" + sItemName + ")");
        Assert.assertTrue(isInventoryItemPresentInInventoryList(sItemName), "Inventory Item '" + sItemName + "'is NOT present in Inventory List!");
        String xPath = createXpathForInventoryItemName(sItemName);
        WebElement inventoryItemName = getWebElement(By.xpath(xPath));
        clickOnWebElement(inventoryItemName);
        InventoryItemPage inventoryItemPage = new InventoryItemPage(driver);
        return inventoryItemPage.verifyInventoryItemPage();
    }

}
