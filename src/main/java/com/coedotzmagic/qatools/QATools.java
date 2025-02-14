package com.coedotzmagic.qatools;

import org.openqa.selenium.WebDriver;
import com.coedotzmagic.qatools.failurehandling.FailureHandlingHelper;
import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import com.coedotzmagic.qatools.util.BrowserHelper;
import com.coedotzmagic.qatools.util.DriverHelper;

public class QATools {

    /**
     * <b>CoedotzMagic()</b>
     * Everyones can be Magician!
     *
     * <br><br>
     *
     * @since 1.0
     */
    public static void CoedotzMagic() {
        System.out.println(TellMeWhy.getIdentity() + "Hi, Everyones can be Magician! but Silence is golden.");
        BrowserHelper.OpenBrowser(TellMeWhy.WEBSITE_COEDOTZMAGIC);
    }

    /**
     * <b>SetWebDriver()</b>
     * Digunakan untuk set WebDriver pada komponen-komponen yang akan dipakai pada QA Tools
     *
     * <br><br>
     *
     * @param driver
     *
     * @since 1.1
     */
    public static void SetWebDriver(WebDriver driver) {
        DriverHelper.SetWebDriver(driver);
    }

    /**
     * <b>SetCloseBrowserWhenError()</b>
     * Digunakan untuk memberikan perilaku jika error maka browser akan keluar
     *
     * <br><br>
     *
     * @param close
     *
     * @since 1.1
     */
    public static void SetCloseBrowserWhenError(boolean close) {
        FailureHandlingHelper.CloseBrowserWhenError(close);
    }
}
