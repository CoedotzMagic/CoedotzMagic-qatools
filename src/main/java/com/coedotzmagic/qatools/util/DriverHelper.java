package com.coedotzmagic.qatools.util;

import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverHelper {
    public static WebDriver driver;

    public static WebDriver SetWebDriver(WebDriver setDriver) {
        driver = setDriver;
        return driver;
    }

    public static WebDriver GetWebDriver() {
        return (driver != null) ? driver : null;
        new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.UNABLE_GET_WEBDRIVER);
    }
}
