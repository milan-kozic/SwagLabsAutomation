package pages;

import data.PageUrlPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.LoggerUtils;
import utils.PropertiesUtils;

public class InventoryPage extends BasePageClass {

    private final String INVENTORY_PAGE_URL = PropertiesUtils.getBaseUrl() + PageUrlPaths.INVENTORY_PAGE;

    private final By inventoryPageTitleLocator = By.xpath("//div[@id='header_container']//span[@class='title']");

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
        if(bVerify) {
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
        WebElement inventoryPageTitle = getWebElement(inventoryPageTitleLocator);
        return getTextFromWebElement(inventoryPageTitle);
    }
}
