package com.coedotzmagic.qatools.failurehandling;

/*
 * write by Coedotz
 * 22-12-2024
 */

public class FailureHandlingHelper {

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

}
