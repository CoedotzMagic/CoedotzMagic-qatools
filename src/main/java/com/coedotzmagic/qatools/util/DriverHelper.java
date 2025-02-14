package com.coedotzmagic.qatools.util;

import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Objects;

/*
 * write by Coedotz
 * 23-01-2025
 */

public class DriverHelper {
    private static WebDriver driver;
    private static boolean calBrowserFromUs = false;

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
        return Objects.requireNonNullElseGet(driver, () -> driver = new ChromeDriver());
    }

    /**
     * <b>SetCallBrowserFromUs()</b>
     * Digunakan untuk memberikan perilaku pemanggilan browser dari kami
     *
     * <br><br>
     *
     * @param isActive
     * @param browser
     *
     * @since 1.1
     */
    public static void UseCallBrowserFromUs(boolean isActive, String browser) {
        calBrowserFromUs = isActive;
        WebDriver driverFromUS = null;
        if(isActive) {
            assert browser != null;
            if (!browser.equalsIgnoreCase("")) {
                switch (browser.toUpperCase()) {
                    case "CHROME":
                        driverFromUS = new ChromeDriver();
                        break;
                    case "FIREFOX":
                        driverFromUS = new FirefoxDriver();
                        break;
                    case "EDGE":
                        driverFromUS = new EdgeDriver();
                        break;
                    case "IE":
                        driverFromUS = new InternetExplorerDriver();
                        break;
                    case "SAFARI":
                        driverFromUS = new SafariDriver();
                        break;
                }
            } else {
                driverFromUS = new ChromeDriver();
            }
            driver = driverFromUS;
        }
    }

}