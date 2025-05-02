package com.coedotzmagic.qatools.util;

import com.coedotzmagic.qatools.failurehandling.FailureHandlingHelper;
import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.*;
import java.time.Duration;

/*
 * write by Coedotz
 * 19-02-2024
 */

public class InteractionsAndKeys {
    private static ThreadLocal<Boolean> hasScrolled = ThreadLocal.withInitial(() -> false);

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
        try {
            assert driver != null;
            ElementHelper.currentElement = element;
            new Actions(driver).scrollToElement(element).perform();
        } catch (Exception e) {
            TellMeWhy.getPrintMsgErrActive(e);
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
        try {
            assert driver != null;
            try {
                new Actions(driver).scrollByAmount(x, y).perform();
            } catch (Exception e1) {
                TellMeWhy.getPrintMsgErrActive(e1);
                new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.UNABLE_TO + "Scroll Element.");
            }
        } catch (Exception e2) {
            TellMeWhy.getPrintMsgErrActive(e2);
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
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element)
                    .clickAndHold()
                    .perform();
        } catch (Exception e) {
            TellMeWhy.getPrintMsgErrActive(e);
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.UNABLE_TO + "Mouse Over Current Components!");
        }
    }

    /**
     * <b>ScrollThePage()</b>
     * used to doing wheel scroll in all page
     *
     * <br><br>
     *
     * @param idElement
     * @param y
     * @param isMutipleScroll
     *
     * @since 1.3.2
     */
    public static void ScrollThePage(String idElement, int y, boolean isMutipleScroll) {
        WebDriver driver = DriverHelper.GetWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(FailureHandlingHelper.GetTimeoutWait()));
        try {
            if (!hasScrolled.get()) {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(idElement)));
                com.coedotzmagic.qatools.util.ElementHelper.currentElement = element;
                WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(element);
                new Actions(driver)
                        .scrollFromOrigin(scrollOrigin, 0, y)
                        .perform();

                hasScrolled.set(!isMutipleScroll ? true : false);
            }
        } catch (Exception e) {
            TellMeWhy.getPrintMsgErrActive(e);
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.UNABLE_TO + "Wheel Scrolling!");
        }
    }

    /**
     * <b>ScrollToElementXPath()</b>
     * used to direct to the desired component
     *
     * @param xpath
     *
     * @since 1.5.0
     */
    public static void ScrollToElementXPath(String xpath) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverHelper.GetWebDriver(), Duration.ofSeconds(FailureHandlingHelper.GetTimeoutWait()));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            new Actions(DriverHelper.GetWebDriver())
                    .scrollToElement(element)
                    .perform();
        } catch (Exception e) {
            TellMeWhy.getPrintMsgErrActive(e);
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.UNABLE_TO + "Scroll To Element.");
        }
    }
}