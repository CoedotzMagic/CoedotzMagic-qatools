package com.coedotzmagic.qatools.util;

import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import com.coedotzmagic.qatools.integration.ScreenRecordingHelper;
import static com.coedotzmagic.qatools.integration.ScreenRecordingHelper.USER_DIR;

/*
 * write by Coedotz
 * 11-02-2025
 */

public class CaptureEvidence {

    /**
     * <b>startScreenRecording()</b>
     * digunakan untuk memulai Screen Recording
     *
     * @since 1.0
     */
    public static void startScreenRecording() {
        try {
            ScreenRecordingHelper.startRecording();
        } catch (final Exception e) {
            new TellMeWhy("w", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.UNABLE_TO + "Start Recording :" + e.getMessage());
        }
    }

    /**
     * <b>stopScreenRecording()</b>
     * digunakan untuk menghentikan Screen Recording
     *
     * @since 1.0
     */
    public static void stopScreenRecording() {
        try {
            ScreenRecordingHelper.stopRecording();
        } catch (final Exception e) {
            new TellMeWhy("w", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.UNABLE_TO + "Stop Recording :" + e.getMessage());
        }
    }

    /**
     * <b>takeScreenshot()</b>
     * digunakan untuk melakukan screenshot
     *
     * <br><br>
     *
     * @param folderName
     * @param fullPage
     *
     * @since 1.0
     */
    public static void TakeScreenshot(String folderName, boolean fullPage) {
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
        if (fullPage) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Long scrollHeight = (Long) js.executeScript("return document.documentElement.scrollHeight");
            js.executeScript("window.scrollTo(0, " + scrollHeight + ")");
            scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            js.executeScript("window.scrollTo(0, 0)");
        } else {
            scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        }
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
}
