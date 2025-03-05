package com.coedotzmagic.qatools.util;

import com.coedotzmagic.qatools.failurehandling.FailureHandlingHelper;
import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * write by Coedotz
 * 19-02-2024
 */

public class TextUtil {
    private static WebElement element;

    /**
     * <b>extractTextFromList()</b>
     * used to extract text from list data
     * means for example, there is a service id Coedotz_123456789_MAGIC
     * well it will do a search by matching the data in the list, whether in targetText
     * there is a word that matches the one in the list, if there is, it will be output, so later in the list there is
     * data "MAGIC" well later the output will be "MAGIC" because in the target text there is the word "MAGIC"
     *
     *<br><br>
     *
     * @param targetText
     * @param listToSearch
     *
     * @since 1.0
     */
    public static String extractTextFromList(String targetText, List<String> listToSearch) {
        for (String textIWant : listToSearch) {
            if (targetText.toLowerCase().contains(textIWant.toLowerCase())) {
                return textIWant;
            }
            if (textIWant.toLowerCase().contains(targetText.toLowerCase())) {
                return textIWant;
            }
        }

        return null;
    }

    /**
     * <b>extractText()</b>
     * used to perform text extraction based on the text we want
     * for example, the text "COEDOTZ ITA 12 SOFT 4 SKYYLUVIE and we want the word COEDOTZ only, the output will be "COEDOTZ"
     *
     * <br><br>
     *
     * @param targetText
     * @param textIWant
     *
     * @since 1.0
     */
    public static String extractText(String targetText, String textIWant) {
        String pattern = "(?i)" + textIWant;
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(targetText);
        if (matcher.find()) {
            return matcher.group(0);
        } else {
            return null;
        }
    }

    /**
     * <b>copyToClipboard()</b>
     * used to copy text to browser external clipboard
     * usually used to handle CTRL+F on chrome or dialog chooser
     *
     * @since 1.0
     */
    public static void copyToClipboard(String text) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
    }

    /**
     * <b>getClipboardContent()</b>
     * used to perform copying and retrieving text from browser external clipboard
     *
     * @since 1.1
     */
    public static String getClipboardContent() {
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable contents = clipboard.getContents(null);

            if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                return (String) contents.getTransferData(DataFlavor.stringFlavor);
            }
        } catch (Exception e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.UNABLE_TO + "GetClipboardContent: " + e.getMessage());
        }
        return null;
    }

    /**
     * <b>InputTextField()</b>
     * used to perform regular text input
     *
     * <br><br>
     *
     * @param id
     * @param text
     *
     * @since 1.1
     */
    public static void InputTextField(String id, String text) {
        try {
            WebDriver driver = DriverHelper.GetWebDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(FailureHandlingHelper.GetTimeoutWait()));
            assert driver != null;
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id, '" + id + "') or contains(@name, '" + id + "') or contains(@class, '" + id + "')]")));
            ElementHelper.currentElement = element;
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.NOT_FOUND_ELEMENT);
        }
    }

    /**
     * <b>ClearInputfield()</b>
     * used to delete input in the input field
     *
     * <br><br>
     *
     * @param id
     *
     * @since 1.2
     */
    public static void ClearInputfield (String id) {
        try {
            WebDriver driver = DriverHelper.GetWebDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(FailureHandlingHelper.GetTimeoutWait()));
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id, '" + id + "') or contains(@name, '" + id + "') or contains(@class, '" + id + "')]")));
            ElementHelper.currentElement = element;
            element.clear();
        } catch (Exception e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.NOT_FOUND_ELEMENT);
        }
    }
}