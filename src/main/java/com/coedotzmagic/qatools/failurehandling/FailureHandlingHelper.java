package com.coedotzmagic.qatools.failurehandling;

/*
 * write by Coedotz
 * 22-12-2024
 */

public class FailureHandlingHelper {

    public static void CloseBrowserWhenError(boolean close) {
        TellMeWhy.setCloseWhenError(close);
    }

}
