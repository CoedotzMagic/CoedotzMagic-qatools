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
    public static boolean resultVerify;

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
            resultVerify = false;
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.FAILED_TO_VERIFY + expect + ", Current value/text is: " +  driver.getCurrentUrl());
        } else {
            resultVerify = true;
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
                resultVerify = false;
                new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.FAILED_TO_VERIFY + expect + ", Current value/text is: " +  currentValue);
            } else {
                resultVerify = true;
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
                resultVerify = false;
                new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.FAILED_TO_VERIFY + expect + " Current value/text is: " +  currentText);
            } else {
                resultVerify = true;
                new TellMeWhy("i", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.VERIFY_OK + expect + " Current value/text is: " +  currentText);
            }
        } catch (Exception e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.NOT_FOUND_ELEMENT);
        }
    }

    /**
     * <b>VerifyMatchTextorValue()</b>
     * used to verify match text or value between input and expect
     *
     * <br><br>
     *
     * @since 1.4.0
     */
    public static void VerifyMatchTextorValue(String input, String expect) {
        if (input.equalsIgnoreCase(expect)) {
            resultVerify = true;
            new TellMeWhy("v", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.VERIFY_OK + expect + ", Current value is: " +  input);
        } else {
            resultVerify = false;
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.FAILED_TO_VERIFY + expect + ", Current value is: " +  input);
        }
    }

    /**
     * <b>GetResultVerify()</b>
     * used to get result verify (true/false)
     *
     * <br><br>
     *
     * @since 1.4.0
     */
    public static boolean GetResultVerify() {
        return resultVerify;
    }
}