package com.coedotzmagic.qatools.util;

import java.io.File;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.coedotzmagic.qatools.integration.ScreenRecordingHelper;
import com.coedotzmagic.qatools.failurehandling.FailureHandlingHelper;
import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import static com.coedotzmagic.qatools.integration.ScreenRecordingHelper.USER_DIR;

/*
 * write by Coedotz
 * 11-02-2025
 */

public class CaptureEvidence {

    /**
     * <b>startScreenRecording()</b>
     * used to start Screen Recording
     *
     * @since 1.0
     */
    public static void startScreenRecording() throws Exception {
        ScreenRecordingHelper.startRecording();
    }

    /**
     * <b>stopScreenRecording()</b>
     * used to stop Screen Recording
     *
     * @since 1.0
     */
    public static void stopScreenRecording() throws Exception {
        ScreenRecordingHelper.stopRecording();
    }

    /**
     * <b>takeScreenshot()</b>
     * used to take screenshots
     *
     * <br><br>
     *
     * @param folderName
     *
     * @since 1.0
     */
    public static void TakeScreenshot(String folderName) {
        WebDriver driver = DriverHelper.GetWebDriver();
        String nameMasterTestcase;
        String targetFolder = null;
        String pathFolderScreenshot = System.getProperty(USER_DIR) + File.separator + "Screenshot";

        new File(pathFolderScreenshot).mkdirs();

        try {
            if (folderName != null) {
                nameMasterTestcase = pathFolderScreenshot + File.separator + folderName;
                targetFolder = folderName + File.separator;
                new File(nameMasterTestcase).mkdirs();
            }
        } catch (Exception e) {
            targetFolder = pathFolderScreenshot + File.separator;
        }

        String timestamp = DateTime.getDateTime();

        String title = "Screenshot ";
        File scrFile;
        scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            if (folderName != null) {
                FileUtils.copyFile(scrFile, new File("Screenshot/" + targetFolder + title + " - " + timestamp + ".jpg"));
            } else {
                FileUtils.copyFile(scrFile, new File("Screenshot/" + title + " - " + timestamp + ".jpg"));
            }
        } catch (Exception e) {
            new TellMeWhy("w", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.UNABLE_TO + "Screenshot this page :" + e.getMessage());
        }
    }

    /**
     * <b>TakeScreenshotSpecificPath()</b>
     * used to take screenshots of specific parts
     *
     * <br><br>
     *
     * @param folderName
     * @param xpath
     *
     * @since 1.1
     */
    public static void TakeScreenshotSpecificPath(String folderName, String xpath) {
        WebDriver driver = DriverHelper.GetWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(FailureHandlingHelper.GetTimeoutWait()));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        ElementHelper.currentElement = element;
        InteractionsAndKeys.ScrollToElement(element);
        TakeScreenshot(folderName);
    }
}