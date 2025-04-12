package com.coedotzmagic.qatools.failurehandling;

import java.util.Map;

import com.coedotzmagic.qatools.util.CaptureEvidence;
import com.coedotzmagic.qatools.util.DateTime;
import com.coedotzmagic.qatools.util.DriverHelper;
import org.openqa.selenium.WebDriver;

/*
 * write by Coedotz
 * 12-02-2025
 */

public class TellMeWhy {
    private static boolean closeWhenError = false;
    private static boolean skipFailure = false;
    private static boolean isMsgErrActive = false;
    private static String identity = "CoedotzMagic - QATools: ";

    public static final String WEBSITE_COEDOTZMAGIC = "https://coedotzmagic.com";
    public static final String REPORT_US = "contact us if show a problem & this message to reporter@coedotzmagic.com or create issue in github https://github.com/CoedotzMagic/CoedotzMagic-qatools";
    public static final String INVALID_NUMBER = "Please insert valid number!";
    public static final String INVALID_VALUE = "Value cannot null or empty!";
    public static final String UNABLE_TO = "Unable to ";
    public static final String UNABLE_EXTRACT_CAPTCHA = "Failed extract captcha, use manual, we hold automation 8s before continue automation.";
    public static final String NOT_FOUND_ELEMENT = "Ahhh, unable located element what you want, sorry.";
    public static final String NOT_FOUND_OCR_TESSDATA = "'tessdata' folder is not in this root project! Put 'tessdata' folder into this root project and can be downloaded from: https://github.com/CoedotzMagic/tessdata";
    public static final String FAILED_READ_EXCEL = "Could not read the Excel sheet : ";
    public static final String FAILED_TO_VERIFY = "Failed to Verify, Expect value: ";
    public static final String FAILED_TO_HITAPI = "Failed to HitApi or Connection Timeout, Please Try Again...";
    public static final String FAILED_TO_GETVALUE_HITAPI = "Failed to get Response Message / Code API or maybe null, Please Try Again...";
    public static final String FAILED_TO_READWRITE_IMG = "Failed to Read/Write Image. ";
    public static final String FAILED_TO_KILL_PROCESS = "Failed to Kill Process WebDriver, please try again... msg: . ";
    public static final String SKIP_FAILURE = "Failed to execute this section. We skip this section...";
    public static final String ERROR_OCR = "Error during OCR: ";
    public static final String STOP_AUTOMATION = "Automation stopped because problem...";
    public static final String BYPASS_FAILURE = "This part should be the problem and automation will force stop, but you bypass the failure (skip failure = true), its okay, we keep continue the next section...";
    public static final String VERIFY_OK = "Successfully Verified, Expect value: ";

    public static final String IN_DEVELOPMENT = "under development, sometimes it works sometimes it doesn't.";
    public static final String IN_BETA = "This is still in beta version, sometimes it works sometimes it doesn't.";

    private static final Map<String, String> LEVELS = Map.of(
            "i", "INFO",
            "w", "WARNING",
            "e", "ERROR",
            "v", "VALIDATION_ERROR"
    );

    public TellMeWhy(String level, String getTrace, String messages) {
        log(level, getTrace, messages);
    }

    public static void UseForITasoft() {
        identity = "ITasoft Reporting Tool: ";
    }

    public static String getIdentity() {
        return identity;
    }

    private static String getTimestamp() {
        return DateTime.getDateTime();
    }

    public static void setContinueOnError(boolean skipErr) {
        skipFailure = skipErr;
    }

    private static boolean getContinueOnError() {
        return skipFailure;
    }

    public static void setCloseWhenError(boolean close) {
        closeWhenError = close;
    }

    public static boolean getCloseWhenError() {
        return closeWhenError;
    }

    public static void setPrintMsgErrActive(boolean isActive) {
        isMsgErrActive = isActive;
    }

    public static boolean getPrintMsgErrActive() {
        return isMsgErrActive;
    }

    public static String getTraceInfo(StackTraceElement[] stackTrace) {
        StackTraceElement element = stackTrace[2];
        String traceMethod = " [Method: " + element.getMethodName() + "]";
        String traceLine = " [Line: " + element.getLineNumber() + "]";
        String traceClass = " Class: " + element.getClassName();
        return traceClass + traceMethod + traceLine;
    }

    private void log(String level, String getTrace, String messages) {
        String identity = getIdentity();
        String timestamp = getTimestamp();
        String levelFormatted = LEVELS.get(level.toLowerCase());

        if (levelFormatted != null && !levelFormatted.equalsIgnoreCase("")) {
            if (!level.equalsIgnoreCase("v")) {
                System.out.println(identity + "[" + levelFormatted + "]" + "[" + timestamp + "] " + getTrace);
            }

            System.out.println(identity + "[" + levelFormatted + "]" + "[" + timestamp + "] " + "CAUSED BY: " + messages);

            if ("e".equalsIgnoreCase(level)) {
                if (!getContinueOnError()) {
                    System.out.println(identity + "[" + levelFormatted + "]" + "[" + timestamp + "] " + STOP_AUTOMATION);
                    System.out.println(identity + "[" + levelFormatted + "]" + "[" + timestamp + "] " + REPORT_US);
                    if (getCloseWhenError()) {
                        WebDriver driver = DriverHelper.GetWebDriver();
                        assert driver != null;
                        driver.quit();
                    }
                    try {
                        CaptureEvidence.stopScreenRecording();
                    } catch (Exception e) {
                        //coedotzwuzzhere
                    }
                    System.exit(1);
                } else {
                    System.out.println(identity + "[" + levelFormatted + "]" + "[" + timestamp + "] " + BYPASS_FAILURE);
                }
            } else if ("w".equalsIgnoreCase(level)) {
                System.out.println(identity + "[" + levelFormatted + "]" + "[" + timestamp + "] " + SKIP_FAILURE);
            }
        }
    }
}