package com.coedotzmagic.qatools.util;

import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * write by Coedotz
 * 22-01-2025
 */

public class GetValueHelper {

    /**
     * <b>getTextElement()</b>
     * used to get text on an element
     *
     * <br><br>
     *
     * @param xpath
     *
     * @since 1.1
     */
    public static String getTextElement(String xpath) {
        WebDriver driver = DriverHelper.GetWebDriver();
        try {
            assert driver != null;
            WebElement textElement = driver.findElement(By.xpath(xpath));
            return textElement.getText();
        } catch (Exception e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.NOT_FOUND_ELEMENT);
            return "";
        }
    }

    /**
     * <b>getValueElement()</b>
     * used to get the value of an element
     *
     * <br><br>
     *
     * @param xpath
     *
     * @since 1.1
     */
    public static String getValueElement(String xpath) {
        WebDriver driver = DriverHelper.GetWebDriver();
        try {
            assert driver != null;
            WebElement valueElement = driver.findElement(By.xpath(xpath));
            return valueElement.getDomProperty("value");
        } catch (Exception e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.NOT_FOUND_ELEMENT);
            return "";
        }
    }

    /**
     * <b>getTitlePage()</b>
     * used to get the page title
     *
     * <br><br>
     *
     * @since 1.1
     */
    public static String getTitlePage() {
        WebDriver driver = DriverHelper.GetWebDriver();
        assert driver != null;
        return driver.getTitle();
    }

}
