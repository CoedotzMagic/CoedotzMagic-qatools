package com.coedotzmagic.qatools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Map;

import com.coedotzmagic.qatools.failurehandling.TellMeWhy;
import com.coedotzmagic.qatools.util.*;

/*
 * write by Coedotz
 * 13-02-2025
 *
 * only for testing use.
 */

/**
 * <b>TestLab</b>
 * only for testing coedotz use.
 *
 * <br><br>
 *
 * @since 1.1
 */
public class TestLab {

//    public static String url = TellMeWhy.WEBSITE_COEDOTZMAGIC;
//    static String filePath = "testingDataDriven.xlsx";
//    static String sheetName = "Sheet1";
//
//    public static void main(String[] args) throws Exception {
//        // create driver
//        WebDriver driver = new ChromeDriver();
//
//        // set webdriver
//        QATools.SetWebDriver(driver);
//
//        // start recording
//        CaptureEvidence.startScreenRecording();
//
//        // open browser
//        BrowserHelper.OpenBrowser(url);
//
//        // get element
//        String getclass = ElementHelper.getClassElement("/html/body/h1/center[2]/span");
//        System.out.println(getclass);
//
//        // get title page
//        System.out.println("Page Title: " + GetValueHelper.getTitlePage());
//
//        // Makesure title correct
//        boolean checkTab = BrowserHelper.checkTabBrowser("coedotzmagic.com");
//        if (checkTab) {
//            System.out.println("OK, this tab active");
//        } else {
//            System.out.println("Not Okay, tab not active!");
//        }
//
//        // wait 15s
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        // get text
//        System.out.println("Get Text magician: " + GetValueHelper.getTextElement("/html/body/h1/center[2]/span"));
//
//        // verifytitle
//        VerifyValueHelper.VerifyTitlePage("CoedotzMagic: Everyones can be Magician!");
//        VerifyValueHelper.VerifyTextElement("/html/body/h1/center[2]/span", "Everyones can be Magician!");
//
//        // take screenshot
//        CaptureEvidence.TakeScreenshot(null);
//
//        // create new tab
//        BrowserHelper.CreateNewTab("http://localhost:8080/jw/web/userview/jogetdx7Components/home/_/enterprise");
//
//        // doing break captcha
//        CaptchaBreaker.CapthaAutomation("//div[contains(@class, 'captchafield')]//img", "field4");
//
//        // take screenshot
//        CaptureEvidence.TakeScreenshot("");
//
//        // take screenshot specific components
//        CaptureEvidence.TakeScreenshotSpecificPath("", "/html/body/div[2]/div[1]/div/div[2]/main/div[1]/fieldset/form/div[2]/div[2]/div/i/input");
//
//        // create new tab
//        BrowserHelper.CreateNewTab("http://localhost:8080/jw/web/login");
//
//        // Data driven source from excel
//        List<Map<String, String>> testData = DataDrivenWithExcel.readTestDataFromExcel(filePath, sheetName);
//        assert testData != null;
//        for (Map<String, String> row : testData) {
//        String username = row.get("Username");
//        String password = row.get("Password");
//
//        TextUtil.InputTextField("username", username);
//        TextUtil.InputTextField("password", password);
//    }
//        // stop recording
//        CaptureEvidence.stopScreenRecording();
//
//        // close browser
//        driver.quit();
//
//        // kill all process
//        QATools.KillAllWebDriverProcess();
//    }
}