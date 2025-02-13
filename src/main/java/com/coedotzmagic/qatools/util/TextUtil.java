package com.coedotzmagic.qatools.util;

import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * write by Coedotz
 * 19-02-2024
 */

public class TextUtil {

    /**
     * <b>extractTextFromList()</b>
     * digunakan untuk melakukan ekstraksi teks dari data list
     * maksudnya misal, ada service id 50250205_122848263712_INTERNET
     * nah dia akan melakukan pencarian dengan mencocokan data di list, apakah pada targetText
     * ada kata yang cocok dengan yang dilist, jika ada nanti akan dioutputkan, jadi nanti dilist ada
     * data "internet" nah nanti outputnya jadi "internet" karena di target textnya ada kata "internet"
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
     * digunakan untuk melakukan ekstraksi teks berdasarkan teks yang kita mau
     * misal, teks "COEDOTZ ITA 12 SOFT 4 SKYYLUVIE dan kita mau kata COEDOTZ aja berati nanti outputnya "COEDOTZ"
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
        }
        return null;
    }

    /**
     * <b>copyToClipboard()</b>
     * digunakan untuk melakukan salin teks ke clipboard external chrome
     * biasanya digunakan untuk handle CTRL+F pada chrome atau dialog chooser
     *
     * @since 1.0
     */
    public static void copyToClipboard(String text) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
    }

    /**
     * <b>getClipboardContent()</b>
     * digunakan untuk melakukan mengambil salin teks dari clipboard external chrome
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
     * digunakan melakukan inputan teks biasa
     *
     * <br><br>
     *
     * @param id
     * @param text
     *
     * @since 1.1
     */
    public static void InputTextField(String id, String text) {
        WebDriver driver = DriverHelper.GetWebDriver();
        WebElement input;
        try {
            assert driver != null;
            input = driver.findElement(By.xpath("//input[contains(@id, '" + id + "') or contains(@name, '" + id + "') or contains(@class, '" + id + "')]"));
            input.clear();
            input.sendKeys(text);
        } catch (Exception e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.NOT_FOUND_ELEMENT);
        }
    }
}