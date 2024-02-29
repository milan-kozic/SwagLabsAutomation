package data;

import org.testng.Assert;
import utils.PropertiesUtils;

import java.util.Properties;

public final class CommonStrings {

    public static final String sLocaleFilePath = "locale_" + PropertiesUtils.getLocale() + ".loc";
    public static Properties locale = PropertiesUtils.loadPropertiesFile(sLocaleFilePath);

    private static String getLocalizedString(String sProperty) {
        String sValue =  locale.getProperty(sProperty);
        Assert.assertNotNull(sValue, "String " + sProperty + " doesn't exist in localization file " + sLocaleFilePath + "!");
        return sValue;
    }

    public static String getInventoryPageTitle() {
        return getLocalizedString("INVENTORY_PAGE_TITLE");
    }

    public static String getLoginErrorMessageWrongCredentials() {
        return getLocalizedString("LOGIN_ERROR_MESSAGE_WRONG_CREDENTIALS");
    }

//    public static String getLoginErrorMessageWrongCredentials() {
//        String sLocale = PropertiesUtils.getLocale();
//        String sMessage = null;
//        switch (sLocale) {
//            case "en" : {
//                sMessage = LOGIN_ERROR_MESSAGE_WRONG_CREDENTIALS_ENG;
//                break;
//            }
//            case "yu" : {
//                sMessage = LOGIN_ERROR_MESSAGE_WRONG_CREDENTIALS_YUG;
//                break;
//            }
//            default : {
//                Assert.fail("Unsupported Language!");
//            }
//        }
//        return sMessage;
//    }

}
