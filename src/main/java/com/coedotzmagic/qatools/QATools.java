package com.coedotzmagic.qatools;

import org.openqa.selenium.WebDriver;
import com.coedotzmagic.qatools.failurehandling.FailureHandlingHelper;
import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import com.coedotzmagic.qatools.util.BrowserHelper;
import com.coedotzmagic.qatools.util.DriverHelper;

public class QATools {

    public static void CoedotzMagic() {
        System.out.println(TellMeWhy.getIdentity() + "Hi, Everyones can be Magician! but Silence is golden.");
        BrowserHelper.OpenBrowser(TellMeWhy.WEBSITE_COEDOTZMAGIC);
    }

    public static void SetWebDriver(WebDriver driver) {
        DriverHelper.SetWebDriver(driver);
    }

    private static void SetCloseBrowserWhenError(boolean close) {
        FailureHandlingHelper.CloseBrowserWhenError(close);
    }
}
