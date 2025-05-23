package com.coedotzmagic.qatools;

import com.coedotzmagic.qatools.failurehandling.KillProcessWebDriver;
import com.coedotzmagic.qatools.util.WebServices;
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
     * @since 1.0
     */
    public static void CoedotzMagic() {
        System.out.println(TellMeWhy.getIdentity() + "Hi, Everyones can be Magician! but Silence is golden.");
        BrowserHelper.OpenBrowser(TellMeWhy.WEBSITE_COEDOTZMAGIC);
    }

    /**
     * <b>SetWebDriver()</b>
     * Used to set WebDriver on components that will be used in QA Tools.
     *
     * @param driver webdriver
     *
     * @since 1.1
     */
    public static void SetWebDriver(WebDriver driver) {
        DriverHelper.SetWebDriver(driver);
    }

    /**
     * <b>SetCloseBrowserWhenError()</b>
     * Used to provide behavior if an error occurs then the browser will exit.
     *
     * @param close If true then the browser will exit automatically if an error occurs, if false then no error occurs.
     *
     * @since 1.1
     */
    public static void SetCloseBrowserWhenError(boolean close) {
        FailureHandlingHelper.CloseBrowserWhenError(close);
    }

    /**
     * <b>SetCallBrowserFromUs()</b>
     * Used to provide browser calling behavior from QA Tools
     *
     * @param isActive if true/yes then it will use a custom browser, if false/no then the opposite
     * @param browser is the choice of browser you want to use (ie, chrome, firefox, safari)
     *
     * @since 1.1
     */
    public static void SetCallBrowserFromUs(boolean isActive, String browser) {
        DriverHelper.UseCallBrowserFromUs(isActive, browser);
    }

    /**
     * <b>KillAllWebDriverProcess()</b>
     * Used to stop the webdriver process that is still running in task manager (windows) / activity monitor (macos/unix)
     *
     * @since 1.1
     */
    public static void KillAllWebDriverProcess() {
        new KillProcessWebDriver();
    }

    /**
     * <b>SetTimeoutWait()</b>
     * Used to set timeout when waiting time for get the element visible
     *
     * @param seconds desired time (in seconds) for automation timeout
     *
     * @since 1.2
     */
    public static void SetTimeoutWait(int seconds) {
        FailureHandlingHelper.SetTimeoutWait(seconds);
    }

    /**
     * <b>ContinueOnError()</b>
     * used to skip if there is a problem with the test runner, and the automation continues to run until completion
     *
     * <br><br>
     *
     * @param skipErr If true then when there is an error the automation will continue running, if false then it will not.
     *
     * @since 1.3
     */
    public static void ContinueOnError(boolean skipErr) {
        FailureHandlingHelper.ContinueOnError(skipErr);
    }

    /**
     * <b>ShowMsgError()</b>
     * used to show specific error message
     *
     * @since 1.4.1
     */
    public static void ShowMsgError() {
        FailureHandlingHelper.ShowMsgError();
    }

    /**
     * <b>setTimeoutConnectionAPI</b>
     * used Set Timeout Connection when doing HitApi()
     *
     * @param seconds desired time (in seconds) for api hit response timeout
     *
     * @since 1.4.1
     */
    public static void setTimeoutConnectionAPI(int seconds) {
        WebServices.setTimeoutConnection(seconds);
    }
}
