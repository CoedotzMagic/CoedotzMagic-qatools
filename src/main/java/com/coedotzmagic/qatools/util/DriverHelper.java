package com.coedotzmagic.qatools.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

/*
 * write by Coedotz
 * 23-01-2025
 */

public class DriverHelper {
    private static WebDriver driver;

    /**
     * <b>SetWebDriver()</b>
     * used to set/determine the webdriver used
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
     * used to get the webdriver used
     *
     * <br><br>
     *
     * @since 1.1
     */
    public static WebDriver GetWebDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
        }
        return driver;
    }

    /**
     * <b>SetCallBrowserFromUs()</b>
     * Used to provide browser calling behavior from QA Tools
     *
     * <br><br>
     *
     * @param isActive
     * @param browser
     *
     * @since 1.1
     */
    public static void UseCallBrowserFromUs(boolean isActive, String browser) {
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