package com.coedotzmagic.qatools.util;

import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import org.openqa.selenium.WebDriver;

/*
 * write by Coedotz
 * 23-01-2025
 */

public class DriverHelper {
    public static WebDriver driver;

    /**
     * <b>SetWebDriver()</b>
     * digunakan untuk set/menentukan web driver yang digunakan
     *
     * <br><br>
     *
     * @param setDriver
     * @since 1.1
     */
    public static void SetWebDriver(WebDriver setDriver) {
        driver = setDriver;
    }

    /**
     * <b>GetWebDriver()</b>
     * digunakan untuk mendapatkan web driver yang digunakan
     *
     * <br><br>
     *
     * @since 1.1
     */
    public static WebDriver GetWebDriver() {
        return (driver != null) ? driver : null;
        new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.UNABLE_GET_WEBDRIVER);
    }
}
