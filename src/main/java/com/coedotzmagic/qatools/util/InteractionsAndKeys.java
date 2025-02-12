package com.coedotzmagic.qatools.util;

import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.*;

/*
 * write by Coedotz
 * 19-02-2024
 */

public class InteractionsAndKeys {

    /**
     * <b>CombinationCTRLF()</b>
     * digunakan untuk melakukan perintah CTRL+F (Find)
     *
     * <br><br>
     *
     * @since 1.0
     */
    void CombinationCTRLF() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_F);
        robot.keyRelease(KeyEvent.VK_F);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    /**
     * <b>CombinationCTRLV()</b>
     * digunakan untuk melakukan perintah CTRL+V (Paste)
     *
     * <br><br>
     *
     * @since 1.0
     */
    void CombinationCTRLV() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    /**
     * <b>CombinationCTRLC()</b>
     * digunakan untuk melakukan perintah CTRL+C (Copy)
     *
     * <br><br>
     *
     * @since 1.0
     */
    void CombinationCTRLC() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    /**
     * <b>CombinationENTER()</b>
     * digunakan untuk melakukan perintah ENTER
     *
     * <br><br>
     *
     * @since 1.0
     */
    void CombinationENTER() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    /**
     * <b>CombinationESC()</b>
     * digunakan untuk melakukan perintah ESC
     *
     * <br><br>
     *
     * @since 1.1
     */
    void CombinationESC() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
    }

    /**
     * <b>ScrollToElement()</b>
     * digunakan untuk melakukan scroll ke element yang diinginkan
     *
     * <br><br>
     *
     * @param element
     *
     * @since 1.1
     */
    void ScrollToElement(WebElement element) {
        WebDriver driver = DriverHelper.GetWebDriver();
        assert driver != null;
        try {
            new Actions(driver).scrollToElement(element).perform();
        } catch (Exception e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.UNABLE_TO + "Scroll Element.");
        }
    }

    /**
     * <b>ScrollByDistance()</b>
     * digunakan untuk melakukan scroll ke jarak yang diinginkan
     *
     * <br><br>
     *
     * @param x
     * @param y
     *
     * @since 1.1
     */
    void ScrollByDistance(int x, int y) {
        WebDriver driver = DriverHelper.GetWebDriver();
        assert driver != null;
        try {
            try {
                new Actions(driver).scrollByAmount(x, y).perform();
            } catch (Exception e1) {
                new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.UNABLE_TO + "Scroll Element.");
            }
        } catch (Exception e2) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.INVALID_NUMBER);
        }
    }
}