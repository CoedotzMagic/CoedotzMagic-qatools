package com.coedotzmagic.qatools.util;

import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * write by Coedotz
 * 31-01-2025
 */

public class VerifyValueHelper {

    /**
     * <b>VerifyTitlePage()</b>
     * digunakan untuk verifikasi title halaman page
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
     * digunakan untuk verifikasi value pada element
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
        String currentValue;
        try {
            assert driver != null;
            WebElement valueElement = driver.findElement(By.xpath(xpath));
            currentValue = valueElement.getDomProperty("value");
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
     * digunakan untuk verifikasi text pada element
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
        String currentText;
        try {
            assert driver != null;
            WebElement textElement = driver.findElement(By.xpath(xpath));
            currentText = textElement.getText();
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
