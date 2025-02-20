package com.coedotzmagic.qatools.util;

import com.coedotzmagic.qatools.failurehandling.FailureHandlingHelper;
import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*
 * write by Coedotz
 * 22-01-2025
 */

public class GetValueHelper {
    private static final WebDriver driver = DriverHelper.GetWebDriver();
    private static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(FailureHandlingHelper.GetTimeoutWait()));

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
        try {
            assert driver != null;
            WebElement textElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
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
        try {
            assert driver != null;
            WebElement valueElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
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
        assert driver != null;
        return driver.getTitle();
    }

}
