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
 * 31-01-2025
 */

public class VerifyValueHelper {
    private static WebElement element;

    /**
     * <b>VerifyTitlePage()</b>
     * used to verify page title
     *
     * <br><br>
     *
     * @param expect
     *
     * @since 1.1
     */
    public static void VerifyTitlePage(String expect) {
        WebDriver driver = DriverHelper.GetWebDriver();
        assert driver != null;
        if (expect != null && !expect.equalsIgnoreCase("") && !driver.getTitle().equalsIgnoreCase(expect)) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.FAILED_TO_VERIFY + expect + ", Current value/text is: " +  driver.getCurrentUrl());
        } else {
            new TellMeWhy("i", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.VERIFY_OK + expect + ", Current value/text is: " +  driver.getCurrentUrl());
        }
    }

    /**
     * <b>VerifyValueElement()</b>
     * used to verify the value of an element
     *
     * <br><br>
     *
     * @param xpath
     * @param expect
     *
     * @since 1.1
     */
    public static void VerifyValueElement(String xpath, String expect) {
        WebDriver driver = DriverHelper.GetWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(FailureHandlingHelper.GetTimeoutWait()));
        String currentValue;
        try {
            assert driver != null;
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            currentValue = element.getDomProperty("value");
            ElementHelper.currentElement = element;
            assert currentValue != null;
            if (!currentValue.equalsIgnoreCase(expect)) {
                new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.FAILED_TO_VERIFY + expect + ", Current value/text is: " +  currentValue);
            } else {
                new TellMeWhy("i", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.VERIFY_OK + expect + ", Current value/text is: " +  currentValue);
            }
        } catch (Exception e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.NOT_FOUND_ELEMENT);
        }
    }

    /**
     * <b>VerifyTextElement()</b>
     * used to verify text on elements
     *
     * <br><br>
     *
     * @param xpath
     * @param expect
     *
     * @since 1.1
     */
    public static void VerifyTextElement(String xpath, String expect) {
        WebDriver driver = DriverHelper.GetWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(FailureHandlingHelper.GetTimeoutWait()));
        String currentText;
        try {
            assert driver != null;
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            currentText = element.getText();
            ElementHelper.currentElement = element;
            if (!currentText.equalsIgnoreCase(expect)) {
                new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.FAILED_TO_VERIFY + expect + " Current value/text is: " +  currentText);
            } else {
                new TellMeWhy("i", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.VERIFY_OK + expect + " Current value/text is: " +  currentText);
            }
        } catch (Exception e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.NOT_FOUND_ELEMENT);
        }
    }
}