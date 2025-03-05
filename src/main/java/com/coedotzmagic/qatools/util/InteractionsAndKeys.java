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
     * used to perform the CTRL+F (Find) command
     *
     * <br><br>
     *
     * @since 1.0
     */
    public static void CombinationCTRLF() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_F);
        robot.keyRelease(KeyEvent.VK_F);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    /**
     * <b>CombinationCTRLV()</b>
     * used to perform the CTRL+V (Paste) command
     *
     * <br><br>
     *
     * @since 1.0
     */
    public static void CombinationCTRLV() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    /**
     * <b>CombinationCTRLC()</b>
     * used to perform the CTRL+C (Copy) command
     *
     * <br><br>
     *
     * @since 1.0
     */
    public static void CombinationCTRLC() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    /**
     * <b>CombinationENTER()</b>
     * used to perform the ENTER command
     *
     * <br><br>
     *
     * @since 1.0
     */
    public static void CombinationENTER() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    /**
     * <b>CombinationESC()</b>
     * used to perform the ESC command
     *
     * <br><br>
     *
     * @since 1.1
     */
    public static void CombinationESC() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
    }

    /**
     * <b>ScrollToElement()</b>
     * used to scroll to the desired element
     *
     * <br><br>
     *
     * @param element
     *
     * @since 1.1
     */
    public static void ScrollToElement(WebElement element) {
        WebDriver driver = DriverHelper.GetWebDriver();
        assert driver != null;
        try {
            ElementHelper.currentElement = element;
            new Actions(driver).scrollToElement(element).perform();
        } catch (Exception e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.UNABLE_TO + "Scroll Element.");
        }
    }

    /**
     * <b>ScrollByDistance()</b>
     * used to scroll to the desired distance
     *
     * <br><br>
     *
     * @param x
     * @param y
     *
     * @since 1.1
     */
    public static void ScrollByDistance(int x, int y) {
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

    /**
     * <b>MouseOverCurrentComponents()</b>
     * used to mouse over and hold in current components
     *
     * <br><br>
     *
     * @param element
     *
     * @since 1.3
     */
    public static void MouseOverCurrentComponents(WebElement element) {
        WebDriver driver = DriverHelper.GetWebDriver();
        ElementHelper.currentElement = element;
        Actions actions = new Actions(driver);
        actions.moveToElement(element)
                .clickAndHold()
                .perform();
    }
}