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
     * digunakan untuk mengecek apakah ada Tab Aktif dengan website khusus atau tidak
     * semisal kita ingin cek apakah situs google aktif atau tidak di tab browser atau tab lainnya
     * jika aktif nanti akan mengembalikan nilai "Tab Found" jika tidak "Tab Not Found"
     *
     * <br><br>
     *
     * @param targetUrl
     *
     * @since 1.0
     */
    public static String checkTabBrowser(String targetUrl) {
        WebDriver driver = DriverHelper.GetWebDriver();

        String jsCodeNumberWindows = "return window.top.frames.length;";
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverHelper.GetWebDriver();
        jsExecutor.executeScript(jsCodeNumberWindows);
        int numberOfOpenWindows = DriverHelper.GetWebDriver().getWindowHandles().size();
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        String tabHandle;

        // jalankan javascript untuk mendapatkan url sekarang dari masing2 tab
        String result = null;
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
                        result = "tab found";
                        break;
                    }
                }
            } catch (Exception e) {
                new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.UNABLE_TO + "get site web :" + e.getMessage());
                result = "Error checking";
            }
        }

        // Arahkan ke tab sebelumnya
        tabHandle = windowHandles.iterator().next();
        driver.switchTo().window(tabHandle);

        // jika tidak ditemukan / kosong
        if (result == null) {
            result = "tab not found";
        }

        // kembalikan nilai result
        return result;
    }

    /**
     * <b>openBrowser()</b>
     * digunakan untuk membantu membuka browser baru
     * dengan url yang ditentukan
     *
     * <br><br>
     *
     * @param url
     *
     * @since 1.1
     */
    public static void OpenBrowser(String url) {
        try {
            if (url != null && !url.equalsIgnoreCase("")) {
                WebDriver driver = DriverHelper.GetWebDriver();
                driver.manage().window().maximize();
                driver.get(url);
            }
        } catch (Exception e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.UNABLE_TO + "Open Browser :" + e.getMessage());
        }
    }

    /**
     * <b>createNewTab()</b>
     * digunakan untuk membantu membuat tab baru
     * dengan url yang ditentukan
     *
     * <br><br>
     *
     * @param url
     *
     * @since 1.0
     */
    public static void CreateNewTab(String url) {
        WebDriver driver = DriverHelper.GetWebDriver();
        try {
            if (url != null && !url.equalsIgnoreCase("")) {
                driver.switchTo().newWindow(WindowType.TAB);
                driver.navigate().to(url);
            }
        } catch (Exception e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.UNABLE_TO + "New Tab Browser :" + e.getMessage());
        }
    }

}