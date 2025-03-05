package com.coedotzmagic.qatools.util;

import com.coedotzmagic.qatools.failurehandling.FailureHandlingHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import net.sourceforge.tess4j.Tesseract;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import com.coedotzmagic.qatools.integration.ImprovImgCaptcha;
import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * write by Coedotz
 * 27-12-2024
 */

public class CaptchaBreaker {

    /**
     * <b>CapthaManual()</b>
     * used to handle captcha in manual
     *
     * <br><br>
     *
     * @param timeout
     *
     * @since 1.1
     */
    public static void CapthaManual(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (Exception e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.INVALID_NUMBER);
        }
    }

    /**
     * <b>CapthaAutomation()</b>
     * used to handle captcha in self action / automatic
     *
     * <br><br>
     *
     * @param idElementInput
     * @param xpathCaptchaElement
     *
     * @since 1.1
     */
    public static void CapthaAutomation(String xpathCaptchaElement, String idElementInput) {
        WebDriver driver = DriverHelper.GetWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(FailureHandlingHelper.GetTimeoutWait()));
        assert driver != null;
        WebElement captchaImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathCaptchaElement)));
        ElementHelper.currentElement = captchaImage;

        File tessDataPath = new File("tessdata");
        String tessDataPathString = tessDataPath.getAbsolutePath();

        if (!tessDataPath.exists() && !tessDataPath.isDirectory()) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.NOT_FOUND_OCR_TESSDATA);
        }

        File screenshot = captchaImage.getScreenshotAs(OutputType.FILE);
        File folder = new File("Captcha");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File captchaImageFile = new File(folder, "captcha-" + DateTime.getDateTime() + ".png");
        BufferedImage cleanedImage = null;
        try {
            FileUtils.copyFile(screenshot, captchaImageFile);

            BufferedImage image = ImageIO.read(captchaImageFile);
            cleanedImage = ImprovImgCaptcha.cleanImage(image);
            ImageIO.write(cleanedImage, "png", captchaImageFile);
        } catch (IOException e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.FAILED_TO_READWRITE_IMG + e.getMessage());
        }

        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(tessDataPathString);

        try {
            String captchaText = tesseract.doOCR(cleanedImage);
            captchaText = captchaText.replaceAll("[^a-zA-Z0-9]", "");
            System.out.println("Captcha text: " + captchaText);
            if (!captchaText.equalsIgnoreCase("")) {
                TextUtil.InputTextField(idElementInput, captchaText);
                CapthaManual(8);
            } else {
                TextUtil.InputTextField(idElementInput, TellMeWhy.UNABLE_EXTRACT_CAPTCHA);
                new TellMeWhy("w", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.UNABLE_EXTRACT_CAPTCHA);
                CapthaManual(8);
            }
        } catch (Exception e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.ERROR_OCR + e.getMessage());
        }
    }
}