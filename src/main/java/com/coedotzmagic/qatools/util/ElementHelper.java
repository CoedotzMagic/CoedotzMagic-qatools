package com.coedotzmagic.qatools.util;

import com.coedotzmagic.qatools.failurehandling.FailureHandlingHelper;
import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * write by Coedotz
 * 22-01-2025
 */

public class ElementHelper {
    public static WebElement currentElement;

    /**
     * <b>getIdElement()</b>
     * used to get the element attributes id, name and class
     *
     * <br><br>
     * @param type
     * @param xpathElement
     *
     * @since 1.1
     */
    public static String getElement(String type, String xpathElement) {
        try {
            WebDriver driver = DriverHelper.GetWebDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(FailureHandlingHelper.GetTimeoutWait()));
            assert driver != null;
            WebElement bodyElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathElement)));

            String dynamicId = (type.equalsIgnoreCase("id")) ? bodyElement.getDomAttribute("id") :
                    (type.equalsIgnoreCase("class")) ? bodyElement.getDomAttribute("class") :
                            (type.equalsIgnoreCase("name")) ? bodyElement.getDomAttribute("name") : null;
            currentElement = bodyElement;
            assert dynamicId != null;
            if (!dynamicId.equalsIgnoreCase("")) {
                return dynamicId;
            } else {
                new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.NOT_FOUND_ELEMENT);
                return "";
            }
        } catch (Exception e) {
            TellMeWhy.getPrintMsgErrActive(e);
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.NOT_FOUND_ELEMENT);
            return "";
        }
    }

    /**
     * <b>getIdElement()</b>
     * used to get the id attribute element
     *
     * <br><br>
     * @param xpathElement
     *
     * @since 1.1
     */
    public static String getIdElement(String xpathElement) {
        return getElement("id", xpathElement);
    }

    /**
     * <b>getClassElement()</b>
     * used to get the class attribute element
     *
     * <br><br>
     * @param xpathElement
     *
     * @since 1.1
     */
    public static String getClassElement(String xpathElement) {
        return getElement("class", xpathElement);
    }

    /**
     * <b>getNameElement()</b>
     * used to get the name attribute element
     *
     * <br><br>
     * @param xpathElement
     *
     * @since 1.1
     */
    public static String getNameElement(String xpathElement) {
        return getElement("name", xpathElement);
    }

    /**
     * <b>getCurrentXpathElement()</b>
     * used to get current xpath element
     *
     * <br><br>
     *
     * @since 1.3
     */
    public static String getCurrentXpathElement() {
        if (currentElement != null) {
            String elementString = currentElement.toString();

            String regex = "xpath:\\s*(/[^\\]]+\\[[^\\]]+\\]?[^\\]]*)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(elementString);

            if (matcher.find()) {
                return matcher.group(1);
            } else {
                return "not found current xpath element.";
            }
        }
        return "not found current xpath element.";
    }

}