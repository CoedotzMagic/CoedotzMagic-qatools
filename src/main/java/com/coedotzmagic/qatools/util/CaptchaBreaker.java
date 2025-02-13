package com.coedotzmagic.qatools.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import net.sourceforge.tess4j.Tesseract;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.coedotzmagic.qatools.integration.ImprovImgCaptcha;
import com.coedotzmagic.qatools.failurehandling.TellMeWhy;

/*
 * write by Coedotz
 * 27-12-2024
 */

public class CaptchaBreaker {

    /**
     * <b>CapthaManual()</b>
     * digunakan untuk menghandle captha dalam bentuk manual
     *
     * <br><br>
     *
     * @param timeout
     *
     * @since 1.1
     */
    void CapthaManual(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (Exception e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.INVALID_NUMBER);
        }
    }

    /**
     * <b>CapthaAutomation()</b>
     * digunakan untuk menghandle captha dalam bentuk otomatis
     *
     * <br><br>
     *
     * @param idElementInput
     * @param xpathCaptchaElement
     *
     * @since 1.1
     */
    void CapthaAutomation(String xpathCaptchaElement, String idElementInput) {
        WebDriver driver = DriverHelper.GetWebDriver();
        assert driver != null;
        WebElement captchaImage = driver.findElement(By.xpath("//div[contains(@class, 'captchafield')]//img"));
        new Actions(driver).sendKeys(Keys.ESCAPE).perform();

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
        File captchaImageFile = new File(folder, "captcha-" + new DateTime().getDateTime() + ".png");
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
                new TextUtil().InputTextField(idElementInput, captchaText);
                CapthaManual(8);
            } else {
                new TextUtil().InputTextField(idElementInput, TellMeWhy.UNABLE_EXTRACT_CAPTCHA);
                new TellMeWhy("w", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.UNABLE_EXTRACT_CAPTCHA);
                CapthaManual(8);
            }
        } catch (Exception e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.ERROR_OCR + e.getMessage());
        }
    }

}
