package com.coedotzmagic.qatools;

import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import com.coedotzmagic.qatools.util.BrowserHelper;
import com.coedotzmagic.qatools.util.DriverHelper;
import org.openqa.selenium.WebDriver;

public class QATools {

    public static void CoedotzMagic() {
        System.out.println(TellMeWhy.getIdentity() + "Hi, Everyones can be Magician! but Silence is golden.");
        BrowserHelper.OpenBrowser(TellMeWhy.WEBSITE_COEDOTZMAGIC);
    }

    public static void SetWebDriver(WebDriver driver) {
        DriverHelper.SetWebDriver(driver);
    }
}
