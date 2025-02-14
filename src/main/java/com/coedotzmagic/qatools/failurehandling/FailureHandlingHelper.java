package com.coedotzmagic.qatools.failurehandling;

/*
 * write by Coedotz
 * 22-12-2024
 */

public class FailureHandlingHelper {

    /**
     * <b>CloseBrowserWhenError()</b>
     * Digunakan untuk memberikan perilaku jika error maka browser akan keluar
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
