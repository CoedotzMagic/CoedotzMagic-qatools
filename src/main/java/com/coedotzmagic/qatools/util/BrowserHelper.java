package com.coedotzmagic.qatools.util;

import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

/*
 * write by Coedotz
 * 11-02-2025
 */

public class BrowserHelper {

    /**
     * <b>checkTabBrowser()</b>
     * used to check whether there is an Active Tab with a specific website or not
     * for example we want to check whether the Google site is active or not in the browser tab or other tabs
     * if active it will return the value "Tab Found" if not "Tab Not Found"
     *
     * <br><br>
     *
     * @param targetUrl
     *
     * @since 1.0
     */
    public static boolean checkTabBrowser(String targetUrl) {
        WebDriver driver = DriverHelper.GetWebDriver();
        String jsCodeNumberWindows = "return window.top.frames.length;";
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverHelper.GetWebDriver();
        jsExecutor.executeScript(jsCodeNumberWindows);
        int numberOfOpenWindows = DriverHelper.GetWebDriver().getWindowHandles().size();
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        String tabHandle;

        // jalankan javascript untuk mendapatkan url sekarang dari masing2 tab
        boolean result = false;
        for (int i = 0; i < numberOfOpenWindows; i++) {
            // arahkan tab ke target
            tabHandle = windowHandles.get(i);
            driver.switchTo().window(tabHandle);

            // jalankan javascript untuk mendapatkan url root saat ini
            String jsCode = "return window.top.location.href;";

            try {
                String currentURL = (String) jsExecutor.executeScript(jsCode);

                // Periksa apakah URL yang diinginkan ada di URL saat ini
                if (currentURL != null && !currentURL.equalsIgnoreCase("")) {
                    if (currentURL.contains(targetUrl)) {
                        result = true;
                    }
                }
            } catch (Exception e) {
                new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.UNABLE_TO + "get site web :" + e.getMessage());
                System.out.println("Error Checking");
            }
        }

        // Arahkan ke tab sebelumnya
        tabHandle = windowHandles.iterator().next();
        driver.switchTo().window(tabHandle);

        // kembalikan nilai result
        return result;
    }

    /**
     * <b>openBrowser()</b>
     * used to help open a new browser
     * with the specified url
     *
     * <br><br>
     *
     * @param url
     *
     * @since 1.1
     */
    public static void OpenBrowser(String url) {
        try {
            WebDriver driver = DriverHelper.GetWebDriver();
            if (url != null && !url.equalsIgnoreCase("")) {
                driver.manage().window().maximize();
                driver.get(url);
            }
        } catch (Exception e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.UNABLE_TO + "Open Browser :" + e.getMessage());
        }
    }

    /**
     * <b>createNewTab()</b>
     * is used to help create new tabs
     * with the specified url
     *
     * <br><br>
     *
     * @param url
     *
     * @since 1.0
     */
    public static void CreateNewTab(String url) {
        try {
            WebDriver driver = DriverHelper.GetWebDriver();
            if (url != null && !url.equalsIgnoreCase("")) {
                driver.manage().window().maximize();
                driver.switchTo().newWindow(WindowType.TAB);
                driver.navigate().to(url);
            }
        } catch (Exception e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.UNABLE_TO + "New Tab Browser :" + e.getMessage());
        }
    }
}