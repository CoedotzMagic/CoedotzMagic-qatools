package com.coedotzmagic.qatools.failurehandling;

/*
 * write by Coedotz
 * 22-12-2024
 */

public class FailureHandlingHelper {
    private static int timeout = 0;

    /**
     * <b>CloseBrowserWhenError()</b>
     * Used to provide behavior if an error occurs then the browser will exit.
     *
     * <br><br>
     *
     * @param close
     *
     * @since 1.0
     */
    public static void CloseBrowserWhenError(boolean close) {
        TellMeWhy.setCloseWhenError(close);
    }

    /**
     * <b>SetTimeoutWait()</b>
     * Used to set timeout when waiting time for get the element visible
     *
     * <br><br>
     *
     * @param seconds
     *
     * @since 1.2
     */
    public static void SetTimeoutWait(int seconds) {
        try {
            timeout = seconds;
        } catch (Exception e) {
            new TellMeWhy("e", TellMeWhy.getTraceInfo(Thread.currentThread().getStackTrace()), TellMeWhy.INVALID_NUMBER);
        }
    }

    /**
     * <b>GetTimeoutWait()</b>
     * Used to get timeout when waiting time for WebDriverWait
     *
     * <br><br>
     *
     * @since 1.2
     */
    public static int GetTimeoutWait() {
        return (timeout != 0) ? timeout : 10;
    }

    /**
     * <b>ContinueOnError()</b>
     * Used to provide behavior if an error occurs then the automation will skip error and handling exception.
     *
     * <br><br>
     *
     * @param skipErr
     *
     * @since 1.3
     */
    public static void ContinueOnError(boolean skipErr) {
        TellMeWhy.setContinueOnError(skipErr);
    }

}
