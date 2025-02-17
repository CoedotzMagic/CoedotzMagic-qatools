package com.coedotzmagic.qatools;

import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import com.coedotzmagic.qatools.util.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 * write by Coedotz
 * 13-02-2025
 *
 * only for testing use.
 */

public class TestLab {

    public static String url = TellMeWhy.WEBSITE_COEDOTZMAGIC;

    public static void main(String[] args) {
        // create driver
        WebDriver driver = new ChromeDriver();

        // set webdriver
        QATools.SetWebDriver(driver);

        // start recording
        CaptureEvidence.startScreenRecording();

        // open browser
        BrowserHelper.OpenBrowser(url);

        // get element
        String getclass = ElementHelper.getClassElement("/html/body/h1/center[2]/span");
        System.out.println(getclass);

        // get title page
        System.out.println("Page Title: " + GetValueHelper.getTitlePage());

        // wait 15s
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // get text
        System.out.println("Get Text magician: " + GetValueHelper.getTextElement("/html/body/h1/center[2]/span"));

        // verifytitle
        VerifyValueHelper.VerifyTitlePage("CoedotzMagic: Everyones can be Magician!");
        VerifyValueHelper.VerifyTextElement("/html/body/h1/center[2]/span", "Everyones can be Magician!");

        // take screenshot
        CaptureEvidence.TakeScreenshot(null);

        // create new tab
        BrowserHelper.CreateNewTab("http://localhost:8080/jw/web/userview/jogetdx7Components/home/_/enterprise");

        // doing break captcha
        CaptchaBreaker.CapthaAutomation("//div[contains(@class, 'captchafield')]//img", "field4");

        // take screenshot
        CaptureEvidence.TakeScreenshot("");

        // stop recording
        CaptureEvidence.stopScreenRecording();
    }
}
