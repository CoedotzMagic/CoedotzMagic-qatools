package com.coedotzmagic.qatools.util;

import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * write by Coedotz
 * 22-01-2025
 */

public class ElementHelper {

    /**
     * <b>getIdElement()</b>
     * digunakan untuk mendapatkan element atribut id
     *
     * <br><br>
     * @param type
     * @param xpathElement
     *
     * @since 1.1
     */
    public String getElement(String type, String xpathElement) {
        try {
            WebDriver driver = DriverHelper.GetWebDriver();
            assert driver != null;
            WebElement bodyElement = driver.findElement(By.xpath(xpathElement));

            String dynamicId = (type.equalsIgnoreCase("id")) ? bodyElement.getAttribute("id") :
                    (type.equalsIgnoreCase("class")) ? bodyElement.getAttribute("class") :
                            (type.equalsIgnoreCase("name")) ? bodyElement.getAttribute("name") : null;

            assert dynamicId != null;
            if (!dynamicId.equalsIgnoreCase("")) {
                return dynamicId;
            } else {
                new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.NOT_FOUND_ELEMENT);
                return "";
            }
        } catch (Exception e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.NOT_FOUND_ELEMENT);
            return "";
        }
    }

    /**
     * <b>getIdElement()</b>
     * digunakan untuk mendapatkan element atribut id
     *
     * <br><br>
     * @param xpathElement
     *
     * @since 1.1
     */
    public String getIdElement(String xpathElement) {
        return getElement("id", xpathElement);
    }

    /**
     * <b>getClassElement()</b>
     * digunakan untuk mendapatkan element atribut class
     *
     * <br><br>
     * @param xpathElement
     *
     * @since 1.1
     */
    public String getClassElement(String xpathElement) {
        return getElement("class", xpathElement);
    }

    /**
     * <b>getNameElement()</b>
     * digunakan untuk mendapatkan element atribut name
     *
     * <br><br>
     * @param xpathElement
     *
     * @since 1.1
     */
    public String getNameElement(String xpathElement) {
        return getElement("name", xpathElement);
    }

}
