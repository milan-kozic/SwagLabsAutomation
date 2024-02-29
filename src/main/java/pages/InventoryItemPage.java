package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.WebDriver;
import utils.LoggerUtils;
import utils.PropertiesUtils;

public class InventoryItemPage extends BasePageClass {

    private final String INVENTORY_ITEM_PAGE_URL = PropertiesUtils.getBaseUrl() + PageUrlPaths.INVENTORY_ITEM_PAGE;

    public InventoryItemPage(WebDriver driver) {
        super(driver);
        LoggerUtils.log.debug("InventoryItemPage()");
        this.driver = driver;
    }

    public InventoryItemPage open() {
        return open(true);
    }

    public InventoryItemPage open(boolean bVerify) {
        LoggerUtils.log.debug("open(" + INVENTORY_ITEM_PAGE_URL + ")");
        openUrl(INVENTORY_ITEM_PAGE_URL);
        if(bVerify) {
            verifyInventoryItemPage();
        }
        return this;
    }

    public InventoryItemPage verifyInventoryItemPage() {
        LoggerUtils.log.debug("verifyInventoryItemPage()");
        waitForUrlChange(INVENTORY_ITEM_PAGE_URL, Time.TIME_SHORTEST);
        return this;
    }
}
