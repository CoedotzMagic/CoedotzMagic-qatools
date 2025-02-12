package com.coedotzmagic.qatools.util;

import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import com.coedotzmagic.qatools.integration.ScreenRecordingHelper;
import static com.coedotzmagic.qatools.integration.ScreenRecordingHelper.USER_DIR;

public class CaptureEvidence {

    /**
     * <b>startScreenRecording()</b>
     * digunakan untuk memulai Screen Recording
     *
     * @since 1.0
     */
    public void startScreenRecording() {
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
    public void stopScreenRecording() {
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
    public void TakeScreenshot(String folderName, boolean fullPage) {
        WebDriver driver = new ChromeDriver();
        String nameMasterTestcase;
        String targetFolder;
        String pathFolderScreenshot = System.getProperty(USER_DIR) + File.separator + "Screenshot";

        new File(pathFolderScreenshot).mkdirs();

        if (folderName != null && !folderName.equalsIgnoreCase("")) {
            nameMasterTestcase = pathFolderScreenshot + File.separator + folderName;
            targetFolder = folderName + File.separator;
            new File(nameMasterTestcase).mkdirs();
        } else {
            targetFolder = pathFolderScreenshot + File.separator;
        }

        String timestamp = new DateTime().getDateTime();

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
        if (scrFile != null) {
            try {
                FileUtils.copyFile(scrFile, new File("Screenshot/" + targetFolder + title + " - " + timestamp + ".jpg"));
            } catch (Exception e) {
                new TellMeWhy("w", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.UNABLE_TO + "Screenshot this page :" + e.getMessage());
            }
        }
    }
}
